package com.jhu.socialnetworking.model;

import java.util.ArrayList;

/**
 * The cart model object.
 *
 * @author Nathan Shih
 * @since Dec 1, 2014
 */
public class Cart {

	private ArrayList<CourseLight> checkedOutCourses;
	
	public Cart() {
		checkedOutCourses = new ArrayList<CourseLight>();
	}

	public ArrayList<CourseLight> getCheckedOutCourses() {
		return checkedOutCourses;
	}

	public void setCheckedOutCourses(ArrayList<CourseLight> checkedOutCourses) {
		this.checkedOutCourses = checkedOutCourses;
	}
	
	public void addToCart(CourseLight courseLight) {
		checkedOutCourses.add(courseLight);
	}
	
	public void removeFromCart(CourseLight courseLight) {
		checkedOutCourses.remove(courseLight);
	}
}
