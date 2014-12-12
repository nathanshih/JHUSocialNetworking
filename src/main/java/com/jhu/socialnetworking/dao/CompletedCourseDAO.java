package com.jhu.socialnetworking.dao;

import java.util.List;

public interface CompletedCourseDAO {

	public void insert(String courseId, int studentId);
	
	List<String> getCompletedCourseIdsByStudentId(int studentId);
	
}