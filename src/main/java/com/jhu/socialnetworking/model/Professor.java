package com.jhu.socialnetworking.model;

public class Professor {

	private int professorId;
	private String firstName;
	private String lastName;
	
	public int getProfessorId() {	
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
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
	public String toString () {
		return this.firstName + " " + this.lastName;
	}
}
