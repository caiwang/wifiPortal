/**
 * 
 */
package net.wyun.rest.wlsp.client;

import java.util.Collection;
import java.util.Date;




import net.wyun.rest.wlsp.repository.ActVst;
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
public interface ActVstSvcApi {
	
	
	public static final String RECTIME_PARAMETER = "rectime";

	// The path where we expect the VideoSvc to live
	public static final String ACTVST_SVC_PATH = "/actvst";

	// The path to search videos by title
	public static final String ACTVST_RECTIME_SEARCH_PATH = ACTVST_SVC_PATH + "/search/findByRectimeLessThan";

	@GET(ACTVST_SVC_PATH)
	public Collection<ActVst> getActVstList();
	
	@Headers("Content-Type: application/json; charset=UTF-8")
	@POST(ACTVST_SVC_PATH)
	public Void addActVst(@Body ActVst v);
	
	
	@GET(ACTVST_RECTIME_SEARCH_PATH)
	public Collection<ActVst> findByRectimeLessThan(@Query(RECTIME_PARAMETER) Date rectime);

}
