package com.spring.Helpdesk.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.spring.Helpdesk.models.Interaction;
import com.spring.Helpdesk.models.Ticket;
import com.spring.Helpdesk.models.User;
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
		
		User userLoggedIn = this.userService.findCurrentUser();
		
		// se for admin
		if(this.userService.checkByRole("ADMIN")) {
			model.addAttribute("list", this.ticketService.findAll());
			model.addAttribute("count", this.ticketService.numTickets());
		}else {
			model.addAttribute("list", this.ticketService.findByUserId(userLoggedIn.getId()));
			model.addAttribute("count", this.ticketService.numTicketsByUser(userLoggedIn.getId()));
		}
		
		
		model.addAttribute("userLoggedIn", this.userService.findCurrentUser());
		
		return "ticket/index";
	}
	
	@GetMapping("{id}")
	public String show(@PathVariable("id") long id, Model model) {
		User userLoggedIn = this.userService.findCurrentUser();
		Ticket ticket = this.ticketService.show(id);
		
		List<Interaction> interactions = ticket.getInteractions();
		
		model.addAttribute("userLoggedIn", userLoggedIn);
		model.addAttribute("ticket", ticket);
		model.addAttribute("interaction", new Interaction());
		model.addAttribute("interactions", interactions);
		
		// se nao for admin e se nao for dono do ticket
		if(!this.userService.checkByRole("ADMIN") && !ticket.getUserOpen().getEmail().equals(userLoggedIn.getEmail())) {
			return "redirect:/denied";
		}
		
		return "ticket/show";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model = this.ticketService.findAllTechnician(model);
		User userLoggedIn = this.userService.findCurrentUser();
		
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("userLoggedIn", userLoggedIn);
		
		return "ticket/create";		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		
		Ticket ticket = this.ticketService.show(id);
		
		User userLoggedIn = this.userService.findCurrentUser();

		model = this.ticketService.findAllTechnician(model);
		model.addAttribute("ticket", ticket);
		model.addAttribute("userLoggedIn", userLoggedIn);

		// se nao for admin e se nao for dono do ticket
		if(!this.userService.checkByRole("ADMIN") && !ticket.getUserOpen().getEmail().equals(userLoggedIn.getEmail())) {
			return "redirect:/denied";
		}
		
		return "ticket/edit";
				
	}
	
	// modelAttribute = th:object
	@PutMapping("{id}")
	public String update(@PathVariable("id") long id, @Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "ticket/edit";
		}
		
		this.ticketService.update(id, ticket);
		return "redirect:/tickets/"+id;
		
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		
		User userLoggedIn = this.userService.findCurrentUser();
		
		Ticket ticket = this.ticketService.findById(id);
		
		// se nao for admin e se nao for dono do ticket
		if(!this.userService.checkByRole("ADMIN") && !ticket.getUserOpen().getEmail().equals(userLoggedIn.getEmail())) {
			return "redirect:/denied";
		}
		
		this.ticketService.delete(id);
		
		return "redirect:/tickets";
		
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
