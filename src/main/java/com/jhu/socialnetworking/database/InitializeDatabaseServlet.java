package com.jhu.socialnetworking.database;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jhu.socialnetworking.dao.CourseDAO;
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

		response.getWriter().println("-----Test Course Insert-----");
		CourseDAO courseDAO = (CourseDAO) context.getBean("courseDAO");

		Course course = new Course();
		course.setCourseId("711.911");
		course.setCourseName("Python Programming");
		course.setDescription("Programming in Python");
		course.setDiscipline("Computer Science");
		course.setUsersCompleted(7);
		course.setUsersCheckedOut(20);

		Course insertedCourse = courseDAO.insert(course);
		response.getWriter().println("courseId:\t\t" + insertedCourse.getCourseId());
		response.getWriter().println("courseName:\t\t" + insertedCourse.getCourseName());
		response.getWriter().println("description:\t\t" + insertedCourse.getDescription());
		response.getWriter().println("discipline:\t\t" + insertedCourse.getDiscipline());
		response.getWriter().println("usersCompleted:\t\t" + insertedCourse.getUsersCompleted());
		response.getWriter().println("usersCheckedOut:\t" + insertedCourse.getUsersCheckedOut());
				
	}
}