/**
 * 
 */
package net.wyun.rest.wlsp.client;

import java.util.Collection;
import java.util.Date;





import net.wyun.rest.wlsp.repository.ActVst;
import net.wyun.rest.wlsp.repository.AuthSms;
import net.wyun.rest.wlsp.repository.ProdOrder;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author Xuecheng
 *
 */
public interface ProdOrderSvcApi {
	
	
	public static final String RECTIME_PARAMETER = "rectime";

	// The path where we expect the VideoSvc to live
	public static final String PRODORDER_SVC_PATH = "/prodorder";

	// The path to search videos by title
	public static final String PRODORDER_RECTIME_SEARCH_PATH = PRODORDER_SVC_PATH + "/search/findByRectimeLessThan";

	@GET(PRODORDER_SVC_PATH)
	public Collection<ProdOrder> getProdOrderList();
	
	@Headers("Content-Type: application/json; charset=UTF-8")
	@POST(PRODORDER_SVC_PATH)
	public boolean addProdOrder(@Body ProdOrder v);
	
	
	@GET(PRODORDER_RECTIME_SEARCH_PATH)
	public Collection<ProdOrder> findByRectimeLessThan(@Query(RECTIME_PARAMETER) Date rectime);

}
