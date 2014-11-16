package com.jhu.socialnetworking.dao;

import java.util.List;

import com.jhu.socialnetworking.model.Course;

/**
 * Interface implemented to persist Course objects
 */
public interface CourseDAO {

	public void insert(Course course);
	
	public void remove(Course course);

	public List<Course> getAllCourses();
	
	public Course getCourseById(String courseId);
	
}
