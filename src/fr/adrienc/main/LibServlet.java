package fr.adrienc.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienc.model.beans.Book;
import fr.adrienc.model.daos.DAOFactory;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet(
		name="/LibServlet",
		urlPatterns={"/library", "/library/0", "/library/*"})
public class LibServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_HOME = "/WEB-INF/library.jsp";
	private int nb_page = 0;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daofactory = DAOFactory.getInstance();
		String uri = request.getRequestURI();
		String[] tab = uri.split("/");
		String page;
		//which page to edit
		if (tab[tab.length-1].equals("next")){
			nb_page ++;
		}else if (tab[tab.length-1].equals("last")){
			nb_page --;
		}
		int nb_books = daofactory.getBookDAO().nb_books();
		System.out.println("nb book : " +nb_books + ", nb " + nb_page);
		
		/*
		 * page 1: no button "last"
		 * last page: no button "next"
		 */
		if ((nb_books/10)> nb_page) {
			page = "next";
		}else{
			page = "nada";
		}
		System.out.println(page);
		System.out.println("page " +page);
		ArrayList<Book> books = daofactory.getBookDAO().find10(nb_page);
		request.setAttribute("page", page);
		request.setAttribute("nb_page", nb_page);
		request.setAttribute("books", books);
		this.getServletContext()
			.getRequestDispatcher(PAGE_HOME)
			.forward(request, response);
	}
	
	private String getActionName(HttpServletRequest req) {
		/*
		 * New architecture with ActionName
		 * NOT USED YET
		 */
        String uri = req.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/")+1); // on decoupe uri en 2, lindex de depart le dernier / +1 pour effacer le /
        return uri;

    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
