package com.jhu.socialnetworking.dao;

import java.util.List;

public interface CompletedCourseDAO {

	public void insert(int course_id, int student_id);
	
	List<Integer> getCompletedCourseIdsByStudentId(int studentID);
	
}