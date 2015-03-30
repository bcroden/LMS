package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;
import com.team1.books.Book;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;

public class CheckInBookResponse extends Response
{
    
    public CheckInBookResponse(boolean wasSuccessful)
    {
        this.wasSuccessful = wasSuccessful;
    }
    
    public static void executeCheckInBookQuery(CheckInBookQuery query)
    {
        try
        {
            Dbwrapper.getInstance().CheckIn(query.isbn);
            this.wasSuccessful = true;
        }
        catch(SQLException | InvalidISBNException e)
        {
            e.printStackTrace();
            this.wasSuccessful = false;
        }
        
        return;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (this.wasSuccessful) s = "true";
        else s = "false";
        String msg = "CheckInBookResponse" + this.DELIMITER + s;
        return msg;
    }
}