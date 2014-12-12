package com.jhu.socialnetworking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jhu.socialnetworking.dao.ProfessorDAO;
import com.jhu.socialnetworking.database.InitializeDatabase;
import com.jhu.socialnetworking.model.Professor;

public class JdbcProfessorDAO implements ProfessorDAO {

	/**
	 * The datasource used to perist professor objects
	 */
	private DataSource dataSource;

	/**
	 * Sets the datasource when the bean is instantiated
	 * 
	 * @param dataSource
	 *            the datasource used to perist professor objects
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Professor insert(Professor professor) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Professor professorObj = null;

		try {

			conn = dataSource.getConnection();

			sql = String
					.format("INSERT INTO Professor(professor_id, name) VALUES (NULL, '%s')",
							professor.getName());
			ps = conn.prepareStatement(sql);
			ps.execute();

			// Return the new professor from the database
			sql = String
					.format("SELECT * FROM Professor WHERE name='%s'",
							professor.getName());
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			rs.next();
			professorObj = new Professor();
			professorObj.setProfessorId(rs.getInt("professor_id"));
			professorObj.setName(rs.getString("name"));

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
		
		return professorObj;

	}

	public void remove(Professor professor) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();

			sql = String.format(
					"DELETE FROM PROFESSOR WHERE professor_id='%s'",
					professor.getProfessorId());
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

	public List<Professor> getAllProfessors() {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Professor> professorList = null;

		try {

			conn = dataSource.getConnection();
			sql = "SELECT * FROM Professor";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			professorList = new ArrayList<Professor>();

			while (rs.next()) {

				Professor professor = new Professor();

				professor.setProfessorId((Integer.parseInt(rs
						.getString("professor_id"))));
				professor.setName(rs.getString("name"));

				professorList.add(professor);
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

		return professorList;

	}

	public Professor getProfessorById(int professorId) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Professor professor = null;

		try {

			conn = dataSource.getConnection();
			sql = String.format(
					"SELECT * FROM PROFESSOR WHERE professor_id='%s'",
					professorId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			professor = new Professor();
			rs.next();
			professor.setProfessorId(Integer.parseInt(rs
					.getString("professor_id")));
			professor.setName(rs.getString("name"));

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

		return professor;

	}

}
