package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;
import com.team1.books.Book;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;
import com.team1.formatting.queries.*;

public class CheckOutBookResponse extends Response
{
    
    public CheckOutBookResponse(boolean wasSuccessful, String sessionID)
    {
        super(wasSuccessful, sessionID);
    }
    
    public CheckOutBookResponse() 
    {
		super(false, "0");
	}

	public void executeCheckOutBookQuery(CheckOutBookQuery query)
    {
        try
        {
            System.out.println("Pre call");
            Dbwrapper.getInstance().CheckOut(query.isbn);
            wasSuccessful = true;
            System.out.println("Post call");
        }
        catch(SQLException | InvalidISBNException e)
        {
            e.printStackTrace();
            wasSuccessful = false;
        }
        
        sessionID = query.sessionID;
        return;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "CheckOutBookResponse" + DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}