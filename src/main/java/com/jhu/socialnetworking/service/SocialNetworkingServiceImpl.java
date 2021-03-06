package com.jhu.socialnetworking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.jhu.socialnetworking.dao.CartDAO;
import com.jhu.socialnetworking.dao.CompletedCourseDAO;
import com.jhu.socialnetworking.dao.CourseDAO;
import com.jhu.socialnetworking.dao.StudentConnectionDAO;
import com.jhu.socialnetworking.dao.StudentDAO;
import com.jhu.socialnetworking.model.Course;
import com.jhu.socialnetworking.model.CourseLight;
import com.jhu.socialnetworking.model.Student;
import com.jhu.socialnetworking.model.StudentConnection;

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
	private StudentConnectionDAO studentConnectionDAO;
	
	public SocialNetworkingServiceImpl() {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Module.xml");
		studentDAO = (StudentDAO) context.getBean("studentDAO");
		courseDAO = (CourseDAO) context.getBean("courseDAO");
		completedCourseDAO = (CompletedCourseDAO) context.getBean("completedCourseDAO");
		cartDAO = (CartDAO) context.getBean("cartDAO");
		studentConnectionDAO = (StudentConnectionDAO) context.getBean("studentConnectionDAO");
	}
	

	@Override
	public Student login(String email, String password) {
		
		return studentDAO.login(email, password);
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
    public String emailStudent(String studentId, String toEmail) {
            
        Student student = studentDAO.getStudentByStudentId(studentId);
        String fromEmail = student.getEmail();
        String content = "Hello,\nI am planning to enroll in a course that "
                         + "you have previously completed in the Johns Hopkins "
                         + "University Engineering for Professionals program. I "
                         + "would appreciate your response and feedback.\n"
                         + "Regards,\n" + student.getName() + "\n" + fromEmail;
        // Send confirmation email using Gmail Utility
        try {
            MailUtilGmail.sendMail(
                    toEmail,
                    fromEmail,
                    "JERCS Email Exchange",
                    content, true);
        } catch (RuntimeException re) {
            System.out.println(re);
            return "";
        }

        return content;
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
	public List<CourseLight> getCompletedCourses(String studentId) {
	
		List<CourseLight> courseLights = new ArrayList<CourseLight>();
		List<String> courseIds = completedCourseDAO.getCompletedCourseIdsByStudentId(Integer.valueOf(studentId));
		for (String courseId : courseIds) {
			Course course = courseDAO.getCourseById(courseId);
			CourseLight courseLight = new CourseLight();
			courseLight.setCourseId(courseId);
			courseLight.setCourseName(course.getCourseName());
			courseLights.add(courseLight);
		}
		
		return courseLights;
	}
	
	@Override
	public Student addToCart(String studentId, String courseId) {

	    // insert checked out course into DB
		cartDAO.insert(courseId, studentId);
		
		Student student = studentDAO.getStudentByStudentId(studentId);
		student = populateStudentExtraInfo(student);
		
		return student;
	}

	@Override
	public List<CourseLight> getCart(String studentId) {
		
		List<CourseLight> courseLights = new ArrayList<CourseLight>();
		List<String> courseIds = cartDAO.getCourseIdsByStudentId(studentId);
		for (String courseId : courseIds) {
			Course course = courseDAO.getCourseById(courseId);
			CourseLight courseLight = new CourseLight();
			courseLight.setCourseId(courseId);
			courseLight.setCourseName(course.getCourseName());
			courseLights.add(courseLight);
		}
		
		return courseLights;
	}
	
	@Override
	public void removeFomCart(String studentId, String courseId) {
		
		cartDAO.remove(courseId, studentId);
	}
	
	@Override
	public StudentConnection addContact(String studentId, String contactId) {
		StudentConnection studentConnection = new StudentConnection();
		studentConnection.setStudentId(Integer.valueOf(studentId));
		studentConnection.setContactId(Integer.valueOf(contactId));
		
		return studentConnectionDAO.insert(studentConnection);
	}
	
	@Override
	public List<Student> getAllContacts(String studentId) {
		
		List<Student> connectedStudents = new ArrayList<Student>();
		List<StudentConnection> studentConnections = studentConnectionDAO.getConnectionsByStudentId(Integer.valueOf(studentId));
		for (StudentConnection studentConnection : studentConnections) {
			Student student = studentDAO.getStudentByStudentId(String.valueOf(studentConnection.getContactId()));
			connectedStudents.add(student);
		}
		
		return connectedStudents;
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
