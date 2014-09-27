package com.jhu.socialnetworking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model class for the student object.
 *
 * @author Nathan Shih
 * @date Sep 27, 2014
 */
public class Student {

	private String name;
	private String email;
	private String password;
	private String contact;
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
}
