/**
 * 
 */
package net.wyun.rest.wlsp.client.impl;

import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.JacksonConverter;
import net.wyun.rest.wlsp.client.AuthSmsSvcApi;
import net.wyun.rest.wlsp.json.ResourcesMapper;
import net.wyun.rest.wlsp.repository.AuthSms;

/**
 * @author Xuecheng
 * A client to access the AuthSmsSvc.
 * It uses retrofit library to send REST request
 * to a sms svc.
 *
 */
@Component
public class AuthSmsClient implements AuthSmsSvcApi{
	private static final Logger logger = LoggerFactory.getLogger(AuthSmsClient.class);
	
	@Value("${wlsp.sms.server}") private String smsSvcUrl; // = "http://localhost:8080";
	
	private JacksonConverter converter = new JacksonConverter(new ResourcesMapper());

	private AuthSmsSvcApi authSmsService;
	

	@Override
	public Collection<AuthSms> getAuthSmsList() {
		throw new RuntimeException("to be implemented!");
	}

	@Override
	//@Async
	public Void addAuthSms(AuthSms v) {
		try{
			authSmsService.addAuthSms(v);
		}catch(RetrofitError e){
			//the returned http response with empty body, the jackson converter doesn't handle it correctly
			//for current Retrofit version, we igonore the CONVERSION error
			if(RetrofitError.Kind.CONVERSION == e.getKind()){
				System.out.print("Conversion error due to empty http body, ignore!");
			}else{
				logger.error("post AuthSms error: " + v.getMac() + ", " + v.getSrcid());
				throw new RuntimeException(e);
			}
			
		}
		
		return null;
	}

	@Override
	public Collection<AuthSms> findByRectimeLessThan(Date rectime) {
		throw new RuntimeException("to be implemented!");
	}
	
	@PostConstruct
    public void init() {
		logger.info("create sms client to send sms to " + this.smsSvcUrl);
		authSmsService = new RestAdapter.Builder().setConverter(converter)
				.setEndpoint(smsSvcUrl).setLogLevel(LogLevel.FULL).build()
				.create(AuthSmsSvcApi.class);
	}
	
	

}
