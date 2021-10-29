package com.spring.Helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.Helpdesk.models.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {

}
