package com.jhu.socialnetworking.model;

import java.util.ArrayList;

/**
 * The contacts model object contain all the contacts for a student.
 * 
 * @author Nathan Shih
 * @since Dec 6, 2014
 */
public class Contacts {

	private ArrayList<String> contacts; // emails of connected students

	public Contacts() {
		contacts = new ArrayList<String>();
	}
	
	public Contacts(ArrayList<String> contacts) {
		setContacts(contacts);
	}
	
	public ArrayList<String> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<String> contacts) {
		this.contacts = contacts;
	}
	
	public void addContact(String contact) {
		contacts.add(contact);
	}
	
	public void removeContact(String contact) {
		contacts.remove(contact);
	}
}
