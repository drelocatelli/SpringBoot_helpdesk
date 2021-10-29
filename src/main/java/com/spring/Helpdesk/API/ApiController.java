package com.spring.Helpdesk.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Helpdesk.models.User;
import com.spring.Helpdesk.services.UserService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public User main() {
		User userLoggedIn = this.userService.findCurrentUser();
		return userLoggedIn;
	}
	
	
}
