package com.team1.formatting.responses;

public class ManualAddBookResponse extends LibrarianResponse
{
	public static final String HEADER = "ManualAddBookResponse";
	
    public ManualAddBookResponse(boolean wasSuccessful, String sessionID)
    {
        super(wasSuccessful, sessionID);
    }
    
    public ManualAddBookResponse() 
    {
		super(false, "0");
	}
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (this.wasSuccessful) s = "true";
        else s = "false";
        String msg = HEADER + DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}