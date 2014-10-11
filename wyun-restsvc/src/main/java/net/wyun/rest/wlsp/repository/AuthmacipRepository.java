/**
 * 
 */
package net.wyun.rest.wlsp.repository;

import net.wyun.rest.wlsp.client.AuthmacipSvcApi;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Xuecheng
 *
 */
@RepositoryRestResource(path = AuthmacipSvcApi.AUTHMACIP_SVC_PATH)
public interface AuthmacipRepository extends CrudRepository<Authmacip, Long>{
	
	// Find all videos that are shorter than a specified duration
		public Collection<Authmacip> findByRectimeLessThan(
				// The @Param annotation tells tells Spring Data Rest which HTTP request
				// parameter it should use to fill in the "duration" variable used to
				// search for Videos
				@Param(AuthmacipSvcApi.RECTIME_PARAMETER) Date rectime);

}
