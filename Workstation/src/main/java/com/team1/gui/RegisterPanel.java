package com.team1.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.team1.formatting.queries.AddBookQuery;
import com.team1.formatting.queries.ManualAddBookQuery;
import com.team1.formatting.responses.AddBookResponse;
import com.team1.formatting.responses.Response;

public class RegisterPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String ISBN_LABEL_TEXT = "ISBN";
	private static final String SUBMIT_BUTTON_TEXT = "Register";
	
	public Controller controller;
	
    private JTextField isbnField;
    private JSpinner countSpinner;
    private JButton submitButton;
    private JTextArea returnTextArea;
    
	public RegisterPanel(final Controller controller) {
		super();
		
		this.controller = controller;
		
        GridBagLayout gbl_registerPanel = new GridBagLayout();
        gbl_registerPanel.columnWidths = new int[]{10, 0, 0, 0, 40, 24, 0, 10, 0};
        gbl_registerPanel.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
        gbl_registerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_registerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gbl_registerPanel);
        
        JLabel isbnLabel = new JLabel(ISBN_LABEL_TEXT);
        GridBagConstraints gbc_isbnLabel = new GridBagConstraints();
        gbc_isbnLabel.anchor = GridBagConstraints.EAST;
        gbc_isbnLabel.insets = new Insets(0, 0, 5, 5);
        gbc_isbnLabel.gridx = 2;
        gbc_isbnLabel.gridy = 1;
        this.add(isbnLabel, gbc_isbnLabel);
        
        isbnField = new JTextField();
        GridBagConstraints gbc_isbnField = new GridBagConstraints();
        gbc_isbnField.insets = new Insets(0, 0, 5, 5);
        gbc_isbnField.fill = GridBagConstraints.HORIZONTAL;
        gbc_isbnField.gridx = 3;
        gbc_isbnField.gridy = 1;
        this.add(isbnField, gbc_isbnField);
        isbnField.setColumns(10);
        
        countSpinner = new JSpinner();
        GridBagConstraints gbc_countSpinner = new GridBagConstraints();
        gbc_countSpinner.fill = GridBagConstraints.HORIZONTAL;
        gbc_countSpinner.insets = new Insets(0, 0, 5, 5);
        gbc_countSpinner.gridx = 4;
        gbc_countSpinner.gridy = 1;
        this.add(countSpinner, gbc_countSpinner);
        
        submitButton = new JButton(SUBMIT_BUTTON_TEXT);
        submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddBookQuery q = new AddBookQuery(controller.model.sessionId, isbnField.getText(), (Integer)countSpinner.getValue());
				
				System.out.println("Spinner: " + (Integer)countSpinner.getValue());
				
				String r = controller.sendMessage(q.toString());
				
				System.out.println("r = " + r);
				
				if(r == null) {
					returnTextArea.setText("Invalid ISBN");
					returnTextArea.setForeground(Color.RED);
				}
				
				System.out.println("Before to response");
				Response response = Response.stringToResponse(r);
				
				System.out.println(response.toString());
				
				
				if(response instanceof AddBookResponse) {
					if(response.wasSuccessful)
						returnTextArea.setText("Register succeded");
					else{
						returnTextArea.setText("Register failed");
						
						// new window to manually enter the book info
						String isbn,title,author,publisher,datePublished,genre,url,numCopies;
						JOptionPane.showMessageDialog(null, "Enter the following information to manually add the book.");
						isbn = JOptionPane.showInputDialog("ISBN");
						System.out.println("isbn ="+isbn);
						
						title = JOptionPane.showInputDialog("Title");
						System.out.println("title ="+title);
						
						author = JOptionPane.showInputDialog("Author");
						System.out.println("author ="+author);
						
						publisher = JOptionPane.showInputDialog("Publisher");
						System.out.println("publisher ="+publisher);
						
						datePublished = JOptionPane.showInputDialog("Date Published");
						System.out.println("datePublished ="+datePublished);
						
						//check that its in mysql year range (1901 -2155)
						while( Integer.parseInt(datePublished) <1901 || Integer.parseInt(datePublished) >2155)
						{
							JOptionPane.showMessageDialog(null, "Invalid publish date, enter a date between 1901 and 2155");
							datePublished = JOptionPane.showInputDialog("Date Published");
						}
						
						genre = JOptionPane.showInputDialog("Genre");
						System.out.println("genre ="+genre);
						
						url = JOptionPane.showInputDialog("Image url");
						System.out.println("url ="+url);
						
						numCopies = JOptionPane.showInputDialog("Number of Copies");
						System.out.println("numCopies ="+numCopies);
						
						
						//build the query
						ManualAddBookQuery query = new ManualAddBookQuery(controller.model.sessionId,isbn,title,author,publisher,datePublished,genre,url,Integer.parseInt(numCopies));
						
						//send query
						String responseMsg = controller.sendMessage(query.toString());
						
						//error checking
						System.out.println("responseMsg ="+responseMsg);
						
						System.out.println("response failed, trying manual add book...\n\nBefore to response");
						
						//rebuild the response
						Response response2 = Response.stringToResponse(responseMsg);
						
						//more error checking
						System.out.println(response2.toString());
						
					}
				}
			}
        });
        GridBagConstraints gbc_submitButton = new GridBagConstraints();
        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
        gbc_submitButton.gridx = 5;
        gbc_submitButton.gridy = 1;
        this.add(submitButton, gbc_submitButton);
        
        returnTextArea = new JTextArea();
        returnTextArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(returnTextArea,
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        GridBagConstraints gbc_returnTextArea = new GridBagConstraints();
        gbc_returnTextArea.gridwidth = 6;
        gbc_returnTextArea.insets = new Insets(0, 0, 5, 5);
        gbc_returnTextArea.fill = GridBagConstraints.BOTH;
        gbc_returnTextArea.gridx = 1;
        gbc_returnTextArea.gridy = 3;
        this.add(scrollPane, gbc_returnTextArea);
	}
}