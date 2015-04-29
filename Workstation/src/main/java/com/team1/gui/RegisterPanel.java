package com.team1.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
						
						
						// new window to manually enter the book info
						String isbn,title,author,publisher,datePublished,genre,url;
						int numCopies;
						
						JTextField jISBN = new JTextField(20);
						JTextField jTITLE = new JTextField(20);
						JTextField jAUTHOR = new JTextField(20);
						JTextField jPUBLISHER = new JTextField(20);
						JTextField jDATE_PUBLISHED = new JTextField(20);
						JTextField jGENRE = new JTextField(20);
						JTextField jURL = new JTextField(20);
						JSpinner jNUM_COPIES = new JSpinner();
						
						JPanel manualAddBookPanel = new JPanel();
						
						manualAddBookPanel.setLayout(new BoxLayout(manualAddBookPanel, BoxLayout.Y_AXIS));
						manualAddBookPanel.add(Box.createVerticalStrut(5));
						manualAddBookPanel.add(new JLabel("ISBN:"));
						manualAddBookPanel.add(jISBN);
						manualAddBookPanel.add(new JLabel("Title:"));
						manualAddBookPanel.add(jTITLE);
						manualAddBookPanel.add(new JLabel("Author:"));
						manualAddBookPanel.add(jAUTHOR);
						manualAddBookPanel.add(new JLabel("Publisher:"));
						manualAddBookPanel.add(jPUBLISHER);
						manualAddBookPanel.add(new JLabel("Date Published:"));
						manualAddBookPanel.add(jDATE_PUBLISHED);
						manualAddBookPanel.add(new JLabel("Genre:"));
						manualAddBookPanel.add(jGENRE);
						manualAddBookPanel.add(new JLabel("URL:"));
						manualAddBookPanel.add(jURL);
						manualAddBookPanel.add(new JLabel("Number of Copies:"));
						manualAddBookPanel.add(jNUM_COPIES);
						
						int result = JOptionPane.showConfirmDialog(null, manualAddBookPanel, "Manual Book Entry",
								      JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						
						isbn = new String(jISBN.getText());
						title = new String(jTITLE.getText());
						author = new String(jAUTHOR.getText());
						publisher = new String(jPUBLISHER.getText());
						datePublished = new String(jDATE_PUBLISHED.getText());
						genre = new String(jGENRE.getText());
						url = new String(jURL.getText());
						numCopies = (Integer)jNUM_COPIES.getValue();
						
						//check that its in mysql year range (1901 -2155)
						while( Integer.parseInt(datePublished) <1901 || Integer.parseInt(datePublished) >2155)
						{
							JOptionPane.showMessageDialog(null, "Invalid publish date, enter a date between 1901 and 2155");
							datePublished = JOptionPane.showInputDialog("Date Published");
						}
						
						
						if(result == JOptionPane.OK_OPTION){
							//build the query
							ManualAddBookQuery query = new ManualAddBookQuery(controller.model.sessionId,isbn,title,author,publisher,datePublished,genre,url,numCopies);
						
							//send query
							String responseMsg = controller.sendMessage(query.toString());
						
							//error checking
							System.out.println("responseMsg ="+responseMsg);
						
							System.out.println("response failed, trying manual add book...\n\nBefore to response");
						
							//rebuild the response
							Response response2 = Response.stringToResponse(responseMsg);
						
							//more error checking
							System.out.println(response2.toString());
							
							if(response2.wasSuccessful)
								returnTextArea.setText("Register succeeded");
							else
								returnTextArea.setText("Register failed");
						}
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