package com.team1.formatting.queries;

public class LoginQuery extends Query
{
	public static final String HEADER = "LoginQuery";
	
	public LoginQuery()
	{
		super("0");
	}
	
    public LoginQuery(String userName, String password) {
        super("0");
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
        String msg = HEADER+DELIMITER+sessionID+DELIMITER+userName+DELIMITER+password;
        return msg;
    }
    
    public static void main(String[] args) {
        LoginQuery query = new LoginQuery(" ", " ");
        System.out.println(query);
        String toString = query.toString();
        Query query2 = Query.buildRequest(toString);
        System.out.println(query2);
    }
}