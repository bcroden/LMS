package com.team1.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.team1.formatting.queries.CheckOutBookQuery;
import com.team1.formatting.responses.CheckOutBookResponse;
import com.team1.formatting.responses.Response;

public class CheckOutPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String PATRON_LABEL_TEXT = "Patron";
	private static final String ISBN_LABEL_TEXT = "ISBN";
	private static final String SUBMIT_BUTTON_TEXT = "Check Out";
	
	public Controller controller;
	
	private JTextField patronField;
    private JTextField isbnField;
    private JButton submitButton;
    private JTextArea returnTextArea;
    
	public CheckOutPanel(final Controller controller) {
		super();
		
		this.controller = controller;
		
		GridBagLayout gbl_checkOutPanel = new GridBagLayout();
        gbl_checkOutPanel.columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0, 0, 10, 0};
        gbl_checkOutPanel.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
        gbl_checkOutPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_checkOutPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gbl_checkOutPanel);
        
        JLabel patronLabel = new JLabel(PATRON_LABEL_TEXT);
        GridBagConstraints gbc_lblPatron = new GridBagConstraints();
        gbc_lblPatron.fill = GridBagConstraints.VERTICAL;
        gbc_lblPatron.insets = new Insets(0, 0, 5, 5);
        gbc_lblPatron.gridx = 2;
        gbc_lblPatron.gridy = 1;
        this.add(patronLabel, gbc_lblPatron);
        
        patronField = new JTextField();
        GridBagConstraints gbc_patronField = new GridBagConstraints();
        gbc_patronField.insets = new Insets(0, 0, 5, 5);
        gbc_patronField.fill = GridBagConstraints.HORIZONTAL;
        gbc_patronField.gridx = 3;
        gbc_patronField.gridy = 1;
        this.add(patronField, gbc_patronField);
        patronField.setColumns(10);
        
        JLabel isbnLabel = new JLabel(ISBN_LABEL_TEXT);
        GridBagConstraints gbc_isbnLabel = new GridBagConstraints();
        gbc_isbnLabel.insets = new Insets(0, 0, 5, 5);
        gbc_isbnLabel.gridx = 4;
        gbc_isbnLabel.gridy = 1;
        this.add(isbnLabel, gbc_isbnLabel);
        
        isbnField = new JTextField();
        GridBagConstraints gbc_isbnField = new GridBagConstraints();
        gbc_isbnField.insets = new Insets(0, 0, 5, 5);
        gbc_isbnField.fill = GridBagConstraints.HORIZONTAL;
        gbc_isbnField.gridx = 5;
        gbc_isbnField.gridy = 1;
        this.add(isbnField, gbc_isbnField);
        isbnField.setColumns(10);
        
        submitButton = new JButton(SUBMIT_BUTTON_TEXT);
        submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckOutBookQuery q	= new CheckOutBookQuery(controller.model.sessionId);
				q.userID = patronField.getText();
				q.isbn = isbnField.getText();
				
				
				String r = controller.sendMessage(q.toString());
				returnTextArea.setText(r);
				
				System.out.println("Message received: " + r);
				
				if(r == null) {
					returnTextArea.setText("Invalid Entries.");
					returnTextArea.setForeground(Color.RED);
				}
				
				Response response = Response.stringToResponse(r);
				
				System.out.println("r = " + r);
				
				if(response instanceof CheckOutBookResponse) {
					if(response.wasSuccessful)
						returnTextArea.setText("Check out successfull");
					else
						returnTextArea.setText("Check out failed");
				}
			}
        });
        GridBagConstraints gbc_submitButton = new GridBagConstraints();
        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
        gbc_submitButton.gridx = 6;
        gbc_submitButton.gridy = 1;
        this.add(submitButton, gbc_submitButton);
        
        returnTextArea = new JTextArea();
        returnTextArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(returnTextArea,
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        GridBagConstraints gbc_returnTextArea = new GridBagConstraints();
        gbc_returnTextArea.gridwidth = 7;
        gbc_returnTextArea.insets = new Insets(0, 0, 5, 5);
        gbc_returnTextArea.fill = GridBagConstraints.BOTH;
        gbc_returnTextArea.gridx = 1;
        gbc_returnTextArea.gridy = 3;
        this.add(scrollPane, gbc_returnTextArea);
	}
}
