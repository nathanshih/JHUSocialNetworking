package com.jhu.socialnetworking.service;

import java.util.ArrayList;
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
	
	public SocialNetworkingServiceImpl() {
		
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
		
		Student studentObj = studentDAO.update(student);
		studentObj = populateStudentExtraInfo(studentObj);
		
		return studentObj;
	}

	@Override
	public void removeStudent(String studentId) {
		
		studentDAO.remove(studentId);
	}
	
	@Override
	public List<Student> getAllStudents() {
		
		List<Student> students = studentDAO.getAllStudents();
		List<Student> studentObjs = new ArrayList<Student>();
		for (Student student : students) {
			student = populateStudentExtraInfo(student);
			studentObjs.add(student);
		}
		
		return studentObjs;
	}
	
    @Override
    public Student emailStudent(Student student) {
            
        String content = "";
        // Send confirmation email using Gmail Utility
        try {
            MailUtilGmail.sendMail(
                    student.getEmail(),
                    "noreply",
                    "JERCS Email Exchange",
                    content, true);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        return student;
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
		completedCourseDAO.insert(courseId, Integer.valueOf(studentId));
		
		Student student = studentDAO.getStudentByStudentId(studentId);
		student = populateStudentExtraInfo(student);
		
		return student;
	}

	@Override
	public Student checkoutCourse(String studentId, String courseId) {
		
		// insert checked out course into DB
		cartDAO.insert(courseId, studentId);
		
		Student student = studentDAO.getStudentByStudentId(studentId);
		student = populateStudentExtraInfo(student);
		
		return student;
	}

	@Override
	public void removeFomCart(String studentId, String courseId) {
		
		cartDAO.remove(courseId, studentId);
	}
	
	@Override
	public List<EmailContact> getAllContacts(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Student populateStudentExtraInfo(Student student) {
		
		// get all the completed courses for a student to populate the model object
		List<String> completedCourseIds = completedCourseDAO.getCompletedCourseIdsByStudentId(Integer.valueOf(student.getId()));
		for (String completedCourseId : completedCourseIds) {
			Course course = courseDAO.getCourseById(completedCourseId);
			CourseLight courseLight = new CourseLight();
			courseLight.setCourseId(course.getCourseId());
			courseLight.setCourseName(course.getCourseName());
			student.addCourse(courseLight);
		}
		
		// get all the checked out courses for a student to populate the model object
		List<String> checkoutCourseIds = cartDAO.getCourseIdsByStudentId(student.getId());
		for (String checkoutCourseId : checkoutCourseIds) {
			Course course = courseDAO.getCourseById(checkoutCourseId);
			CourseLight courseLight = new CourseLight();
			courseLight.setCourseId(course.getCourseId());
			courseLight.setCourseName(course.getCourseName());
			student.addToCart(courseLight);
		}
		
		return student;
	}
}
