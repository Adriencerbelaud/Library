package fr.adrienc.model.daos;

import java.util.ArrayList;
import java.util.Set;

import fr.adrienc.model.beans.Author;

public interface InterfaceDAO<T> {
	T find(int id);
	void delete(int id);
	void update(T t);
	int create(T t);
	ArrayList<T> findAll();
}
