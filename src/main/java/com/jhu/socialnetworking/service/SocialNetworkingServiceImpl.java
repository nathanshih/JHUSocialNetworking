package com.jhu.socialnetworking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhu.socialnetworking.model.Student;

/**
 * This is the implementation of the service. The actual business logic code is here.
 *
 * @author Nathan Shih
 * @date Sep 27, 2014
 */
@Service
public class SocialNetworkingServiceImpl implements SocialNetworkingService {

	private List<Student> students; // TODO: should this be a Map instead of List?
	
	public SocialNetworkingServiceImpl() {
		students = new ArrayList<Student>();
	}
	
	@Override
	public Student register(Student student) {
		students.add(student);
		
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
		return students;
	}
}
