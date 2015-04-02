package com.team1.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LookupPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String SUBMIT_BUTTON_TEXT = "Search";
	private static final String[] COMBO_BOX_OPTIONS = {	"ISBN", "Title", "Author", "Publisher", "Genre", "Date"};
	
	private JTextArea returnTextArea;
	private JTextField searchField;
	private JButton submitButton;
	private JComboBox<String> comboBox;
	
	public LookupPanel() {
		super();
		
        GridBagLayout gbl_lookupPanel = new GridBagLayout();
        gbl_lookupPanel.columnWidths = new int[]{10, 10, 0, 0, 0, 0, 10, 0};
        gbl_lookupPanel.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
        gbl_lookupPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_lookupPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gbl_lookupPanel);
        
        comboBox = new JComboBox<String>();
        for(String s : COMBO_BOX_OPTIONS)
        	comboBox.addItem(s);
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 2;
        gbc_comboBox.gridy = 1;
        this.add(comboBox, gbc_comboBox);
        
        searchField = new JTextField();
        GridBagConstraints gbc_searchField = new GridBagConstraints();
        gbc_searchField.insets = new Insets(0, 0, 5, 5);
        gbc_searchField.fill = GridBagConstraints.HORIZONTAL;
        gbc_searchField.gridx = 3;
        gbc_searchField.gridy = 1;
        this.add(searchField, gbc_searchField);
        searchField.setColumns(10);
        
        submitButton = new JButton(SUBMIT_BUTTON_TEXT);
        GridBagConstraints gbc_submitButton = new GridBagConstraints();
        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
        gbc_submitButton.gridx = 4;
        gbc_submitButton.gridy = 1;
        this.add(submitButton, gbc_submitButton);
        
        returnTextArea = new JTextArea();
        returnTextArea.setEditable(false);
        GridBagConstraints gbc_returnTextArea = new GridBagConstraints();
        gbc_returnTextArea.gridwidth = 5;
        gbc_returnTextArea.insets = new Insets(0, 0, 5, 5);
        gbc_returnTextArea.fill = GridBagConstraints.BOTH;
        gbc_returnTextArea.gridx = 1;
        gbc_returnTextArea.gridy = 3;
        this.add(returnTextArea, gbc_returnTextArea);
	}
}