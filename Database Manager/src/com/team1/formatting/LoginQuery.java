package com.team1.formatting;

public class LoginQuery extends Query
{
    private String d = ";";
    
    public LoginQuery(boolean wasSuccessful, String sessionID,String userName, String password) {
        super(wasSuccessful, sessionID);
        // TODO Auto-generated constructor stub
        this.userName = userName;
        this.password = password;
    }
    //Login Info
    public String userName;
    public String password;
    
    @Override
    public String toString()
    {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "LoginQuery;"+s+d+sessionID+d+userName+d+password;
        return msg;
    }
}