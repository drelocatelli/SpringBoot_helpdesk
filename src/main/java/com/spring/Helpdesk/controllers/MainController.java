package com.spring.Helpdesk.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	@GetMapping()
	public String main() {
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home(Authentication auth, Model model) {
		model.addAttribute("loggedIn", auth.getName());
		return "index";
	}
	
}
