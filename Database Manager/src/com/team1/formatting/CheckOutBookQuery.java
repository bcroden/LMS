package com.team1.formatting;

public class CheckOutBookQuery extends LibrarianQuery
{
    public CheckOutBookQuery(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }
    

    //book information for either a look up or book add in. 
    public String isbn;
    public String title;
    public String author;
    
    //variable for if a book is checked out/in
    public String availability;
    
    //patron info to add it to their checked out list
    public String fName,lName,userID;
    
}