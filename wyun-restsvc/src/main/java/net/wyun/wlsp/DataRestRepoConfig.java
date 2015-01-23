/**
 * 
 */
package net.wyun.wlsp;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author Xuecheng
 *
 */
@Configuration
@ComponentScan
public class DataRestRepoConfig extends RepositoryRestMvcConfiguration {

	/*
	@Override
	public RequestMappingHandlerMapping repositoryExporterHandlerMapping() {
		RequestMappingHandlerMapping mapping = super.repositoryExporterHandlerMapping();

		mapping.setInterceptors(new Object[] { new LoggerInterceptor() });
		return mapping;
	}
	
	*/
	
	 @Bean
	    public FilterRegistrationBean filterRegistrationBean () {
	        
	    	LoggerFilter filter = new LoggerFilter();
	        
	        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	        
	        registrationBean.setFilter(filter);
	        
	        return registrationBean;
	    }

}
