package com.team1.formatting.responses;



public class AddBookResponse extends LibrarianResponse
{
    public AddBookResponse(boolean wasSuccessful, String sessionID)
    {
        super(wasSuccessful, sessionID);
    }
    
    public AddBookResponse() 
    {
		super(false, "0");
	}
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (this.wasSuccessful) s = "true";
        else s = "false";
        String msg = "AddBookResponse"+ DELIMITER + s + DELIMITER + sessionID;
        return msg;
    }
}