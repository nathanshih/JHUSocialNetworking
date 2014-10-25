package com.jhu.socialnetworking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class for the student object.
 *
 * @author Nathan Shih
 * @date Sep 27, 2014
 */
public class Student {

	private int studentId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String contact;
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
	public String getName() {
		return firstName;
	}
	
	public void setName(String name) {
		this.firstName = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonIgnore // used to not send the pasword back in the response
	public String getPassword() {
		return password;
	}
	
	@JsonProperty("password") // need this in conjunction with @JsonIgnore to ensure password is set
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
}
