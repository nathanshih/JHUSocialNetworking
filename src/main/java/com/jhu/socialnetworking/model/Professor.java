package com.jhu.socialnetworking.model;

public class Professor {

	private int professorId;
	private String name;
	
	public int getProfessorId() {	
		return professorId;
	}
	
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
