package com.spring.Helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Helpdesk.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
