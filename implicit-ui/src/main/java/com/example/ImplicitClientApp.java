package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@RestController
@RequestMapping("/")
public class ImplicitClientApp {

	public static void main(String[] args) {
		SpringApplication.run(ImplicitClientApp.class, args);
	}

	@GetMapping("hello")
	public String helloWorld() {
		return "Hello there!";
	}

}