package com.spring.Helpdesk.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.Helpdesk.models.User;

@Controller
public class AuthController {

	@GetMapping("/login")
	public String login(Model model) {
		return "auth/login";
	}
	
	@GetMapping("/registration")
	public String registration(Authentication auth, Model model) {
		model.addAttribute("user", new User());
		return "auth/registration";
	}
	
}
