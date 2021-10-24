package com.spring.Helpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Helpdesk.models.Role;
import com.spring.Helpdesk.repositories.RolesRepository;

@Service
public class RolesServiceImpl implements RoleService {

	@Autowired
	private RolesRepository repository;
	
	public RolesServiceImpl(RolesRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Role> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Role create(Role role) {
		role.setName(role.getName().toUpperCase());
		Role roleCreated = this.repository.save(role);
		
		return roleCreated;
	}

	@Override
	public boolean delete(long id) {
		Role role = findById(id);
		
		if(role != null) {
			this.repository.delete(role);
			return true;
		}
		
		return false;
		
	}
	
	private Role findById(long id) {
		return this.repository.findById(id).orElse(null);
	}

}
