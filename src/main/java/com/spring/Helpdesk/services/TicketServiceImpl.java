package com.spring.Helpdesk.services;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.Helpdesk.models.Role;
import com.spring.Helpdesk.models.Ticket;
import com.spring.Helpdesk.models.User;
import com.spring.Helpdesk.repositories.TicketRepository;
import com.spring.Helpdesk.repositories.UserRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, RoleService roleService, UserService userService) {
    	this.roleService = roleService;
    	this.userService = userService;
    	this.userRepository = userRepository;
		this.ticketRepository = ticketRepository;
	}
	
	@Override
	public List<Ticket> findAll() {
		return (List<Ticket>) this.ticketRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	@Override
	public List<Ticket> findByUserId(long user_id) {
		return this.ticketRepository.findByUserId(user_id);
	}

	@Override
	public Ticket create(Ticket ticket) {
		User userLogged = this.userService.findCurrentUser();
		
		ticket.setUserOpen(userLogged);
		
		return this.ticketRepository.save(ticket);
	}

	@Override
	public boolean delete(long id) {
		Ticket ticketExists = findById(id);
		
		if(ticketExists != null) {
			this.ticketRepository.delete(ticketExists);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean update(long id, Ticket ticket) {
		Ticket ticketExists = findById(id);
		
		if(ticketExists != null) {
			ticketExists.setId(ticket.getId());
			ticketExists.setName(ticket.getName());
			ticketExists.setDescription(ticket.getDescription());
			ticketExists.setFinished(ticket.isFinished());
			ticketExists.setTechnician(ticket.getTechnician());
			
			if(ticket.isFinished()) {
				ticketExists.setClosed(new Date());
			}else {
				ticketExists.setClosed(null);
			}
			
			this.ticketRepository.save(ticketExists);
			return true;
			
		}
		
		return false;
	}

	@Override
	public Ticket show(long id) {
		return this.ticketRepository.findById(id).orElse(null);
	}
	
	public Ticket findById(long id) {
		return this.ticketRepository.findById(id).orElse(null);
	}

	@Override
	public Model findAllTechnician(Model model) {
		
		Role adminRole = this.roleService.findByName("ADMIN");
		User userLogged =  this.userService.findCurrentUser();

		List<User> techs = this.userService.findAllWhereRoleEquals(adminRole.getId(), userLogged.getId());
		model.addAttribute("techs", techs);
		
		return model;
	}

	@Override
	public long numTickets() {
		return this.ticketRepository.numTickets();
	}

	@Override
	public long numTicketsByUser(long user_id) {
		return this.ticketRepository.numTicketsByUser(user_id);
	}

}
