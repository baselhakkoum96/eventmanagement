package com.example.eventmanagment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
//Adds view controllers that map URLs to view templates, simplifying the configuration of simple, static views.
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/homepage").setViewName("homepage");
		registry.addViewController("/").setViewName("homepage");
		registry.addViewController("/signup").setViewName("signup");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/listofusers").setViewName("listofusers");
		registry.addViewController("/edituser").setViewName("edituser");
		registry.addViewController("/addevent").setViewName("addevent");
		registry.addViewController("/editevent").setViewName("editevent");
	}
	
	//This method creates a new BCryptPasswordEncoder bean, which can be used to encode and decode passwords.
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}