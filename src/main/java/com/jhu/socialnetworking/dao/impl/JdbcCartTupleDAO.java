package com.jhu.socialnetworking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jhu.socialnetworking.dao.CartTupleDAO;
import com.jhu.socialnetworking.database.InitializeDatabase;
import com.jhu.socialnetworking.model.CartTuple;

public class JdbcCartTupleDAO implements CartTupleDAO {

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

	@Override
	public void insert(CartTuple cartTuple) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();

			sql = String
					.format("INSERT INTO CartTuple (cart_id, course_id, student_id) VALUES (NULL, '%s', '%s')",
							cartTuple.getCourseId(), cartTuple.getStudentId());
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

	public List<Integer> getCourseIdsByStudentId(int studentId) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Integer> courseIdList = new ArrayList<Integer>();

		try {

			conn = dataSource.getConnection();
			sql = String.format(
					"SELECT * FROM CartTuple WHERE student_id='%s'", studentId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				courseIdList.add(rs.getInt("course_id"));

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

		return courseIdList;

	}
}
