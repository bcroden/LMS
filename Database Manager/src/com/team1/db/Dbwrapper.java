package com.team1.db;       //Alex changed 3-7-15 this to get everything to compile

import java.sql.*;
import java.util.ArrayList;

//get book objects
import com.team1.books.Book;



public class Dbwrapper {

	private static Dbwrapper db = new Dbwrapper();
	Connection con;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql//127.0.0.1:3306/LMS";
	
	static final String username = "cadeg";
	static final String pass = "";
	
	//each function will concatenate sql queries
	//and return the result to the request
	//from authentication
	
	//I'm thinking we should return arraylists filled with book objects
	//Then presenting lists of data will be really easy in keeping stuff together
	
	private Dbwrapper(){
		//get driver and connection
		try{
			Class.forName(JDBC_DRIVER).newInstance();
			//connect
			con = DriverManager.getConnection("jdbc:mysql://localhost/LMS", "cadeg", "");
			System.out.println("Connected if we get here");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
		//instance method for singleton
		public static Dbwrapper getInstance(){
			return db;
			
		}
		
	public void query() throws SQLException
	{
		Statement stmt = con.createStatement();
		String sql = "Select * From book ISBN";
                ResultSet result =  stmt.executeQuery(sql);
                while(result.next()){
                String isbn = result.getString("ISBN");
                System.out.println(isbn);
                }
	}
	
	public void addBook(Book book)throws SQLException
	{
		Statement stmt = con.createStatement();
		
	}
	
	//These functions should return all books that match the criteria
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
        
        //Static testing - won't actually be here for final product
	public static void main(String args[]) throws SQLException
	{
		Dbwrapper temp = getInstance();
		temp.query();
	}
}
