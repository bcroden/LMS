package com.team1.formatting.responses;

import java.sql.SQLException;

import com.team1.formatting.queries.*;
import com.team1.authentication.Authentication;
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
		//Check users authentication
		int status = Authentication.getInstance().authenticate(query);
        
        if (status == 0 || status == 1) wasSuccessful = false;
        else if (status == 2 || status == 3)
        {
        	//execute a pay fine
        	try {
    			Dbwrapper.getInstance().payBalance(query.userName,query.paymentAmount);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
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
        String msg = "PayFinesResponse" + DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}