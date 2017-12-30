package fr.adrienc.model.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import fr.adrienc.model.beans.User;
import fr.adrienc.model.beans.User;
import fr.adrienc.model.utils.Country;
import fr.adrienc.model.utils.Role;
import fr.adrienc.model.beans.User;

public class UserDAOImpl implements InterfaceDAO<User>{
	private final String COLUMN_ID = "id";
	private final String COLUMN_PSEUDO = "pseudo";
	private final String COLUMN_PASSWORD = "password";
	private final String COLUMN_FIRSTNAME = "firstname";
	private final String COLUMN_LASTNAME = "lastname";
	private final String COLUMN_EMAIL = "email";
	private final String COLUMN_ROLE = "role";

	private DAOFactory daofactory;	
	private Statement statement;
	
	public UserDAOImpl(){
		this.daofactory = DAOFactory.getInstance();
	}
	private int executeQuery(String query){
		/*
		 * Execute the query using the Connection
		 */
		ResultSet result = null;
		int id_max = 0;
		Connection cnx = DAOFactory.getConnection();		
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
	

	public User find(int id) {
		/*
		 * find a user in the database using the Id
		 */
		User user = new User();
		executeQuery("SELECT * FROM user WHERE id = " + id);

		try{
			ResultSet result = statement.getResultSet();
			if(result.next()){
				user.setId(result.getInt(COLUMN_ID));
				user.setPseudo(result.getString(COLUMN_PSEUDO));
				user.setPassword(result.getString(COLUMN_PASSWORD));
				user.setFirstname(result.getString(COLUMN_FIRSTNAME));
				user.setLastname(result.getString(COLUMN_LASTNAME));
				user.setEmail(result.getString(COLUMN_EMAIL));
				user.setRole(Role.valueOf(result.getString(COLUMN_ROLE)));

			}		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DAOFactory.closeConnection();
		}
		return user;
	}

	public void update(User user) {
		/*
		 * Update a user in the database using an instance of User
		 */
		executeQuery("UPDATE User SET "
				+ " pseudo = " + user.getPseudo()
				+ " password = " + user.getPassword()
				+ " firstname = " + user.getFirstname()
				+ " lastname = " + user.getLastname()
				+ " email = " + user.getEmail()
				+ " role = " + user.getRole().toString()				
				+ " WHERE id = " + user.getId());
		DAOFactory.closeConnection();
	}

	@Override
	public int create(User user) {
		/*
		 * insert a new user in the database
		 */
		int id = executeQuery("INSERT INTO User(pseudo, password, firstname, lastname, email, role) VALUES"
				+ "("
				+ user.getPseudo() + ", "
				+ user.getPassword() + ", "
				+ user.getFirstname() + ", "
				+ user.getLastname() + ", "
				+ user.getEmail() + ", "
				+ "'USER' "
				+ ")");
		DAOFactory.closeConnection();
		return id;
	}

	@Override
	public ArrayList<User> findAll() {
		/*
		 * return the list of all users in the database
		 */
		ArrayList<User> users = new ArrayList<User>();
		
		String query = "SELECT * FROM user";
		try{
			executeQuery(query);
			ResultSet result = statement.getResultSet();
	        while (result.next() ) {
	        	User user = new User();
	        	user.setId(result.getInt(COLUMN_ID));
				user.setPseudo(result.getString(COLUMN_PSEUDO));
				user.setPassword(result.getString(COLUMN_PASSWORD));
				user.setFirstname(result.getString(COLUMN_FIRSTNAME));
				user.setLastname(result.getString(COLUMN_LASTNAME));
				user.setEmail(result.getString(COLUMN_EMAIL));
				user.setRole(Role.valueOf(result.getString(COLUMN_ROLE)));
	        	//Si le user n'est pas déjà dans users	        	
	        	users.add(user);
	        }
		}catch (SQLException e){
			e.printStackTrace();
		}
		return users;
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
