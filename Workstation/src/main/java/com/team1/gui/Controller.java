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
    	try {
    		//TODO: less magic numbers
			TCPClient client = new TCPClient(Model.HOST, Model.PORT);
			
			client.sendRequest(message);
			String reply = client.getReply();
			
			client.close();	
			return reply;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	System.out.println("Returning null from sendMessage in controller");
    	return null;
    }
}