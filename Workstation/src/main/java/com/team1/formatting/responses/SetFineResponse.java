package com.team1.formatting.responses;

public class SetFineResponse extends AdminResponse
{
	public static final String HEADER = " ";
	
    public SetFineResponse(boolean wasSuccessful, String sessionID)
    {
    	super(wasSuccessful, sessionID);
    }
}