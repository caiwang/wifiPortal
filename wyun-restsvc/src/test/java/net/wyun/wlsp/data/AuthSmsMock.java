package net.wyun.wlsp.data;

import java.util.Date;

import net.wyun.rest.wlsp.json.ResourcesMapper;
import net.wyun.rest.wlsp.repository.AuthSms;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthSmsMock {
	
	private static final ObjectMapper objectMapper = new ResourcesMapper();
	
	/**
	 * Construct and return a Video object with a
	 * rnadom name, url, and duration.
	 * 
	 * @return
	 */
	public static AuthSms generateAuthSms() {
		AuthSms as = new AuthSms();
		
		as.setPrefix("认证码Cert Code:");
		as.setSms("123456");
		as.setPhone("18833500052");
		as.setSender("node001");
		as.setNetid("ssid1");
		as.setProgid("pushsms.py");
		as.setOptime(new Date());
		as.setSendtime(new Date());
		
		return as;
	}
	
	/**
	 *  Convert an object to JSON using Jackson's ObjectMapper
	 *  
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String toJson(Object o) throws Exception{
		return objectMapper.writeValueAsString(o);
	}

}
