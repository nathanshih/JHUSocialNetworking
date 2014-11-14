package com.jhu.socialnetworking.dao;

import java.util.List;

import com.jhu.socialnetworking.model.StudentConnection;

public interface StudentConnectionDAO {
	
	public void insert(StudentConnection studentConnection);
	
	public void remove(StudentConnection studentConnection);
	
	public List<StudentConnection> getConnectionsByStudentId(int studentId);

}
