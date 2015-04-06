package com.team1.formatting.responses;

public class AddLibrarianResponse extends AdminResponse
{
	public static final String HEADER = " ";
	
    public AddLibrarianResponse(boolean wasSuccessful, String sessionID)
    {
    	super(wasSuccessful, sessionID);
    }
}