package com.team1.formatting.queries;

public class AddBookQuery extends LibrarianQuery
{
    public int numCopies;
    public String isbn;
    
    public AddBookQuery(String sessionID)
    {
    	this(sessionID, " ", 0);
    }
    
    public AddBookQuery(String sessionID,String isbn, int numCopies)
    {
        super(sessionID);
        this.isbn = isbn;
        this.numCopies = numCopies;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        //toString for isbn
        String msg = "AddBookQuery"+ DELIMITER + sessionID + DELIMITER + isbn + DELIMITER + numCopies;
        return msg;
    }
}