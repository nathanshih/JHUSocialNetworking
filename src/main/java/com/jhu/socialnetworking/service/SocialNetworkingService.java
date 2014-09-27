package com.jhu.socialnetworking.service;

import java.util.List;

import com.jhu.socialnetworking.model.Student;

/**
 *
 * @author Nathan Shih
 * @date Sep 27, 2014
 */
public interface SocialNetworkingService {

	public Student register(Student student);
	
	public List<Student> getAllStudents();
}
