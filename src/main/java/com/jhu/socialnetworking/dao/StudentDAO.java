package com.jhu.socialnetworking.dao;

import java.util.List;

import com.jhu.socialnetworking.model.Student;

/**
 * Interface implemented to persist Student objects
 */
public interface StudentDAO {

	public Student insert(Student student);
	
	public void remove(String studentId);
	
	public Student update(Student student);

	public List<Student> getAllStudents();
	
	public Student getStudentByStudentId(String studentId);

}