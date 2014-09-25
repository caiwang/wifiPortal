package net.wyun.rest.wlsp;


import net.wyun.rest.wlsp.json.ResourcesMapper;
import net.wyun.rest.wlsp.repository.VideoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ch.qos.logback.access.tomcat.LogbackValve;

import com.fasterxml.jackson.databind.ObjectMapper;

//Tell Spring to automatically inject any dependencies that are marked in
//our classes with @Autowired
@EnableAutoConfiguration
// Tell Spring to automatically create a JPA implementation of our
// VideoRepository
@EnableJpaRepositories(basePackageClasses = VideoRepository.class)
// Tell Spring to turn on WebMVC (e.g., it should enable the DispatcherServlet
// so that requests can be routed to our Controllers)
@EnableWebMvc
// Tell Spring that this object represents a Configuration for the
// application
@Configuration
// Tell Spring to go and scan our controller package (and all sub packages) to
// find any Controllers or other components that are part of our applciation.
// Any class in this package that is annotated with @Controller is going to be
// automatically discovered and connected to the DispatcherServlet.
@Import(DataRestRepoConfig.class)
@ComponentScan
public class Application extends RepositoryRestMvcConfiguration {

	// Tell Spring to launch our app!
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	// We are overriding the bean that RepositoryRestMvcConfiguration 
	// is using to convert our objects into JSON so that we can control
	// the format. The Spring dependency injection will inject our instance
	// of ObjectMapper in all of the spring data rest classes that rely
	// on the ObjectMapper. This is an example of how Spring dependency
	// injection allows us to easily configure dependencies in code that
	// we don't have easy control over otherwise.
	//
	// Normally, we would not override this object mapping. However, in this
	// case, we are overriding the JSON conversion so that we can easily
	// extract a list of videos, etc. using Retrofit. You can remove this
	// method from the class to see what the default HATEOAS-based responses
	// from Spring Data Rest look like. You will need to access the server
	// from your browser as removing this method will break the Retrofit 
	// client.
	//
	// See the ResourcesMapper class for more details.
	/*
	@Override
	public ObjectMapper halObjectMapper(){
		ObjectMapper om = new ResourcesMapper();
		return om;
	}
	*/
	
	
	    @Bean
	    public EmbeddedServletContainerCustomizer containerCustomizer(){
	        return new EmbeddedServletContainerCustomizer() {
	            public void customize(ConfigurableEmbeddedServletContainer factory) {

	                if(factory instanceof TomcatEmbeddedServletContainerFactory){
	                    TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) factory;

	                    LogbackValve  logbackValve = new LogbackValve();
	                    //TODO: not sure how to handle the logback-access.xml
	                    //its location maybe needs to be set in a property file
	                    logbackValve.setFilename("logback-access.xml");
	                    containerFactory.addContextValves(logbackValve);


	                }

	            }

				
	        };
	    }
	
}
