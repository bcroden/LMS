package com.team1.gui;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.team1.books.Book;
import com.team1.formatting.queries.BookInfoQuery;
import com.team1.formatting.responses.BookInfoResponse;
import com.team1.formatting.responses.Response;

public class LookupPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String SUBMIT_BUTTON_TEXT = "Search";
	private static final String[] COMBO_BOX_OPTIONS = {	"ISBN", "Title", "Author", "Publisher", "Genre", "Date"};
	
	public Controller controller;
	
	private JComboBox<String> comboBox;
	private JTextField searchField;
	private JButton submitButton;
//	private JTextArea returnTextArea;
	private JPanel returnPanel;
	
	public LookupPanel(final Controller controller) {
		super();
		
		this.controller = controller;
		
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
        submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				returnPanel.removeAll();
				//Create new BookInfoQuery TODO: Get latest formatting packages and modify accordingly
				BookInfoQuery q = new BookInfoQuery(controller.model.sessionId);
				
				//Reflect the query and invoke the correct searchBy method
				for(Method m : q.getClass().getMethods()) {
					if (m.getName().contains("searchBy") && m.getName().contains((String)comboBox.getSelectedItem())) {
						try {
							m.invoke(q, searchField.getText());
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
				
				String r = controller.sendMessage(q.toString());
				
				System.out.println("Response string = " + r);
				
				if(r == null) {
//					returnTextArea.setText("Invalid search field.");
//					returnTextArea.setForeground(Color.RED);
				}
				
				Response response = Response.stringToResponse(r);
				if(response instanceof BookInfoResponse) {
					ArrayList<Book> books = ((BookInfoResponse)response).books;
					if(books == null)
						System.out.println("Books = null");
					else
						System.out.println("Books != null");
					returnPanel.add(Box.createHorizontalGlue());
					int i = 0;
					for(;i < books.size()-1; i++) {
						System.out.println(books.get(i).toString());
//						returnTextArea.setText(b.toString());
						try {
							returnPanel.add(new BookLabel(books.get(i)));
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
						returnPanel.add(Box.createHorizontalStrut(20));
					}
					
					try {
						returnPanel.add(new BookLabel(books.get(i)));
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					returnPanel.add(Box.createHorizontalGlue());
				}
				setCursor(Cursor.getDefaultCursor());
			}
        });
        GridBagConstraints gbc_submitButton = new GridBagConstraints();
        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
        gbc_submitButton.gridx = 4;
        gbc_submitButton.gridy = 1;
        this.add(submitButton, gbc_submitButton);
        
//        returnTextArea = new JTextArea();
//        returnTextArea.setEditable(false);
        
//        JScrollPane scrollPane = new JScrollPane(returnTextArea,
//        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//        		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.X_AXIS));
        
        GridBagConstraints gbc_returnTextArea = new GridBagConstraints();
        gbc_returnTextArea.gridwidth = 5;
        gbc_returnTextArea.insets = new Insets(0, 0, 5, 5);
        gbc_returnTextArea.fill = GridBagConstraints.BOTH;
        gbc_returnTextArea.gridx = 1;
        gbc_returnTextArea.gridy = 3;
        this.add(returnPanel, gbc_returnTextArea);
	}
}