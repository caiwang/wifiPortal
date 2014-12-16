/**
 * 
 */
package net.wyun.rest.wlsp.client;

import java.util.Collection;
import java.util.Date;

import net.wyun.rest.wlsp.repository.UserMacs;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author Xuecheng
 *
 */
public interface UserMacsSvcApi {
	
	
	public static final String RECTIME_PARAMETER = "rectime";

	// The path where we expect the VideoSvc to live
	public static final String USERMACS_SVC_PATH = "/usermacs";

	// The path to search videos by title
	public static final String USERMACS_RECTIME_SEARCH_PATH = USERMACS_SVC_PATH + "/search/findByRectimeLessThan";

	@GET(USERMACS_SVC_PATH)
	public Collection<UserMacs> getUserMacsList();
	
	@Headers("Content-Type: application/json; charset=UTF-8")
	@POST(USERMACS_SVC_PATH)
	public Void addUserMacs(@Body UserMacs v);
	
	
	@GET(USERMACS_RECTIME_SEARCH_PATH)
	public Collection<UserMacs> findByRectimeLessThan(@Query(RECTIME_PARAMETER) Date rectime);

}
