package com.jhu.socialnetworking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jhu.socialnetworking.dao.StudentDAO;
import com.jhu.socialnetworking.model.Student;

public class JdbcStudentDAO implements StudentDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insert(Student student) {

		initializeDatabase();
		
		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();
			
			sql = "INSERT INTO Student(first_name, last_name) VALUES ('Chris', 'Karlen')";
			ps = conn.prepareStatement(sql);
			ps.execute();
			
			sql = "INSERT INTO Student(first_name, last_name) VALUES ('Arthur', 'Tucker')";
			ps = conn.prepareStatement(sql);
			ps.execute();
			
			sql = "INSERT INTO Student(first_name, last_name) VALUES ('Nathan', 'Shih')";
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

	public List<Student> getAllStudents() {

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
				
				String studentName = rs.getString("first_name") + ":"
						+ rs.getString("last_name");
				
				student.setName(studentName);
				
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

	private void initializeDatabase() {

		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			sql = "CREATE TABLE Student (first_name varchar(10), last_name varchar(10))";
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.execute();

		} catch (SQLException e) {
			// throw new RuntimeException(e);
		}
	}
}