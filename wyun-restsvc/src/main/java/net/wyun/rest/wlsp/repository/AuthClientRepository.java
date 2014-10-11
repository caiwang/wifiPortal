/**
 * 
 */
package net.wyun.rest.wlsp.repository;

import net.wyun.rest.wlsp.client.AuthClientSvcApi;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Xuecheng
 *
 */
@RepositoryRestResource(path = AuthClientSvcApi.AUTHCLIENT_SVC_PATH)
public interface AuthClientRepository extends CrudRepository<AuthClient, Long>{
	
	// Find all videos that are shorter than a specified duration
		public Collection<AuthClient> findByRectimeLessThan(
				// The @Param annotation tells tells Spring Data Rest which HTTP request
				// parameter it should use to fill in the "duration" variable used to
				// search for Videos
				@Param(AuthClientSvcApi.RECTIME_PARAMETER) Date rectime);

}
