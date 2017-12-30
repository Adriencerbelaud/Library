package fr.adrienc.model.beans;

import fr.adrienc.model.utils.Role;

public class User {
	private int id;
	private String pseudo;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private Role role;
	
	public User(){
		
	}
	public User(String pseudo, String password, String firstname, String lastname, String email) {
		this.pseudo = pseudo;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public User(int id, String pseudo, String password, String firstname, String lastname, String email, Role role) {
		this.id = id;
		this.pseudo = pseudo;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
}
