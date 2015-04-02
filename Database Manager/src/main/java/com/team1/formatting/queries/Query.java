package com.team1.formatting.queries;

public class Query
{
    public static final String DELIMITER = ";";
    //Variable for other functions to see what kind of query they are being given
    public static String queryType;
    public String sessionID;
    
    //Alex added 3-7-15 this to get everything to compile 
    public Query(String sessionID)
    {
        this.sessionID = sessionID;
    }

    //Constructor to take a msg and turn it into a query object
    public static Query buildRequest(String msg)
    {
        //split the msg into each piece (deliminated by ;)
        String[] str = msg.split(DELIMITER);
        
        //get the first piece
        //first piece determines request type
        queryType = str[0];
        
        //build a new request from the rest of the msg.split, checking the queryType to determine which kind of request to build
        if (queryType.equals("BookInfoQuery"))
        {
            BookInfoQuery request = new BookInfoQuery(str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8]);
            return request;
        }
        else if (queryType.equals("CheckOutBookQuery"))
        {
            CheckOutBookQuery request = new CheckOutBookQuery(str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8]);
            return request;
        }
        else if (queryType.equals("CheckInBookQuery"))
        {
            CheckInBookQuery request = new CheckInBookQuery(str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8]);
            return request;
        }
        else if (queryType.equals("LoginQuery"))
        {
            LoginQuery request = new LoginQuery(str[1], str[2], str[3]);    //sessionID is not required (included sessionID parameter)
            return request;
        }
        else if (queryType.equals("AddBookQuery"))
        {
            AddBookQuery request = new AddBookQuery(str[1],str[2],str[3]);    //sessionID is not required
            return request;
        }
        else if (queryType.equals("PasswordChangeQuery"))
        {
            PasswordChangeQuery request = new PasswordChangeQuery(str[1],str[2],str[3],str[4]);    //sessionID is not required
            return request;
        }
        else if (queryType.equals("PayFinesQuery"))
        {
            PayFinesQuery request = new PayFinesQuery(str[1],str[2],str[3]);    //sessionID is not required
            return request;
        }
        return new Query(false);
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String msg = "Query request toString return message.";
        return msg;
    }
    
    //Query isValid command
    public static boolean isValid(String string)
    {
        return string != null && !string.equals("");
    }
}



