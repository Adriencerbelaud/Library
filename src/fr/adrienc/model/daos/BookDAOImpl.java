package fr.adrienc.model.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.adrienc.model.beans.Author;
import fr.adrienc.model.beans.Book;

public class BookDAOImpl implements InterfaceDAO<Book> {
	private DAOFactory daofactory;
	private final String COLUMN_ID = "id";
	private final String COLUMN_TITLE = "title";
	private final String COLUMN_AVAILABILITY = "availability";
	private final String COLUMN_PRICE = "price"; 
	private final String COLUMN_ID_AUTHOR = "id_author";
	private Statement statement;
	
	public BookDAOImpl(){
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
	
	public Book find(int id) {
		/*
		 * Find a book in the database using the Id
		 * 							   using the bean Book
		 */
		Book book = new Book();
		executeQuery("SELECT * FROM Book b JOIN Author_Book ab ON ab.id_book = b.id "
				+ "WHERE b.id = " + id);

		try{
			ResultSet result = statement.getResultSet();
			while(result.next()){
				Author author = daofactory.getAuthorDAO().find(result.getInt("id_author"));
				
				if(result.isFirst()){
					book.setId(result.getInt(COLUMN_ID));
					book.setTitle(result.getString(COLUMN_TITLE));
					book.setAvailability(result.getBoolean(COLUMN_AVAILABILITY));
					book.setPrice(result.getFloat(COLUMN_PRICE));
				}
				book.addAuthor(author);
			}		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DAOFactory.closeConnection();
		}
		return book;
	}

	public void delete(int id) {
		/*
		 * delete a book in the database using the Id
		 */
		executeQuery("DELETE FROM Book WHERE id="+id);
		DAOFactory.closeConnection();
	}

	public void update(Book book) {
		/*
		 * Update a book in the database using an instance of Book
		 */
		String bookbool;
		if (book.isAvailability()){
			bookbool = "1";
		}else{
			bookbool = "0";
		}
		executeQuery("UPDATE Book SET "
				+ " title = '" + book.getTitle()
				+ "', price = '" + book.getPrice()
				+ "', availability = '" + bookbool
				+ "' WHERE id = '" + book.getId() 
				+ "'");
		DAOFactory.closeConnection();
		// TODO Auto-generated method stub

	}

	public int create(Book book) {
		/*
		 * Save a book's instance in the database
		 * WITHOUT AUTHOR
		 */
		int max = executeQuery("INSERT INTO Book(title, price, availability) VALUES"
				+ "('"
				+ book.getTitle() + "', '"
				+ book.getPrice() + "', '"
				+ book.isAvailability()
				+ "')");
		
		DAOFactory.closeConnection();
		return max;
	}
	public int create(Book book, ArrayList<Author> authors) {
		/*
		 * Save a book's instance in the database 
		 * 		using an arraylist of authors
		 */
		String bookbool;
		if (book.isAvailability()){
			bookbool = "1";
		}else{
			bookbool = "0";
		}
		String q = "INSERT INTO Book(title, price, availability) VALUES"
				+ "('"
				+ book.getTitle() + "', '"
				+ book.getPrice() + "', '"
				+ bookbool
				+ "')";
		System.out.println(q);
				
		int id_book = executeQuery(q);
		System.out.println("id_book " + id_book );
	
		for (Author author : authors){
			int id_author = 0;
			System.out.println(author.getId());
			if(0 == author.getId()){
				id_author = daofactory.getAuthorDAO().create(author);	
			}else{
				id_author = author.getId();
			}
			String query = ("INSERT INTO Author_Book(id_author, id_book) VALUES"
				+ "('" + id_author + "', '" + id_book + "')" );
			System.out.println(query);
			executeQuery(query);
		}
		DAOFactory.closeConnection();
		return id_book;
	}
	
	public void update(int id, ArrayList<Author> authors){
		/* 
		 * update the authors list of a book in the database
		 */
		executeQuery("DELETE * FROM Author_Book WHERE id_book ='" + id + "'");
		for (Author author: authors){
			executeQuery("INSERT INTO Author_Book(id_author, id_book) VALUES"
				+ "('" + author.getId() + "', '" + id + "')" );
		}
		
	}

	public ArrayList<Book> findAll() {
		/*
		 * find all the books in the database
		 */
		ArrayList<Book> books = new ArrayList<Book>();
		
		String query = "SELECT b.*, a.id as id_n FROM book b "
				+ "JOIN author_book ab ON b.id=ab.id_book "
				+ "JOIN author a ON a.id=ab.id_author "
				//+ "JOIN nativecountry c ON a.nativecountry=c.id "
				+ "ORDER BY b.id";
		try{
			executeQuery(query);
			ResultSet result = statement.getResultSet();
	        int cpt = 0;
	        Book book = null;
	        while (result.next() ) {
	        	Author author = daofactory.getAuthorDAO().find(result.getInt("id_n"));
//	        	Country country = Country.valueOf(result.getString(9));
//	        	Author author = new Author(	result.getInt(5),
//	        								result.getString(6),
//	        								result.getString(7),
//	        								country);
	        	//Si le book n'est pas déjà dans books
	        	if (cpt != result.getInt(1)){
	        		if (null != book){ 
	        			books.add(book);
	        		}
	        		book = new Book(result.getInt(1),result.getString(2),result.getFloat(3), result.getBoolean(4), author);                    
	        	}else{
	        		book.addAuthor(author);
	        	}
	        	cpt = result.getInt(1);
	        }
	        books.add(book);
		}catch (SQLException e){
			e.printStackTrace();
		}
		return books;
	}
	public ArrayList<Book> find10(int nb_page) {
		/*
		 * find 10 books in the database depending of the page's number  
		 */
		int nb = 10;
		ArrayList<Book> books = new ArrayList<Book>();		
		String query = "SELECT b.*, a.id as id_n FROM book b "
				+ "JOIN author_book ab ON b.id=ab.id_book "
				+ "JOIN author a ON a.id=ab.id_author "
				//+ "JOIN nativecountry c ON a.nativecountry=c.id "
				+ "ORDER BY b.id";
		try{
			executeQuery(query);
			ResultSet result = statement.getResultSet();
	        int cpt = 0;
	        Book book = null;
	        result.absolute((nb_page*10));
	        while ((result.next()) && (nb > 1)) {
	        	Author author = daofactory.getAuthorDAO().find(result.getInt("id_n"));
//			        	Country country = Country.valueOf(result.getString(9));
//			        	Author author = new Author(	result.getInt(5),
//			        								result.getString(6),
//			        								result.getString(7),
//			        								country);
	        	//Si le book n'est pas déjà dans books
	        	if (cpt != result.getInt(1)){
	        		if (null != book){ 
	        			books.add(book);
	        			nb --;
	        		}
	        		book = new Book(result.getInt(1),result.getString(2),result.getFloat(3), result.getBoolean(4), author);                    
	        	}else{
	        		book.addAuthor(author);
	        	}
	        	cpt = result.getInt(1);
	        }
	        books.add(book);
		}catch (SQLException e){
			e.printStackTrace();
		}
		return books;
	}
	
	public void addAuthor(int id_book, int id_author){
		/* 
		 * add an author to a book
		 */
		executeQuery("INSERT INTO Author_Book(id_author, id_book) VALUES"
				+ "('" + id_author + "', '" + id_book + "')");
	}
	
	public void deleteAuthor(int id_book, int id_author){
		/*
		 * delete a relation between an author and a book
		 */
		executeQuery("DELETE * FROM Author_Book "
				+ "WHERE id_book ='" + id_book + "' "
				+ "AND id_author ='" + id_author + "'");
	}
	
	public int findMax(String table){
		/*
		 * Find the max Id of a table 
		 * NOT USED ANYMORE
		 */
		executeQuery("SELECT MAX(id) FROM " + table);
		int id = 0;
		try{
			ResultSet result = statement.getResultSet();
	        if (result.next() ) {
	        	id = result.getInt("id");
	        }
		}catch (SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public int nb_books() {
		/*
		 * return the quantity of books in the database
		 */
		int size = 0;
		String query = "SELECT * FROM book ";
		try{
			executeQuery(query);
			ResultSet rs = statement.getResultSet();		
			if (rs != null){  
			  rs.beforeFirst();  
			  rs.last();  
			  size = rs.getRow();  
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return size;
	}
}
