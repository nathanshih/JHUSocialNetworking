package com.jhu.socialnetworking.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.jhu.socialnetworking.dao.CartDAO;
import com.jhu.socialnetworking.dao.CompletedCourseDAO;
import com.jhu.socialnetworking.dao.CourseDAO;
import com.jhu.socialnetworking.dao.StudentDAO;
import com.jhu.socialnetworking.model.Course;
import com.jhu.socialnetworking.model.CourseLight;
import com.jhu.socialnetworking.model.EmailContact;
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
	private CompletedCourseDAO completedCourseDAO;
	private CartDAO cartDAO;
	
	//private static AtomicLong idCounter;
	
	public SocialNetworkingServiceImpl() {
		//idCounter = new AtomicLong();
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Module.xml");
		studentDAO = (StudentDAO) context.getBean("studentDAO");
		courseDAO = (CourseDAO) context.getBean("courseDAO");
		completedCourseDAO = (CompletedCourseDAO) context.getBean("completedCourseDAO");
		cartDAO = (CartDAO) context.getBean("cartDAO");
	}
	
	@Override
	public Student register(Student student) {
			
		return studentDAO.insert(student);
	}

	@Override
	public Student updateStudent(Student student) {
		
		return studentDAO.update(student);
	}

	@Override
	public void removeStudent(String studentId) {
		
		studentDAO.remove(studentId);
	}
	
	@Override
	public List<Student> getAllStudents() {
		
		return studentDAO.getAllStudents();
	}
	
    @Override
    public Course insertCourse(Course course) {
               
        return courseDAO.insert(course);
    }

	@Override
	public Course updateCourse(Course course) {
		
		return courseDAO.update(course);
	}

	@Override
	public void removeCourse(String courseId) {
		
		courseDAO.remove(courseId);
	}
	
    @Override
    public List<Course> getAllCourses() {
    	
        return courseDAO.getAllCourses();
    }

	@Override
	public Student addCompletedCourse(String studentId, String courseId) {
		
		// insert completed course into DB
		completedCourseDAO.insert(courseId, studentId);
		
		// get all the completed courses for a student to populate the model object
		List<Integer> completedCourses = completedCourseDAO.getCompletedCourseIdsByStudentId(studentId);
		Student student = studentDAO.getStudentByStudentId(studentId);
		for (Integer completedCourse : completedCourses) {
			Course course = courseDAO.getCourseById(Integer.toString(completedCourse));
			CourseLight courseLight = new CourseLight();
			courseLight.setCourseId(course.getCourseId());
			courseLight.setCourseName(course.getCourseName());
			student.addCourse(courseLight);
		}
		
		return student;
	}

	@Override
	public Student addCourseCheckedOut(String studentId, String courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmailContact> getAllContacts(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}
}
