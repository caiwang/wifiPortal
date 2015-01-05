package net.wyun.rest.wlsp.controller;

import java.util.Collection;
import java.util.Date;

import net.wyun.rest.wlsp.client.AuthSmsSvcApi;
import net.wyun.rest.wlsp.client.ProdOrderSvcApi;
import net.wyun.rest.wlsp.client.impl.AuthSmsClient;
import net.wyun.rest.wlsp.repository.AuthSms;
import net.wyun.rest.wlsp.repository.ProdOrder;
import net.wyun.rest.wlsp.repository.ProdOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import retrofit.http.GET;

import com.google.common.collect.Lists;

/**
 * This simple VideoSvc allows clients to send HTTP POST requests with
 * videos that are stored in memory using a list. Clients can send HTTP GET
 * requests to receive a JSON listing of the videos that have been sent to
 * the controller so far. Stopping the controller will cause it to lose the history of
 * videos that have been sent to it because they are stored in memory.
 * 
 * Notice how much simpler this VideoSvc is than the original VideoServlet?
 * Spring allows us to dramatically simplify our service. Another important
 * aspect of this version is that we have defined a VideoSvcApi that provides
 * strong typing on both the client and service interface to ensure that we
 * don't send the wrong paraemters, etc.
 * 
 * @author jules
 *
 */

// Tell Spring that this class is a Controller that should 
// handle certain HTTP requests for the DispatcherServlet
@Controller
public class ProdOrderSvc implements ProdOrderSvcApi {
	
	
	//
	@Autowired
	private ProdOrderRepository orders;
	
	@Autowired
	private AuthSmsClient smsClient;

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
	@RequestMapping(value=ProdOrderSvcApi.PRODORDER_SVC_PATH, method=RequestMethod.POST)
	public @ResponseBody boolean addProdOrder(@RequestBody ProdOrder v){
		 orders.save(v);
		 
		 //sms to operator
		 AuthSms as1 = this.generateOperatorAuthSms(v);
		 smsClient.addAuthSms(as1);
		 
		 //sms to customer or user
		 AuthSms as2 = this.generateUserAuthSms(v);
		 smsClient.addAuthSms(as2);
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
	
	private static String Operator_Phone_Num = "18202297821";
	private AuthSms generateOperatorAuthSms(ProdOrder v) {
		AuthSms as = new AuthSms();
		
		as.setPrefix("彩票：" + v.getProdspec());
		as.setSms("请购买");
		as.setPhone(Operator_Phone_Num);
		as.setSender(v.getSender());
		as.setNetid(v.getNetid());
		as.setProgid(v.getProgid());
		as.setOptime(new Date());
		as.setSendtime(new Date());
		
		return as;
	}
	
	private AuthSms generateUserAuthSms(ProdOrder v) {
		AuthSms as = new AuthSms();
		
		as.setPrefix("用户 " + v.getRecipname() + ", " + v.getProdspec());
		as.setSms("彩票回执");
		as.setPhone(v.getRecipphone1());
		as.setSender(v.getSender());
		as.setNetid(v.getNetid());
		as.setProgid(v.getProgid());
		as.setOptime(new Date());
		as.setSendtime(new Date());
		
		return as;
	}


}
