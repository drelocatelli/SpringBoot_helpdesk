package com.spring.Helpdesk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.Helpdesk.models.Interaction;
import com.spring.Helpdesk.models.Ticket;
import com.spring.Helpdesk.models.User;
import com.spring.Helpdesk.repositories.InteractionRepository;
import com.spring.Helpdesk.repositories.TicketRepository;
import com.spring.Helpdesk.repositories.UserRepository;

@Service
public class InteractionServiceImpl implements InteractionService {

	@Autowired
	private InteractionRepository interactionRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserService userService;
	
	public InteractionServiceImpl(InteractionRepository interactionRepository, TicketRepository ticketRepository, UserService userService) {
		this.interactionRepository = interactionRepository;
		this.ticketRepository = ticketRepository;
		this.userService = userService;
	}
	
	@Override
	public Interaction create(Interaction interaction, long ticketId) {
		Ticket ticket = this.ticketRepository.findById(ticketId).orElse(null);
		
		User userLogged = this.userService.findCurrentUser();
		
		interaction.setTicket(ticket);
		interaction.setUserInteraction(userLogged);
		
		return this.interactionRepository.save(interaction);
	}

	@Override
	public boolean delete(long id, long ticketId) {
		// TODO Auto-generated method stub
		return false;
	}

}
