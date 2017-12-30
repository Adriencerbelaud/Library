package fr.adrienc.model.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.adrienc.model.beans.Book;

public class DAOFactory{
	private static DAOFactory instance;
	private static Connection cnx = null;
	private static String url, user, password;
	
	private DAOFactory(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public static DAOFactory getInstance(){
		if (null == instance){	
			instance = new DAOFactory(
					"jdbc:mysql://localhost:3306/library",
					"root",
					""
			);	
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		return instance;
	}
	
	public static Connection getConnection(){
		if(DAOFactory.instance == null){
			DAOFactory.instance = DAOFactory.getInstance();
		}
		Connection cnx = null;
		try{
			cnx = DriverManager.getConnection(url, user, password);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return cnx;
	}
	
	public static void closeConnection(){
		try{
			if (null != cnx && !cnx.isClosed()){
				cnx.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}	
	}
	
	/*
	 * DAO used : Book, Author, User
	 */
	
	public BookDAOImpl getBookDAO(){
		return new BookDAOImpl();
	}
	public AuthorDAOImpl getAuthorDAO(){
		return new AuthorDAOImpl();
	}
	public UserDAOImpl getUserDAO(){
		return new UserDAOImpl();
	}
}
