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
import com.jhu.socialnetworking.dao.ProfessorDAO;
import com.jhu.socialnetworking.dao.StudentDAO;
import com.jhu.socialnetworking.model.Course;
import com.jhu.socialnetworking.model.Professor;
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

		response.getWriter().println("-----Testing StudentDAO-----");
		testStudentDAO(request, response);
		response.getWriter().println("-----Testing CourseDAO-----");
		testCourseDAO(request, response);
		response.getWriter().println("-----Testing ProfessorDAO-----");
		testProfessorDAO(request, response);

	}

	private void testProfessorDAO(HttpServletRequest request,
			HttpServletResponse response) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Module.xml");

		// Get a professor DAO to add and remove students
		ProfessorDAO professorDAO = (ProfessorDAO) context.getBean("professorDAO");

		// Add some professor
		Professor professor = new Professor();
		professor.setFirstName("John");
		professor.setLastName("Sheppard");
		professorDAO.insert(professor);
		
		professor = new Professor();
		professor.setFirstName("Kiran");
		professor.setLastName("Chittargi");
		professorDAO.insert(professor);

		// Get a professor from the database by ID
		professor = professorDAO.getProfessorById(1);
		try {
			response.getWriter().print("Retrieved professor with id 1:  ");
			response.getWriter().println(professor);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Get all the professors from the database
		List<Professor> professorList = professorDAO.getAllProfessors();

		try {
			// Print out all the professors and remove each professor from the database
			for (Professor professorObj : professorList) {
				response.getWriter().println(professorObj);
				professorDAO.remove(professorObj);
			}

			// Print confirmation that all students were removed
			professorList = professorDAO.getAllProfessors();
			if (professorList.size() == 0)
				response.getWriter().println("No professors in database");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void testStudentDAO(HttpServletRequest request,
			HttpServletResponse response) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Module.xml");

		// Get a student DAO to add and remove students
		StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");

		// Add some students
		Student student = new Student();
		student.setFirstName("Chris");
		student.setLastName("Karlen");
		studentDAO.insert(student);

		student = new Student();
		student.setFirstName("Nathan");
		student.setLastName("Shih");
		studentDAO.insert(student);

		student = new Student();
		student.setFirstName("Arthur");
		student.setLastName("Tucker");
		studentDAO.insert(student);
		
		// Get all the students from the database
		List<Student> studentList = studentDAO.getAllStudents();

		try {
			// Print out all the students and remove each student from the database
			for (Student studentObj : studentList) {
				response.getWriter().println(studentObj);
				studentDAO.remove(studentObj);
			}

			// Print confirmation that all students were removed
			studentList = studentDAO.getAllStudents();
			if (studentList.size() == 0)
				response.getWriter().println("No students in database");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void testCourseDAO(HttpServletRequest request,
			HttpServletResponse response) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Module.xml");

		// Get a course DAO to add and remove courses
		CourseDAO courseDAO = (CourseDAO) context.getBean("courseDAO");

		// Add some courses
		Course course = new Course();
		course.setCourseId("605.421");
		course.setCourseName("Foundations of Algorithms");
		course.setCourseDescription("This follow-on course to data structures (e.g., 605.202) provides a survey of computer algorithms, examines fundamental techniques in algorithm design and analysis, and develops problem-solving skills required in all programs of study involving computer science.");
		courseDAO.insert(course);
		
		course = new Course();
		course.setCourseId("605.411");
		course.setCourseName("Foundations of Computer Architecture");
		course.setCourseDescription("This course provides a detailed examination of the internal structure and operation of modern computer systems.");
		courseDAO.insert(course);

		course = new Course();
		course.setCourseId("605.747");
		course.setCourseName("Evolutionary Computation");
		course.setCourseDescription("This course explores how principles from theories of evolution and natural selection can be used to construct machines that exhibit nontrivial behavior..");
		courseDAO.insert(course);
		
		// Get a course from the database by ID
		course = courseDAO.getCourseById("605.747");
		try {
			response.getWriter().print("Retrieved course with id 605.747: ");
			response.getWriter().println(course);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Get all the courses from the database
		List<Course> courseList = courseDAO.getAllCourses();

		try {
			// Print out all the courses and remove each course from the database
			for (Course courseObj : courseList) {
				response.getWriter().print("Retrieve and delete:  ");
				response.getWriter().println(courseObj);
				courseDAO.remove(courseObj);
			}

			// Print confirmation that all courses were removed
			courseList = courseDAO.getAllCourses();
			if (courseList.size() == 0)
				response.getWriter().println("No courses in database");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}