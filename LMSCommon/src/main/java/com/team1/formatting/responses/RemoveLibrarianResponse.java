package com.team1.formatting.responses;

public class RemoveLibrarianResponse extends AdminResponse
{
	public static final String HEADER = " ";
	
    public RemoveLibrarianResponse(boolean wasSuccessful, String sessionID)
    {
    	super(wasSuccessful, sessionID);
    }
}