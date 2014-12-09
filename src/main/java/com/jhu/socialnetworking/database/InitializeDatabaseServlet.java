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

import com.jhu.socialnetworking.dao.CartDAO;
import com.jhu.socialnetworking.dao.ProfessorCourseDAO;

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

		response.getWriter().println("-----Test Cart Insert-----");
		CartDAO ctDAO = (CartDAO) context.getBean("cartDAO");
		ctDAO.insert(100, 200);
		ctDAO.insert(300, 200);
		
		response.getWriter().println("-----Test CartTuple getCourseIdsByStudentId-----");
		List<Integer> completedCoursesIds = null;
		completedCoursesIds = null;
		completedCoursesIds = ctDAO.getCourseIdsByStudentId(200);

		/*
		for (Integer courseId : completedCoursesIds) {

			response.getWriter().println("course id: " + courseId);

			ccDAO.insert(400,2);
			ccDAO.insert(500,2);
				
			response.getWriter().println("-----Test CompletedCourses getCourseIds by studentId-----");
			
			List<Integer> completedCoursesIds = null;
			completedCoursesIds = ccDAO.getCompletedCourseIdsByStudentId(2);
		}
		*/
		
		response.getWriter().println("-----Test Cart Remove-----");
		ctDAO.remove(100, 200);
		
		completedCoursesIds = null;
		completedCoursesIds = ctDAO.getCourseIdsByStudentId(200);

		for (Integer courseId : completedCoursesIds) {

			response.getWriter().println("course id: " + courseId);

		}

		response.getWriter().println("-----Test ProfessorCourse insert-----");
		ProfessorCourseDAO pcDAO = (ProfessorCourseDAO) context.getBean("professorCourseDAO");

		pcDAO.insert(2000, 100);
		pcDAO.insert(2000, 200);
		
		response.getWriter().println("-----Test ProfessorCourse getProfessorIdsByCourseId-----");
		List<Integer> professorIdList = null;
		professorIdList = pcDAO.getProfessorIdsByCourseId(2000);
		
		for (Integer professorId : professorIdList) {
			
			response.getWriter().println("professor id: " + professorId);
			
		}
	}
}