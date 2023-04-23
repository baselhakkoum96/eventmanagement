package com.example.eventmanagment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.eventmanagment.services.UserDetailService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
    public WebSecurityConfiguration(UserDetailService userDetailService) {
		super();
		this.userDetailService = userDetailService;
	}
    
    /*
     * This method creates a new SecurityFilterChain that defines the security configuration for the application.
     * The method also defines a custom login page and logout behavior.
     */
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/homepage").permitAll()
				.requestMatchers("/signup","/CSS/**").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.defaultSuccessUrl("/homepage")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}
    /*
     * This method configures the AuthenticationManagerBuilder with a UserDetailsService and a BCryptPasswordEncoder,
     * which allows Spring Security to authenticate users against a database of user details.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
    } 
 
}
