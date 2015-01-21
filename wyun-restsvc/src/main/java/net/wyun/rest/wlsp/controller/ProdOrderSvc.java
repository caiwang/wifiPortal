package net.wyun.rest.wlsp.controller;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import net.wyun.lottery.ScheduledLotteryTasks;
import net.wyun.rest.wlsp.client.ProdOrderSvcApi;
import net.wyun.rest.wlsp.client.impl.AuthSmsClient;
import net.wyun.rest.wlsp.repository.AuthSms;
import net.wyun.rest.wlsp.repository.ProdOrder;
import net.wyun.rest.wlsp.repository.ProdOrderRepository;
import net.wyun.service.SmsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import retrofit.http.GET;

import com.google.common.collect.Lists;

/**
 * 
 * @author michaelyin
 *
 */

// Tell Spring that this class is a Controller that should 
// handle certain HTTP requests for the DispatcherServlet
@Controller
public class ProdOrderSvc implements ProdOrderSvcApi {
	
	private static final Logger logger = LoggerFactory.getLogger(ProdOrderSvc.class);
	
	@Autowired
	private ScheduledLotteryTasks lotteryHandler;
	//
	@Autowired
	private ProdOrderRepository orders;
	
	@Autowired
	private SmsService smsSvc;

	
	private final static String USER_PHONENUM = ", user phone#: ";
	// Receives POST requests to /prodorder and converts the HTTP
	// request body, which should contain json, into a ProdOrder
	// object before adding it to the list. The @RequestBody
	// annotation on the Video parameter is what tells Spring
	// to interpret the HTTP request body as JSON and convert
	// it into a ProdOrder object to pass into the method. The
	// @ResponseBody annotation tells Spring to convert the
	// return value from the method back into JSON and put
	// it into the body of the HTTP response to the client.
	//
	// The PRODORDER_SVC_PATH is set to "/prodorder" in the ProdOrderSvcApi
	// interface. We use this constant to ensure that the 
	// client and service paths for the ProdOrderSvc are always
	// in synch.
	//
	private final static String HOPE_DONATE = "hope";
	@RequestMapping(value=ProdOrderSvcApi.PRODORDER_SVC_PATH, method=RequestMethod.POST)
	public @ResponseBody boolean addProdOrder(@RequestBody ProdOrder v, HttpServletResponse response){
		 ProdOrder v1 = orders.save(v);
		 logger.info("process new order (order id): " + v1.getId() + USER_PHONENUM + v.getRecipphone1());
		 
		 //if is for hope donation
		 if(HOPE_DONATE.equals(v1.getProdtype())){
			 logger.info("process new order (order id) for hope donation: " + v1.getId() + USER_PHONENUM + v.getRecipphone1());
			 
			 response.setStatus(HttpServletResponse.SC_CREATED);
			 return true;
		 }
		 
		 if(!lotteryHandler.addProdOrder(v1)){
			 logger.error("cannot add prodorder to the lottery handler service: " + v1.getId());
		 }
			
		 
		 //sms to operator //sms service is a async call
		 AuthSms as1 = this.generateOperatorAuthSms(v1);
	     if(!smsSvc.addAuthSms(as1)){
	    	 logger.error("cannot add authsms to the sms service: " + v1.getId());
	     }
	
		 response.setStatus(HttpServletResponse.SC_CREATED);
		 return true;
		
	}
	
	// Receives GET requests to /video and returns the current
	// list of videos in memory. Spring automatically converts
	// the list of videos to JSON because of the @ResponseBody
	// annotation.
	//@GET(PRODORDER_SVC_PATH)
	//public Collection<ProdOrder> getProdOrderList();
	
	@RequestMapping(value=ProdOrderSvcApi.PRODORDER_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<ProdOrder> getProdOrderList(){
		return Lists.newArrayList(orders.findAll());
	}


	@Override
	public Collection<ProdOrder> findByRectimeLessThan(Date rectime) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Value("${caipiao.operator.num}") private String Operator_Phone_Num; // = "13333339022"; //"18833500052";
	private static String O_Prefix = "双色球";
	private static String O_Postfix = "请下单 Ref#";
	
	private AuthSms generateOperatorAuthSms(ProdOrder v) {
		AuthSms as = new AuthSms();
		
		as.setPrefix(O_Prefix);
		as.setSms("彩票新单, " + O_Postfix);
		as.setPostfix("" + v.getId());
		as.setMsgtype(O_Prefix);
		as.setPhone(Operator_Phone_Num);
		as.setSender(v.getSender());
		as.setNetid(v.getNetid());
		as.setProgid(v.getProgid());
		as.setOptime(new Date());
		as.setSendtime(new Date());
		
		return as;
	}
	
	


}
