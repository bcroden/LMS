package com.team1.formatting.queries;

public class PayFinesQuery extends LibrarianQuery
{
	public static final String HEADER = "PayFinesQuery";
	
    public float paymentAmount;
    public String userName;
    
    public PayFinesQuery(String sessionID)
    {
    	this(sessionID, " ", 0);
    }
    
    public PayFinesQuery(String sessionID, String userName, float paymentAmount)
    {
        super(sessionID);
        this.userName = userName;
        this.paymentAmount = paymentAmount;
    }
    
    @Override
    public String toString() {
        String msg = HEADER+DELIMITER+sessionID+DELIMITER+userName+DELIMITER+paymentAmount;
        return msg;
    }
}