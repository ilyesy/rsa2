package com.talan.rsa.restController;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/session")
	public String getSessionStatus(HttpSession session) {
			System.out.println("session call");
		return "";
	}
}
