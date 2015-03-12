package com.team1.gui;

import java.io.IOException;
import java.security.InvalidKeyException;

import com.team1.network.TCPClient;

public class Model {
    // Maybe read these from files in the future?
    public static final String HOST = "172.17.25.222";
    public static final int PORT = 3612;

    public TCPClient client;

    // Temp Fields for release 1...
    protected String username;
    protected char[] password;
    protected String sessionId;

    public Model() {
        this.sessionId = "10000000";
        /*
         * TODO: Uncomment once the DBM is up and running
         */
        try {
            this.client = new TCPClient(HOST, PORT);
        }
        catch (InvalidKeyException e) {
            // Should never occur...
            e.printStackTrace();
        }
        catch (IOException e) {
            // Oh noes...
            e.printStackTrace();
        }
    }
}