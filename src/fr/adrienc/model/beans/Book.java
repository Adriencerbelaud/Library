package fr.adrienc.model.beans;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Book {
	private int id;
	private String title;
	private float price;
	private boolean availability;
	private Set<Author> authors;
	
	public Book(){
		authors = new LinkedHashSet<Author>();
	}
	public Book(String id, String title, float price, boolean availability) {
		this();
		this.id = Integer.valueOf(id);
		this.title = title;
		this.price = price;
		this.availability = availability;
	}
	public Book(String title, float price, boolean availability) {
		this();
		this.title = title;
		this.price = price;
		this.availability = availability;
	}
	
	public Book(String title, float price, boolean availability, Author author) {
		this();
		this.title = title;
		this.price = price;
		this.availability = availability;
		this.authors.add(author);
	}
	public Book(int id, String title, float price, boolean availability, Author author) {
		this(title, price, availability, author);
		this.id = id;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	public void addAuthor(Author author){
		this.authors.add(author);
	}

}
