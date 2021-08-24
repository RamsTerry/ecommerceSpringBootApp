package com.simplilearn.shoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;




@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable= false,length = 45)
	private String first_name;
	
	@Column(nullable= false,length = 45)
	private String last_Name;
	
	@Column(nullable= false , unique = true, length = 45)
	@NotEmpty
	@Email(message = "{errors.invalid_email}")
	private String Email;
	
	@NotEmpty
	@Column(nullable= false,length = 64)
	private String password;

	public User() {

	}

	public User(Long id, String first_name, String last_Name,
			@NotEmpty @javax.validation.constraints.Email(message = "{errors.invalid_email}") String email,
			@NotEmpty String password) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_Name = last_Name;
		Email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_Name=" + last_Name + ", Email=" + Email
				+ ", password=" + password + "]";
	}


}
