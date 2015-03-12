package com.team1.formatting;

public class Query
{
    //Variable for other functions to see what kind of query they are being given
    public String queryType;
    public boolean wasSuccessful;
    
    //Alex added 3-7-15 this to get everything to compile 
    public Query(boolean wasSuccessful)
    {
        // TODO Auto-generated constructor stub
        this.wasSuccessful = wasSuccessful;
    }

    //Constructor to take a msg and turn it into a query object
    public static Query buildRequest(String msg)
    {
        //split the msg into each piece (deliminated by ;)
        String[] str = msg.split(";");
        
        //get the first piece
        //first piece determines request type
        queryType = str[0];
        boolean success;
        if(str[1].compareTo("true") success = true;
        else success = false;
        
        //build a new request from the rest of the msg.split, checking the queryType to determine which kind of request to build
        if (queryType == "BookInfoQuery")
        {
            BookInfoQuery request = new BookInfoQuery(success, str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9]);
            return request;
        }
        else if (queryType == "CheckOutBookQuery")
        {
            CheckOutBookQuery request = new CheckOutBookQuery(success, str[2], str[3], str[4], str[5], str[6], str[7], str[8]);
            return request;
        }
        else if (queryType == "CheckInBookQuery")
        {
            CheckInBookQuery request = new CheckInBookQuery(success, str[2], str[3], str[4], str[5], str[6], str[7], str[8]);
            return request;
        }
        else if (queryType == "LoginQuery")
        {
            LoginQuery request = new LoginQuery(success,str[2],str[3]);
            return request;
        }
        return new Query("error");
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String msg = "Query request toString return message.";
        return msg;
    }
        
}



