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
		name="UpdateServlet", urlPatterns={"/update", "/update/connu", "/update/inconnu", "/update/*"}
		)
public class UpdateServlet extends HttpServlet {
	/*
	 * UpdateServlet managed the Update.jsp form
	 * UpdateServlet managed updatinig the books in the database
	 */
	private static final long serialVersionUID = 1L;
	private static final String PAGE_HOME = "/WEB-INF/update.jsp";
	public DAOFactory daofactory;
	public ArrayList<Author> authors = new ArrayList<Author>();
	public ArrayList<Author> pre_authors = new ArrayList<Author>();
	int id_book;
	private boolean redirect = false;
	
    public UpdateServlet() {	
    	 daofactory = DAOFactory.getInstance();
		 authors = daofactory.getAuthorDAO().findAll();
		 pre_authors = new ArrayList<Author>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daofactory = DAOFactory.getInstance();
	
		if (redirect == false){
			String uri = request.getRequestURI();
			System.out.println(uri + "fin");
			String[] tab = uri.split("/");			
			if (tab[tab.length-1].equals("supprime")){	
				System.out.println("supprime");
				String prenom = tab[tab.length-3];
				String nom = tab[tab.length-2];
				System.out.println("delete : " + prenom + ", " + nom);
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
					        	daofactory.getBookDAO().deleteAuthor(id_book, o.getId());
					        }
					    }
					}
					//pre_authors.remove(map_pre.get(id_del));
				}
				redirect=true;
				response.sendRedirect(request.getContextPath()+"/update");
			}else if(!tab[tab.length-1].equals("update")){
				/*
				** if there is an Id in the URL
				*/
				id_book = Integer.valueOf(tab[tab.length-1]);				
				Book newbook = daofactory.getBookDAO().find(id_book);
				getServletContext().setAttribute("authors", authors);
				getServletContext().setAttribute("modif_book", newbook);	
				getServletContext().setAttribute("pre_authors", newbook.getAuthors());
			}
		}else{
			redirect = false;
		}
		for (Author pre_aut: pre_authors){
			Iterator<Author> iterator = authors.iterator();
			while (iterator.hasNext() ) {
			    Author o = iterator.next();
			    if ((o.getFirstname().equals(pre_aut.getFirstname())) && (o.getLastname().equals(pre_aut.getLastname()))) {
			        /** Deleting the present element of the list
			         */
			        iterator.remove();
			    }
			}
		}
		
//		for (Author author: pre_authors){
//			authors.remove(author);
//		}
		getServletContext().setAttribute("authors", authors);
		getServletContext().setAttribute("pre_authors", pre_authors);
		getServletContext().setAttribute("countrys",  Country.values());
		if (redirect == false){
		this.getServletContext()
			.getRequestDispatcher(PAGE_HOME)
			.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] tab = uri.split("/");
		System.out.println("POST" + uri);
		if (tab[tab.length-1].equals("connu")){
			/**
			 * Add a known author
			 */
			String id_author =  request.getParameter("id_author");
			int id_aut = Integer.valueOf(id_author);
			Author author = daofactory.getAuthorDAO().find(id_aut);
			pre_authors.add(author);
			daofactory.getBookDAO().addAuthor(id_book, id_aut);
		}else if(tab[tab.length-1].equals("inconnu")){
			/**
			 * Add an unknown author
			 */
			String firstname =  request.getParameter("firstname");
			String lastname =  request.getParameter("lastname");
			String country =  request.getParameter("country");
			System.out.println(country);
			Author author = new Author(firstname, lastname, Country.valueOf(country));
			pre_authors.add(author);
			int id_aut = daofactory.getAuthorDAO().create(author);
			daofactory.getBookDAO().addAuthor(id_book, id_aut);
		}else{
			/**
			 * Update title, price, availabitity
			 */
			String id_book = request.getParameter("id_book");
			String title =  request.getParameter("title");
			Float price =  Float.parseFloat(request.getParameter("price"));
			System.out.println("avail : " + request.getParameter("availability"));
			Boolean availability =  (request.getParameter("availability") == "1");
			System.out.println(availability);
			Book newbook = new Book(id_book, title, price, availability);
			daofactory.getBookDAO().update(newbook);
		}

		
		redirect=true;
		response.sendRedirect(request.getContextPath()+"/update/"+id_book);
	}
}
