package com.team1.formatting.responses;

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