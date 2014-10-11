/**
 * 
 */
package net.wyun.rest.wlsp.client;

import java.util.Collection;
import java.util.Date;

import net.wyun.rest.wlsp.repository.WlAct;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author Xuecheng
 *
 */
public interface WlActSvcApi {
	
	
	public static final String RECTIME_PARAMETER = "rectime";

	// The path where we expect the VideoSvc to live
	public static final String WLACT_SVC_PATH = "/wlact";

	// The path to search videos by title
	public static final String WLACT_RECTIME_SEARCH_PATH = WLACT_SVC_PATH + "/search/findByRectimeLessThan";

	@GET(WLACT_SVC_PATH)
	public Collection<WlAct> getWlActList();
	
	@Headers("Content-Type: application/json; charset=UTF-8")
	@POST(WLACT_SVC_PATH)
	public Void addWlAct(@Body WlAct v);
	
	
	@GET(WLACT_RECTIME_SEARCH_PATH)
	public Collection<WlAct> findByRectimeLessThan(@Query(RECTIME_PARAMETER) Date rectime);

}
