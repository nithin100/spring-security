package com.example.config;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebMvcConfiguration {
	
	@PostConstruct
	public void some() {
		System.out.println("Controller bean created");
	}

//	@RequestMapping(value = "/ui**")
//	public String redirect() {
//		// Forward to home page so that route is preserved.
//		System.out.println("Hey there!");
//		return "forward:/";
//	}

}
