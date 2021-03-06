package com.spring.Helpdesk.services;

import java.util.List;

import com.spring.Helpdesk.models.User;

public interface UserService {
	
	public List<User> findAll();
	public User create(User user);
	public boolean delete(long id);
	public boolean update(long id, User user);
	public User show(long id);
	public List<User> findAllWhereRoleEquals(long role_id, long user_id);
	public User findCurrentUser();
	public boolean checkByRole(String role);

}
