/**
 * 
 */
package net.wyun.rest.wlsp.repository;

import net.wyun.rest.wlsp.client.ActVstSvcApi;
import net.wyun.rest.wlsp.client.AuthSmsSvcApi;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Xuecheng
 *
 */
@RepositoryRestResource(path = ActVstSvcApi.ACTVST_SVC_PATH)
public interface ActVstRepository extends CrudRepository<ActVst, Long>{
	
	// Find all videos that are shorter than a specified duration
		public Collection<ActVst> findByRectimeLessThan(
				// The @Param annotation tells tells Spring Data Rest which HTTP request
				// parameter it should use to fill in the "duration" variable used to
				// search for Videos
				@Param(ActVstSvcApi.RECTIME_PARAMETER) Date rectime);

}
