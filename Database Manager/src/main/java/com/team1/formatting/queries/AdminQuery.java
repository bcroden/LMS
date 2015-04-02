package com.team1.formatting.queries;

public class AdminQuery extends LibrarianQuery
{

    public AdminQuery(boolean wasSuccessful, String sessionID) {
        super(wasSuccessful);
        // TODO Auto-generated constructor stub
        this.sessionID = sessionID;
    }

}