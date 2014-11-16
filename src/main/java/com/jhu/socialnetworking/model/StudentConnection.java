package com.jhu.socialnetworking.model;

public class StudentConnection {
	
	private int connectionId;
	
	private int firstStudentId;
	
	private int secondStudentId;

	public StudentConnection(int firstStudentId, int secondStudentId) {
		this.firstStudentId = firstStudentId;
		this.secondStudentId = secondStudentId;
	}
	
	public StudentConnection() {

	}
	
	public int getFirstStudentId() {
		return firstStudentId;
	}

	public void setFirstStudentId(int firstStudentId) {
		this.firstStudentId = firstStudentId;
	}

	public int getSecondStudentId() {
		return secondStudentId;
	}

	public void setSecondStudentId(int secondStudentId) {
		this.secondStudentId = secondStudentId;
	}
	
	public int getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}
	
	public String toString() {
		return "firstStudentId: " + firstStudentId + " secondStudentId: " + secondStudentId;
	}
	
}
