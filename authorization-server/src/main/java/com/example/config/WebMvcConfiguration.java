package com.example.config;

import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/*@EnableWebMvc
@SessionAttributes("authorizationRequest")*/
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/login").setViewName("login");
		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

}
