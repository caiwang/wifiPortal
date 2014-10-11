/**
 * 
 */
package net.wyun.rest.wlsp.client;

import java.util.Collection;
import java.util.Date;

import net.wyun.rest.wlsp.repository.WlAct;
import net.wyun.rest.wlsp.repository.WlSta;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author Xuecheng
 *
 */
public interface WlStaSvcApi {
	
	
	public static final String RECTIME_PARAMETER = "rectime";

	// The path where we expect the VideoSvc to live
	public static final String WLSTA_SVC_PATH = "/wlsta";

	// The path to search videos by title
	public static final String WLSTA_RECTIME_SEARCH_PATH = WLSTA_SVC_PATH + "/search/findByRectimeLessThan";

	@GET(WLSTA_SVC_PATH)
	public Collection<WlSta> getWlStaList();
	
	@Headers("Content-Type: application/json; charset=UTF-8")
	@POST(WLSTA_SVC_PATH)
	public Void addWlSta(@Body WlSta v);
	
	
	@GET(WLSTA_RECTIME_SEARCH_PATH)
	public Collection<WlSta> findByRectimeLessThan(@Query(RECTIME_PARAMETER) Date rectime);

}
