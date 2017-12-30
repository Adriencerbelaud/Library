package fr.adrienc.model.daos;

import java.util.List;

import fr.adrienc.model.beans.Author;

public interface AuthorDAO {
	Author find(int id);
	void delete(int id);
	void update(Author author);
	void create(Author author);
	List<Author> findAll();
}