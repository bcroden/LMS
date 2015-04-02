package com.team1.formatting.responses;


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