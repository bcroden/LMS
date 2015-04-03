package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.authentication.Authentication;
import com.team1.books.Book;
import com.team1.books.BookFinder;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;
import com.team1.formatting.queries.*;

public class CheckInBookResponse extends Response
{
    
    public CheckInBookResponse(boolean wasSuccessful, String sessionID)
    {
        super(wasSuccessful, sessionID);
    }
    
    public CheckInBookResponse() 
    {
		super(false, "0");
	}

	public void executeCheckInBookQuery(CheckInBookQuery query)
    {
		//Check users authentication
		int status = Authentication.getInstance().authenticate(query);
        
        if (status == 0 || status == 1) wasSuccessful = false;
        else if (status == 2 || status == 3)
        {
            try
            {
                Dbwrapper.getInstance().CheckIn(query.isbn,query.userID);
                wasSuccessful = true;
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
        String msg = "CheckInBookResponse" + DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}