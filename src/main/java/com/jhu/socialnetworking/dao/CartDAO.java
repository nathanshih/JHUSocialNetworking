package com.jhu.socialnetworking.dao;

import java.util.List;

public interface CartDAO {

	public void insert (int course_id, int student_id);
	
	public List<Integer> getCourseIdsByStudentId(int studentId);
	
	public void remove (int course_id, int student_id);
	
}