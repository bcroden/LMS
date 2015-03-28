package com.team1.formatting.responses;

public class Response
{
    public boolean wasSuccessful;
    
    public Response(boolean wasSuccessful)
    {
        this.wasSuccessful = wasSuccessful;
    }
    
    
    public static Response executeQuery(Query query)
    {

        if(query instanceof BookInfoQuery)
        {
            BookInfoResponse response = new BookInfoResponse();
            response.buildResponse(query);
            return response;
        }
        if(query instanceof CheckInBookQuery)
        {
            CheckInBookResponse response = new CheckInBookResponse();
            response.executeCheckInBookQuery(query)
            return response;
        }
        if(query instanceof CheckOutBookQuery)
        {
            CheckOutBookResponse response = new CheckOutBookResponse();
            response.executeCheckOutBookQuery(query)
            return response;
        }
        
        Response response = new Response(false);
        return response;
    }

}