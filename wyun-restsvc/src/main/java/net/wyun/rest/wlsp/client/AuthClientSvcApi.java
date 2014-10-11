/**
 * 
 */
package net.wyun.rest.wlsp.client;

import java.util.Collection;
import java.util.Date;



import net.wyun.rest.wlsp.repository.AuthClient;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author Xuecheng
 *
 */
public interface AuthClientSvcApi {
	
	
	public static final String RECTIME_PARAMETER = "rectime";

	// The path where we expect the VideoSvc to live
	public static final String AUTHCLIENT_SVC_PATH = "/authclient";

	// The path to search videos by title
	public static final String AUTHCLIENT_RECTIME_SEARCH_PATH = AUTHCLIENT_SVC_PATH + "/search/findByRectimeLessThan";

	@GET(AUTHCLIENT_SVC_PATH)
	public Collection<AuthClient> getAuthSmsList();
	
	@Headers("Content-Type: application/json; charset=UTF-8")
	@POST(AUTHCLIENT_SVC_PATH)
	public Void addAuthSms(@Body AuthClient v);
	
	
	@GET(AUTHCLIENT_RECTIME_SEARCH_PATH)
	public Collection<AuthClient> findByRectimeLessThan(@Query(RECTIME_PARAMETER) Date rectime);

}
