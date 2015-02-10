/**
 * 
 */
package net.wyun.wlsp;

/**
 * @author Xuecheng
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String[] UNSECURED_RESOURCE_LIST = new String[] { "/test.html", "/",
        "/resources/**", "/assets/**", "/css/**", "/webjars/**", "/images/**",
        "/dandelion-assets/**", "/unauthorized", "/error*", "/users*" };
	
	private static final String[] UNSECURED_REST_LIST = new String[] {"/actvst", "/authclient",
		"/authmacip", "/authmac",
		"/authsms", "/ihostloc", "/prodorder", "/useraccounts", "/useractive",
		"/usermacs", "/wlact", "/wlsta"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/actvst", "/authclient", "/authmacip", "/authmac",
                		"/authsms", "/ihostloc", "/prodorder/**", "/useraccounts", 
                		"/useractive", "/usermacs", "/wlact", "/wlsta")
                .permitAll()
                .antMatchers("/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .defaultSuccessUrl("/d3")
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
        
       
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("set up user: " + "wlsp");
        auth
            .inMemoryAuthentication()
                .withUser("wlsp").password("r3st_w1sp?").roles("USER");
        
        auth
        .inMemoryAuthentication()
            .withUser("admin").password("r3st_w1sp?").roles("USER", "ADMIN");
         
    }
    
    
}