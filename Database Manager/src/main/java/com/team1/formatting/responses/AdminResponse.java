package com.team1.formatting.responses;

public class AdminResponse extends LibrarianResponse
{
	public static final String HEADER = " ";
	
    public AdminResponse(boolean wasSuccessful,String sessionID)
    {
    	super(wasSuccessful, sessionID);
    }
}