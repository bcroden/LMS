package com.team1.formatting.responses;

import com.team1.authentication.Authentication;
import com.team1.formatting.queries.LoginQuery;

public class LogInResponse extends Response
{
	public static final String HEADER = "LogInResponse";
	
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
    	
    	int temp = auth.authenticate(query);
    	
    	System.out.println("after after setting SessionID = " + temp);
    	
    	this.sessionID = Integer.toString(temp);
    	
    	System.out.println("temp = " + temp);

    	if(temp != 0)
    		this.status = auth.getLevel(temp);
    	
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
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = HEADER + DELIMITER + s + DELIMITER + sessionID + DELIMITER + status;
        return msg;
    }
}