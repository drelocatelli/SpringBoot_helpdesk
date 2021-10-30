package com.spring.Helpdesk.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.Helpdesk.models.User;
import com.spring.Helpdesk.services.UserService;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "auth/login";
	}
	
	@GetMapping("/registration")
	public String registration(Authentication auth, Model model) {
		model.addAttribute("user", new User());
		return "auth/registration";
	}
	
	@PostMapping("/registration")
	public String save(@Valid @ModelAttribute("user") User user, BindingResult bindinResult, Model model) {
		if(bindinResult.hasErrors()) {
			return "auth/registration";
		}
		
		this.userService.create(user);
		return "redirect:/login";
	}
	
}
