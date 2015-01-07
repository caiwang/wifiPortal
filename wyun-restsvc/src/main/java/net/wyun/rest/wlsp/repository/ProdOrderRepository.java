/**
 * 
 */
package net.wyun.rest.wlsp.repository;

import net.wyun.rest.wlsp.client.ProdOrderSvcApi;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Xuecheng
 *
 */
@Repository
public interface ProdOrderRepository extends CrudRepository<ProdOrder, Long>{
	
	// Find all videos that are shorter than a specified duration
		public Collection<ProdOrder> findByRectimeGreaterThan(
				// The @Param annotation tells tells Spring Data Rest which HTTP request
				// parameter it should use to fill in the "duration" variable used to
				// search for Videos
				@Param(ProdOrderSvcApi.RECTIME_PARAMETER) Date rectime);
		
		public Collection<ProdOrder> findByDelicodeIsNull(final Sort sort);

}
