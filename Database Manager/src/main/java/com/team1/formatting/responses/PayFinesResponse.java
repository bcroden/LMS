package com.team1.formatting.responses;

import com.team1.formatting.queries.*;
import com.team1.books.*;
import com.team1.db.Dbwrapper;

public class PayFinesResponse extends Response
{
	public PayFinesResponse()
	{
		super(false,"0");
	}
	
    public PayFinesResponse(boolean wasSuccessful, String sessionID)
    {
    	super(wasSuccessful,sessionID);
    }
    
    public void executePayFinesQuery(PayFinesQuery query)
    {
    	//execute a password change
    	
    	sessionID = query.sessionID;
    	return;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "PayFinesResponse" + DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}