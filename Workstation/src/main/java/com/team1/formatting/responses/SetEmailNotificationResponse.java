package com.team1.formatting.responses;


public class SetEmailNotificationResponse extends Response
{
    public SetEmailNotificationResponse(boolean wasSuccessful, String sessionID)
    {
    	super(wasSuccessful, sessionID);
    }
}