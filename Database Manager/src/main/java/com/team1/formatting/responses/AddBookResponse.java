package com.team1.formatting.responses;

public class AddBookResponse extends LibrarianResponse
{
    public AddBookResponse(boolean wasSuccessful)
    {
        this.wasSuccessful = wasSuccessful;
    }
    
    public static void executeAddBookQuery(AddBookQuery query)
    {
        //build book object from isbn
        Book book = 
        Dbwrapper.getInstance().addBook(query.book);
        return;
    }
    
    //Override of toString. Method to return the object information in the form of a string.
    @Override
    public String toString() {
        String s;
        if (this.wasSuccessful) s = "true";
        else s = "false";
        String msg = "AddBookResponse" + this.DELIMITER + s;
        return msg;
    }
}