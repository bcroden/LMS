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
	
	private JTextArea textPane;
	private JTextField textField;
	private JButton searchButton;
	private JComboBox<String> comboBox;
	
	public LookupPanel() {
        GridBagLayout gbl_lookupPanel = new GridBagLayout();
        gbl_lookupPanel.columnWidths = new int[]{10, 0, 0, 0, 10, 0};
        gbl_lookupPanel.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
        gbl_lookupPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_lookupPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gbl_lookupPanel);
        
        comboBox = new JComboBox<String>();
        comboBox.addItem("ISBN");
        comboBox.addItem("Title");
        comboBox.addItem("Author");
        comboBox.addItem("Publisher");
        comboBox.addItem("Genre");
        comboBox.addItem("Date");
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 1;
        this.add(comboBox, gbc_comboBox);
        
        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 2;
        gbc_textField.gridy = 1;
        this.add(textField, gbc_textField);
        textField.setColumns(10);
        
        searchButton = new JButton("Search");
        GridBagConstraints gbc_searchButton = new GridBagConstraints();
        gbc_searchButton.insets = new Insets(0, 0, 5, 5);
        gbc_searchButton.gridx = 3;
        gbc_searchButton.gridy = 1;
        this.add(searchButton, gbc_searchButton);
        
        textPane = new JTextArea();
        textPane.setEditable(false);
        GridBagConstraints gbc_textPane = new GridBagConstraints();
        gbc_textPane.gridwidth = 3;
        gbc_textPane.insets = new Insets(0, 0, 5, 5);
        gbc_textPane.fill = GridBagConstraints.BOTH;
        gbc_textPane.gridx = 1;
        gbc_textPane.gridy = 3;
        this.add(textPane, gbc_textPane);
	}
}