package com.team1.formatting.queries;

public class RemoveLibrarianQuery extends AdminQuery
{
	public static final String HEADER = "RemoveLibrarianQuery";
	
	public RemoveLibrarianQuery ()
	{
		this("-1"," ");
	}
	
	public RemoveLibrarianQuery (String sessionID)
	{
		this(sessionID," ");
	}
	
	public RemoveLibrarianQuery (String sessionID, String userName)
	{
		super(sessionID);
	}
	
	public String userName;
	
	@Override
    public String toString() {
        String msg = HEADER+DELIMITER+sessionID+DELIMITER+userName;
        return msg;
    }
}