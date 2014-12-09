package com.jhu.socialnetworking.dao;

import java.util.List;

public interface ProfessorCourseDAO {

	public void insert(int courseId, int professorId);
	
	public List<Integer> getProfessorIdsByCourseId(int courseId);
	
}
