/**
 * 
 */
package net.wyun.rest.wlsp.repository;

import net.wyun.rest.wlsp.client.WlActSvcApi;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Xuecheng
 *
 */
@RepositoryRestResource(path = WlActSvcApi.WLACT_SVC_PATH)
public interface WlActRepository extends CrudRepository<WlAct, Long>{
	
	// Find all videos that are shorter than a specified duration
		public Collection<WlAct> findByRectimeLessThan(
				// The @Param annotation tells tells Spring Data Rest which HTTP request
				// parameter it should use to fill in the "duration" variable used to
				// search for Videos
				@Param(WlActSvcApi.RECTIME_PARAMETER) Date rectime);

}
