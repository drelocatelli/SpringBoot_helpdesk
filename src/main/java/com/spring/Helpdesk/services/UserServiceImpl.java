package com.spring.Helpdesk.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.Helpdesk.models.Role;
import com.spring.Helpdesk.models.User;
import com.spring.Helpdesk.repositories.RolesRepository;
import com.spring.Helpdesk.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RolesRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(UserRepository repository,RolesRepository roleRepository ,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.repository = repository;
	}
	
	@Override
	public List<User> findAll() {
		return this.repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public User create(User user) {
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Role userRole = this.roleRepository.findByName("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		
		return this.repository.save(user);
	}

	@Override
	public boolean delete(long id) {
		User user = findById(id);
		
		if(user != null) {
			this.repository.delete(user);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean update(long id, User user) {
		User userExists = findById(id);
		
		if(userExists != null) {
			userExists.setId(user.getId());
			userExists.setName(user.getName());
			userExists.setLastname(user.getLastname());
			userExists.setEmail(user.getEmail());
			userExists.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			userExists.setActive(user.isActive());
			
			Role userRole = this.roleRepository.findByName(user.getRoles().iterator().next().getName());
			
			userExists.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
			
			this.repository.save(userExists);
			
			return true;
		}
		
		return false;
	}
	
	private User findById(long id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public User show(long id) {
		return findById(id);
	}

	@Override
	public List<User> findAllWhereRoleEquals(long role_id) {
		return this.repository.findAllWhereRoleEquals(role_id);
	}

	@Override
	public User findCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String email = auth.getName();
		
		User userLogged = this.repository.findByEmail(email);
		
		return userLogged;
		
	}

}
