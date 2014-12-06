package com.jhu.socialnetworking.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class for the student object.
 *
 * @author Nathan Shih
 * @date Sep 27, 2014
 */
public class Student {

	private String id;
	private String name;
	private String email;
	private String password;
	private String contact;
	private String degreeProgram;
	private ArrayList<CourseLight> completedCourses;
	private Cart cart;
	
	public Student() {
		completedCourses = new ArrayList<CourseLight>();
		cart = new Cart();
	}
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDegreeProgram() {
		return degreeProgram;
	}

	public void setDegreeProgram(String degreeProgram) {
		this.degreeProgram = degreeProgram;
	}
	
	public ArrayList<CourseLight> getCompletedCourses() {
		return completedCourses;
	}
	
	public void setCompletedCourses(ArrayList<CourseLight> completedCourses) {
		this.completedCourses = completedCourses;
	}
	
	public void addCourse(CourseLight courseLight) {
		completedCourses.add(courseLight);
	}
	
	public void removeCourse(CourseLight courseLight) {
		completedCourses.remove(courseLight);
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public void addToCart(CourseLight courseLight) {
		cart.addToCart(courseLight);
	}
	
	public void removeFromCart(CourseLight courseLight) {
		cart.removeFromCart(courseLight);
	}
}
