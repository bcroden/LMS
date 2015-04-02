package com.team1.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PaymentPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String PATRON_LABEL_TEXT = "Patron";
	private static final String AMOUNT_LABEL_TEXT = "Amount";
	private static final String SUBMIT_BUTTON_TEXT = "Submit";
	
    private JTextField patronField;
    private JTextField amountField;
    private JButton submitButton;
    private JTextArea returnTextArea;
	
	public PaymentPanel() {
		super();
		
        GridBagLayout gbl_paymentPanel = new GridBagLayout();
        gbl_paymentPanel.columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0, 0, 10, 0};
        gbl_paymentPanel.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
        gbl_paymentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_paymentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gbl_paymentPanel);
        
        JLabel patronLabel = new JLabel(PATRON_LABEL_TEXT);
        GridBagConstraints gbc_patronLabel = new GridBagConstraints();
        gbc_patronLabel.insets = new Insets(0, 0, 5, 5);
        gbc_patronLabel.anchor = GridBagConstraints.EAST;
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
        
        JLabel amountLabel = new JLabel(AMOUNT_LABEL_TEXT);
        GridBagConstraints gbc_amountLabel = new GridBagConstraints();
        gbc_amountLabel.anchor = GridBagConstraints.EAST;
        gbc_amountLabel.insets = new Insets(0, 0, 5, 5);
        gbc_amountLabel.gridx = 4;
        gbc_amountLabel.gridy = 1;
        this.add(amountLabel, gbc_amountLabel);
        
        amountField = new JTextField();
        GridBagConstraints gbc_amountField = new GridBagConstraints();
        gbc_amountField.insets = new Insets(0, 0, 5, 5);
        gbc_amountField.fill = GridBagConstraints.HORIZONTAL;
        gbc_amountField.gridx = 5;
        gbc_amountField.gridy = 1;
        this.add(amountField, gbc_amountField);
        amountField.setColumns(10);
        
        submitButton = new JButton(SUBMIT_BUTTON_TEXT);
        GridBagConstraints gbc_submitButton = new GridBagConstraints();
        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
        gbc_submitButton.gridx = 6;
        gbc_submitButton.gridy = 1;
        this.add(submitButton, gbc_submitButton);
        
        returnTextArea = new JTextArea();
        GridBagConstraints gbc_returnTextArea = new GridBagConstraints();
        gbc_returnTextArea.gridwidth = 7;
        gbc_returnTextArea.insets = new Insets(0, 0, 5, 5);
        gbc_returnTextArea.fill = GridBagConstraints.BOTH;
        gbc_returnTextArea.gridx = 1;
        gbc_returnTextArea.gridy = 3;
        this.add(returnTextArea, gbc_returnTextArea);
	}
}
