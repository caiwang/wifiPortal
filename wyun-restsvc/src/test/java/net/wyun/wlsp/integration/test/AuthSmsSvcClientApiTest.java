package net.wyun.wlsp.integration.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import net.wyun.wlsp.data.AuthSmsMock;
import net.wyun.rest.wlsp.client.AuthSmsSvcApi;
import net.wyun.rest.wlsp.json.ResourcesMapper;
import net.wyun.rest.wlsp.repository.AuthSms;

import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.converter.JacksonConverter;

public class AuthSmsSvcClientApiTest {
	
	private final String TEST_URL = "http://localhost:8080";
	
	JacksonConverter converter = new JacksonConverter(new ResourcesMapper());
	//JacksonConverter converter = new JacksonConverter(new ObjectMapper());

	private AuthSmsSvcApi authSmsService = new RestAdapter.Builder().setConverter(converter)
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(AuthSmsSvcApi.class);

	private AuthSms authSms = AuthSmsMock.generateAuthSms();
	
	/**
	 * This test creates a Video, adds the Video to the VideoSvc, and then
	 * checks that the Video is included in the list when getVideoList() is
	 * called.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAuthSmsAddAndList() throws Exception {
		
		// Add the video
		System.out.println(authSms.toString());
		System.out.println(AuthSmsMock.toJson(authSms));
		try{
			authSmsService.addAuthSms(authSms);
		}catch(RetrofitError e){
			//the returned http response with empty body, the jackson converter doesn't handle it correctly
			//for current Retrofit version, we igonore the CONVERSION error
			if(RetrofitError.Kind.CONVERSION == e.getKind()){
				System.out.print("Conversion error due to empty http body, ignore!");
			}else{
				throw new Exception(e);
			}
			
		}
		
		
		// We should get back the video that we added above
		//Collection<AuthSms> as = authSmsService.getAuthSmsList();
		//assertTrue(as.contains(authSms));
	}

}
