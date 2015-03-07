package com.team1.formatting;

public class Query
{
    //Variable for other functions to see what kind of query they are being given
    public static String queryType;
    
    //Constructor to take a msg and turn it into a query object
    public static Query buildRequest(String msg)
    {
        //split the msg into each piece (deliminated by ;)
        String[] str = msg.split(";");
        
        //get the first piece
        //first piece determines request type
        queryType = str[0];
        
        
    }
    
    //method to return the object information in the form of a string
    public static String format()
    {
        String msg = "";
        //build the msg from query info
        return msg;
    }
        
}



