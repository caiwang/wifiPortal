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

public class CaiPiaoHandler {
	
	public final static String CP_USER = "13701272752@163.com";
	public final static String CP_PW = "123ZAQqaz";
	
	private final static String CP_Url = "http://caipiao.163.com/";
	private final static String loginUrl = "https://reg.163.com/logins.jsp";
	private final static String summaryUrl = "http://caipiao.163.com/my/order.html";
	
	/**
	 * 
	 */
	public CaiPiaoHandler() throws Exception {
		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());
	}

	private List<String> cookies;
	private HttpsURLConnection conn;
	private final String USER_AGENT = "Mozilla/5.0";
	
	public void init() throws Exception{
		// 1. login
		login();
	}

	private void login() throws Exception {
		//1. Send a "GET" request, so that you can extract the form's data.
		String page = getPageContent(CP_Url);
		FileHelper.save(page, "caipiao.html");
		
		String postParams = fillLoginForm(page, CP_USER, CP_PW);

		// 2. Construct above post's content and then send a POST request for
		// authentication
		sendPost(loginUrl, postParams);
	}
	
	public List<CaiPiao> getActiveCaiPiaos() throws Exception{
		
		List<CaiPiao> actives = new ArrayList<CaiPiao>();
		// 2. go to order summary url.
		String result = getPageContent(summaryUrl);
		FileHelper.save(result, "order.html");

		String orderUrl = "http://caipiao.163.com/hit/n_2014122614CP50275996.html";
		result = getPageContent(orderUrl);
		FileHelper.save(result, "order_n.html");

		// 3. caipiao detail
		String cpXQ = "http://caipiao.163.com/hit/nd_shuzi_cpxq.html?pageSize=10&pageNum=1&lottOrderId=2014123005CP24979085";
		result = getPageContent(cpXQ);
		FileHelper.save(result, "order_detail.html");

		Document doc = Jsoup.parse(result);
		Elements trs = doc.select("tr");

		System.out.println("rows: " + trs.size());
		for (Element e : trs) {
			System.out.println(e);
		}
		Element e1 = trs.get(0);
		Element e2 = trs.get(1);

		String txt = e2.select("span").text().replace("红球：", "")
				.replace("蓝球：", "").replace("单式", "");

		System.out.println("caipiao #: " + txt);
		
		return actives;
		
	}

	public static void main(String[] args) throws Exception {

		CaiPiaoHandler handler = new CaiPiaoHandler();
        handler.init();
        handler.getActiveCaiPiaos();
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
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response Code : " + responseCode);

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
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

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

		System.out.println("Extracting form's data...");

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