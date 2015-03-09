package com.team1.db;

import java.sql.*;
import java.util.ArrayList;

//get book objects
import com.team1.books.*;



public class Dbwrapper {

	private static Dbwrapper db = new Dbwrapper();
	Connection con;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql//127.0.0.1:3306/LMS";
	//Can adjust these and they don't have to be hard coded
	static final String username = "cadeg";
	static final String pass = "";
	
	//each function will concatenate sql queries
	//and return the result to the request
	//from authentication
	
	
	private Dbwrapper(){
		//get driver and connection
		try{
			Class.forName(JDBC_DRIVER).newInstance();
			//connect
			con = DriverManager.getConnection("jdbc:mysql://localhost/LMS", "cadeg", "");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
		//instance method for singleton
		public static Dbwrapper getInstance(){
			return db;
			
		}
		
		//Test function
	public void query() throws SQLException{
		Statement stmt = con.createStatement();
		String sql = "Select * From book ISBN";
                ResultSet result =  stmt.executeQuery(sql);
                while(result.next()){
                String isbn = result.getString("ISBN");
                System.out.println(isbn);
                }
	}
	
	//--------------------------------------------------------------------------
	
	//Function for adding a new book to the repo
	//--------------------------------------------------------------------------
	public void addBook(Book book)throws SQLException{
		String tempp = book.publisher;
		String temppd = book.datePublished;
		String tempt = book.title;
		
		System.out.println("Contents: " + book.isbn + "', '" + book.title + "', '" + book.author +
					 "', ' Genre: " + book.genre + "', ' Publisher: " + book.publisher + "', '" + book.datePublished);
		
		if(tempp == null || tempp.equals("")){
			System.out.println("Null publisher");
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO book " +
					 "(isbn, title, author, genre, publishdate, likes, dislikes, copies) " + 
					 "VALUES ('" + book.isbn + "', '" + book.title + "', '" + book.author +
					 "', '" + book.genre + "', '" + book.datePublished + 
					 "', '" + 0 + "', '" + 0 + "', '" + 0 + "')";
		stmt.executeUpdate(sql);
		}
		else if(temppd == null || temppd.equals("")){
			System.out.println("Null publisherdate");
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO book " +
					 "(isbn, title, author, genre, publisher, likes, dislikes, copies) " + 
					 "VALUES ('" + book.isbn + "', '" + book.title + "', '" + book.author +
					 "', '" + book.genre + "', '" + book.publisher + "', '" + 0 + "', '" + 0 + "', '" + 0 + "')";
		stmt.executeUpdate(sql);
		}
		else if(tempt == null || tempt.equals("")){
			System.out.println("Null title");
			System.out.println("The ISBN did not provide a title, no database entry created.");
		}
		else{
			System.out.println("No null");
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO book " +
					 "(isbn, title, author, genre, publisher, publishdate, likes, dislikes, copies) " + 
					 "VALUES ('" + book.isbn + "', '" + book.title + "', '" + book.author +
					 "', '" + book.genre + "', '" + book.publisher + "', '" + book.datePublished + 
					 "', '" + 0 + "', '" + 0 + "', '" + 0 + "')";
		stmt.executeUpdate(sql);
		}
	}
	//Functions for updating book info (likes dislikes and copies)
	
	
	//--------------------------------------------------------------------------
	
	//These functions should return all books that match the criteria
	//--------------------------------------------------------------------------
    public ArrayList<Book> SearchAuthor(String author)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE author = '" + author + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	ArrayList list = new ArrayList<Book>();
    	while(result.next()){
    		Book book = new Book(
    			result.getString("ISBN"),
    			result.getString("title"),
    			result.getString("author"),
    			result.getString("publisher"),
    			result.getString("publishdate"),
    			result.getString("genre"));
    			
    		list.add(book);
    	}
    	return list;
    }
    
    public ArrayList<Book> SearchTitle(String title)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE title = '" + title + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	ArrayList list = new ArrayList<Book>();
    	while(result.next()){
    		Book book = new Book(
    			result.getString("ISBN"),
    			result.getString("title"),
    			result.getString("author"),
    			result.getString("publisher"),
    			result.getString("publishdate"),
    			result.getString("genre"));
    			
    		list.add(book);
    	}
    	return list;
    }
    
    public ArrayList<Book> SearchPublisher(String publisher)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE publisher = '" + publisher + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	ArrayList list = new ArrayList<Book>();
    	while(result.next()){
    		Book book = new Book(
    			result.getString("ISBN"),
    			result.getString("title"),
    			result.getString("author"),
    			result.getString("publisher"),
    			result.getString("publishdate"),
    			result.getString("genre"));
    			
    		list.add(book);
    	}
    	return list;
    }
	//date 1 is an earlier date than date2, its possible to error check here
	//but we can fix this on input
    public ArrayList<Book> SearchDate(String date, String date2)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	//date has to be in the format yyyy
    	//This can be handled on front end of searches though
    	String sql = "SELECT * FROM book WHERE publishdate between '" + date + "' and '" + date2 + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	ArrayList list = new ArrayList<Book>();
    	while(result.next()){
    		Book book = new Book(
    			result.getString("ISBN"),
    			result.getString("title"),
    			result.getString("author"),
    			result.getString("publisher"),
    			result.getString("publishdate"),
    			result.getString("genre"));
    			
    		list.add(book);
    	}
    	return list;
    }
    
    public ArrayList<Book> SearchGenre(String genre)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE genre = '" + genre + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	ArrayList list = new ArrayList<Book>();
    	while(result.next()){
    		Book book = new Book(
    			result.getString("ISBN"),
    			result.getString("title"),
    			result.getString("author"),
    			result.getString("publisher"),
    			result.getString("publishdate"),
    			result.getString("genre"));
    			
    		list.add(book);
    	}
    	return list;
    }
    //End of queries returning book lists   
    //--------------------------------------------------------------------------
	//These functions should return singlar books
	//--------------------------------------------------------------------------
	public Book SearchISBN(String ISBN)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE isbn = '" + ISBN + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	String isbn = "";
    	String title = "";
    	String author = "";
    	String publisher = "";
    	String date = "";
    	String genre = "";
    	while(result.next()){
    			isbn = result.getString("ISBN");
    			title = result.getString("title");
    			author = result.getString("author");
    			publisher = result.getString("publisher");
    			date = result.getString("publishdate");
    			genre = result.getString("genre");
    		}
    	Book book = new Book(isbn, title, author, publisher, date, genre);
    	return book;
    }
    
    public Book SearchID(int id)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE id = '" + id + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	String isbn = "";
    	String title = "";
    	String author = "";
    	String publisher = "";
    	String date = "";
    	String genre = "";
    	while(result.next()){
    			isbn = result.getString("ISBN");
    			title = result.getString("title");
    			author = result.getString("author");
    			publisher = result.getString("publisher");
    			date = result.getString("publishdate");
    			genre = result.getString("genre");
    		}
    	Book book = new Book(isbn, title, author, publisher, date, genre);
    	return book;
    }
    //end of single returning book queries
    //--------------------------------------------------------------------------
    
    //User related queries
    //--------------------------------------------------------------------------
    	public void addUser(String user, String pass, String email, String firstname, String lastname, int enotify)throws SQLException{
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO user " +
					 "(username, password, fname, lname, email, notify) " + 
					 "VALUES ('" + user + "', '" + pass + "', '" + firstname +
					 "', '" + lastname + "', '" + email + "', '" + enotify + "')";
		stmt.executeUpdate(sql);
    	}
    	
    	public String getPass(String user){
    		Statement stmt = con.createStatement();
    		String sql = "SELECT password FROM user WHERE username = '" + user + "'";
    		ResultSet result = stmt.executeQuery(sql);
    		String pass;
    		while(result.next()){
    			pass = result.getString("password");
    		}
    		return pass;
    	}
    	
    	
    
    //--------------------------------------------------------------------------
    
    //Librarian related queries
    //--------------------------------------------------------------------------
    public void CheckOut(String isbn)throws SQLException{
    	Statement stmt = con.createStatement();
    	String sql = "SELECT copies FROM BOOK WHERE isbn = '" + isbn + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	int copies;
    	while(result.next()){
    		copies = result.getInt(copies);
    	}
    	copies--;
    	sql = "UPDATE BOOK copies = '" + copies + "' WHERE isbn = '" + isbn + "'";
    	stmt.executeUpdate(sql);
    	
    	//Book related to isbn will be added to user who checked it out
    	
    }
    
     public void CheckIn(String isbn)throws SQLException{
    	Statement stmt = con.createStatement();
    	String sql = "SELECT copies FROM BOOK WHERE isbn = '" + isbn + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	int copies;
    	while(result.next()){
    		copies = result.getInt(copies);
    	}
    	copies++;
    	sql = "UPDATE BOOK copies = '" + copies + "' WHERE isbn = '" + isbn + "'";
    	stmt.executeUpdate(sql);
    	
    	//book will be removed from user checked out list
    	//and added to user history
    }
    
     public void SetCopies(String isbn, int copies)throws SQLException{
    	Statement stmt = con.createStatement();
    	String sql = "UPDATE BOOK copies = '" + copies + "' WHERE isbn = '" + isbn + "'";
    	stmt.executeUpdate(sql);
    }
    //--------------------------------------------------------------------------
    
    //Administrative queries
    //Not sure what exactly we want from here
}
