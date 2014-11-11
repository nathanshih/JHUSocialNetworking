package com.jhu.socialnetworking.model;

/**
 * Model class for the evaluation object.
 *
 * @author Nathan Shih
 * @since Nov 1, 2014
 */
public class Evaluation {

	private Integer evaluationId;
	private Integer registrationId;
	private Integer rating;
	private String comments;
	
	public Integer getEvaluationId() {
		return evaluationId;
	}
	
	public void setEvaluationId(Integer evaluationId) {
		this.evaluationId = evaluationId;
	}
	
	public Integer getRegistrationId() {
		return registrationId;
	}
	
	public void setRegistrationId(Integer registrationId) {
		this.registrationId = registrationId;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}	
}
