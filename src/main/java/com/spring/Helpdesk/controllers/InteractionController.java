package com.spring.Helpdesk.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.Helpdesk.models.Interaction;
import com.spring.Helpdesk.models.Ticket;
import com.spring.Helpdesk.models.User;
import com.spring.Helpdesk.services.InteractionService;
import com.spring.Helpdesk.services.TicketService;
import com.spring.Helpdesk.services.UserService;

@Controller
@RequestMapping("/tickets/{ticketId}/interactions")
public class InteractionController {
	
	@Autowired
	private InteractionService interactionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TicketService ticketService;
	
	public InteractionController(InteractionService interactionService, UserService userService, TicketService ticketService) {
		this.ticketService = ticketService;
		this.userService = userService;
		this.interactionService = interactionService;
	}
	
	@PostMapping
	public String save(@PathVariable("ticketId") long ticketId, @Valid @ModelAttribute("interaction") Interaction interaction, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "ticket/show";
		}
		
		this.interactionService.create(interaction, ticketId);
		return "redirect:/tickets/"+ticketId;
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable("ticketId") long ticketId, @PathVariable("id") long id, Model model) {
		
		Interaction interaction = this.interactionService.findById(id);
		Ticket ticket = this.ticketService.findById(ticketId);
		User userLoggedIn = this.userService.findCurrentUser();
		
		// se nao for dono do interaction
		if(!interaction.getUserInteraction().getEmail().equals(userLoggedIn.getEmail())) {
			return "redirect:/denied";
		}
		
		this.interactionService.delete(id, ticketId);
		return "redirect:/tickets/"+ticketId;
	}

}
