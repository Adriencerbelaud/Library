package fr.adrienc.model.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.adrienc.model.beans.Author;
import fr.adrienc.model.utils.Country;

public class AuthorDAOImpl implements InterfaceDAO<Author>{
	private final String COLUMN_ID = "id";
	private final String COLUMN_FIRSTNAME = "firstname";
	private final String COLUMN_LASTNAME = "lastname";
	private final String COLUMN_COUNTRY = "country"; 
	private final String COLUMN_ID_COUNTRY = "id";
	private Statement statement;
	private DAOFactory daofactory;
	
	public AuthorDAOImpl(){
		this.daofactory = DAOFactory.getInstance();
	}
	private int executeQuery(String query){
		/*
		 * Execute the query using the Connection
		 */
		Connection cnx = DAOFactory.getConnection();	
		ResultSet result = null;
		int id_max = 0;
		try{
			statement = cnx.createStatement();
			statement.execute(query, Statement.RETURN_GENERATED_KEYS);
			result = statement.getGeneratedKeys();
			if (result.next()){
				id_max = result.getInt(1);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return id_max;
	}
	@Override
	public Author find(int id) {
		/*
		 * Find an author in the database using the Id
		 */
		Author author = new Author();
		executeQuery("SELECT * FROM author a JOIN nativecountry c ON a.nativeCountry=c.id WHERE a.id = " + id);

		try{
			ResultSet result = statement.getResultSet();
			if(result.next()){
				author.setId(result.getInt(COLUMN_ID));
				author.setFirstname(capitalize(result.getString(COLUMN_FIRSTNAME)));
				author.setLastname(capitalize(result.getString(COLUMN_LASTNAME)));
				String country = result.getString(COLUMN_COUNTRY);
				author.setNativeCountry(Country.valueOf(country));
			}		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DAOFactory.closeConnection();
		}
		return author;
	}

	private String capitalize(String name){
		String res = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		return res;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	public void delete(String prenom, String nom) {
		executeQuery("DELETE * FROM author "
				+ "WHERE prenom = " + prenom 
				+ "AND nom = " + nom);
		
	}


	@Override
	public int create(Author author) {
		/*
		 * Insert a new author in the database
		 */
		int id_country = 0;
		System.out.println(author.getStrCountry());
		String q1 = "SELECT * FROM nativecountry WHERE country = '" + author.getStrCountry() + "'";
		System.out.println(q1);
		executeQuery(q1);
		try{
			ResultSet result = statement.getResultSet();
			if(result.next()){
				id_country = result.getInt(COLUMN_ID_COUNTRY);			
			}		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DAOFactory.closeConnection();
		}
		String q2 = "INSERT INTO Author(firstname, lastname, nativecountry) VALUES"
				+ "('"
				+ author.getFirstname() + "', '"
				+ author.getLastname() + "', '"
				+ Integer.toString(id_country)
				+ "')";
		System.out.println(q2);
		int id_author = executeQuery(q2);

		DAOFactory.closeConnection();
		return id_author;
		
	}

	@Override
	public ArrayList<Author> findAll() {
		/*
		 * Return all authors in the database
		 */
		ArrayList<Author> authors = new ArrayList<Author>();
		
		String query = "SELECT a.*, c.country FROM author a "
				+ "JOIN nativecountry c ON a.nativecountry=c.id "
				+ "ORDER BY a.id";
		try{
			executeQuery(query);
			ResultSet result = statement.getResultSet();
	        while (result.next() ) {
	        	Country country = Country.valueOf(result.getString(COLUMN_COUNTRY));
	        	Author author = new Author(	result.getInt(COLUMN_ID),
	        								result.getString(COLUMN_FIRSTNAME),
	        								result.getString(COLUMN_LASTNAME),
	        								country);
	        	//Si le author n'est pas déjà dans authors	        	
	        	authors.add(author);
	        }
		}catch (SQLException e){
			e.printStackTrace();
		}
		return authors;
	}
	@Override
	public void update(Author t) {
		// TODO Auto-generated method stub
		
	}

}
