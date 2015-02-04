/**
 * 
 */
package net.wyun.wlsp;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author Xuecheng
 *
 */
//@Configuration //has to disable the loggerfilter because it interferes Spring security
//@ComponentScan
public class DataRestRepoConfig extends RepositoryRestMvcConfiguration {

	
	 @Bean
	    public FilterRegistrationBean filterRegistrationBean () {
	        
	    	LoggerFilter filter = new LoggerFilter();
	        
	        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	        
	        registrationBean.setFilter(filter);
	        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	        
	        return registrationBean;
	    }

}
