package com.team1.gui;

import java.io.IOException;
import java.security.InvalidKeyException;

import com.team1.formatting.queries.Query;
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
    public String sendMessage(Query q) {
    	try {
    		//TODO: less magic numbers
			TCPClient client = new TCPClient(Model.HOST, Model.PORT, 1000);
			
			client.sendRequest(q.toString());
			String reply = client.getReply();
			
			//TODO build reply and handle result
			
			client.close();	
			
			//Return request
			return reply;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return " ";
    }
}