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

		// response.getWriter().println("-----Testing StudentDAO-----");
		// testStudentDAO(request, response);
		// response.getWriter().println("-----Testing CourseDAO-----");
		// testCourseDAO(request, response);
		response.getWriter().println("-----Testing ProfessorDAO-----");
		testProfessorDAO(request, response);
		response.getWriter().println(
				"-----Testing EvaluationDAO,RegistrationDAO-----");
		testEvaluationRegistrationDAOs(request, response);

	}

	private void testEvaluationRegistrationDAOs(HttpServletRequest request,
			HttpServletResponse response) {

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

		// Get a professor DAO to add and remove students
		ProfessorDAO professorDAO = (ProfessorDAO) context
				.getBean("professorDAO");

//		// Add some professors
//		Professor professor1 = new Professor();
//		professor1.setName("John Sheppard");
//		professorDAO.insert(professor1);
//
//		Professor professor2 = new Professor();
//		professor2.setName("Kiran Chittargi");
//		professorDAO.insert(professor2);

		// Get a course DAO to add and remove courses
		CourseDAO courseDAO = (CourseDAO) context.getBean("courseDAO");

		// Add some courses
		Course course1 = new Course();
		course1.setCourseId("605.421");
		course1.setCourseName("Foundations of Algorithms");
		course1.setCourseDescription("This follow-on course to data structures (e.g., 605.202) provides a survey of computer algorithms, examines fundamental techniques in algorithm design and analysis, and develops problem-solving skills required in all programs of study involving computer science.");
		courseDAO.insert(course1);

		Course course2 = new Course();
		course2.setCourseId("605.411");
		course2.setCourseName("Foundations of Computer Architecture");
		course2.setCourseDescription("This course provides a detailed examination of the internal structure and operation of modern computer systems.");
		courseDAO.insert(course2);

		Course course3 = new Course();
		course3.setCourseId("605.747");
		course3.setCourseName("Evolutionary Computation");
		course3.setCourseDescription("This course explores how principles from theories of evolution and natural selection can be used to construct machines that exhibit nontrivial behavior..");
		courseDAO.insert(course3);

		// Get a registration DAO to add and remove registrations
		RegistrationDAO registrationDAO = (RegistrationDAO) context
				.getBean("registrationDAO");

		// Add some registrations
		Registration registration1 = new Registration();
		registration1.setCourseId(course1.getCourseId());
		registration1.setProfessorId(1);
		registration1.setStudentId(1);
		registration1.setSemester("Fall");
		registration1.setYear(2014);
		registrationDAO.insert(registration1);

		Registration registration2 = new Registration();
		registration2.setCourseId(course2.getCourseId());
		registration2.setProfessorId(2);
		registration2.setStudentId(2);
		registration2.setSemester("Spring");
		registration2.setYear(2013);
		registrationDAO.insert(registration2);

		Registration registration3 = new Registration();
		registration3.setCourseId(course1.getCourseId());
		registration3.setProfessorId(1);
		registration3.setStudentId(2);
		registration3.setSemester("Fall");
		registration3.setYear(2014);
		registrationDAO.insert(registration3);

		Registration registration4 = new Registration();
		registration4.setCourseId(course2.getCourseId());
		registration4.setProfessorId(2);
		registration4.setStudentId(1);
		registration4.setSemester("Spring");
		registration4.setYear(2013);
		registrationDAO.insert(registration4);

		// Get an evaluation DAO to add and remove evaluations
		EvaluationDAO evaluationDAO = (EvaluationDAO) context
				.getBean("evaluationDAO");

		// Add some evaluations
		Evaluation evaluation = new Evaluation();
		evaluation.setComments("This class was fantastic");
		evaluation.setRating("5");
		evaluation.setRegistrationId(1);
		evaluationDAO.insert(evaluation);

		evaluation = new Evaluation();
		evaluation.setComments("This class was phenomenal");
		evaluation.setRating("4");
		evaluation.setRegistrationId(2);
		evaluationDAO.insert(evaluation);

		evaluation = new Evaluation();
		evaluation.setComments("This class was amazing");
		evaluation.setRating("3");
		evaluation.setRegistrationId(3);
		evaluationDAO.insert(evaluation);

		evaluation = new Evaluation();
		evaluation.setComments("This class was incredible");
		evaluation.setRating("2");
		evaluation.setRegistrationId(4);
		evaluationDAO.insert(evaluation);

		List<Evaluation> evaluationList = evaluationDAO
				.getEvaluationsByCourseId(course1.getCourseId());

		try {
			response.getWriter().println(
					"-----getEvaluationsByCourseId(course1)-----");
			for (Evaluation evaluationObj : evaluationList) {
				response.getWriter().println(evaluationObj);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		evaluationList = evaluationDAO.getEvaluationsByProfessorId(1);
		try {
			response.getWriter().println(
					"-----getEvaluationsByProfessorId(professor1)-----");
			for (Evaluation evaluationObj : evaluationList) {
				response.getWriter().println(evaluationObj);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		evaluationList = evaluationDAO.getEvaluationsByProfessorId(2);
		try {
			response.getWriter().println(
					"-----getEvaluationsByProfessorId(professor2)-----");
			for (Evaluation evaluationObj : evaluationList) {
				response.getWriter().println(evaluationObj);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StudentConnectionDAO studentConnectionDAO = (StudentConnectionDAO) context
				.getBean("studentConnectionDAO");

		try {
			response.getWriter().println(
					"-----insert(new StudentConnection)-----");

			studentConnectionDAO.insert(new StudentConnection(1, 2));
			studentConnectionDAO.insert(new StudentConnection(2, 1));
			studentConnectionDAO.insert(new StudentConnection(1, 3));

			response.getWriter().println(
					"-----getConnectionsByStudentId(studentId)-----");
			List<StudentConnection> list = studentConnectionDAO
					.getConnectionsByStudentId(1);

			for (StudentConnection connection : list) {
				response.getWriter().println(connection);
				studentConnectionDAO.remove(connection);
			}

			response.getWriter().println(
					"-----getConnectionsByStudentId(studentId)-----");
			List<StudentConnection> list2 = studentConnectionDAO
					.getConnectionsByStudentId(3);
			for (StudentConnection connection : list2) {
				response.getWriter().println(connection);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void testProfessorDAO(HttpServletRequest request,
			HttpServletResponse response) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Module.xml");

		// Get a professor DAO to add and remove students
		ProfessorDAO professorDAO = (ProfessorDAO) context
				.getBean("professorDAO");

		// Add some professors
		Professor professor = new Professor();
		professor.setName("John Sheppard");
		professorDAO.insert(professor);

		professor = new Professor();
		professor.setName("Kiran Chittargi");
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
			// Print out all the professors and remove each professor from the
			// database
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
		student.setName("Chris Karlen");
		studentDAO.insert(student);

		student = new Student();
		student.setName("Nathan Shih");
		studentDAO.insert(student);

		student = new Student();
		student.setName("Arthur Tucker");
		studentDAO.insert(student);

		// Get all the students from the database
		List<Student> studentList = studentDAO.getAllStudents();

		try {
			// Print out all the students and remove each student from the
			// database
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
			// Print out all the courses and remove each course from the
			// database
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