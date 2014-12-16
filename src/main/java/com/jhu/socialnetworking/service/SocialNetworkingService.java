package com.jhu.socialnetworking.service;

import java.util.List;

import com.jhu.socialnetworking.model.EmailContacts;
import com.jhu.socialnetworking.model.Course;
import com.jhu.socialnetworking.model.Student;
import com.jhu.socialnetworking.model.StudentConnection;

/**
 * This is the interface for the service. All the methods for the business logic will be written here.
 *
 * @author Nathan Shih
 * @date Sep 27, 2014
 */
public interface SocialNetworkingService {

	/**
	 * This processes a login call. Matching on the email and password is case sensitive.
	 *
	 * @param email - the email of the registered student
	 * @param password - the password of the registered student
	 * @return the registered Student object
	 */
	public Student login(String email, String password);
	
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
	public Student updateStudent(Student student);
	
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
     * This sends an email to a student.
     *
     * @param studentId - the sender student ID
     * @param toEmail - the student email recipient
     * @return the email content
     */
    public String emailStudent(String studentId, String toEmail);

    /**
     * This adds a new connection for a student.
     *
     * @param studentId - the student ID
     * @param contactId - the contact ID of the student to be added
     * @return a StudentConnection object
     */
    public StudentConnection addContact(String studentId, String contactId);
    
	/**
	 * This gets a list of email addresses for whom the student is connected with.
	 *
	 * @param studentId - the id of the student to
	 * @return the set of emails for students this student is connected with
	 */
	public EmailContacts getAllContacts(String studentId);
	
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
	public Student checkoutCourse(String studentId, String courseId);
	
	/**
	 * This removes a course from a student's cart.
	 *
	 * @param studentId - the id of the student to remove from their cart
	 * @param courseId - the id of the course to remove from the student's cart
	 */
	public void removeFomCart(String studentId, String courseId);
}
