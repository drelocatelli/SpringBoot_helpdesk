package com.spring.Helpdesk.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Must be not empty")
	private String email;
	
	@Column
	@NotEmpty(message = "Must be not empty")
	private String name;
	
	@Column
	@NotEmpty(message = "Must be not empty")
	private String lastname;
	
	@Column
	@NotEmpty(message = "Must be not empty")
	@Length(min = 5, message = "You need to provide a password that contains at least 5 characters")
	private String password;
	
	@Column
	private boolean active = true;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	public User() {
		
	}
	
	public User(Long id, String email, String name, String lastname, String password, boolean active) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.lastname = lastname;
		this.password = password;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
