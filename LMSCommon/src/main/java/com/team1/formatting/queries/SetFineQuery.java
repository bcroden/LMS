package com.team1.formatting.queries;

public class SetFineQuery extends AdminQuery
{
	public static final String HEADER = "SetFineQuery";
	
	public SetFineQuery ()
	{
		this("-1",0);
	}
	
	public SetFineQuery (String sessionID)
	{
		this(sessionID,0);
	}
	
	public SetFineQuery (String sessionID, float rate)
	{
		super(sessionID);
		this.rate = rate;
	}
	
	public float rate;
	
	@Override
    public String toString() {
        String msg = HEADER+DELIMITER+sessionID+DELIMITER+rate;
        return msg;
    }
}