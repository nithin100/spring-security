package com.example.security;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserInfoController {
	
	@GetMapping("user")
	public Principal user(Principal principal) {
		System.out.println("Incoming request for principal: ");
		return principal;
	}
	
}
