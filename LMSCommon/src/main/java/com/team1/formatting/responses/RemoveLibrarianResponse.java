package com.team1.formatting.responses;

public class RemoveLibrarianResponse extends AdminResponse
{
	public static final String HEADER = "RemoveLibrarianResponse";
	
    public RemoveLibrarianResponse(boolean wasSuccessful, String sessionID, String userName, String fName, String lName)
    {
    	super(wasSuccessful, sessionID);
    	this.userName = userName;
    	this.fName = fName;
    	this.lName = lName;
    	this.auth = "2";
    }
    
    public RemoveLibrarianResponse()
    {
    	super(false, "-1");
    	this.userName = " ";
    	this.fName = " ";
    	this.lName = " ";
    	this.auth = "2";
    }
    
    public String userName, fName, lName, auth;
    
    @Override
    public String toString() {
        String s;
        
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = HEADER + DELIMITER + s + DELIMITER + sessionID + DELIMITER + userName + DELIMITER + fName + DELIMITER + lName;

        return msg;
    }
}