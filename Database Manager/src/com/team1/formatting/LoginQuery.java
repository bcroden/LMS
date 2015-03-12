package com.team1.formatting;

public class LoginQuery extends Query
{
    private String d = ";";
    
    public LoginQuery(boolean wasSuccessful, String userName, String password) {
        super(wasSuccessful);
        // TODO Auto-generated constructor stub
        this.userName = userName;
        this.password = password;
    }
    //Login Info
    public String userName;
    public String password;
    //make session ID optional
    public String sessionID = " ";
    
    @Override
    public String toString()
    {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "LoginQuery;"+s+d+sessionID+d+userName+d+password;
        return msg;
    }
    
    public static void main(String[] args) {
        LoginQuery query = new LoginQuery(false, " ", " ");
        System.out.println(query);
        String toString = query.toString();
        Query query2 = Query.buildRequest(toString);
        System.out.println(query2);
    }
}