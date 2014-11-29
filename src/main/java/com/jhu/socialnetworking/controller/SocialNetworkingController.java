package com.jhu.socialnetworking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * This returns all the students that are currently registered.
	 *
	 * @return List<Student>
	 */
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	@ResponseBody
	public List<Student> getAllStudents() {
		
		return socialNetworkingService.getAllStudents();
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Student register(@RequestBody Student student) {
		
		LOG.debug("Registering student: " + student.toString());
		
		return socialNetworkingService.register(student);
	}

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String getCourses() {
        
        return "courses";
    }
    
}
