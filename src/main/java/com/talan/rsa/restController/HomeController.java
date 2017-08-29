package com.talan.rsa.restController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class HomeController {

//	@RequestMapping("/session")
//	public String getSessionStatus(HttpSession session) {
//			System.out.println("session call");
//		return "";
//	}
	
	@RequestMapping(value = "/principal", produces="application/json")
	public List<String> getPrincipal(Authentication p){
		return p.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/session")
	public String getSessionState(HttpSession session){
		//ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		System.out.println(RequestContextHolder.currentRequestAttributes().getSessionId());
	    return  RequestContextHolder.currentRequestAttributes().getSessionId();
	}
}
