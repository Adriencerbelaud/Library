package fr.adrienc.model.daos;

import java.util.List;

import fr.adrienc.model.beans.Book;

public interface BookDAO {
	Book find(int id);
	void delete(int id);
	void update(Book book);
	void create(Book book);
	List<Book> findAll();
}
