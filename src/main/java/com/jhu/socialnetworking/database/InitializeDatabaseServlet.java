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

import com.jhu.socialnetworking.dao.CartTupleDAO;
import com.jhu.socialnetworking.dao.CompletedCourseDAO;
import com.jhu.socialnetworking.dao.CourseDAO;
import com.jhu.socialnetworking.dao.StudentDAO;
import com.jhu.socialnetworking.model.CartTuple;
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

		response.getWriter().println("-----Test CompletedCourses Insert-----");
		CompletedCourseDAO ccDAO = (CompletedCourseDAO) context
				.getBean("completedCourseDAO");

		CompletedCourse cc = new CompletedCourse();
		cc.setStudentId(0);
		cc.setCourseId(3);
		ccDAO.insert(cc);

		cc = new CompletedCourse();
		cc.setStudentId(0);
		cc.setCourseId(4);
		ccDAO.insert(cc);

		response.getWriter().println(
				"-----Test CompletedCourses getCourseIds by studentId-----");

		List<Integer> completedCoursesIds = null;
		completedCoursesIds = ccDAO.getCompletedCourseIdsByStudentId(0);

		for (Integer courseId : completedCoursesIds) {

			response.getWriter().println("course id: " + courseId);

		}

		response.getWriter().println("-----Test CartTuple Insert-----");
		CartTupleDAO ctDAO = (CartTupleDAO) context.getBean("cartTupleDAO");

		CartTuple ct = new CartTuple();
		ct.setCourseId(100);
		ct.setStudentId(200);
		ctDAO.insert(ct);

		ct = new CartTuple();
		ct.setCourseId(300);
		ct.setStudentId(200);
		ctDAO.insert(ct);

		response.getWriter().println(
				"-----Test CartTuple getCourseIdsByStudentId-----");

		completedCoursesIds = null;
		completedCoursesIds = ctDAO.getCourseIdsByStudentId(200);

		for (Integer courseId : completedCoursesIds) {

			response.getWriter().println("course id: " + courseId);

		}

	}
}