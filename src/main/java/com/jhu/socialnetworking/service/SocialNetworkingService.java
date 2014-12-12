package com.jhu.socialnetworking.service;

import java.util.List;

import com.jhu.socialnetworking.model.EmailContact;
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
	 * @return the registered Student object
	 */
	public Student register(Student student);
	
	/**
	 * This updates a student's information.
	 *
	 * @param student - the student to be updated
	 * @return an updated Student object
	 */
	public Student update(Student student);
	
	/**
	 * This removes a student from the system.
	 *
	 * @param studentId - the id of the student to be removed
	 */
	public void removeStudent(String studentId);
	
	/**
	 * This returns back all the students currently registered.
	 *
	 * @return a list of Student objects for all students in the system
	 */
	public List<Student> getAllStudents();
	
    /**
     * This inserts a new course.
     *
     * @param course - the new course to be inserted
     * @return the add Course object
     */
    public Course insertCourse(Course course);
    
    /**
     * This updates a course's information.
     *
     * @param course - the course to be updated
     * @return an updated Course object
     */
    public Course updateCourse(Course course);
    
    /**
     * This removes a course from the system
     *
     * @param courseId - the id of the course to be removed
     */
    public void removeCourse(String courseId);
    
    /**
     * This returns back all the course in the system.
     *
     * @return a list of Course objects for all courses in the system
     */
    public List<Course> getAllCourses();
    
    /**
     * This adds a completed course the list of completed courses for a given student.
     *
     * @param studentId - the id of the student who completed the course
     * @param courseId - the id of the course the student completed
     * @return an updated Student object
     */
	public Student addCompletedCourse(String studentId, String courseId);
	
	/**
	 * This adds a course to a student's cart.
	 *
	 * @param studentId - the id of the student to add to their cart
	 * @param courseId - the id of the course to add to the student's cart
	 * @return an updated Student object
	 */
	public Student addCourseCheckedOut(String studentId, String courseId);
	
	/**
	 * This gets a list of email addresses for whom the student is connected with.
	 *
	 * @param studentId - the id of the student to
	 * @return
	 */
	public List<EmailContact> getAllContacts(String studentId);
}
