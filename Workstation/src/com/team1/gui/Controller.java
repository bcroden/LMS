package com.team1.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;

import com.team1.formatting.BookInfoQuery;
import com.team1.formatting.CheckInBookQuery;
import com.team1.formatting.CheckOutBookQuery;
import com.team1.formatting.LoginQuery;
import com.team1.formatting.Query;
import com.team1.network.TCPClient;

/**
 * This class creates a controller object for the GUI. All of the code is
 * temporary for release 1 and will be changed, so don't get too emotionally
 * attached to it.
 * 
 * @author Brandon
 *
 */
public class Controller implements ActionListener {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

        this.view.submitButton.addActionListener(this);
        this.view.searchButton.addActionListener(this);
        this.view.checkOutButton.addActionListener(this);
        this.view.checkInButton.addActionListener(this);
    }

    /*
     * This entire thing is awful.
     * TODO: Make it not awful.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.submitButton) {
            String username = this.view.usernameField.getText();
            /*
             * This is interesting...
             * TODO: Look into this char[] thing more
             */
            String password = new String(this.view.passwordField.getPassword());

            //LoginQuery query = new LoginQuery(false, username, password);
            //System.out.println(query);
            /*
             * TODO: Uncomment code for connecting to DB manager since it is not on my machine...
             */
            //try {
            //    this.model.client.sendRequest(query.toString());
            //}
            //catch (InvalidKeyException e1) {
              //  e1.printStackTrace();
            //}
            //catch (IOException e1) {
            //    e1.printStackTrace();
            //}

            Query response = null;
            try {
                response = Query.buildRequest(this.model.client.getReply());
            }
            catch (InvalidKeyException e1) {
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            // I don't have the db on my machine...
            // For now... 
            // TODO: use response from DBM
            // TODO: don't just cast things
            //response = new LoginQuery(true, username, password);
            if (response.wasSuccessful)
                this.view.loginResponse.setText("You were successfully logged in as: " + ((LoginQuery)response).userName);
            else
                this.view.loginResponse.setText("Invalid username or password, please re-enter and try again.");
        }
        else if (e.getSource() == this.view.searchButton) {
            String isbn = this.view.isbnField.getText();
            BookInfoQuery query = new BookInfoQuery(false, model.sessionId, isbn, " ", " ", " ", " ", " ", " ");
            System.out.println(query);
            /*
             * TODO: Uncomment code for sending and receiving to/from DB manager since it is not on my machine...
             */
            try {
				this.model.client = new TCPClient(model.HOST, model.PORT);
			} catch (InvalidKeyException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            
            try {
                this.model.client.sendRequest(query.toString());
            }
            catch (InvalidKeyException e1) {
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            Query response = null;
            try {
                response = Query.buildRequest(this.model.client.getReply());
            }
            catch (InvalidKeyException e1) {
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            // I don't have the db on my machine...
            // For now... TODO: Fix
            //response = new BookInfoQuery(true, model.sessionId, isbn, "a", "b", "c", "d", "e", "f");
            if (response.wasSuccessful)
                this.view.getBookResponse.setText("Title:  " + ((BookInfoQuery)response).title + 
                                                  "\nAuthor: " + ((BookInfoQuery)response).author + 
                                                  "\nStatus: " + ((BookInfoQuery)response).availability);
            else
                this.view.getBookResponse.setText("Incorrect ISBN");
        }
        else if (e.getSource() == this.view.checkOutButton) {
            String isbn = this.view.isbnField2.getText();
            String userID = this.view.patronField.getText();
            System.out.println(isbn);

            CheckOutBookQuery query = new CheckOutBookQuery(false, model.sessionId, isbn, " ", " ", " ", " ", " ", userID);
            System.out.println(query.toString());
            /*
             * TODO: Uncomment code for connecting to DB manager since it is not on my machine...
             */
            try {
				this.model.client = new TCPClient(model.HOST, model.PORT);
			} catch (InvalidKeyException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            
            try {
                this.model.client.sendRequest(query.toString());
            }
            catch (InvalidKeyException e1) {
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            Query response = null;
            try {
                response = Query.buildRequest(this.model.client.getReply());
            }
            catch (InvalidKeyException e1) {
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            
            // I don't have the db on my machine...
            // For now... TODO: Fix
            //response = new CheckOutBookQuery(true, model.sessionId, isbn, "a", "b", "c", "d", "e", userID);
            if(response.wasSuccessful)
                this.view.checkOutResponse.setText("The book " + ((CheckOutBookQuery)response).title + " was successfully checked out to " + ((CheckOutBookQuery)response).userID);
            else
                this.view.checkOutResponse.setText("Invalid ISBN or User ID");
        }
        else if (e.getSource() == this.view.checkInButton) {
            String isbn = this.view.isbnField3.getText();
            String userID = this.view.patronField2.getText();

            CheckInBookQuery query = new CheckInBookQuery(false, model.sessionId, isbn, " ", " ", " ", " ", " ", userID);
            System.out.println(query);
            /*
             * TODO: Uncomment code for connecting to DB manager since it is not on my machine...
             */
            try {
				this.model.client = new TCPClient(model.HOST, model.PORT);
			} catch (InvalidKeyException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            
            try {
                this.model.client.sendRequest(query.toString());
            }
            catch (InvalidKeyException e1) {
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            Query response = null;
            try {
                response = Query.buildRequest(this.model.client.getReply());
            }
            catch (InvalidKeyException e1) {
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            // I don't have the db on my machine...
            // For now... TODO: Fix
            //response = new CheckInBookQuery(true, model.sessionId, isbn, "a", "b", "c", "d", "e", userID);
            if(response.wasSuccessful)
                this.view.checkInResponse.setText("The book " + ((CheckInBookQuery)response).title + " was successfully checked in from " + ((CheckInBookQuery)response).userID);
            else
                this.view.checkInResponse.setText("Invalid ISBN or User ID");
        }
    }
}
