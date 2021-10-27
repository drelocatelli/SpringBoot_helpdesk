package com.spring.Helpdesk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.Helpdesk.services.UserService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String main() {
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("userLoggedIn", this.userService.findCurrentUser());
		return "index";
	}
	
}
