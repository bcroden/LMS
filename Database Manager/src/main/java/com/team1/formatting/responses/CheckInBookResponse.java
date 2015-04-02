package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;
import com.team1.books.Book;
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
        try
        {
            Dbwrapper.getInstance().CheckIn(query.isbn);
            wasSuccessful = true;
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
        String msg = "CheckInBookResponse" + DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}