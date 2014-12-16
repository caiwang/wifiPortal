/**
 * 
 */
package net.wyun.rest.wlsp.client;

import java.util.Collection;
import java.util.Date;





import net.wyun.rest.wlsp.repository.ActVst;
import net.wyun.rest.wlsp.repository.AuthSms;
import net.wyun.rest.wlsp.repository.UserAccounts;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author Xuecheng
 *
 */
public interface UserAccountsSvcApi {
	
	
	public static final String RECTIME_PARAMETER = "rectime";

	// The path where we expect the VideoSvc to live
	public static final String USERACCOUNTS_SVC_PATH = "/useraccounts";

	// The path to search videos by title
	public static final String USERACCOUNTS_RECTIME_SEARCH_PATH = USERACCOUNTS_SVC_PATH + "/search/findByRectimeLessThan";

	@GET(USERACCOUNTS_SVC_PATH)
	public Collection<UserAccounts> getUserAccountsList();
	
	@Headers("Content-Type: application/json; charset=UTF-8")
	@POST(USERACCOUNTS_SVC_PATH)
	public Void addActVst(@Body UserAccounts v);
	
	
	@GET(USERACCOUNTS_RECTIME_SEARCH_PATH)
	public Collection<UserAccounts> findByRectimeLessThan(@Query(RECTIME_PARAMETER) Date rectime);

}
