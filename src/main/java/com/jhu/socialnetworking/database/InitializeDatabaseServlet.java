package com.jhu.socialnetworking.database;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jhu.socialnetworking.dao.ProfessorDAO;
import com.jhu.socialnetworking.model.Professor;
import com.jhu.socialnetworking.dao.CourseDAO;
import com.jhu.socialnetworking.model.Course;
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

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Module.xml");

		response.getWriter().println("-----Test Professor Insert-----");
		ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");
		
		Professor professor = new Professor();
		professor.setName("Charles Kingsfield");
		
		Professor insertedProfessor = professorDAO.insert(professor);
		
		response.getWriter().println("professor id:\t\t" + insertedProfessor.getProfessorId());
		response.getWriter().println("professor name:\t\t" + insertedProfessor.getName());

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
		
		response.getWriter().println("\n-----Test Course Update-----");
		insertedCourse.setCourseName("Java Programming");
		insertedCourse.setDescription("Programming in Java");
		insertedCourse.setDiscipline("Information Systems");
		insertedCourse.setUsersCompleted(500);
		insertedCourse.setUsersCheckedOut(75);

		insertedCourse = courseDAO.update(insertedCourse);
		response.getWriter().println("course id:\t\t" + insertedCourse.getCourseId());
		response.getWriter().println("course name:\t\t" + insertedCourse.getCourseName());
		response.getWriter().println("course email:\t\t" + insertedCourse.getDescription());
		response.getWriter().println("course discipline:\t" + insertedCourse.getDiscipline());
		response.getWriter().println("course usersCompleted:\t" + insertedCourse.getUsersCompleted());
		response.getWriter().println("course usersCheckedOut:\t" + insertedCourse.getUsersCheckedOut());

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
		
		response.getWriter().println("\n-----Test Student Update-----");
		insertedStudent.setName("Jay Culter");
		insertedStudent.setEmail("jay@bears.com");
		insertedStudent.setPassword("packers");
		insertedStudent.setDiscipline("Losing");
		
		insertedStudent = studentDAO.update(insertedStudent);
		response.getWriter().println("student id:\t\t" + insertedStudent.getId());
		response.getWriter().println("student name:\t\t" + insertedStudent.getName());
		response.getWriter().println("student email:\t\t" + insertedStudent.getEmail());
		response.getWriter().println("student password:\t" + insertedStudent.getPassword());
		response.getWriter().println("student discipline:\t" + insertedStudent.getDiscipline());
		
		response.getWriter().println("\n-----Test getStudentByStudentId-----");
		student = studentDAO.getStudentByStudentId(insertedStudent.getId());
		response.getWriter().println("student id:\t\t" + student.getId());
		response.getWriter().println("student name:\t\t" + student.getName());
		response.getWriter().println("student email:\t\t" + student.getEmail());
		response.getWriter().println("student password:\t" + student.getPassword());
		response.getWriter().println("student discipline:\t" + student.getDiscipline());
	}
}