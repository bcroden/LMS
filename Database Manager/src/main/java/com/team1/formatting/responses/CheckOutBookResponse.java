package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.authentication.Authentication;
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
		//Check users authentication
		int status = Authentication.getInstance().authenticate(query);
        
        if (status == 0 || status == 1) wasSuccessful = false;
        else if (status == 2 || status == 3)
        {
            try
            {
                System.out.println("Pre call");
                Dbwrapper.getInstance().CheckOut(query.isbn,query.userID);
                wasSuccessful = true;
                System.out.println("Post call");
            }
            catch(SQLException | InvalidISBNException e)
            {
                e.printStackTrace();
                wasSuccessful = false;
            }
            
            sessionID = query.sessionID;
        }
        else
        {
            wasSuccessful = false;
            sessionID = query.sessionID;
            System.out.print("unexpected return value from authenticate...\n");
        }
        
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