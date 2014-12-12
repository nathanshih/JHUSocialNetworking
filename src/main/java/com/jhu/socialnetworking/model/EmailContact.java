package com.jhu.socialnetworking.model;

import java.util.ArrayList;

/**
 * The emailContact model object contain all the email contacts for a student.
 * 
 * @author Nathan Shih
 * @since Dec 6, 2014
 */
public class EmailContact {

	private ArrayList<String> emailContacts; // emails of connected students

	public EmailContact() {
		emailContacts = new ArrayList<String>();
	}
	
	public EmailContact(ArrayList<String> emailContacts) {
		setEmailContacts(emailContacts);
	}
	
	public ArrayList<String> getEmailContacts() {
		return emailContacts;
	}

	public void setEmailContacts(ArrayList<String> emailContacts) {
		this.emailContacts = emailContacts;
	}
	
	public void addEmailContact(String emailContact) {
		emailContacts.add(emailContact);
	}
	
	public void removeEmailContact(String emailContact) {
		emailContacts.remove(emailContact);
	}
}
