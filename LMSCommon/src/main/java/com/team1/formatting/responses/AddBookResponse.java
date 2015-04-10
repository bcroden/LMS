package com.team1.formatting.responses;

import java.sql.SQLException;

import com.team1.books.Book;
import com.team1.books.BookFinder;
import com.team1.books.InvalidISBNException;
import com.team1.formatting.queries.AddBookQuery;

public class AddBookResponse extends LibrarianResponse
{
	public static final String HEADER = "AddBookResponse";
	
    public AddBookResponse(boolean wasSuccessful, String sessionID)
    {
        super(wasSuccessful, sessionID);
    }
    
    public AddBookResponse() 
    {
		super(false, "0");
	}
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (this.wasSuccessful) s = "true";
        else s = "false";
        String msg = HEADER + DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}