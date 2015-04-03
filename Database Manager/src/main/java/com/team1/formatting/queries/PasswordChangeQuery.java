package com.team1.formatting.queries;

public class PasswordChangeQuery extends Query
{
    public String oldPassword;
    public String newPassword;
    public String userName;
    
    public PasswordChangeQuery(String sessionID)
    {
    	this(sessionID, " ", " ", " ");
    }
    
    public PasswordChangeQuery(String sessionID, String oldPassword, String newPassword, String userName)
    {
        super(sessionID);
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.userName = userName;
    }
    
    @Override
    public String toString() {
        String msg = "PasswordChangeQuery"+DELIMITER+sessionID+DELIMITER+oldPassword+DELIMITER+newPassword+DELIMITER+userName;
        return msg;
    }
}