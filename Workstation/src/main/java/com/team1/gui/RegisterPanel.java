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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.team1.formatting.queries.AddBookQuery;
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
						returnTextArea.setText("Check out successfull");
					else
						returnTextArea.setText("Check out failed");
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