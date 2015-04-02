package com.team1.formatting.responses;

import com.team1.formatting.queries.*;
import com.team1.books.*;
import com.team1.db.Dbwrapper;

public class SetEmailNotificationResponse extends Response
{
    public SetEmailNotificationResponse(boolean wasSuccessful)
    {
    	super(wasSuccessful);
    }
}