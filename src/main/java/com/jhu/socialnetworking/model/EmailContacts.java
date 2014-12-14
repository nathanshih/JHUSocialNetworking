package com.jhu.socialnetworking.model;

import java.util.ArrayList;

/**
 * The emailContact model object contain all the email contacts for a student.
 * 
 * @author Nathan Shih
 * @since Dec 6, 2014
 */
public class EmailContacts {

	private ArrayList<String> emails; // emails of connected students

	public EmailContacts() {
		emails = new ArrayList<String>();
	}
	
	public EmailContacts(ArrayList<String> emails) {
		setEmailContacts(emails);
	}
	
	public ArrayList<String> getEmailContacts() {
		return emails;
	}

	public void setEmailContacts(ArrayList<String> emails) {
		this.emails = emails;
	}
	
	public void addEmailContact(String email) {
		this.emails.add(email);
	}
	
	public void removeEmailContact(String emails) {
		this.emails.remove(emails);
	}
}
