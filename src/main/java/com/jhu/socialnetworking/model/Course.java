package com.jhu.socialnetworking.model;

import java.util.ArrayList;

public class Course {

	private String courseId;
	private String courseName;
	private String discipline;
	private Integer usersCompleted;
	private Integer usersCheckedOut;
	private ArrayList<Professor> professors;
	
	public Course() {
		professors = new ArrayList<Professor>();
	}
	
	public String getCourseId() {
		return courseId;
	}
	
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getDiscipline() {
		return discipline;
	}
	
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	
	public Integer getUsersCompleted() {
		return usersCompleted;
	}

	public void setUsersCompleted(Integer usersCompleted) {
		this.usersCompleted = usersCompleted;
	}

	public Integer getUsersCheckedOut() {
		return usersCheckedOut;
	}

	public void setUsersCheckedOut(Integer usersCheckedOut) {
		this.usersCheckedOut = usersCheckedOut;
	}

	public ArrayList<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(ArrayList<Professor> professors) {
		this.professors = professors;
	}

	public String toString() {
		return courseId + " : " + courseName;
	}
}
