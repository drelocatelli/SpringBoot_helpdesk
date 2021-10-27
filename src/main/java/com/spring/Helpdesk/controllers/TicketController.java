package com.spring.Helpdesk.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.Helpdesk.models.Ticket;
import com.spring.Helpdesk.services.TicketService;
import com.spring.Helpdesk.services.UserService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String main(Model model) {
		model.addAttribute("list", this.ticketService.findAll());
		model.addAttribute("userLoggedIn", this.userService.findCurrentUser());
		return "ticket/index";
	}
	
	@GetMapping("{id}")
	public String show(@PathVariable("id") long id, Model model) {
		model.addAttribute("ticket", this.ticketService.show(id));
		return "ticket/show";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model = this.ticketService.createTemplate(model);
		return "ticket/create";		
	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "ticket/create";
		}
		
		this.ticketService.create(ticket);
		return "redirect:/tickets";
	}
	
}
