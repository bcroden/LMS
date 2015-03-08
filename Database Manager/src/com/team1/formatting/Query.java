package com.team1.formatting;

public class Query
{
    //Variable for other functions to see what kind of query they are being given
    public static String queryType;
    
    //Alex added 3-7-15 this to get everything to compile 
    public Query(String msg)
    {
        // TODO Auto-generated constructor stub
    }

    //Constructor to take a msg and turn it into a query object
    public static Query buildRequest(String msg)
    {
        //split the msg into each piece (deliminated by ;)
        String[] str = msg.split(";");
        
        //get the first piece
        //first piece determines request type
        queryType = str[0];
        
        return new Query("");   //Alex added 3-7-15 this to get everything to compile
    }
    
    //method to return the object information in the form of a string
    public static String format()
    {
        String msg = "";
        //build the msg from query info
        return msg;
    }
        
}



