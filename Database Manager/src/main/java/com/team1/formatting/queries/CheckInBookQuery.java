package com.team1.formatting.queries;

public class CheckInBookQuery extends LibrarianQuery
{
    public CheckInBookQuery(boolean wasSuccessful, String sessionID, String isbn, String title, String author, String availability, String fName, String lName, String userID)
    {
        super(wasSuccessful, sessionID);
        // TODO Auto-generated constructor stub
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.availability = availability;
        this.fName = fName;
        this.lName = lName;
        this.userID = userID;
    }
    
    //Methods to set single variables
    public CheckInBookQuery searchByISBN(String isbn) {
        this.isbn = isbn;
        return this;
    }
    
    public CheckInBookQuery logByfName(String fName) {
        this.fName = fName;
        return this;
    }
    
    public CheckInBookQuery logBylName(String lName) {
        this.lName = lName;
        return this;
    }
    
    public CheckInBookQuery logByuserID(String userID) {
        this.userID = userID;
        return this;
    }
    
    //delimeter for building toString
//    private String d = ";";

    //book information for either a look up or book add in. 
    public String isbn,title,author;
    
    //variable for if a book is checked out/in
    public String availability;
    
    //patron info to remove it from their checked out list
    public String fName,lName,userID;
    
    @Override
    public String toString() {
        String s;
        if (this.wasSuccessful) s = "true";
        else s = "false";
        String msg = "CheckInBookQuery;"+s+this.DELIMITER+sessionID+this.DELIMITER+isbn+this.DELIMITER+title+this.DELIMITER+author+this.DELIMITER+availability+this.DELIMITER+fName+this.DELIMITER+lName+this.DELIMITER+userID;
        return msg;
    }
    
    public static void main(String[] args) {
        CheckInBookQuery query = new CheckInBookQuery(false, "0", "054792822X", " ", " ", " ", " ", " ", " ");
        System.out.println(query);
        String toString = query.toString();
        Query query2 = Query.buildRequest(toString);
        System.out.println(query2);
    }
}