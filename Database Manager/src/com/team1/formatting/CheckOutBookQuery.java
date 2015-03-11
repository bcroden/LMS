package com.team1.formatting;

public class CheckOutBookQuery extends LibrarianQuery
{
    public CheckOutBookQuery(String sessionID, String isbn, String title, String author, String availability, String fName, String lName, String userID)
    {
        super(sessionID);
        // TODO Auto-generated constructor stub
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.availability = availability;
        this.fName = fName;
        this.lName = lName;
        this.userID = userID;
    }
    
    //delimeter for building toString
    public String d = ";";

    //book information for either a look up or book add in. 
    public String isbn,title,author;

    //variable for if a book is checked out/in
    public String availability;
    
    //patron info to add it to their checked out list
    public String fName,lName,userID;
    
    @Override
    public String toString() {
        String msg = "BookInfoQuery;"+sessionID+d+isbn+d+title+d+author+d+availability+d+fName+d+lName+d+userID;
        return msg;
    }
}