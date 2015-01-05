/**
 * 
 */
package net.wyun.rest.wlsp.client.impl;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.JacksonConverter;
import net.wyun.rest.wlsp.LoggerInterceptor;
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
public class AuthSmsClient implements AuthSmsSvcApi{
	private static final Logger logger = LoggerFactory.getLogger(AuthSmsClient.class);
	
	private static String smsSvcUrl = "http://localhost:8080";
	
	JacksonConverter converter = new JacksonConverter(new ResourcesMapper());

	private AuthSmsSvcApi authSmsService = new RestAdapter.Builder().setConverter(converter)
			.setEndpoint(smsSvcUrl).setLogLevel(LogLevel.FULL).build()
			.create(AuthSmsSvcApi.class);

	@Override
	public Collection<AuthSms> getAuthSmsList() {
		throw new RuntimeException("to be implemented!");
	}

	@Override
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
	
	

}
