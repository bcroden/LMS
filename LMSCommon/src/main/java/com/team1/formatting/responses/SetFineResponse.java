package com.team1.formatting.responses;

public class SetFineResponse extends AdminResponse
{
	public static final String HEADER = "SetFineResponse";
	
    public SetFineResponse(boolean wasSuccessful, String sessionID, String rate)
    {
    	super(wasSuccessful, sessionID);
    	this.rate = rate;
    }
    
    public SetFineResponse()
    {
    	super(false, "-1");
    	this.rate = "0";
    }
    
    public String rate;
    
    @Override
    public String toString() {
        String s;
        
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = HEADER + DELIMITER + s + DELIMITER + sessionID + DELIMITER + rate;

        return msg;
    }
}