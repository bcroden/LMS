package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.books.Book;
import com.team1.formatting.queries.BookInfoQuery;

public class BookInfoResponse extends Response
{
	public static final String HEADER = "BookInfoResponse";
	
    public ArrayList<Book> books = null;
    public static final String bookBreak = ";#;";

//  public boolean filled = false;
    
    public BookInfoResponse()
    {
    	super(false,"0");
    	books = new ArrayList<Book>();
    }
    
    public BookInfoResponse(boolean wasSuccessful, String sessionID, int numBooks, String strBooks)
    {
        super(wasSuccessful, sessionID);
        
        books = new ArrayList<Book>();
        
        String[] bookList = strBooks.split(bookBreak);
        for (int i = 0; i < numBooks; i++)
        {
            books.add(new Book(bookList[i]));
        }
        
    }
    
    
    private static boolean isValid(String string)
    {
        return string != null && !string.equals("");
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        int numBooks = 0;
        if (books.isEmpty()) System.out.print("\nbooks is empty, execute bookInfoQuery before attempting to read Response Object");
        else numBooks = books.size();
        
        if (this.wasSuccessful) s = "true";
        else s = "false";
        
        
        String msg = HEADER + DELIMITER + s + DELIMITER + sessionID + DELIMITER + numBooks + DELIMITER;
        
        System.out.println("numBooks = " + numBooks);
        
        for (int i = 0; i < numBooks; i++)
        {
            msg = msg.concat(books.get(i).getSerialized());
            System.out.println("Book = " + books.get(i).getSerialized());
            msg = msg.concat(bookBreak);
        }
        
        System.out.println("msg = " + msg);
        
        return msg;
    }
    
}
