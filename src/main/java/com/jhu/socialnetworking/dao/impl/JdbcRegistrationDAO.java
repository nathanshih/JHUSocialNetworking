package com.jhu.socialnetworking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.jhu.socialnetworking.dao.RegistrationDAO;
import com.jhu.socialnetworking.database.InitializeDatabase;
import com.jhu.socialnetworking.model.Registration;

public class JdbcRegistrationDAO implements RegistrationDAO {

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

	public void insert(Registration registration) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();

			sql = String
					.format("INSERT INTO Registration(registration_id, student_id, course_id, professor_id, semester, year) VALUES (NULL, '%s', '%s', '%s', '%s', '%s')",
							registration.getStudentId(),
							registration.getCourseId(),
							registration.getProfessorId(),
							registration.getSemester(), registration.getYear());
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

	public void remove(Registration registration) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();

			sql = String.format(
					"DELETE FROM REGISTRATION WHERE registration_id='%s'",
					registration.getRegistrationId());
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
}
