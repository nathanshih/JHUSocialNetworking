package com.jhu.socialnetworking.dao;

import java.util.List;

import com.jhu.socialnetworking.model.Student;

/**
 * Interface implemented to persist Student objects
 */
public interface StudentDAO {

	public void insert(Student student);
	
	public void remove(Student student);

	public List<Student> getAllStudents();

}