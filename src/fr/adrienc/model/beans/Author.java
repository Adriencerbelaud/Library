package fr.adrienc.model.beans;

import fr.adrienc.model.utils.Country;

public class Author {
	private int id;
	private String firstname;
	private String lastname;
	private Country nativeCountry;
	
	public Author(){}
	
	public Author(String name, String lastname, Country nativeCountry) {
		this.firstname = name;
		this.lastname = lastname;
		this.nativeCountry = nativeCountry;
	}
	
	public Author(int id, String name, String lastname, Country nativeCountry) {
		this.id = id;
		this.firstname = name;
		this.lastname = lastname;
		this.nativeCountry = nativeCountry;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String name) {
		this.firstname = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Country getNativeCountry() {
		return nativeCountry;
	}
	public String getStrCountry() {
		return nativeCountry.toString();
	}
	
	public void setNativeCountry(Country nativeCountry) {
		this.nativeCountry = nativeCountry;
	}
	public String toString(){
		return firstname + " " + lastname;
	}
	
}
