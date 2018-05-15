package com.ecom.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "user_table")
@PrimaryKeyJoinColumn(name = "personID")
public class User extends Person {

	@Column(name = "userName")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name="role")
	private String role;

	@Column(name="email")
	private String email;

	
//	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//	private Email email;
	
	public User(String username, String password, String role, String email) {
		this.username = username;
		this.password = password;
		this.role=role;
		this.email=email;
	}

	public User() {
	
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

//	public Email getEmail() {
//		return email;
//	}
//
//	public void setEmail(Email email) {
//		this.email = email;
//	}


}
