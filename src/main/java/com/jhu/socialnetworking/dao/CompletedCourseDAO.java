package com.jhu.socialnetworking.dao;

import java.util.List;

public interface CompletedCourseDAO {

	public void insert(String courseId, String studentId);
	
	List<Integer> getCompletedCourseIdsByStudentId(String studentId);
	
}