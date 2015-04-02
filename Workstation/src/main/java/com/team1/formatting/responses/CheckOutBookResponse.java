package com.team1.formatting.responses;


public class CheckOutBookResponse extends Response
{
    
    public CheckOutBookResponse(boolean wasSuccessful, String sessionID)
    {
        super(wasSuccessful, sessionID);
    }
    
    public CheckOutBookResponse() 
    {
		super(false, "0");
	}

	//Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "CheckOutBookResponse" + DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}