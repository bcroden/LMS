package com.team1.formatting;

public class CheckInBookQuery extends LibrarianQuery
{
    public CheckInBookQuery(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }
    

    //book information for either a look up or book add in. 
    public String isbn;
    public String title;
    public String author;
    
    //variable for if a book is checked out/in
    public String availability;
    
    //patron info to remove it from their checked out list
    public String fName,lName,userID;
    
    
}