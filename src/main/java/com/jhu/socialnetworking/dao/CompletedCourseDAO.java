package com.jhu.socialnetworking.dao;

import java.util.List;

import com.jhu.socialnetworking.model.CompletedCourse;

public interface CompletedCourseDAO {

	public CompletedCourse insert(CompletedCourse completedCourse);
	
	List<Integer> getCompletedCourseIdsByStudentId(int studentID);
	
}
