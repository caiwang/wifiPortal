/**
 * 
 */
package net.wyun.rest.wlsp.client;

import java.util.Collection;
import java.util.Date;




import net.wyun.rest.wlsp.repository.Authmac;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author Xuecheng
 *
 */
public interface AuthmacSvcApi {
	
	
	public static final String RECTIME_PARAMETER = "rectime";

	// The path where we expect the VideoSvc to live
	public static final String AUTHMAC_SVC_PATH = "/authmac";

	// The path to search videos by title
	public static final String AUTHMAC_RECTIME_SEARCH_PATH = AUTHMAC_SVC_PATH + "/search/findByRectimeLessThan";

	@GET(AUTHMAC_SVC_PATH)
	public Collection<Authmac> getAuthmacList();
	
	@Headers("Content-Type: application/json; charset=UTF-8")
	@POST(AUTHMAC_SVC_PATH)
	public Void addAuthmac(@Body Authmac v);
	
	
	@GET(AUTHMAC_RECTIME_SEARCH_PATH)
	public Collection<Authmac> findByRectimeLessThan(@Query(RECTIME_PARAMETER) Date rectime);

}
