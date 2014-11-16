package com.jhu.socialnetworking.dao;

import com.jhu.socialnetworking.model.Registration;

public interface RegistrationDAO {

	public void insert (Registration registration);
	
	public void remove (Registration registration);
	
	/**
	 * Only implement if needed
	 */
	// public List<Registration> getRegistrationsByProfessorId(int professorId);
	
	// public List<Registration> getRegistrationsByCourseId(String courseId);
	
	// public List<Registration> getRegistrationsByStudentId(int studentId);
	
}
