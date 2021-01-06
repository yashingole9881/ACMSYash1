package com.aadhar.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {
	
	@GetMapping("/dashboard")
	public String getOwnerHomePage() {
		return "owner/dashboard";		
	}
	

}
