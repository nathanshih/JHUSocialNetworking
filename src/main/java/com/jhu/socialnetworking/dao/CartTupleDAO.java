package com.jhu.socialnetworking.dao;

import java.util.List;

import com.jhu.socialnetworking.model.CartTuple;

public interface CartTupleDAO {

	public void insert (CartTuple cartTuple);
	
	public List<Integer> getCourseIdsByStudentId(int studentId);
	
}
