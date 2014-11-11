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

import com.jhu.socialnetworking.dao.StudentDAO;
import com.jhu.socialnetworking.model.Student;

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

		// Get a student DAO to add and remove students
		StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
		
		// Add some students
		Student student = new Student();
		student.setName("Chris Karlen");
		student.setEmail("chris@email.com");			
		studentDAO.insert(student);
		
		student = new Student();
		student.setName("Nathan Shih");
		student.setEmail("nathan@email.com");			
		studentDAO.insert(student);
		
		student = new Student();
		student.setName("Arthur Tucker");
		student.setEmail("arthur@email.com");			
		studentDAO.insert(student);		
				
		// Get all the students from the database
		List<Student> studentList = studentDAO.getAllStudents();
				
		// Print out all the students and remove each student from the database
		for (Student studentObj : studentList) {
			response.getWriter().println(studentObj);
			studentDAO.remove(studentObj);
		}
				
		// Print confirmation that all students were removed
		studentList = studentDAO.getAllStudents();
		if (studentList.size() == 0)
			response.getWriter().println("No students in database");

	}
}