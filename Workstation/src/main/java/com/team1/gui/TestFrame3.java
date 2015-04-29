package com.team1.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.team1.books.Book;
import com.team1.books.BookFinder;
import com.team1.books.InvalidISBNException;
import com.team1.formatting.queries.CheckOutBookQuery;
import com.team1.formatting.responses.CheckOutBookResponse;
import com.team1.formatting.responses.Response;

public class TestFrame3 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static final String PATRON_LABEL_TEXT = "Patron";
	private static final String ISBN_LABEL_TEXT = "ISBN";
	private static final String SUBMIT_BUTTON_TEXT = "Check Out";
	
	public Controller controller;
	
	private JTextField patronField;
    private JTextField isbnField;
    private JButton submitButton;
    private JPanel panel;
    private JLabel lblCheckoutSuccessful;
    private LMSBookScrollPane bookDisplay;
	
	public TestFrame3() {
		super();
		
		this.setSize(800, 600);
		
		GridBagLayout gbl_checkOutPanel = new GridBagLayout();
        gbl_checkOutPanel.columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0, 0, 10, 0};
        gbl_checkOutPanel.rowHeights = new int[]{10, 0, 10, 0, 10, 0, 10, 0};
        gbl_checkOutPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_checkOutPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gbl_checkOutPanel);
        
        JLabel patronLabel = new JLabel(PATRON_LABEL_TEXT);
        GridBagConstraints gbc_lblPatron = new GridBagConstraints();
        gbc_lblPatron.fill = GridBagConstraints.VERTICAL;
        gbc_lblPatron.insets = new Insets(0, 0, 5, 5);
        gbc_lblPatron.gridx = 2;
        gbc_lblPatron.gridy = 1;
        getContentPane().add(patronLabel, gbc_lblPatron);
        
        patronField = new JTextField();
        GridBagConstraints gbc_patronField = new GridBagConstraints();
        gbc_patronField.insets = new Insets(0, 0, 5, 5);
        gbc_patronField.fill = GridBagConstraints.HORIZONTAL;
        gbc_patronField.gridx = 3;
        gbc_patronField.gridy = 1;
        getContentPane().add(patronField, gbc_patronField);
        patronField.setColumns(10);
        
        JLabel isbnLabel = new JLabel(ISBN_LABEL_TEXT);
        GridBagConstraints gbc_isbnLabel = new GridBagConstraints();
        gbc_isbnLabel.insets = new Insets(0, 0, 5, 5);
        gbc_isbnLabel.gridx = 4;
        gbc_isbnLabel.gridy = 1;
        getContentPane().add(isbnLabel, gbc_isbnLabel);
        
        isbnField = new JTextField();
        GridBagConstraints gbc_isbnField = new GridBagConstraints();
        gbc_isbnField.insets = new Insets(0, 0, 5, 5);
        gbc_isbnField.fill = GridBagConstraints.HORIZONTAL;
        gbc_isbnField.gridx = 5;
        gbc_isbnField.gridy = 1;
        getContentPane().add(isbnField, gbc_isbnField);
        isbnField.setColumns(10);
        
        submitButton = new JButton(SUBMIT_BUTTON_TEXT);
        submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CheckOutBookQuery q	= new CheckOutBookQuery(controller.model.sessionId);
				q.userID = patronField.getText();
				q.isbn = isbnField.getText();
				
				//System.out.println("Q = " + q.toString());
				String r = controller.sendMessage(q.toString());
				
				//System.out.println("Message received: " + r);
				
				if(r == null) {
				}
				
				Response response = Response.stringToResponse(r);
				
				//System.out.println("r = " + r);
				
				if(response instanceof CheckOutBookResponse) {
					if(response.wasSuccessful)
						;
					else
						;
				}
			}
        });
        GridBagConstraints gbc_submitButton = new GridBagConstraints();
        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
        gbc_submitButton.gridx = 6;
        gbc_submitButton.gridy = 1;
        getContentPane().add(submitButton, gbc_submitButton);
        
        panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 3;
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 3;
        gbc_panel.gridy = 3;
        getContentPane().add(panel, gbc_panel);
        
        lblCheckoutSuccessful = new JLabel("Checkout Successful");
        panel.add(lblCheckoutSuccessful);
        
        bookDisplay = new LMSBookScrollPane("Other Books Checked out to user");
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.gridwidth = 7;
        gbc_panel_1.insets = new Insets(0, 0, 5, 5);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 5;
        getContentPane().add(bookDisplay, gbc_panel_1);
        
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		TestFrame3 tf = new TestFrame3();
		
		ArrayList<Book> books = new ArrayList<Book>();
		Book book = null;
		try {
			book = BookFinder.getBookFromGoogle("054792822X");
		} catch (InvalidISBNException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < 12; i++)
			books.add(book);
		tf.bookDisplay.showBooks(books);
	}
}