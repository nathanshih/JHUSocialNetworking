package com.jhu.socialnetworking.database;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jhu.socialnetworking.dao.CourseDAO;
import com.jhu.socialnetworking.dao.EvaluationDAO;
import com.jhu.socialnetworking.dao.ProfessorDAO;
import com.jhu.socialnetworking.dao.RegistrationDAO;
import com.jhu.socialnetworking.dao.StudentConnectionDAO;
import com.jhu.socialnetworking.dao.StudentDAO;
import com.jhu.socialnetworking.model.Course;
import com.jhu.socialnetworking.model.Evaluation;
import com.jhu.socialnetworking.model.Professor;
import com.jhu.socialnetworking.model.Registration;
import com.jhu.socialnetworking.model.Student;
import com.jhu.socialnetworking.model.StudentConnection;

/**
 * Servlet implementation class InitializeDatabaseServlet
 */
@WebServlet("/InitializeDatabaseServlet")
public class InitializeDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitializeDatabaseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Module.xml");

		// Test StudentDAO.update()
		response.getWriter().println("-----Test Student Update-----");
		StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
		List<Student> studentList = studentDAO.getAllStudents();
		Student student = studentList.get(0);
		response.getWriter().println(student.getName());
		student.setName("Aaron Rogers");
		studentDAO.update(student);
		studentList = studentDAO.getAllStudents();
		student = studentList.get(0);
		response.getWriter().println(student.getName());
		
		response.getWriter().println("-----Test Course Update-----");
		CourseDAO courseDAO = (CourseDAO) context.getBean("courseDAO");
		List<Course> courseList = courseDAO.getAllCourses();
		Course course = courseList.get(0);
		response.getWriter().println(course.getCourseName());
		course.setCourseName("Introduction to Python Programming");
		courseDAO.update(course);
		courseList = courseDAO.getAllCourses();
		course = courseList.get(0);
		response.getWriter().println(course.getCourseName());

	}
}