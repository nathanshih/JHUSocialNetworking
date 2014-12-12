package com.jhu.socialnetworking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jhu.socialnetworking.dao.StudentDAO;
import com.jhu.socialnetworking.database.InitializeDatabase;
import com.jhu.socialnetworking.model.Student;

/**
 * Provides the implementation logic to persist a Student object
 */
public class JdbcStudentDAO implements StudentDAO {

	/**
	 * The datasource used to perist student objects
	 */
	private DataSource dataSource;

	/**
	 * Sets the datasource when the bean is instantiated
	 * 
	 * @param dataSource
	 *            the datasource used to perist student objects
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Persists a student in the database
	 */
	@Override
	public Student insert(Student student) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student studentObj = null;

		try {

			conn = dataSource.getConnection();

			// Insert the new student
			sql = String
					.format("INSERT INTO Student(student_id, name, email, password, discipline) VALUES (NULL, '%s', '%s', '%s', '%s')",
							student.getName(), student.getEmail(),
							student.getPassword(), student.getDiscipline());
			ps = conn.prepareStatement(sql);
			ps.execute();

			// Return the new student from the database
			sql = String
					.format("SELECT * FROM Student WHERE name='%s' AND email='%s' AND password='%s' AND discipline='%s'",
							student.getName(), student.getEmail(),
							student.getPassword(), student.getDiscipline());
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			rs.next();
			studentObj = new Student();
			studentObj.setId(Integer.toString(rs.getInt("student_id")));
			studentObj.setName(rs.getString("name"));
			studentObj.setEmail(rs.getString("email"));
			studentObj.setPassword(rs.getString("password"));
			studentObj.setDiscipline(rs.getString("discipline"));

			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return studentObj;
	}

	/**
	 * Removes a student from the database based on student id
	 */
	@Override
	public void remove(String studentId) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();

			sql = String.format("DELETE FROM STUDENT WHERE student_id='%s'",
					studentId);
			ps = conn.prepareStatement(sql);
			ps.execute();

			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	/**
	 * Updates a student from the database based on student id
	 */
	@Override
	public Student update(Student student) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student studentObj = null;

		try {

			conn = dataSource.getConnection();

			sql = String
					.format("UPDATE Student SET name='%s', email='%s', password='%s', discipline='%s' WHERE student_id='%s'",
							student.getName(), student.getEmail(),
							student.getPassword(), student.getDiscipline(),
							student.getId());
			ps = conn.prepareStatement(sql);
			ps.execute();

			// Return the updated student from the database
			sql = String.format("SELECT * FROM Student WHERE student_id='%s'",
					student.getId());
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			rs.next();
			studentObj = new Student();
			studentObj.setId(Integer.toString(rs.getInt("student_id")));
			studentObj.setName(rs.getString("name"));
			studentObj.setEmail(rs.getString("email"));
			studentObj.setPassword(rs.getString("password"));
			studentObj.setDiscipline(rs.getString("discipline"));

			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return studentObj;
	}

	/**
	 * Get all the students in the database
	 */
	public List<Student> getAllStudents() {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Student> studentList = null;

		try {

			conn = dataSource.getConnection();
			sql = "SELECT * FROM Student";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			studentList = new ArrayList<Student>();

			while (rs.next()) {

				Student student = new Student();

				student.setId(rs.getString("student_id"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setEmail(rs.getString("password"));
				student.setEmail(rs.getString("discipline"));

				studentList.add(student);
			}

			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return studentList;
	}

	public Student getStudentByStudentId(String studentId) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student studentObj = null;

		try {

			conn = dataSource.getConnection();

			// Return the student from the database
			sql = String.format("SELECT * FROM Student WHERE student_id='%s'",
					studentId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			rs.next();
			studentObj = new Student();
			studentObj.setId(Integer.toString(rs.getInt("student_id")));
			studentObj.setName(rs.getString("name"));
			studentObj.setEmail(rs.getString("email"));
			studentObj.setPassword(rs.getString("password"));
			studentObj.setDiscipline(rs.getString("discipline"));

			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return studentObj;
	}
}