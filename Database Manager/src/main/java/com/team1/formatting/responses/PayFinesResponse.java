package com.team1.formatting.responses;

import java.sql.SQLException;

import com.team1.authentication.Authentication;
import com.team1.db.Dbwrapper;
import com.team1.formatting.queries.PayFinesQuery;

public class PayFinesResponse extends Response
{
	public static final String HEADER = "PayFinesResponse";
	
	public String fines;
	
	public PayFinesResponse()
	{
		super(false,"0");
	}
	
    public PayFinesResponse(boolean wasSuccessful, String sessionID, String fines)
    {
    	super(wasSuccessful,sessionID);
    	this.fines = fines;
    }
    
    public void executePayFinesQuery(PayFinesQuery query)
    {
		//Check users authentication
    	int temp = Authentication.getInstance().authenticate(query);
		sessionID = Integer.toString(temp);
		int status = Authentication.getInstance().getLevel(temp);
        
        if (status == 0 || status == 1) wasSuccessful = false;
        else if (status == 2 || status == 3)
        {
        	//execute a pay fine
        	try {
        		wasSuccessful = true;
    			Dbwrapper.getInstance().payBalance(query.userName,query.paymentAmount);
    			this.fines = ""+Dbwrapper.getInstance().getBalance(query.userName);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        else
        {
            wasSuccessful = false;
            System.out.print("unexpected return value from authenticate...\n");
        }
    	
    	
    	return;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = HEADER + DELIMITER + s + DELIMITER + sessionID + DELIMITER + this.fines;
        return msg;
    }
}