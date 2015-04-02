package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;
import com.team1.books.Book;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;
import com.team1.formatting.queries.*;

public class CheckInBookResponse extends Response
{
    
    public CheckInBookResponse(boolean wasSuccessful)
    {
        super(wasSuccessful);
    }
    
    public CheckInBookResponse() 
    {
		super(false);
	}

	public void executeCheckInBookQuery(CheckInBookQuery query)
    {
        try
        {
            Dbwrapper.getInstance().CheckIn(query.isbn);
            this.wasSuccessful = true;
        }
        catch(SQLException | InvalidISBNException e)
        {
            e.printStackTrace();
            wasSuccessful = false;
        }
        
        return;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "CheckInBookResponse" + DELIMITER + s;
        return msg;
    }
}