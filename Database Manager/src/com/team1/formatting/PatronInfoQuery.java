package com.team1.formatting;

public class PatronInfoQuery extends LibrarianQuery
{
    public PatronInfoQuery(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }
    //Temporary value for now...
    public int MAX_CHECKOUT = 5;
    
    //patron first name and last name
    public String fName, lName;
    
    //variable for what kind of patron query is being sent: fine lookup, fine payment, fine 
    public String action;
    
    //Variables used to either check out books or tell the librarian what books they have checked out
    //MAX_CHECKOUT number to be decided
    public String[] booksOut;
    public int numBooksOut;
    
    //variables used to tell the database how many books are being returned and which books they are
    public int numBooksIn;
    public String bookCheckIn;
    
    //variables for: fines owed, payment amount, or fine addition amount
    public float fines;
    public float payment;
    public float fineAddition;
}