package com.team1.formatting.queries;

import com.team1.books.Book;

public class ManualAddBookQuery extends LibrarianQuery
{
	public static final String HEADER = "ManualAddBookQuery";
	
    public int numCopies;
    public Book book;
    
    public ManualAddBookQuery(String sessionID)
    {
    	super(sessionID);
    	this.numCopies = 0;
    }
    
    public ManualAddBookQuery(String sessionID, String serializedBook, int numCopies)
    {
    	super(sessionID);
    	this.numCopies = numCopies;
    	this.book = new Book(serializedBook);
    }
    
    public ManualAddBookQuery(String sessionID, String isbn, String title, String author, String publisher,
    						  String datePublished, String genre, String url, int numCopies)
    {
        super(sessionID);
        this.book = new Book(isbn, title, author, publisher, datePublished, genre, url);
        this.numCopies = numCopies;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        //toString for isbn
    	String serializedBook = book.getSerialized();
        String msg = HEADER + DELIMITER + sessionID + DELIMITER + serializedBook + DELIMITER + numCopies;
        return msg;
    }
}