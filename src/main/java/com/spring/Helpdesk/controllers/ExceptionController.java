package com.spring.Helpdesk.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController implements ErrorController {

	@GetMapping("/error")
	public String handleError(Model model) {
		
		Map<String, String> error = new HashMap<>();
		error.put("code", "404");
		error.put("message", "Not found");
		error.put("instructions", "Oops! We can't found anything.");		
		model.addAttribute("error", error);
		
		return "error";
		
	}
	
	@GetMapping("/denied")
	public String denied(Model model) {
		return "403";
	}
	
}
