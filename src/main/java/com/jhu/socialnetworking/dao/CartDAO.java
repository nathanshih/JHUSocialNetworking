package com.jhu.socialnetworking.dao;

import java.util.List;

public interface CartDAO {

	public void insert (String courseId, String studentId);
	
	public List<Integer> getCourseIdsByStudentId(String studentId);
	
	public void remove (String courseId, String studentId);
	
}