package com.jhu.socialnetworking.dao;

import java.util.List;

import com.jhu.socialnetworking.model.Professor;

public interface ProfessorDAO {

		public void insert(Professor professor);
		
		public void remove(Professor professor);

		public List<Professor> getAllProfessors();
		
		public Professor getProfessorById(int professorId);
	
}
