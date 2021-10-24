package com.spring.Helpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Helpdesk.models.User;
import com.spring.Helpdesk.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<User> findAll() {
		return this.repository.findAll();
	}

	@Override
	public User create(User user) {
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
		return false;
	}
	
	private User findById(long id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public User show(long id) {
		return findById(id);
	}

}
