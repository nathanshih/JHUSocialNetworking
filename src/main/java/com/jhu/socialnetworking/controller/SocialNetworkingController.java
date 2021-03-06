package com.jhu.socialnetworking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhu.socialnetworking.model.Course;
import com.jhu.socialnetworking.model.CourseLight;
import com.jhu.socialnetworking.model.Student;
import com.jhu.socialnetworking.model.StudentConnection;
import com.jhu.socialnetworking.service.SocialNetworkingService;

/**
 * This is the main entry point in to the service. All requests are sent through here which then forwards
 * the information on to the service for processing. 
 *
 * @author Nathan Shih
 * @date Sep 27, 2014
 */
@Controller
@RequestMapping("/")
public class SocialNetworkingController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SocialNetworkingController.class);
	
	private final SocialNetworkingService socialNetworkingService;
	
	@Autowired
	public SocialNetworkingController(SocialNetworkingService socialNetworkingService){
		this.socialNetworkingService = socialNetworkingService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome() {
		return "home";
	}
	
	/**
	 * This processes a login call. Matching on the email and password is case sensitive.
	 *
	 * @param email - the email of the registered student
	 * @param password - the password of the registered student
	 * @return the registered Student object
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Student> register(@RequestParam(value = "email", required = true) String email,
		      				@RequestParam(value = "password", required = true) String password) {
		
		LOG.debug("Attempting to login user: " + email);
		
		Student student = socialNetworkingService.login(email, password);
		if (student == null) {
			return new ResponseEntity<Student>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		}
	}
	
	/**
	 * This registers a new student.
	 *
	 * @param student - the new student to be registered
	 * @return the registered Student object
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Student register(@RequestBody Student student) {
		
		LOG.debug("Registering student: " + student.toString());
		
		return socialNetworkingService.register(student);
	}
	
	/**
	 * This updates a student's information.
	 *
	 * @param student - the student to be updated
	 * @return an updated Student object
	 */
	@RequestMapping(value = "/updateStudent", method = RequestMethod.PUT)
	@ResponseBody
	public Student updateStudent(@RequestBody Student student) {
		
		LOG.debug("Updating student: " + student.toString());
		
		return socialNetworkingService.updateStudent(student);
	}
	
	/**
	 * This removes a student from the system.
	 *
	 * @param studentId - the id of the student to be removed
	 */
	@RequestMapping(value = "/removeStudent", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeStudent(@RequestParam(value = "studentId", required = true) String studentId) {
		
		LOG.debug("Deleting student with id: " + studentId);
		
		socialNetworkingService.removeStudent(studentId);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * This returns back all the students currently registered.
	 *
	 * @return a list of Student objects for all students in the system
	 */
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	@ResponseBody
	public List<Student> getAllStudents() {
		
		LOG.debug("Get all students.");
		
		return socialNetworkingService.getAllStudents();
	}

    /**
     * This sends an email to a student.
     *
     * @param studentId - the sender student ID
     * @param toEmail - the student email recipient
     * @return the email content
     */
    @RequestMapping(value = "/emailStudent", method = RequestMethod.POST)
    @ResponseBody
    public String emailStudent(@RequestParam(value = "studentId", 
                                   required = true) String studentId,
                               @RequestParam(value = "toEmail",
                                   required = true) String toEmail) {
        
        LOG.debug("Emailing student " + toEmail + " from " + studentId);
        
        return socialNetworkingService.emailStudent(studentId, toEmail);
    }
    
    /**
     * This adds a new connection for a student.
     *
     * @param studentId - the student ID
     * @param contactId - the contact ID of the student to be added
     * @return a StudentConnection object
     */
    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    @ResponseBody
    public StudentConnection addContacts(@RequestParam(value = "studentId", required = true) String studentId,
    										@RequestParam(value = "contactId", required = true) String contactId) {
		
    	LOG.debug("Adding contact: " + contactId + " for student: " + studentId);
    	
    	return socialNetworkingService.addContact(studentId, contactId);	
    }
    
    /**
	 * This gets a list of email addresses for whom the student is connected with.
	 *
	 * @param studentId - the id of the student to
	 * @return the set of emails for students this student is connected with
	 */
    @RequestMapping(value = "/getAllContacts", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getAllContacts(@RequestParam(value = "studentId", required = true) String studentId) {
		
    	LOG.debug("Getting all contacts for student: " + studentId);
    	
    	return socialNetworkingService.getAllContacts(studentId);	
    }
    
	/**
     * This inserts a new course.
     *
     * @param course - the new course to be inserted
     * @return the add Course object
     */
    @RequestMapping(value = "/insertCourse", method = RequestMethod.POST)
    @ResponseBody
    public Course insertCourse(@RequestBody Course course) {
        
        LOG.debug("Inserting course: " + course.toString());
        
        return socialNetworkingService.insertCourse(course);
    }
    
    /**
     * This updates a course's information.
     *
     * @param course - the course to be updated
     * @return an updated Course object
     */
    @RequestMapping(value = "/updateCourse", method = RequestMethod.PUT)
    @ResponseBody
    public Course updateCourse(@RequestBody Course course) {
        
        LOG.debug("Updating course: " + course.toString());
        
        return socialNetworkingService.updateCourse(course);
    }

    /**
     * This removes a course from the system
     *
     * @param courseId - the id of the course to be removed
     */
	@RequestMapping(value = "/removeCourse", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeCourse(@RequestParam(value = "courseId", required = true) String courseId) {
		
		LOG.debug("Deleting course with id: " + courseId);
		
		socialNetworkingService.removeCourse(courseId);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
     * This returns back all the course in the system.
     *
     * @return a list of Course objects for all courses in the system
     */
    @RequestMapping(value = "/allCourses", method = RequestMethod.GET)
    @ResponseBody
    public List<Course> getAllCourses() {
        
    	LOG.debug("Getting all coruses.");
    	
        return socialNetworkingService.getAllCourses();
    }
    
    /**
     * This adds a completed course the list of completed courses for a given student.
     *
     * @param studentId - the id of the student who completed the course
     * @param courseId - the id of the course the student completed
     * @return an updated Student object
     */
    @RequestMapping(value = "/addCompletedCourse", method = RequestMethod.POST)
    @ResponseBody
    public Student addCompletedCourse(@RequestParam(value = "studentId", required = true) String studentId,
    							      @RequestParam(value = "courseId", required = true) String courseId) {
    	
    	LOG.debug("Adding completed course: " + courseId + " to student: " + studentId);
    	
    	return socialNetworkingService.addCompletedCourse(studentId, courseId);
    }

    /**
	 * This gets the completed courses for a given student
	 *
	 * @param studentId - the id of the student to get completed courses for
	 * @return a list of completed courses for a student
	 */
    @RequestMapping(value = "/getCompletedCourses", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseLight> getCompletedCourses(@RequestParam(value = "studentId", required = true) String studentId) {
    	
    	LOG.debug("Getting completed courses for student: " + studentId);
    	
    	return socialNetworkingService.getCompletedCourses(studentId);
    }
    
    /**
	 * This adds a course to a student's cart.
	 *
	 * @param studentId - the id of the student to add to their cart
	 * @param courseId - the id of the course to add to the student's cart
	 * @return an updated Student object
	 */
    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    @ResponseBody
    public Student addToCart(@RequestParam(value = "studentId", required = true) String studentId,
    							      @RequestParam(value = "courseId", required = true) String courseId) {
    	
    	LOG.debug("Checking out course: " + courseId + " by student: " + studentId);
    	
    	return socialNetworkingService.addToCart(studentId, courseId);
    }
    
    /**
	 * This returns the cart for the student
	 *
	 * @param studentId - the id of the student to get their cart from
	 * @return a list of courses in the students cart
	 */
    @RequestMapping(value = "/getCart", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseLight> getCart(@RequestParam(value = "studentId", required = true) String studentId) {
    	
    	LOG.debug("Getting cart for student: " + studentId);
    	
    	return socialNetworkingService.getCart(studentId);
    }
    
    /**
	 * This removes a course from a student's cart.
	 *
	 * @param studentId - the id of the student to remove from their cart
	 * @param courseId - the id of the course to remove from the student's cart
	 */
    @RequestMapping(value = "/removeFromCart", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeCourse(@RequestParam(value = "studentId", required = true) String studentId,
		      									@RequestParam(value = "courseId", required = true) String courseId) {
		
		LOG.debug("Removing course: " + courseId + " from cart.");
		
		socialNetworkingService.removeFomCart(studentId, courseId);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
