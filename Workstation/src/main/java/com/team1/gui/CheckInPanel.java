package com.team1.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CheckInPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String PATRON_LABEL_TEXT = "Patron";
	private static final String ISBN_LABEL_TEXT = "ISBN";
	private static final String SUBMIT_BUTTON_TEXT = "Check In";
	
	private JTextField patronField;
    private JTextField isbnField;
    private JButton submitButton;
    private JTextArea returnTextArea;
    
	public CheckInPanel() {
		super();
		
		GridBagLayout gbl_checkInPanel = new GridBagLayout();
        gbl_checkInPanel.columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0, 0, 10, 0};
        gbl_checkInPanel.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
        gbl_checkInPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_checkInPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gbl_checkInPanel);
        
        JLabel patronLabel = new JLabel(PATRON_LABEL_TEXT);
        GridBagConstraints gbc_patronLabel = new GridBagConstraints();
        gbc_patronLabel.fill = GridBagConstraints.VERTICAL;
        gbc_patronLabel.insets = new Insets(0, 0, 5, 5);
        gbc_patronLabel.gridx = 2;
        gbc_patronLabel.gridy = 1;
        this.add(patronLabel, gbc_patronLabel);
        
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
        GridBagConstraints gbc_submitButton = new GridBagConstraints();
        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
        gbc_submitButton.gridx = 6;
        gbc_submitButton.gridy = 1;
        this.add(submitButton, gbc_submitButton);
        
        returnTextArea = new JTextArea();
        returnTextArea.setEditable(false);
        GridBagConstraints gbc_returnTextArea = new GridBagConstraints();
        gbc_returnTextArea.gridwidth = 7;
        gbc_returnTextArea.insets = new Insets(0, 0, 5, 5);
        gbc_returnTextArea.fill = GridBagConstraints.BOTH;
        gbc_returnTextArea.gridx = 1;
        gbc_returnTextArea.gridy = 3;
        this.add(returnTextArea, gbc_returnTextArea);
	}
}
