package com.team1.formatting.responses;

import java.sql.SQLException;

import com.team1.formatting.queries.*;
import com.team1.authentication.Authentication;
import com.team1.books.*;
import com.team1.db.Dbwrapper;

public class PasswordChangeResponse extends Response
{
	public PasswordChangeResponse()
	{
		super(false,"0");
	}
	
    public PasswordChangeResponse(boolean wasSuccessful,String sessionID)
    {
    	super(wasSuccessful, sessionID);
    }
    
    public void executePasswordChangeQuery(PasswordChangeQuery query)
    {
		//Check users authentication
    	int temp = Authentication.getInstance().authenticate(query);
		sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
        
        if (status == 0) wasSuccessful = false;
        else if (status == 1 || status == 2 || status == 3)
        {
        	//execute a password change
        	int success = 0;
        	try {
    			success = Dbwrapper.getInstance().setNewPass(query.userName,query.oldPassword,query.newPassword);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        	if(success == 1)
        	{
        		wasSuccessful = true;
        	}
        	else if(success == -1)
        	{
        		wasSuccessful = false;
        	}
        	else
        	{
        		wasSuccessful = false;
        		System.out.print("Unexpected return value from setNewPass\n");
        	}
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
        String msg = "PasswordChangeResponse" + DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}