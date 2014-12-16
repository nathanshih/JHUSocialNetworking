package com.jhu.socialnetworking.model;

public class StudentConnection {
	
	private int connectionId;
	
	private int studentId;
	
	private int contactId;

	public StudentConnection(int studentId, int contactId) {
		this.studentId = studentId;
		this.contactId = contactId;
	}
	
	public StudentConnection() {

	}
	
	public int getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String toString() {
		return "Student ID: " + studentId + " connected with: " + contactId;
	}
	
}
