package com.jhu.socialnetworking.database;

import java.io.IOException;

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

		response.getWriter().println("-----Test Student Insert-----");
		StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
		
		Student student = new Student();
		student.setName("Aaron Rogers");
		student.setEmail("aaron@packers.com");
		student.setPassword("bears");
		student.setDiscipline("Football");
		
		Student insertedStudent = studentDAO.insert(student);
		response.getWriter().println("student id:\t\t" + insertedStudent.getId());
		response.getWriter().println("student name:\t\t" + insertedStudent.getName());
		response.getWriter().println("student email:\t\t" + insertedStudent.getEmail());
		response.getWriter().println("student password:\t" + insertedStudent.getPassword());
		response.getWriter().println("student discipline:\t" + insertedStudent.getDiscipline());
		
		
	}
}