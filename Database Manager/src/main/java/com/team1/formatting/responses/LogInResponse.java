package com.team1.formatting.responses;

import java.sql.SQLException;
import java.util.ArrayList;
import com.team1.books.Book;
import com.team1.books.InvalidISBNException;
import com.team1.db.Dbwrapper;
import com.team1.authentication.Authentication;
import com.team1.formatting.queries.*;

public class LogInResponse extends Response
{
    public static int status;
    
    public LogInResponse()
    {
    	super(false);
    }
    
    public LogInResponse(boolean wasSuccessful, String sessionID)
    {
        super(wasSuccessful);
        this.sessionID = sessionID;
    }
    
    public void executeLogInQuery(LoginQuery query)
    {

        //execute a login
        status = Authentication.authenticate(query);
        
        if (status == 0) wasSuccessful = false;
        else if (status >= 1 && status <= 3)
        {
            wasSuccessful = true;
            sessionID = query.sessionID;
        }
        else
        {
            wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "LogInResponse" + DELIMITER + s + sessionID;
        return msg;
    }
}