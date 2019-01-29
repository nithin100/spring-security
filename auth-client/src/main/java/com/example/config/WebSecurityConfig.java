package com.example.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableZuulProxy
@Configuration
@EnableOAuth2Sso
@Order(-100)
public class WebSecurityConfig
    extends
        WebSecurityConfigurerAdapter {
	
	

    @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**/*.js","/**/*.js.**","/**/favicon.ico");
		super.configure(web);
	}

	@Override
    protected void configure(HttpSecurity http)
        throws Exception {
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/","/app/**","/webjars/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .permitAll()
            .and()
            .csrf()
            .csrfTokenRepository(
                CookieCsrfTokenRepository
                    .withHttpOnlyFalse());
    }

}
