package com.team1.gui;

import java.io.IOException;
import java.security.InvalidKeyException;

import com.team1.network.TCPClient;

public class Controller {
	public LMSWindow currentWindow;
	public Model model;
	
    public Controller() {
    	this.model = new Model();
        this.currentWindow = new LoginWindow(this, this.model);
    }
    
    public void showMainWindow() {
    	this.currentWindow.close();
    	this.currentWindow = new MainWindow(this, this.model);
    }
    
    //TODO: more fixing
    public String sendMessage(String message) {
    	//System.out.println("Entering send message");
    	try {
			TCPClient client = new TCPClient(Model.HOST, Model.PORT);
			//System.out.println("Received message in sendMessage: " + message);
			client.sendRequest(message);
			//System.out.println("After request sent");
			String reply = client.getReply();
			//System.out.println("Reply: " + reply);
			client.close();
			return reply;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	//System.out.println("Returning null from sendMessage in controller");
    	return null;
    }
}