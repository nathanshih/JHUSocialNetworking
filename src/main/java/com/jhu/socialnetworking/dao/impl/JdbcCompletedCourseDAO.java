package com.jhu.socialnetworking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jhu.socialnetworking.dao.CompletedCourseDAO;
import com.jhu.socialnetworking.database.InitializeDatabase;
import com.jhu.socialnetworking.model.CompletedCourse;
import com.jhu.socialnetworking.model.Professor;

public class JdbcCompletedCourseDAO implements CompletedCourseDAO {

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

	public CompletedCourse insert(CompletedCourse completedCourse) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CompletedCourse cc = null;

		try {

			conn = dataSource.getConnection();

			sql = String
					.format("INSERT INTO CompletedCourse(completed_course_id, course_id, student_id) VALUES (NULL, '%s', '%s')",
							completedCourse.getCourseId(), completedCourse.getStudentId());
			ps = conn.prepareStatement(sql);
			ps.execute();
			
			sql = String.format("SELECT * FROM CompletedCourse WHERE course_id='%s' AND student_id='%s'",
					completedCourse.getCourseId(), completedCourse.getStudentId());
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			cc = new CompletedCourse();
			cc.setCompletedCourseId(rs.getInt("completed_course_id"));
			cc.setCourseId(rs.getInt("course_id"));
			cc.setStudentId(rs.getInt("student_id"));
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
		
		return cc;
		
	}

	public List<Integer> getCompletedCourseIdsByStudentId(int studentID) {
	
		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		List<Integer> completedCoursesIdList = new ArrayList();
		
		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Professor professor = null;

		try {

			conn = dataSource.getConnection();
			sql = String.format("SELECT * FROM CompletedCourse WHERE student_id='%s'",
					studentID);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				completedCoursesIdList.add(rs.getInt("course_id"));
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

		return completedCoursesIdList;

	}

}
