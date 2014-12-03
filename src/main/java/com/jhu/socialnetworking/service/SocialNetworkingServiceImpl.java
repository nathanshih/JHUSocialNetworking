package com.jhu.socialnetworking.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.jhu.socialnetworking.dao.CourseDAO;
import com.jhu.socialnetworking.dao.StudentDAO;
import com.jhu.socialnetworking.model.Course;
import com.jhu.socialnetworking.model.Student;

/**
 * This is the implementation of the service. The actual business logic code is here.
 *
 * @author Nathan Shih
 * @date Sep 27, 2014
 */
@Service
public class SocialNetworkingServiceImpl implements SocialNetworkingService {
	
	private StudentDAO studentDAO;
	private CourseDAO courseDAO;
	
	private static AtomicLong idCounter;
	
	public SocialNetworkingServiceImpl() {
		idCounter = new AtomicLong();
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Module.xml");
		studentDAO = (StudentDAO) context.getBean("studentDAO");
		courseDAO = (CourseDAO) context.getBean("courseDAO");
	}
	
	@Override
	public Student register(Student student) {
		studentDAO.insert(student);
		
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}
	
    @Override
    public Course insertCourse(Course course) {
        courseDAO.insert(course);
        
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }
	
}
