package com.team1.formatting.responses;

public class CreatePatronAccountResponse extends Response
{
	public static final String HEADER = " ";
	
    public CreatePatronAccountResponse(boolean wasSuccessful, String sessionID)
    {
    	super(wasSuccessful, sessionID);
    }
}