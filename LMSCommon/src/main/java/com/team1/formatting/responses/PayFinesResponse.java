package com.team1.formatting.responses;

import java.sql.SQLException;

import com.team1.formatting.queries.PayFinesQuery;

public class PayFinesResponse extends Response
{
	public static final String HEADER = "PayFinesResponse";
	
	public String fines;
	
	public PayFinesResponse()
	{
		super(false,"0");
	}
	
    public PayFinesResponse(boolean wasSuccessful, String sessionID, String fines)
    {
    	super(wasSuccessful,sessionID);
    	this.fines = fines;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = HEADER + DELIMITER + s + DELIMITER + sessionID + DELIMITER + this.fines;
        return msg;
    }
}