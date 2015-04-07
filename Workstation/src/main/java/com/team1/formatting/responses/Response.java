package com.team1.formatting.responses;


public class Response
{
    public boolean wasSuccessful;
    public static final String DELIMITER = ";&;";
    public static String responseType;
    public String sessionID;

    public Response(boolean wasSuccessful,String sessionID)
    {
        this.wasSuccessful = wasSuccessful;
        this.sessionID = sessionID;
    }
    
    
    
    //Constructor to take a msg and turn it into a query object
    public static Response stringToResponse(String msg)
    {
    	System.out.println("begining stringToResponse");
        //split the msg into each piece (deliminated by ;)
        String[] str = msg.split(DELIMITER);
        
        System.out.println("after split");
        
        //get the first piece
        //first piece determines response type
        responseType = str[0];
        System.out.println("after setting response type");
        boolean success;
        if(str[1].equalsIgnoreCase("true")) success = true;
        else success = false;
        System.out.println("after setting success");
        
        //build a new response from the rest of the msg.split, checking the responseType to determine which kind of request to build
        if (responseType.equals(BookInfoResponse.HEADER))
        {
        	System.out.println("Before new BookInfoResponse in Response");
            BookInfoResponse response = new BookInfoResponse(success, str[2], Integer.parseInt(str[3]), str[4]);
            System.out.println("After new BookInfoResponse in Response");
            return response;
        }
        else if (responseType.equals(CheckOutBookResponse.HEADER))
        {
            CheckOutBookResponse response = new CheckOutBookResponse(success, str[2], str[3], str[4], Integer.parseInt(str[5]), str[6]);
            return response;
        }
        else if (responseType.equals(CheckInBookResponse.HEADER))
        {
            CheckInBookResponse response = new CheckInBookResponse(success, str[2], str[3], str[4], Integer.parseInt(str[5]), str[6]);
            return response;
        }
        else if (responseType.equals(LogInResponse.HEADER))
        {
            LogInResponse response = new LogInResponse(success, str[2], Integer.parseInt(str[3]));
            return response;
        }
        else if (responseType.equals(AddBookResponse.HEADER))
        {
            AddBookResponse response = new AddBookResponse(success, str[2]);
            return response;
        }
        else if (responseType.equals(PasswordChangeResponse.HEADER))
        {
            PasswordChangeResponse response = new PasswordChangeResponse(success, str[2]);
            return response;
        }
        else if (responseType.equals(PayFinesResponse.HEADER))
        {
            PayFinesResponse response = new PayFinesResponse(success, str[2]);
            return response;
        }
        return new Response(false, "0");
    }
    
    
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String msg = "Response request toString return message.";
        return msg;
    }

}