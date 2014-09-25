/**
 * 
 */
package net.wyun.rest.wlsp.client;

import java.util.Collection;
import java.util.Date;



import net.wyun.rest.wlsp.repository.AuthSms;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author Xuecheng
 *
 */
public interface AuthSmsSvcApi {
	
	
	public static final String RECTIME_PARAMETER = "rectime";

	// The path where we expect the VideoSvc to live
	public static final String AUTHSMS_SVC_PATH = "/authsms";

	// The path to search videos by title
	public static final String AUTHSMS_RECTIME_SEARCH_PATH = AUTHSMS_SVC_PATH + "/search/findByRectimeLessThan";

	@GET(AUTHSMS_SVC_PATH)
	public Collection<AuthSms> getAuthSmsList();
	
	@Headers("Content-Type: application/json; charset=UTF-8")
	@POST(AUTHSMS_SVC_PATH)
	public Void addAuthSms(@Body AuthSms v);
	
	
	@GET(AUTHSMS_RECTIME_SEARCH_PATH)
	public Collection<AuthSms> findByRectimeLessThan(@Query(RECTIME_PARAMETER) Date rectime);

}
