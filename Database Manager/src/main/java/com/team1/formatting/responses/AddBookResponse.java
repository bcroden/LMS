package com.team1.formatting.responses;

import java.sql.SQLException;

import com.team1.formatting.queries.*;
import com.team1.books.*;
import com.team1.db.Dbwrapper;


public class AddBookResponse extends LibrarianResponse
{
    public AddBookResponse(boolean wasSuccessful)
    {
        super(wasSuccessful);
    }
    
    public AddBookResponse() 
    {
		super(false);
	}

	public void executeAddBookQuery(AddBookQuery query) throws InvalidISBNException, SQLException
    {
        //build book object from isbn
        Book book = BookFinder.getBookFromGoogle(query.isbn);
        Dbwrapper.getInstance().addBook(book);
        return;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (this.wasSuccessful) s = "true";
        else s = "false";
        String msg = "AddBookResponse" + this.DELIMITER + s;
        return msg;
    }
}