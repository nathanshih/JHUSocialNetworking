package com.jhu.socialnetworking.dao;

import java.util.List;

import com.jhu.socialnetworking.model.Student;

public interface StudentDAO {

	public void insert(Student student);

	public List<Student> getAllStudents();

}