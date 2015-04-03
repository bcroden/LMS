package com.team1.formatting.responses;


public class LogInResponse extends Response
{
    public static int status;
    
    public LogInResponse()
    {
    	super(false,"0");
    }
    
    public LogInResponse(boolean wasSuccessful, String sessionID)
    {
        super(wasSuccessful,sessionID);
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "LogInResponse" + DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}