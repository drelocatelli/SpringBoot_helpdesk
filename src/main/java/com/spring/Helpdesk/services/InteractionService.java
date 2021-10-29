package com.spring.Helpdesk.services;

import java.util.List;

import com.spring.Helpdesk.models.Interaction;

public interface InteractionService {

	public Interaction create(Interaction interaction, long ticketId);
	public boolean delete(long id, long ticketId);
	
}
