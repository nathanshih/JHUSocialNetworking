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
import com.jhu.socialnetworking.dao.CourseDAO;
import com.jhu.socialnetworking.dao.StudentDAO;
import com.jhu.socialnetworking.model.CompletedCourse;
import com.jhu.socialnetworking.model.Course;
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
		
		response.getWriter().println("-----Test CompletedCourses Insert-----");
		CompletedCourseDAO ccDAO = (CompletedCourseDAO) context.getBean("completedCourseDAO");

		CompletedCourse cc = new CompletedCourse();
		cc.setStudentId(0);
		cc.setCourseId(3);
		CompletedCourse returnedCC = ccDAO.insert(cc);
		response.getWriter().print("printing the CompletedCourse inserted into the database: ");
		response.getWriter().print("returnedCC.getCompletedCourseId(): " + returnedCC.getCompletedCourseId());
		response.getWriter().print(",    returnedCC.getCourseId(): " + returnedCC.getCourseId());
		response.getWriter().println(",    returnedCC.getStudentId(): " + returnedCC.getStudentId());
		
		cc = new CompletedCourse();
		cc.setStudentId(0);
		cc.setCourseId(4);
		returnedCC = ccDAO.insert(cc);
		response.getWriter().print("printing the CompletedCourse inserted into the database: ");
		response.getWriter().print("returnedCC.getCompletedCourseId(): " + returnedCC.getCompletedCourseId());
		response.getWriter().print(",    returnedCC.getCourseId(): " + returnedCC.getCourseId());
		response.getWriter().println(",    returnedCC.getStudentId(): " + returnedCC.getStudentId());
		
		response.getWriter().println();
		response.getWriter().println("-----Test CompletedCourses getCourseIds by studentId-----");
		
		List<Integer> completedCoursesIds = null;
		completedCoursesIds = ccDAO.getCompletedCourseIdsByStudentId(0);

		for (Integer courseId : completedCoursesIds) {
			
			response.getWriter().println("course id: " + courseId);
			
		}
		
	}
}