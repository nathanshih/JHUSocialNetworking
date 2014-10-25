package com.jhu.socialnetworking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.jhu.socialnetworking.dao.StudentDAO;
import com.jhu.socialnetworking.database.InitializeDatabase;
import com.jhu.socialnetworking.model.Student;

public class JdbcStudentDAO implements StudentDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		
	@Override
	public void insert(Student student) {

		// Ensure datasource is initialized with InitializeDatabase singleton
		InitializeDatabase.getInstance().initializeDatabase(dataSource);
		
		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();
			
			sql = String.format("INSERT INTO Student(student_ID, first_name, last_name) VALUES (NULL, '%s', '%s')", student.getFirstName(), student.getLastName());
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
				
				student.setStudentId(Integer.parseInt(rs.getString("student_id")));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				
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
}