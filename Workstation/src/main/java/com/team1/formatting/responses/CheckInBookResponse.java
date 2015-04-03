package com.team1.formatting.responses;


public class CheckInBookResponse extends Response
{
    
    public CheckInBookResponse(boolean wasSuccessful, String sessionID)
    {
        super(wasSuccessful, sessionID);
    }
    
    public CheckInBookResponse() 
    {
		super(false, "0");
	}

	//Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "CheckInBookResponse" + DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}