/**
 * 
 */
package net.wyun.lottery;

/**
 * @author Xuecheng
 *
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import net.wyun.util.FileHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

public class CaiPiaoHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(CaiPiaoHandler.class);
	public final static String CP_USER = "13701272752@163.com";
	public final static String CP_PW = "123ZAQqaz";
	
	private final static String CP_Url = "http://caipiao.163.com/";
	private final static String loginUrl = "https://reg.163.com/logins.jsp";
	private final static String summaryUrl = "http://caipiao.163.com/my/order.html?ifWaitingAward=1&ifWin=0";
	
	/**
	 * 
	 */
	public CaiPiaoHandler() throws Exception {
		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());
		init();
	}

	private List<String> cookies;
	private HttpsURLConnection conn;
	private final String USER_AGENT = "Mozilla/5.0";
	
	private void init() throws Exception{
		// 1. login
		logger.info("initialization, login...");
		login();
	}

	private void login() throws Exception {
		//1. Send a "GET" request, so that you can extract the form's data.
		String page = getPageContent(CP_Url);
		//FileHelper.save(page, "caipiao.html");
		
		String postParams = fillLoginForm(page, CP_USER, CP_PW);

		// 2. Construct above post's content and then send a POST request for
		// authentication
		sendPost(loginUrl, postParams);
	}
	
	public List<CaiPiao> getActiveCaiPiaos() throws Exception
	{
		// 2. go to order summary url.
		return getActiveCaiPiaos(summaryUrl);
	}
	
	public List<CaiPiao> getActiveCaiPiaos(String url) throws Exception {

		String result = getPageContent(url);
		//FileHelper.save(result, "order.html");
		// <table class="tableData">
		Document os = Jsoup.parse(result);
		return this.getActiveCaiPiaos(os);
	}
	
	/**
	 * Generate a List of CaiPiao with no details
	 * @return
	 * @throws Exception
	 */
	public List<CaiPiao> getActiveCaiPiaos(Document doc) throws Exception{
		
		List<CaiPiao> actives = new ArrayList<CaiPiao>();
		
		Elements es = doc.select("table.tableData tr"); //here got all active caipiaos
		int e_num = es.size();
		if(e_num > 1){ //the first tr is for the headers
			
			for (int i=1; i < e_num; i++){
				Element e = es.get(i);
				Elements tds = e.select("td");
				/*
				for(Element td:tds){
					System.out.println(td.text());
				}
				*/
				
				CaiPiao cp = new CaiPiao();
				cp.setOrderTime(tds.get(0).text());
				cp.setPeriods(tds.get(2).text().replace("普通投注", ""));
				cp.setPublishTime(tds.get(5).text().replace("等待开奖", ""));
				Elements hrefs = tds.get(6).select("a");
				for(Element h:hrefs){
					//System.out.println(h.attr("href")); ///order/order_continueOrder.html?lotteryOrderId=2015010711CP93012077
				}
				
				String detailLink = hrefs.get(1).attr("href");
				int ind = detailLink.indexOf('=');
				cp.setOrderId(detailLink.substring(ind+1));
				System.out.println(cp.getOrderId());
				
				actives.add(cp);

				
			}
			
		}
		
		getDetailsCaiPiaos(actives);
		
		return actives;
		
	}

	
	/**
	 * pass in a list of CaiPiaos, then fill in details from the detail page
	 * pre-condition, the CaiPiao has a valid order id
	 * @param cps
	 */
	private void getDetailsCaiPiaos(List<CaiPiao> cps) throws Exception{
		
		for(CaiPiao cp:cps){
			fillDetailsCaiPiao(cp);
		}
		
		
	}
	
	private final static String cpXQ = "http://caipiao.163.com/hit/nd_shuzi_cpxq.html?pageSize=10&pageNum=1&lottOrderId=";
	private void fillDetailsCaiPiao(CaiPiao cp) throws Exception{
		
		String detailLink = cpXQ + cp.getOrderId();
		String result = getPageContent(detailLink);
		//FileHelper.save(result, "order_detail.html");

		Document doc = Jsoup.parse(result);
		Elements trs = doc.select("tr");

		System.out.println("rows: " + trs.size());
		for (Element e : trs) {
			//System.out.println(e);
		}
		Element e1 = trs.get(0);
		Elements spans = e1.select("span");
		String bzm = spans.get(1).text().replace("彩票标识码：", "");
		cp.setBiaoZhiMa(bzm);
		
		Element e2 = trs.get(1);

		//String txt = e2.select("span").text().replace("红球：", "").replace("蓝球：", "").replace("单式", "").replaceAll("[^\\d.]", "");
		String txt = e2.select("span").text().replaceAll("[^\\d.]", "");
		
		cp.setNumber(txt);

		logger.info("caipiao #: " + txt);
	}

	/**
	 *  used in login to the cp.163.com
	 */
	private void sendPost(String url, String postParams) throws Exception {

		URL obj = new URL(url);
		conn = (HttpsURLConnection) obj.openConnection();

		// Acts like a browser
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Host", "reg.163.com");
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6");
		for (String cookie : this.cookies) {
			conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
		}
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Referer", "http://caipiao.163.com/");
		conn.setRequestProperty("Origin", "http://caipiao.163.com");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		conn.setRequestProperty("Content-Length",
				Integer.toString(postParams.length()));

		conn.setDoOutput(true);
		conn.setDoInput(true);

		// Send post request
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(postParams);
		wr.flush();
		wr.close();

		int responseCode = conn.getResponseCode();
		logger.info("Sending 'POST' request to URL : " + url);
		logger.info("Post parameters : " + postParams);
		logger.info("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	}

	private String getPageContent(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

		// default is GET
		((HttpURLConnection) conn).setRequestMethod("GET");

		conn.setUseCaches(false);

		// act like a browser
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		// conn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");

		if (cookies != null) {
			for (String cookie : this.cookies) {
				System.out.print(cookie + '\n');
				conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}
		int responseCode = conn.getResponseCode();
		logger.info("Sending 'GET' request to URL : " + url);
		logger.info("Response Code : " + responseCode);

		/*
		 * Well I'm thinking the problem is when you are reading from the
		 * stream. You should either call the readUTF method on the
		 * DataInputStream instead of calling readLine or, what I would do,
		 * would be to create an InputStreamReader and set the encoding, then
		 * you can read from the BufferedReader line by line (this would be
		 * inside your existing try/catch):
		 */
		Charset charset = Charset.forName("UTF8");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Get the response cookies
		setCookies(conn.getHeaderFields().get("Set-Cookie"));

		return response.toString();

	}

	public String fillLoginForm(String html, String username, String password)
			throws UnsupportedEncodingException {

		logger.info("Extracting form's data...");

		List<String> paramList = new ArrayList<String>();
		paramList.add("username" + "=" + URLEncoder.encode(username, "UTF-8"));
		paramList.add("password" + "=" + URLEncoder.encode(password, "UTF-8"));

		String value = "http://caipiao.163.com/agent/loginAgent.htm";
		paramList.add("url" + "=" + URLEncoder.encode(value, "UTF-8"));
		paramList.add("url2" + "=" + URLEncoder.encode(value, "UTF-8"));

		value = "caipiao";
		paramList.add("product" + "=" + URLEncoder.encode(value, "UTF-8"));

		value = "1";
		paramList.add("type" + "=" + URLEncoder.encode(value, "UTF-8"));
		paramList.add("noRedirect" + "=" + URLEncoder.encode(value, "UTF-8"));

		// build parameters list
		StringBuilder result = new StringBuilder();
		for (String param : paramList) {
			if (result.length() == 0) {
				result.append(param);
			} else {
				result.append("&" + param);
			}
		}
		return result.toString();
	}

	public List<String> getCookies() {
		return cookies;
	}

	public void setCookies(List<String> cookies) {
		this.cookies = cookies;
	}

}