package com.spring.Helpdesk.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.Helpdesk.models.Role;
import com.spring.Helpdesk.models.User;
import com.spring.Helpdesk.services.RoleService;
import com.spring.Helpdesk.services.UserService;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	public UserController(UserService userService, RoleService roleService) {
		this.roleService = roleService;
		this.userService = userService;
	}
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.userService.findAll());
		return "users/index";
	}

	@GetMapping("/new")
	public String create(Authentication auth, Model model) {
		model.addAttribute("user", new User());
		return "users/create";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		User user = this.userService.show(id);
		
		List<Role> roles = this.roleService.findAll();
		
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		
		return "users/edit";
	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute("user") User user, BindingResult bindinResult, Model model) {
		if(bindinResult.hasErrors()) {
			return "users/create";
		}
		
		this.userService.create(user);
		return "redirect:/users";
	}
	
	@PutMapping("{id}")
	public String update(@PathVariable("id") long id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult , Model model) {
		
		if(bindingResult.hasErrors()) {
			List<Role> roles = this.roleService.findAll();

	        model.addAttribute("roles", roles);
			return "users/edit";
		}
		
		this.userService.update(id, user);
		return "redirect:/users";
		
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		this.userService.delete(id);
		
		return "redirect:/users";
	}
	
}
