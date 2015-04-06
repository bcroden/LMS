package com.team1.formatting.responses;

public class ViewPatronInfoResponse extends LibrarianResponse
{
	public static final String HEADER = " ";
	
    public ViewPatronInfoResponse(boolean wasSuccessful, String sessionID)
    {
    	super(wasSuccessful, sessionID);
    }
}