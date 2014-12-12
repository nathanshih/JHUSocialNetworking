package com.jhu.socialnetworking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jhu.socialnetworking.dao.CourseDAO;
import com.jhu.socialnetworking.database.InitializeDatabase;
import com.jhu.socialnetworking.model.Course;

/**
 * Provides the implementation logic to persist a Course object
 */
public class JdbcCourseDAO implements CourseDAO {

	/**
	 * The datasource used to perist course objects
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
	 * Persists a course in the database
	 */
	@Override
	public Course insert(Course course) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Course courseObj = null;

		try {

			conn = dataSource.getConnection();

			sql = String
					.format("INSERT INTO Course(course_id, course_name, description, discipline, usersCompleted, usersCheckedOut) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
							course.getCourseId(), course.getCourseName(),
							course.getDescription(), course.getDiscipline(),
							course.getUsersCompleted(),
							course.getUsersCheckedOut());
			ps = conn.prepareStatement(sql);
			ps.execute();

			// Return the new course from the database
			sql = String
					.format("SELECT * FROM Course WHERE course_id='%s' AND course_name='%s' AND description='%s' AND discipline='%s' AND usersCompleted='%s' AND usersCheckedOut='%s'",
							course.getCourseId(), course.getCourseName(),
							course.getDescription(), course.getDiscipline(),
							course.getUsersCompleted(),
							course.getUsersCheckedOut());
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			rs.next();
			courseObj = new Course();
			courseObj.setCourseId(rs.getString("course_id"));
			courseObj.setCourseName(rs.getString("course_name"));
			courseObj.setDescription(rs.getString("description"));
			courseObj.setDiscipline(rs.getString("discipline"));
			courseObj.setUsersCompleted(rs.getInt("usersCompleted"));
			courseObj.setUsersCheckedOut(rs.getInt("usersCheckedOut"));

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

		return courseObj;
	}

	/**
	 * Removes a course from the database based on course id
	 */
	@Override
	public void remove(Course course) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();

			sql = String.format("DELETE FROM Course WHERE course_id='%s'",
					course.getCourseId());
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
	 * Get all the courses in the database
	 */
	public List<Course> getAllCourses() {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Course> courseList = null;

		try {

			conn = dataSource.getConnection();
			sql = "SELECT * FROM Course";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			courseList = new ArrayList<Course>();

			while (rs.next()) {

				Course course = new Course();

				course.setCourseId(rs.getString("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setDiscipline(rs.getString("description"));
				course.setDiscipline(rs.getString("discipline"));
				course.setUsersCompleted(rs.getInt("usersCompleted"));
				course.setUsersCheckedOut(rs.getInt("usersCheckedOut"));

				courseList.add(course);
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

		return courseList;
	}

	/**
	 * Get a course by ID from the database
	 */
	public Course getCourseById(String courseId) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Course course = null;

		try {

			conn = dataSource.getConnection();
			sql = String.format("SELECT * FROM Course WHERE course_id='%s'",
					courseId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			course = new Course();
			rs.next();
			course.setCourseId(rs.getString("course_id"));
			course.setCourseName(rs.getString("course_name"));
			course.setDiscipline(rs.getString("description"));
			course.setDiscipline(rs.getString("discipline"));
			course.setUsersCompleted(rs.getInt("usersCompleted"));
			course.setUsersCheckedOut(rs.getInt("usersCheckedOut"));

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

		return course;
	}

	/**
	 * Updates a course from the database based on course id
	 */
	@Override
	public Course update(Course course) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Course courseObj = null;

		try {

			conn = dataSource.getConnection();

			sql = String.format("UPDATE Course SET " + "course_name='%s', "
					+ "description='%s', " + "discipline='%s', "
					+ "usersCompleted='%s', "
					+ "usersCheckedOut='%s' WHERE course_id='%s'",
					course.getCourseName(), course.getDescription(),
					course.getDiscipline(), course.getUsersCompleted(),
					course.getUsersCheckedOut(), course.getCourseId());
			ps = conn.prepareStatement(sql);
			ps.execute();

			// Return the updated course from the database
			sql = String
					.format("SELECT * FROM Course WHERE course_id='%s'",
							course.getCourseId());
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			rs.next();
			courseObj = new Course();
			courseObj.setCourseId(rs.getString("course_id"));
			courseObj.setCourseName(rs.getString("course_name"));
			courseObj.setDescription(rs.getString("description"));
			courseObj.setDiscipline(rs.getString("discipline"));
			courseObj.setUsersCompleted(rs.getInt("usersCompleted"));
			courseObj.setUsersCheckedOut(rs.getInt("usersCheckedOut"));

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
		
		return courseObj;
	}
}
