package com.team1.formatting.responses;

import java.sql.SQLException;

import com.team1.formatting.queries.*;
import com.team1.authentication.Authentication;
import com.team1.books.*;
import com.team1.db.Dbwrapper;


public class AddBookResponse extends LibrarianResponse
{
    public AddBookResponse(boolean wasSuccessful, String sessionID)
    {
        super(wasSuccessful, sessionID);
    }
    
    public AddBookResponse() 
    {
		super(false, "0");
	}

	public void executeAddBookQuery(AddBookQuery query) throws InvalidISBNException, SQLException
    {
		//Check users authentication
		int temp = Authentication.getInstance().authenticate(query);
		sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
        
        if (status == 0 || status == 1) wasSuccessful = false;
        else if (status == 2 || status == 3)
        {
        	//build book object from isbn
            Book book = BookFinder.getBookFromGoogle(query.isbn);
            Dbwrapper.getInstance().addBook(book, query.numCopies);
            
            wasSuccessful = true;
        }
        else
        {
            wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
		
        return;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (this.wasSuccessful) s = "true";
        else s = "false";
        String msg = "AddBookResponse"+ DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}