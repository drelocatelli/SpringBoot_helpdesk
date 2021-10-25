package com.spring.Helpdesk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.Helpdesk.models.Role;
import com.spring.Helpdesk.models.Ticket;
import com.spring.Helpdesk.services.RoleService;
import com.spring.Helpdesk.services.TicketService;
import com.spring.Helpdesk.services.UserService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	private final long ROLE_ID = 4;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("ticket", new Ticket());
		
		Role adminRole = this.roleService.findByName("ADMIN");
		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(adminRole.getId()));
//		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(ROLE_ID));
		return "ticket/create";
	}
	
}
