package com.jhu.socialnetworking.model;

public class CompletedCourse {

	int completedCourseId;
	int courseId;
	int studentId;
	
	public int getCompletedCourseId() {
		return completedCourseId;
	}
	public void setCompletedCourseId(int completedCourseId) {
		this.completedCourseId = completedCourseId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
}
