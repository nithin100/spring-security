package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class TokenEnhancer {
	private static final String SIGNING_KEY = "s1f41234pwqdqkl4l12ghg9853123sd";

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(SIGNING_KEY);
		return converter;
	}
}
