package com.jhu.socialnetworking.model;

/**
 * Lightweight version of the course model object.
 * 
 * @author Nathan Shih
 * @since Dec 1, 2014
 */
public class CourseLight {

	private String courseId;
	private String courseName;
	
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
}
