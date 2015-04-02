package com.team1.formatting.queries;

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
    
/*    //Methods to set single variables
    public CheckOutBookQuery searchByISBN(String isbn) {
        this.isbn = isbn;
        return this;
    }*/
    
    public CheckOutBookQuery(String sessionId) {
    	this(sessionId, " ", " ", " ", " ", " ", " ", " ");
	}

	public CheckOutBookQuery logByfName(String fName, String ISBN) {
        this.fName = fName;
        this.isbn = ISBN;
        return this;
    }
    
    public CheckOutBookQuery logBylName(String lName, String ISBN) {
        this.lName = lName;
        this.isbn = ISBN;
        return this;
    }
    
    public CheckOutBookQuery logByuserID(String userID, String ISBN) {
        this.userID = userID;
        this.isbn = ISBN;
        return this;
    }
    

    //book information for either a look up or book add in. 
    public String isbn,title,author;

    //variable for if a book is checked out/in
    public String availability;
    
    //patron info to add it to their checked out list
    public String fName,lName,userID;
    
    @Override
    public String toString() {
        String msg = "CheckOutBookQuery"+DELIMITER+sessionID+DELIMITER+isbn+DELIMITER+title+DELIMITER+author+DELIMITER+availability+DELIMITER+fName+DELIMITER+lName+DELIMITER+userID;
        return msg;
    }
    
    public static void main(String[] args) {
        CheckOutBookQuery query = new CheckOutBookQuery("0", "054792822X", " ", " ", " ", " ", " ", " ");
        System.out.println(query);
        String toString = query.toString();
        Query query2 = Query.buildRequest(toString);
        System.out.println(query2);
    }
}