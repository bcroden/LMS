package com.team1.formatting.responses;

public class Response
{
    public boolean wasSuccessful;
    public static final String DELIMITER = ";&;";
    public static String responseType;

    
    public Response(boolean wasSuccessful)
    {
        this.wasSuccessful = wasSuccessful;
    }
    
    
    
    //Constructor to take a msg and turn it into a query object
    public static Response stringToResponse(String msg)
    {
        //split the msg into each piece (deliminated by ;)
        String[] str = msg.split(DELIMITER);
        
        //get the first piece
        //first piece determines response type
        responseType = str[0];
        boolean success;
        if(str[1].equalsIgnoreCase("true")) success = true;
        else success = false;
        
        //build a new response from the rest of the msg.split, checking the responseType to determine which kind of request to build
        if (responseType.equals("BookInfoQuery"))
        {
            BookInfoResponse response = new BookInfoResponse(success, str[2], str[3]);
            return response;
        }
        else if (responseType.equals("CheckOutBookResponse"))
        {
            CheckOutBookResponse response = new CheckOutBookResponse(success);
            return response;
        }
        else if (responseType.equals("CheckInBookResponse"))
        {
            CheckInBookResponse response = new CheckInBookResponse(success);
            return response;
        }
        else if (responseType.equals("LoginResponse"))
        {
            LoginResponse response = new LoginResponse(success);
            return response;
        }
        return new Response(false);
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
            response.executeCheckInBookQuery(query);
            return response;
        }
        if(query instanceof CheckOutBookQuery)
        {
            CheckOutBookResponse response = new CheckOutBookResponse();
            response.executeCheckOutBookQuery(query);
            return response;
        }
        if(query instanceof LoginQuery)
        {
            LogInResponse response = new LogInResponse();
            response.executeLogInResponse(query);
            return response;
        }
        
        return new Response(false);
    }

    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String msg = "Response request toString return message.";
        return msg;
    }

}