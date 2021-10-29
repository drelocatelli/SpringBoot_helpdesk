package com.spring.Helpdesk.services;

import java.util.List;

import org.springframework.ui.Model;

import com.spring.Helpdesk.models.Ticket;

public interface TicketService {

	public List<Ticket> findAll();
	public List<Ticket> findByUserId(long user_id);
	public Ticket findById(long id);
	public Model findAllTechnician(Model model);
	public Ticket create(Ticket ticket);
	public boolean delete(long id);
	public boolean update(long id, Ticket ticket);
	public Ticket show(long id);
	public long numTickets(); 
	public long numTicketsByUser(long user_id);
	
}
