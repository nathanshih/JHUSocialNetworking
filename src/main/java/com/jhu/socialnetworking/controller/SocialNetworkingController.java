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
import com.jhu.socialnetworking.model.Student;
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
    @RequestMapping(value = "/completedCourse", method = RequestMethod.POST)
    @ResponseBody
    public Student addCompletedCourse(@RequestParam(value = "studentId", required = true) String studentId,
    							      @RequestParam(value = "courseId", required = true) String courseId) {
    	
    	LOG.debug("Adding completed course: " + courseId + " to student: " + studentId);
    	
    	return socialNetworkingService.addCompletedCourse(studentId, courseId);
    }

}
