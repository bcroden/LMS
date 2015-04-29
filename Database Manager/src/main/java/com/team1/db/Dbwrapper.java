package com.team1.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.team1.books.Book;
import com.team1.books.InvalidISBNException;
//get book objects



public class Dbwrapper {

	private static Dbwrapper db = new Dbwrapper();
	Connection con;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/LMS";
	//Can adjust these and they don't have to be hard coded
	static final String username = "root";
	static final String pass = "LMS";
	
	//each function will concatenate sql queries
	//and return the result to the request
	//from authentication
	
	
	private Dbwrapper(){
		//get driver and connection
		try{
		System.out.println("Dbwrapper");
			Class.forName(JDBC_DRIVER).newInstance();
			//connect
			con = DriverManager.getConnection("jdbc:mysql://localhost/LMS", "root", "LMS");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
		//instance method for singleton
		public static Dbwrapper getInstance(){
			return db;
			
		}
		
		//----------------------------------------------------------------------
		//Encode and decode for insertions
		public String encode(String str){
			if(str.indexOf("'")!=-1){
				str = new StringBuilder(str).insert((str.indexOf("'")), "\\").toString();
			}
			return str;
		}
		
		public String decode(String str){
			if(str.indexOf("\\")!=-1){
				str = str.replaceAll("\\", "").toString();	
			}
			
			return str;
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
	public synchronized void addBook(Book book, int num) throws SQLException{
		
		System.out.println("No null");
		Statement stmt;
		try {
			stmt = con.createStatement();
			// TODO Auto-generated catch block
		String sql = "INSERT INTO book " +
					 "(isbn, title, author, genre, publisher, publishdate, likes, dislikes, copiesin, copiesout, picURL) " + 
					 "VALUES ('" + book.isbn + "', '" + encode(book.title) + "', '" + encode(book.author) +
					 "', '" + encode(book.genre) + "', '" + encode(book.publisher) + "', '" + book.datePublished + 
					 "', '" + 0 + "', '" + 0 + "', '" + num + "', '" + 0 + "', '" + book.url + "')";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Error code: " + e.getErrorCode());
			if(e.getErrorCode() == 1062){
				stmt = con.createStatement();
				String sql = "SELECT copiesin FROM book WHERE isbn = '" + book.isbn + "'";
				ResultSet result = stmt.executeQuery(sql);
				int old = 0;
				while(result.next()){
					old = result.getInt("copiesin");
				}
				int newTotal = old + num;
				sql = "UPDATE book SET copiesin = '" + newTotal + "' WHERE isbn = '" + book.isbn + "'";
				stmt.executeUpdate(sql);
			}
			else
				throw e;
		}
	}
	//Functions for updating book info (likes dislikes and copies)
	
	
	//--------------------------------------------------------------------------
	
	//These functions should return all books that match the criteria
	//--------------------------------------------------------------------------
    public synchronized ArrayList<Book> SearchAuthor(String author)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE author LIKE '%" + author + "%'";
    	ResultSet result = stmt.executeQuery(sql);
    	ArrayList<Book> list = new ArrayList<Book>();
    	while(result.next()){
    		System.out.println("Title: "+ result.getString("isbn"));
    		Book book = new Book(
    			result.getString("isbn"),
    			result.getString("title"),
    			result.getString("author"),
    			result.getString("publisher"),
    			result.getString("publishdate"),
    			result.getString("genre"),
    			result.getString("picURL"));
    			
    		list.add(book);
    	}
    	return list;
    }
    
    public synchronized ArrayList<Book> SearchTitle(String title)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE title LIKE '%" + title + "%'";
    	ResultSet result = stmt.executeQuery(sql);
    	ArrayList<Book> list = new ArrayList<Book>();
    	while(result.next()){
    		Book book = new Book(
    			result.getString("ISBN"),
    			result.getString("title"),
    			result.getString("author"),
    			result.getString("publisher"),
    			result.getString("publishdate"),
    			result.getString("genre"),
    			result.getString("picURL"));
    			
    		list.add(book);
    	}
    	return list;
    }
    
    public synchronized ArrayList<Book> SearchPublisher(String publisher)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE publisher LIKE '%" + publisher + "%'";
    	ResultSet result = stmt.executeQuery(sql);
    	ArrayList<Book> list = new ArrayList<Book>();
    	while(result.next()){
    		Book book = new Book(
    			result.getString("ISBN"),
    			result.getString("title"),
    			result.getString("author"),
    			result.getString("publisher"),
    			result.getString("publishdate"),
    			result.getString("genre"),
    			result.getString("picURL"));
    			
    		list.add(book);
    	}
    	return list;
    }
	//date 1 is an earlier date than date2, its possible to error check here
	//but we can fix this on input
    public ArrayList<Book> SearchDate(String date)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	//date has to be in the format yyyy
    	//This can be handled on front end of searches though
    	String sql = "SELECT * FROM book WHERE publishdate = '" + date + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	ArrayList<Book> list = new ArrayList<Book>();
    	while(result.next()){
    		Book book = new Book(
    			result.getString("ISBN"),
    			result.getString("title"),
    			result.getString("author"),
    			result.getString("publisher"),
    			result.getString("publishdate"),
    			result.getString("genre"),
    			result.getString("picURL"));
    			
    		list.add(book);
    	}
    	return list;
    }
    
    public synchronized ArrayList<Book> SearchGenre(String genre)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE genre LIKE '%" + genre + "%'";
    	ResultSet result = stmt.executeQuery(sql);
    	ArrayList<Book> list = new ArrayList<Book>();
    	while(result.next()){
    		Book book = new Book(
    			result.getString("ISBN"),
    			result.getString("title"),
    			result.getString("author"),
    			result.getString("publisher"),
    			result.getString("publishdate"),
    			result.getString("genre"),
    			result.getString("picURL"));
    		list.add(book);
    	}
    	return list;
    }
    //End of queries returning book lists   
    //--------------------------------------------------------------------------
	//These functions should return singlar books
	//--------------------------------------------------------------------------
	public synchronized Book SearchISBN(String ISBN)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE isbn = '" + ISBN + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	String isbn = " ";
    	String title = " ";
    	String author = " ";
    	String publisher = " ";
    	String date = " ";
    	String genre = " ";
    	String url = " ";
    	while(result.next()){
    			isbn = result.getString("ISBN");
    			title = result.getString("title");
    			author = result.getString("author");
    			publisher = result.getString("publisher");
    			date = result.getString("publishdate");
    			genre = result.getString("genre");
    			url = result.getString("picURL");
    		}
    	Book book = new Book(isbn, title, author, publisher, date, genre, url);
    	if(isbn.equals(" ")){
    		return null;
    	}
    	else{
    	return book;
    	}
    }
    
    //data for our purposes
    public synchronized Book SearchID(int id)throws SQLException
    {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT * FROM book WHERE id = '" + id + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	String isbn = " ";
    	String title = " ";
    	String author = " ";
    	String publisher = " ";
    	String date = " ";
    	String genre = " ";
    	String url = " ";
    	while(result.next()){
    			isbn = result.getString("ISBN");
    			title = result.getString("title");
    			author = result.getString("author");
    			publisher = result.getString("publisher");
    			date = result.getString("publishdate");
    			genre = result.getString("genre");
    		}
    	Book book = new Book(isbn, title, author, publisher, date, genre, url);
    	return book;
    }
    //end of single returning book queries
    //--------------------------------------------------------------------------
    
    //User related queries
    //--------------------------------------------------------------------------
    	public synchronized void addUser(String user, String pass, String email, String firstname, String lastname, int enotify, int auth)throws SQLException{
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO user " +
					 "(username, password, fname, lname, email, notify, auth) " + 
					 "VALUES ('" + user + "', '" + pass + "', '" + firstname +
					 "', '" + lastname + "', '" + email + "', '" + enotify + "', '" + auth + "')";
		stmt.executeUpdate(sql);
    	}
    	
    	public synchronized void removeUser(String user) throws SQLException{
    		Statement stmt = con.createStatement();
    		String sql = "DELETE FROM user WHERE username = '" + user + "'";
    		stmt.executeUpdate(sql);
    	}
    	
    	public synchronized String getPass(String user)throws SQLException{
    		Statement stmt = con.createStatement();
    		String sql = "SELECT password FROM user WHERE BINARY username = '" + user + "'";
    		ResultSet result = stmt.executeQuery(sql);
    		String pass = "";
    		while(result.next()){
    			pass = result.getString("password");
    		}
    		return pass;
    	}
    	
    	public synchronized String getBooksOut(String username) throws SQLException{
    		Statement stmt = con.createStatement();
    		String sql = "SELECT booksout FROM user WHERE username = '" + username + "'";
    		ResultSet result = stmt.executeQuery(sql);
    		String books = " ";
    		while(result.next()){
    			books = result.getString("booksout");
    		}
			return books;
    	}
    	
    	public synchronized int getAuthorization(String user, String pass)throws SQLException{
    		Statement stmt = con.createStatement();
    		String sql = "SELECT auth FROM user WHERE BINARY username = '" + user + "' and BINARY password = '"+ pass +"'";
    		ResultSet result = stmt.executeQuery(sql);
    		int auth = 0;
    		while(result.next()){
    				auth = result.getInt("auth");
    		}
    		return auth;
    	}
    
    	public synchronized int setNewPass(String user, String oldPass, String newPass) throws SQLException{
    		Statement stmt = con.createStatement();
    		String sql = "SELECT password FROM user WHERE username = '" + user + "'";
    		ResultSet result = stmt.executeQuery(sql);
    		String tempPass = null;
    		while(result.next()){
    				tempPass = result.getString("password");
    		}
    		if(oldPass.equals(tempPass)){
    			//here is where we set the new pass word
    			sql = "UPDATE user SET password = '" + newPass + "' WHERE username = '" + user + "'";
    			System.out.println("Set to new pass: " + newPass);
    			stmt.executeUpdate(sql);
    			return 1;
    		}
    		else{
    			//notify that the password
    			System.out.println("Failed");
    			return -1;
    		}
    	}
    	
    //--------------------------------------------------------------------------
    
    //Librarian related queries
    //--------------------------------------------------------------------------
    public synchronized void CheckOut(String isbn, String username)throws SQLException, InvalidISBNException{
    	Statement stmt = con.createStatement();
    	String sql = "SELECT copiesin, copiesout, copiesreserved FROM book WHERE isbn = '" + isbn + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	int copiesin = 0;
    	int copiesout = 0;
    	int copiesr = 0;
    	System.out.println("ISBN: " + isbn);
    	while(result.next()){
    		copiesin = result.getInt("copiesin");
    		copiesout = result.getInt("copiesout");
    		copiesr = result.getInt("copiesreserved");
//    		System.out.println("ISBN: " + isbn + " copiesin: " + copiesin + " copiesout: " + copiesout);
    	}
    	if((copiesin - copiesr) <= 0){
    		//not enough copies to check one out to another user
    		sql = "SELECT userreserved FROM book WHERE isbn = '" + isbn + "'";
    		result = stmt.executeQuery(sql);
    		String usercmp = "";
    		while(result.next()){
    			usercmp = result.getString("userreserved");
    		}
    		Boolean contains = false;
    		String[] users = usercmp.split(",");
    		for(String str: users){
    			if(username.equals(str)){
    				contains = true;
    				break;
    			}
    			else
    				continue;
    		}
    		if(contains){
    			//check out to user
    			if(copiesin > 0){
    		    	copiesin--;
    		    	copiesout++;
    		    	copiesr--;
    		    	sql = "UPDATE book set copiesin = '" + copiesin + "' WHERE isbn = '" + isbn + "'";
    		    	stmt.executeUpdate(sql);
    		    	
    		    	sql = "UPDATE book set copiesout = '" + copiesout + "' WHERE isbn = '" + isbn + "'";
    		    	stmt.executeUpdate(sql);
    		    	
    		    	sql = "UPDATE book set copiesreserved = '" + copiesout + "' WHERE isbn = '" + isbn + "'";
    		    	stmt.executeUpdate(sql);
    		    	//Here we add the ISBN to the end of the users books out
    		    	//and the current system time to their dateout
    		    	String temp = "";
    		    	sql = "SELECT booksout FROM user WHERE username = '" + username + "'";
    		    	result = stmt.executeQuery(sql);
    		    	while(result.next()){
    		    		temp = result.getString("booksout");
    		    	}
    		    	String books = temp + isbn + ",";
    		    	String tempTimes = "";
    		    	sql = "SELECT dateout FROM user WHERE username = '" + username + "'";
    		    	result = stmt.executeQuery(sql);
    		    	while(result.next()){
    		    		tempTimes = result.getString("dateout");
    		    	}
    		    	long time = System.currentTimeMillis();
    		    	String now = String.valueOf(time);
    		    	String times = tempTimes + now + ",";
    		    	System.out.println("Times: " + times);
    		    	sql = "UPDATE user SET booksout = '" + books + "' WHERE username = '" + username + "'";
    		    	stmt.executeUpdate(sql);
    		    	sql = "UPDATE user SET dateout = '" + times + "' WHERE username = '" + username + "'";
    		    	stmt.executeUpdate(sql);
    		    	//might want to execute queries
    		    	}
    		    	else{
    		    		System.out.println("Problem checking in");
    		    		throw new InvalidISBNException("Error checkout");
    		    		}
    		}
    		else{
    			//book is not reserved to user
    			//noting will happen
    		}
    	}
    	else{//This is a normal check out
    	if(copiesin > 0){
    	copiesin--;
    	copiesout++;
    	
    	sql = "UPDATE book set copiesin = '" + copiesin + "' WHERE isbn = '" + isbn + "'";
    	stmt.executeUpdate(sql);
    	
    	sql = "UPDATE book set copiesout = '" + copiesout + "' WHERE isbn = '" + isbn + "'";
    	stmt.executeUpdate(sql);
    	
    	//Here we add the ISBN to the end of the users books out
    	//and the current system time to their dateout
    	String temp = "";
    	sql = "SELECT booksout FROM user WHERE username = '" + username + "'";
    	result = stmt.executeQuery(sql);
    	while(result.next()){
    		temp = result.getString("booksout");
    	}
    	String books = temp + isbn + ",";
    	String tempTimes = "";
    	sql = "SELECT dateout FROM user WHERE username = '" + username + "'";
    	result = stmt.executeQuery(sql);
    	while(result.next()){
    		tempTimes = result.getString("dateout");
    	}
    	long time = System.currentTimeMillis();
    	String now = String.valueOf(time);
    	String times = tempTimes + now + ",";
    	System.out.println("Times: " + times);
    	sql = "UPDATE user SET booksout = '" + books + "' WHERE username = '" + username + "'";
    	stmt.executeUpdate(sql);
    	sql = "UPDATE user SET dateout = '" + times + "' WHERE username = '" + username + "'";
    	stmt.executeUpdate(sql);
    	//might want to execute queries
    	}
    	else{
    		System.out.println("Problem checking in");
    		throw new InvalidISBNException("Error checkout");
    		}
    	}
    	//Book related to isbn will be added to user who checked it out
    	
    }
    
     public synchronized void CheckIn(String isbn, String username)throws SQLException, InvalidISBNException{
    	Statement stmt = con.createStatement();
    	System.out.println("Made it in checkin");
    	String sql = "SELECT copiesin, copiesout FROM book WHERE isbn = '" + isbn + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	int copiesin = 0;
    	int copiesout = 0;
    	while(result.next()){
    		System.out.println("Getting copiesin and out");
    		copiesin = result.getInt("copiesin");
    		copiesout = result.getInt("copiesout");
    	}
    	System.out.println("Copiesin: " + copiesin + " Copiesout: " + copiesout);
    	if(copiesout > 0){
    	copiesin++;
    	copiesout--;
    	sql = "UPDATE book SET copiesin = '" + copiesin + "' WHERE isbn = '" + isbn + "'";
    	stmt.executeUpdate(sql);
    	sql = "UPDATE book SET copiesout = '" + copiesout + "' WHERE isbn = '" + isbn + "'";
    	stmt.executeUpdate(sql);
    	System.out.println("Updates copiesin: " + copiesin + " copiesout: " + copiesout);
    	
    	
    	//Remove books and time from current books out and add book to history
    	ArrayList<String> books = new ArrayList<String>();
    	ArrayList<String> times = new ArrayList<String>();
    	String[] temp = {""};
    	String[] tempTimes = {""};
    	sql = "SELECT booksout FROM user WHERE username = '" + username + "'";
    	result = stmt.executeQuery(sql);
    	while(result.next()){
    		System.out.println("Set books out to array");
    		temp = result.getString("booksout").split(",");
    	}
    	for(int i = 0; i < temp.length; i++){System.out.println("Temp[i]: " + temp[i]);}
    	sql = "SELECT dateout FROM user WHERE username = '" + username + "'";
    	result = stmt.executeQuery(sql);
    	while(result.next()){
    		System.out.println("Set dateout to array");
    		tempTimes = result.getString("dateout").split(",");
    	}
    	
    	for(String s : temp){
    		System.out.println("added " + s + " to books list");
    		books.add(s);
    		System.out.println("After setting");
    	}
    	for(String s : tempTimes){
    		System.out.println("added " + s + " to times list");
    		times.add(s);
    	}
    	
    	String totalBooks = "";
    		System.out.println("Key: " + books.indexOf(isbn));
    		int key = books.indexOf(isbn);
    		if(key < 0){
    			return;
    		}
    		books.remove(key);
    		times.remove(key);
    		for(int j = 0; j < books.size(); j++){
    			totalBooks = totalBooks.concat(books.get(j) + ",");
    		}
    	String totalTimes = "";
    		for(int l = 0; l < books.size(); l++){
    			totalTimes = totalTimes.concat(times.get(l) + ",");
    		}
    		System.out.println(totalBooks);
    	
    		//System.out.println("Updates 2");
    	//update history
    		String hist = "";
        	sql = "SELECT history FROM user WHERE username = '" + username + "'";
        	result = stmt.executeQuery(sql);
        	while(result.next()){
        		hist = result.getString("history");
        	}
        	String history = hist + isbn + ",";
        	sql = "UPDATE user SET history = '" + history + "' WHERE username = '" + username + "'";
        	stmt.executeUpdate(sql);
        	System.out.println("Updateing Balance");
        	//update the balance
        	updateBalance(username);
        	System.out.println("Updated Balance");

        	//update the books now that everything is done
        	//put back whats out and the times
    		sql = "UPDATE user SET booksout = '" + totalBooks + "' WHERE username = '" + username + "'";
    		stmt.executeUpdate(sql);
    		sql = "UPDATE user SET dateout = '" + totalTimes + "' WHERE username = '" + username + "'";
    		stmt.executeUpdate(sql);
    	}
    	else{
    		System.out.println("Problem on checkin");
    		throw new InvalidISBNException("Error on checkin");
    	}
    	
    	//book will be removed from user checked out list
    	//and added to user history
    }
    
     public synchronized void SetCopies(String isbn, int copies)throws SQLException{
    	Statement stmt = con.createStatement();
    	//TODO: Set this to add books and not to set the total
    	String sql = "UPDATE book SET copiesin = '" + copies + "' WHERE isbn = '" + isbn + "'";
    	stmt.executeUpdate(sql);
    }
    
    public synchronized int GetAvailable(String isbn)throws SQLException{
    	Statement stmt = con.createStatement();
    	String sql = "SELECT copiesin FROM book WHERE isbn = '" + isbn + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	int copies = 0;
    	while(result.next()){
    		copies = result.getInt("copiesin");
    	}
    	
    	return copies;
    	
    }
    
    public synchronized float getBalance(String username) throws SQLException {
    	Statement stmt = con.createStatement();
    	String sql = "SELECT balance FROM user WHERE username = '" + username + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	float balance = 0;
    	while(result.next()){
    		balance = result.getFloat("balance");
    	}
    	
    	return balance;
    }
    
    public synchronized void updateBalance(String username) throws SQLException {
    	Statement stmt = con.createStatement();
    	
    	String sql = "SELECT balance FROM user WHERE username = '" + username + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	float temp = 0;
    	while(result.next()){
    		temp = result.getInt("balance");
    	}
    	float costs = 0;
    	sql = "SELECT dateout FROM user WHERE username = '" + username + "'";
    	result = stmt.executeQuery(sql);
    	while(result.next()){
    		System.out.println(result.getString("dateout"));
    		String[] dates = result.getString("dateout").split(",");
    		System.out.println("Calculate Costs");
    		costs = calculateCost(dates);
    	}
    	temp += costs;
    	sql = "UPDATE user SET balance = '" + temp + "' WHERE username = '" + username + "'";
    	stmt.executeUpdate(sql);
    }
    
    public synchronized void setRate(float rate) throws SQLException{
    	
    	Statement stmt = con.createStatement();
    	String sql = "UPDATE settings SET rate = " + rate + "";
    	stmt.executeUpdate(sql);
    	
    }
    
    public synchronized void setDays(int days) throws SQLException{
    	
    	Statement stmt = con.createStatement();
    	String sql = "UPDATE settings SET days = " + days + "";
    	stmt.executeUpdate(sql);
    	
    }
    
    //Check for any late fees of the user
    public synchronized float calculateCost(String[] dates) throws SQLException{
    	
    	//get the days late and rate values
    	Statement stmt = con.createStatement();
    	String sql = "SELECT rate, days FROM settings";
    	ResultSet result = stmt.executeQuery(sql);
    	float rate = 0;
    	int days = 0;
    	while(result.next()){
    		rate = result.getFloat("rate");
    		days = result.getInt("days");
    	}
    	
    	float cost = 0;
    	long time = System.currentTimeMillis();
    	for(int i = 0; i < dates.length; i++){
    		System.out.println("Convert to long");
    		long then = Long.parseLong(dates[i]);
    		System.out.println("Now: " + time + " Then: " + then);
    	    float elapsed = ((time - then)/86400000f);
    		System.out.println("Time out: " + elapsed);
    		if(elapsed > (float) days){
    			elapsed -= (float) days;
    			cost += (elapsed * rate);
    		}
    		else{
    			//nothing to do
    			System.out.println("Book not overdue");
    		}
    	}
    	
    	return cost;
    }
    
    public synchronized void payBalance(String username, float payment) throws SQLException{
    	Statement stmt = con.createStatement();
    	String sql = "SELECT balance FROM user WHERE username = '" + username + "'";
    	ResultSet result = stmt.executeQuery(sql);
    	String temp = "0.0";
    	while(result.next()){
    		temp = result.getString("balance");
    	}
    	float balance = Float.valueOf(temp);
    	balance -= payment;
    	sql = "UPDATE user SET balance = '" + balance + "' WHERE username = '" + username + "'"; 
    	
    	stmt.executeUpdate(sql);
    }
    
    public synchronized void setBalance(String username, float amount)throws SQLException{
    	Statement stmt = con.createStatement();
    	String sql = "SELECT balance FROM user WHERE username = '" + username + "'";
    	float bal = 0.0f;
    	ResultSet result = stmt.executeQuery(sql);
    	while(result.next()){
    		bal = result.getFloat("balance");
    	}
    	bal += amount;
    	
    	sql = "UPDATE user SET balance = " + bal + " WHERE username = '" + username + "'";
    	stmt.executeUpdate(sql);
    }
    //--------------------------------------------------------------------------
    
    //Administrative queries
    //Not sure what exactly we want from here
    
}
