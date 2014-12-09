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

import com.jhu.socialnetworking.dao.CompletedCourseDAO;

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
		
		response.getWriter().println("-----Test CompletedCourses Insert-----");
		CompletedCourseDAO ccDAO = (CompletedCourseDAO) context.getBean("completedCourseDAO");

		ccDAO.insert(400,2);
		ccDAO.insert(500,2);
				
		response.getWriter().println("-----Test CompletedCourses getCourseIds by studentId-----");
		
		List<Integer> completedCoursesIds = null;
		completedCoursesIds = ccDAO.getCompletedCourseIdsByStudentId(2);

		for (Integer courseId : completedCoursesIds) {
			
			response.getWriter().println("course id: " + courseId);
			
		}
		
	}
}