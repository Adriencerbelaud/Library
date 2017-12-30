package fr.adrienc.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.adrienc.model.beans.Author;
import fr.adrienc.model.utils.Country;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet(
		name="/FrontServlet",
		value="/home/0")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_HOME = "/WEB-INF/library.jsp";
	
	/**
	 * The main servlet is actually LibServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		response.sendRedirect(request.getContextPath()+"/library/0");
		
	}


}
