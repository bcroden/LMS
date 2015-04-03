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
	public int status = 0;
    public LogInResponse()
    {
    	super(false,"0");
    	status = 0;
    }
    
    public LogInResponse(boolean wasSuccessful, String sessionID, int status)
    {
        super(wasSuccessful,sessionID);
        this.status = status;
    }
    
    public void executeLogInQuery(LoginQuery query)
    {
    	System.out.println("before authenticate");
        //execute a login
    	Authentication auth = Authentication.getInstance();
    	
    	System.out.println("after getInstance");
    	
    	sessionID = "" + auth.authenticate(query);
    	//TODO: ADD Ryans thing
    	status = auth.getLevel(Integer.getInteger(sessionID));
    	
    	System.out.println("after authenticate");
        
    	System.out.println("Status ==" + status);
    	
        if (status == 0) wasSuccessful = false;
        else if (status >= 1 && status <= 3)
        {
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
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "LogInResponse" + DELIMITER + s + DELIMITER + sessionID + DELIMITER + status;
        return msg;
    }
}