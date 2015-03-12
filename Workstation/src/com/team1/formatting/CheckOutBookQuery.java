package com.team1.formatting;

public class CheckOutBookQuery extends LibrarianQuery
{
    public CheckOutBookQuery(boolean wasSuccessful, String sessionID, String isbn, String title, String author, String availability, String fName, String lName, String userID)
    {
        super(wasSuccessful,sessionID);
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
    private String d = ";";

    //book information for either a look up or book add in. 
    public String isbn,title,author;

    //variable for if a book is checked out/in
    public String availability;
    
    //patron info to add it to their checked out list
    public String fName,lName,userID;
    
    @Override
    public String toString() {
        String s;
        if (wasSuccessful) s = "true";
        else s = "false";
        String msg = "CheckOutBookQuery;"+s+d+sessionID+d+isbn+d+title+d+author+d+availability+d+fName+d+lName+d+userID;
        return msg;
    }
    
    public static void main(String[] args) {
        CheckOutBookQuery query = new CheckOutBookQuery(false, "0", "054792822X", " ", " ", " ", " ", " ", " ");
        System.out.println(query);
        String toString = query.toString();
        Query query2 = Query.buildRequest(toString);
        System.out.println(query2);
    }
}