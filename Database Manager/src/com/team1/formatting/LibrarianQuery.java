package com.team1.formatting;

public class LibrarianQuery extends Query
{

    public LibrarianQuery(boolean wasSuccessful, String sessionID) {
        super(wasSuccessful);
        // TODO Auto-generated constructor stub
        System.out.println("Libriarian query constructor");
        this.sessionID = sessionID;
    }
    public String sessionID;
}