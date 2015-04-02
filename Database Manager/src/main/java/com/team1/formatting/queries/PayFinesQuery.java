package com.team1.formatting.queries;

public class PayFinesQuery extends LibrarianQuery
{
    public int paymentAmount;
    public String userName;
    
    public PayFinesQuery(String sessionID, String userName, int paymentAmount)
    {
        super(sessionID);
        this.userName = userName;
        this.paymentAmount = paymentAmount;
    }
    
    @Override
    public String toString() {
        String msg = "PayFinesQuery"+DELIMITER+sessionID+DELIMITER+userName+DELIMITER+paymentAmount;
        return msg;
    }
}