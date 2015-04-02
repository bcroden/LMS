package com.team1.formatting.responses;


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