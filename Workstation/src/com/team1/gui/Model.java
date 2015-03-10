package com.team1.gui;

import java.io.IOException;
import java.security.InvalidKeyException;

import com.team1.network.TCPClient;

public class Model {
    //Maybe read these from files in the future?
    public static final String HOST = "localhost";
    public static final int PORT = 3612;
    
    TCPClient client;
    
    //Temp Fields for release 1...
    protected String username;
    protected char[] password;
    //private <something> sessionId;
    
    
    public Model() {
        try {
            client = new TCPClient(HOST, PORT);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //Oh noes...
            e.printStackTrace();
        }
    }
}