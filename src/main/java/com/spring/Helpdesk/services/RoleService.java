package com.spring.Helpdesk.services;

import java.util.List;

import com.spring.Helpdesk.models.Role;

public interface RoleService {
	
	public List<Role> findAll();
	public Role create(Role name);
	public boolean delete(long id);
	public Role findByName(String name);

}
