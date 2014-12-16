/**
 * 
 */
package net.wyun.rest.wlsp.client;

import java.util.Collection;
import java.util.Date;

import net.wyun.rest.wlsp.repository.UserActive;
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
public interface UserActiveSvcApi {
	
	
	public static final String RECTIME_PARAMETER = "rectime";

	// The path where we expect the VideoSvc to live
	public static final String USERACTIVE_SVC_PATH = "/useractive";

	// The path to search videos by title
	public static final String USERACTIVE_RECTIME_SEARCH_PATH = USERACTIVE_SVC_PATH + "/search/findByRectimeLessThan";

	@GET(USERACTIVE_SVC_PATH)
	public Collection<UserActive> getUserActiveList();
	
	@Headers("Content-Type: application/json; charset=UTF-8")
	@POST(USERACTIVE_SVC_PATH)
	public Void addUserActive(@Body UserActive v);
	
	
	@GET(USERACTIVE_RECTIME_SEARCH_PATH)
	public Collection<UserActive> findByRectimeLessThan(@Query(RECTIME_PARAMETER) Date rectime);

}
