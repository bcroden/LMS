package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;
import com.team1.books.Book;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;

public class CheckInBookResponse extends Response
{
    
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
}