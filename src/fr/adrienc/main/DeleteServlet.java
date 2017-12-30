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
import fr.adrienc.model.daos.DAOFactory;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete/*")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_REDIRECT = "/WEB-INF/delete.jsp";
	public DAOFactory daofactory;
	private boolean redirect = false;       

    public DeleteServlet() {
    	daofactory = DAOFactory.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println("supprim :" + uri );
		String[] tab = uri.split("/");			
		daofactory.getBookDAO().delete(Integer.valueOf(tab[tab.length-1]));
			
		this.getServletContext()
		.getRequestDispatcher(PAGE_REDIRECT)
		.forward(request, response);

	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
