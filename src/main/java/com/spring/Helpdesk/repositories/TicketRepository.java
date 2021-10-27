package com.spring.Helpdesk.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.Helpdesk.models.Ticket;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {
	
	@Query(value = "SELECT * FROM tickets INNER JOIN users ON users.id = tickets.user_id WHERE users.id = :user_id ORDER BY tickets.id DESC", nativeQuery = true)
	public List<Ticket> findByUserId(@Param("user_id") long user_id);
	
}
