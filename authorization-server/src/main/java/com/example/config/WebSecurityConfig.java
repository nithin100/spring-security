package com.example.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private BCryptPasswordEncoder passwordEncoder;

	public WebSecurityConfig(BCryptPasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	@Order(1)
	protected UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(new User("nithin", passwordEncoder.encode("nithin"),
				Arrays.asList(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("USER_READ"))));
		manager.createUser(new User("admin", passwordEncoder.encode("admin"),
				Arrays.asList(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("SUPER_ADMIN"))));
		
		return manager;
	}

	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService());
		super.configure(authenticationManagerBuilder);
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers()
		.antMatchers("/login", "/oauth/authorize")
		.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin().permitAll()
		.and().csrf().disable();
		super.configure(http);
	}

}
