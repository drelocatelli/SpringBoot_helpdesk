package com.spring.Helpdesk.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.Helpdesk.models.Ticket;

@Service
public class TicketServiceImpl implements TicketService {

	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket create(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
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

}
