package com.jhu.socialnetworking.dao;

import java.util.List;

import com.jhu.socialnetworking.model.Evaluation;

public interface EvaluationDAO {

	public void insert (Evaluation evaluation);
	
	public void remove (Evaluation evaluation);
	
	public List<Evaluation> getEvaluationsByProfessorId(int professorId);
	
	public List<Evaluation> getEvaluationsByCourseId(String courseId);
	
}
