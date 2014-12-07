package com.jhu.socialnetworking.dao;

import java.util.List;

import com.jhu.socialnetworking.model.ProfessorCourse;

public interface ProfessorCourseDAO {

	public void insert(ProfessorCourse professorCourse);
	
	public List<Integer> getProfessorIdsByCourseId(int courseId);
	
}
