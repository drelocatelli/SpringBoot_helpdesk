package com.spring.Helpdesk.services;

import java.util.List;

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
	private TicketService ticketService;
	
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
	public Ticket create(Ticket ticket) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User userLogged = this.userRepository.findByEmail(userName);
		
		ticket.setUserOpen(userLogged);
		
		return this.ticketRepository.save(ticket);
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(long id, Ticket ticket) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ticket show(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model createTemplate(Model model) {
		model.addAttribute("ticket", new Ticket());
		
		Role adminRole = this.roleService.findByName("ADMIN");

		//		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(ROLE_ID));
		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String userName = auth.getName();
		
//		User userLogged =  this.userRepository.findByEmail(userName);
		List<User> techs = this.userService.findAllWhereRoleEquals(adminRole.getId());
		model.addAttribute("techs", techs);
		
		return model;
	}

}
