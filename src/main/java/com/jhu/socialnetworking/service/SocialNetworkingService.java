package com.jhu.socialnetworking.service;

import java.util.List;

import com.jhu.socialnetworking.model.Course;
import com.jhu.socialnetworking.model.Student;

/**
 * This is the interface for the service. All the methods for the business logic will be written here.
 *
 * @author Nathan Shih
 * @date Sep 27, 2014
 */
public interface SocialNetworkingService {

	/**
	 * This registers a new student.
	 *
	 * @param student - the new student to be registered
	 * @return Student
	 */
	public Student register(Student student);
	
	/**
	 * This returns back all the students currently registered.
	 *
	 * @return List<Student>
	 */
	public List<Student> getAllStudents();
	
    /**
     * This inserts a new course.
     *
     * @param course - the new course to be inserted
     * @return Course
     */
    public Course insertCourse(Course course);
    
    /**
     * This returns back all the course currently available.
     *
     * @return List<Course>
     */
    public List<Course> getAllCourses();
    
	// TODO: Add new busness logic methods here
}
