package fr.adrienc.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienc.model.beans.Author;
import fr.adrienc.model.beans.Book;
import fr.adrienc.model.daos.DAOFactory;
import fr.adrienc.model.utils.Country;

/**
 * Servlet implementation class AjoutServlet
 */
@WebServlet(
		name="AjoutServlet", urlPatterns={"/ajout", "/ajout/connu", "/ajout/inconnu", "/ajout/*"}
		)
public class AjoutServlet extends HttpServlet {
	/*
	 * AjoutServlet managed the Addbook form in the ajout.jsp
	 * AjoutServlet managed the authors list of this book before the book
	 *  	is saved in the database
	 */
	
	
	private static final long serialVersionUID = 1L;
	private static final String PAGE_HOME = "/WEB-INF/ajout.jsp";
	private static final String PAGE_VALIDATION = "/WEB-INF/valide.jsp";

	public DAOFactory daofactory;
	
	// authors are all the authors in the database
	public ArrayList<Author> authors = new ArrayList<Author>();
	// pre_authors are the authors which write the present book
	public ArrayList<Author> pre_authors = new ArrayList<Author>();
	private boolean redirect = false;

    public AjoutServlet() {	
    	 this.daofactory = DAOFactory.getInstance();
		 authors = daofactory.getAuthorDAO().findAll();
		 pre_authors = new ArrayList<Author>();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daofactory = DAOFactory.getInstance();
		String page = PAGE_HOME;
		if (redirect == false){
			String uri = request.getRequestURI();
			System.out.println(uri + "fin");
			String[] tab = uri.split("/");			
			if (tab[tab.length-1].equals("supprime")){	
				/**
				 * Remove an author from pre_authors
				 */
				String prenom = tab[tab.length-3];
				String nom = tab[tab.length-2];
				if (null != prenom){
					Iterator<Author> iterator = pre_authors.iterator();
					while ( iterator.hasNext() ) {
					    Author o = iterator.next();
					    if ((o.getFirstname().equals(prenom)) && (o.getLastname().equals(nom))) {
					        // On supprime l'élément courant de la liste
					        iterator.remove();
					        System.out.println(o.getId());
					        if (o.getId() != 0){
					        	authors.add(o);
					        }
					    }
					}
				}
				redirect=true;
				response.sendRedirect(request.getContextPath()+"/ajout");
			}else {
				/**
				 * Initialisation of pre_authors
				 */
				pre_authors = new ArrayList<Author>();
			}
		}else{
			redirect = false;
		}
		for (Author pre_aut: pre_authors){
			/**
			 * remove pre_authors form Select Authors
			 * (they already have been selected)
			 */
			Iterator<Author> iterator = authors.iterator();
			while (iterator.hasNext() ) {
			    Author o = iterator.next();
			    if ((o.getFirstname().equals(pre_aut.getFirstname())) && (o.getLastname().equals(pre_aut.getLastname()))) {
			        // On supprime l'élément courant de la liste
			        iterator.remove();
			    }
			}
		}
		
		getServletContext().setAttribute("authors", authors);
		getServletContext().setAttribute("pre_authors", pre_authors);
		getServletContext().setAttribute("countrys",  Country.values());
		if (redirect == false){
			this.getServletContext()
			.getRequestDispatcher(page)
			.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] tab = uri.split("/");
		if (tab[tab.length-1].equals("connu")){
			/**
			 * Add a known author in pre_authors
			 */
			String id_author =  request.getParameter("id_author");
			System.out.println(id_author);
			int id_int = Integer.valueOf(id_author);
			Author author = daofactory.getAuthorDAO().find(id_int);
			System.out.println(author.getFirstname());
			pre_authors.add(author);
		}else if(tab[tab.length-1].equals("inconnu")){
			/**
			 * Add an unknown author in pre_author
			 */
			String firstname =  request.getParameter("firstname");
			String lastname =  request.getParameter("lastname");
			String country =  request.getParameter("country");
			System.out.println(country);
			Author author = new Author(firstname, lastname, Country.valueOf(country));
			pre_authors.add(author);
		}else{
			/**
			 * Insert the book and its pre_authors in the database
			 */
			String title =  request.getParameter("title");
			Float price =  Float.parseFloat(request.getParameter("price"));
			Boolean availability =  (request.getParameter("availability").equals("1"));			
			Book book = new Book(title, price, availability);
			System.out.println(book.getTitle());
			daofactory.getBookDAO().create(book, pre_authors);
			this.getServletContext()
				.getRequestDispatcher(PAGE_VALIDATION)
				.forward(request, response);
		}

		
		redirect=true;
		response.sendRedirect(request.getContextPath()+"/ajout");
		// doGet(request, response);
	}
}
