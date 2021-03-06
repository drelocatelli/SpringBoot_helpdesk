package com.spring.Helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Helpdesk.models.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
	
}
