package com.spring.Helpdesk.repositories;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.Helpdesk.models.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Serializable> {

}
