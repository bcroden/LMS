package com.team1.formatting.queries;

public class AddLibrarianQuery extends AdminQuery
{
	public static final String HEADER = "AddLibrarianQuery";
	
	public AddLibrarianQuery()
    {
    	super("-1");
    }
	
    public AddLibrarianQuery(String sessionID)
    {
    	this(sessionID," "," "," "," "," ", 0);
    }
    
    public AddLibrarianQuery(String sessionID, String userName, String password, String email, String fName, String lName, int enotify)
    {
    	super(sessionID);
    	this.userName = userName;
    	this.password = password;
    	this.email = email;
    	this.fName = fName;
    	this.lName = lName;
    	this.enotify = enotify;
    }
    
    public String userName, password, email, fName, lName;
    public int enotify;
    
    @Override
    public String toString() {
        String msg = HEADER+DELIMITER+sessionID+DELIMITER+userName+DELIMITER+password+DELIMITER+email+DELIMITER+fName+DELIMITER+lName+DELIMITER+enotify;
        return msg;
    }
}