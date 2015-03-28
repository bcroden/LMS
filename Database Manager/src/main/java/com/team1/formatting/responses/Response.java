package com.team1.formatting.responses;

public class Response
{
    public boolean wasSuccessful;
    
    
    
    public static void executeQuery()
    {
        
        /*
        if(query instanceof BookInfoQuery)
            return executeBookInfoQuery((BookInfoQuery) query);
        if(query instanceof CheckInBookQuery)
            return executeCheckInBookQuery((CheckInBookQuery) query);
        if(query instanceof CheckOutBookQuery)
            return executeCheckOutBookQuery((CheckOutBookQuery) query);
        */
        
        this.buildResponse();
        
        return new Query(false);
    }

}