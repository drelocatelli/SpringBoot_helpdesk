package com.spring.Helpdesk.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.Helpdesk.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
//	@Query(value = "SELECT us.* FROM users us INNER JOIN users_roles ur ON us.id = ur.user_id WHERE ur.role_id = :role_id AND us.id NOT IN(:user_id)", nativeQuery = true)
//	public List<User> findAllWhereRoleEquals(@Param("role_id") long role_id, @Param("user_id") long user_id);
	
	@Query(value = "select * from users inner join users_roles on users.id = users_roles.user_id where users_roles.role_id = :role_id", nativeQuery = true)
	public List<User> findAllWhereRoleEquals(@Param("role_id") Long role_id);
	
	public User findByEmail(String email);
	
}
