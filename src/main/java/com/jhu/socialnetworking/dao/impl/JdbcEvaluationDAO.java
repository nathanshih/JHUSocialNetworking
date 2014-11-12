package com.jhu.socialnetworking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jhu.socialnetworking.dao.EvaluationDAO;
import com.jhu.socialnetworking.database.InitializeDatabase;
import com.jhu.socialnetworking.model.Evaluation;

public class JdbcEvaluationDAO implements EvaluationDAO {

	/**
	 * The datasource used to perist evaluation objects
	 */
	private DataSource dataSource;

	/**
	 * Sets the datasource when the bean is instantiated
	 * 
	 * @param dataSource
	 *            the datasource used to perist evaluation objects
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Evaluation evaluation) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();

			sql = String
					.format("INSERT INTO Evaluation(evaluation_id, registration_id, rating, comments) VALUES (NULL, '%s', '%s', '%s')",
							evaluation.getRegistrationId(),
							evaluation.getRating(), evaluation.getComments());
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

	public void remove(Evaluation evaluation) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();

			sql = String.format(
					"DELETE FROM EVALUATION WHERE evaluation_id='%s'",
					evaluation.getEvaluationId());
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

	public List<Evaluation> getEvaluationsByProfessorId(int professorId) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Evaluation> evaluationList = null;

		try {

			conn = dataSource.getConnection();

			sql = String
					.format("SELECT * FROM EVALUATION INNER JOIN (SELECT * FROM REGISTRATION WHERE professor_id='%s') registrations ON EVALUATION.registration_id=registrations.registration_id;",
							professorId);

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
  
			evaluationList = new ArrayList<Evaluation>();

			while (rs.next()) {

				Evaluation evaluation = new Evaluation();

				evaluation.setEvaluationId(Integer.parseInt(rs
						.getString("evaluation_id")));
				evaluation.setRegistrationId(Integer.parseInt(rs
						.getString("registration_id")));
				evaluation.setRating(rs.getString("rating"));
				evaluation.setRating(rs.getString("comments"));

				evaluationList.add(evaluation);
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

		return evaluationList;

	}

	public List<Evaluation> getEvaluationsByCourseId(String courseId) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Evaluation> evaluationList = null;

		try {

			conn = dataSource.getConnection();

			sql = String
					.format("SELECT * FROM EVALUATION INNER JOIN (SELECT * FROM REGISTRATION WHERE course_id='%s') registrations ON EVALUATION.registration_id=registrations.registration_id;",
							courseId);

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			evaluationList = new ArrayList<Evaluation>();

			while (rs.next()) {

				Evaluation evaluation = new Evaluation();

				evaluation.setEvaluationId(Integer.parseInt(rs
						.getString("evaluation_id")));
				evaluation.setRegistrationId(Integer.parseInt(rs
						.getString("registration_id")));
				evaluation.setRating(rs.getString("rating"));
				evaluation.setRating(rs.getString("comments"));

				evaluationList.add(evaluation);
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

		return evaluationList;

	}
}
