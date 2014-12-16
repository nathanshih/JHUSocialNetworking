package com.jhu.socialnetworking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jhu.socialnetworking.dao.StudentConnectionDAO;
import com.jhu.socialnetworking.database.InitializeDatabase;
import com.jhu.socialnetworking.model.StudentConnection;

public class JdbcStudentConnectionDAO implements StudentConnectionDAO {

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
	 * Persists a student connection in the database
	 */
	@Override
	public StudentConnection insert(StudentConnection studentConnection) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StudentConnection sc = null;
		
		try {

			conn = dataSource.getConnection();

			sql = String
					.format("INSERT INTO StudentConnection(connection_id, student_id, contact_id) VALUES (NULL, '%s', '%s')",
							studentConnection.getStudentId(),
							studentConnection.getContactId());
			ps = conn.prepareStatement(sql);
			ps.execute();
			
			sql = String
					.format("SELECT * FROM StudentConnection WHERE student_id='%s' and contact_id='%s'",
							studentConnection.getStudentId(),
							studentConnection.getContactId());
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				sc = new StudentConnection();
				sc.setConnectionId(Integer.parseInt(rs.getString("connection_id")));
				sc.setStudentId(Integer.parseInt(rs.getString("student_id")));
				sc.setContactId(Integer.parseInt(rs.getString("contact_id")));
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
		
		return sc;
	}

	/**
	 * Removes a student connection from the database based on course id
	 */
	@Override
	public void remove(StudentConnection studentConnection) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();

			sql = String.format(
					"DELETE FROM StudentConnection WHERE connection_id='%s'",
					studentConnection.getConnectionId());
			
			System.out.println(studentConnection.getConnectionId());
			
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

	public List<StudentConnection> getConnectionsByStudentId(int studentId) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<StudentConnection> studentConnectionList = null;

		try {

			conn = dataSource.getConnection();

			sql = String
					.format("SELECT * FROM StudentConnection WHERE student_id='%s';",
							studentId);

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			studentConnectionList = new ArrayList<StudentConnection>();

			while (rs.next()) {

				StudentConnection studentConnection = new StudentConnection();

				studentConnection.setConnectionId(Integer.parseInt(rs
						.getString("connection_id")));
				
				studentConnection.setStudentId(Integer.parseInt(rs
						.getString("student_id")));

				studentConnection.setContactId(Integer.parseInt(rs
						.getString("contact_id")));

				studentConnectionList.add(studentConnection);
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

		return removeDuplicateStudentConnections(studentConnectionList);
	}

	/**
	 * Eliminates any duplicate connections
	 * 
	 * @param inputList
	 * @return
	 */
	List<StudentConnection> removeDuplicateStudentConnections(
			List<StudentConnection> inputList) {

		for (int i = 0; i < inputList.size(); i++) {
			for (int j = 0; j < inputList.size(); j++) {

				if (inputList.get(i).getStudentId() == inputList.get(j)
						.getContactId()
						&& inputList.get(j).getStudentId() == inputList
								.get(i).getContactId()) {
					inputList.remove(i);
				}
			}
		}
		return inputList;
	}
}