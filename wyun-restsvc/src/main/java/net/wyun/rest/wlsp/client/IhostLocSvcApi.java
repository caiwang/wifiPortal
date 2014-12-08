/**
 * 
 */
package net.wyun.rest.wlsp.client;

import java.util.Collection;
import java.util.Date;




import net.wyun.rest.wlsp.repository.IhostLoc;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author Xuecheng
 *
 */
public interface IhostLocSvcApi {
	
	
	public static final String RECTIME_PARAMETER = "rectime";

	// The path where we expect the VideoSvc to live
	public static final String IHOSTLOC_SVC_PATH = "/ihostloc";

	// The path to search videos by title
	public static final String IHOSTLOC_RECTIME_SEARCH_PATH = IHOSTLOC_SVC_PATH + "/search/findByRectimeLessThan";

	@GET(IHOSTLOC_SVC_PATH)
	public Collection<IhostLoc> getIhostLocList();
	
	@Headers("Content-Type: application/json; charset=UTF-8")
	@POST(IHOSTLOC_SVC_PATH)
	public Void addIhostLoc(@Body IhostLoc v);
	
	
	@GET(IHOSTLOC_RECTIME_SEARCH_PATH)
	public Collection<IhostLoc> findByRectimeLessThan(@Query(RECTIME_PARAMETER) Date rectime);

}
