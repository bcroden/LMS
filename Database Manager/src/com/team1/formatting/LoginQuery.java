package com.team1.formatting;

public class LoginQuery extends Query
{
    public String d = ";";
    
    public LoginQuery(String userName, String password) {
        super("");
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
        String msg = "LoginQuery;"+userName+d+password;
        return msg;
    }
}