package com.team1.formatting.responses;

import java.sql.SQLException;

import com.team1.formatting.queries.*;
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
    		
    	sessionID = query.sessionID;
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