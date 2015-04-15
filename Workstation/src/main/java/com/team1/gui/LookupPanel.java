package com.team1.gui;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.team1.books.Book;
import com.team1.books.BookFinder;
import com.team1.books.InvalidISBNException;
import com.team1.formatting.queries.BookInfoQuery;
import com.team1.formatting.responses.BookInfoResponse;
import com.team1.formatting.responses.Response;

public class LookupPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String SUBMIT_BUTTON_TEXT = "Search";
	private static final String[] COMBO_BOX_OPTIONS = {"ISBN", "Title", "Author", "Publisher", "Genre", "Date"};
	
	public Controller controller;
	
	private JComboBox<String> comboBox;
	private JTextField searchField;
	private JButton submitButton;
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
				returnPanel.revalidate();
				returnPanel.repaint();
				
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
				
				if(r != null) {
					Response response = Response.stringToResponse(r);
					BookInfoResponse bookInfoResponse = (BookInfoResponse)response;
					if(bookInfoResponse.wasSuccessful) {
						ArrayList<Book> books = bookInfoResponse.books;
						
						if(books == null || books.size() == 0) {
							System.out.println("Books = null");
							JOptionPane.showMessageDialog(null, "No results found.");
						}
						else {
							System.out.println("Books != null");
							showBooks(books);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No results found.");
					//TODO: Show error message
				}
				setCursor(Cursor.getDefaultCursor());
			}
        });
        GridBagConstraints gbc_submitButton = new GridBagConstraints();
        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
        gbc_submitButton.gridx = 4;
        gbc_submitButton.gridy = 1;
        this.add(submitButton, gbc_submitButton);
        returnPanel = new JPanel();
        
        JScrollPane scrollPane = new JScrollPane(returnPanel,
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        GridBagConstraints gbc_returnTextArea = new GridBagConstraints();
        gbc_returnTextArea.gridwidth = 5;
        gbc_returnTextArea.insets = new Insets(0, 0, 5, 5);
        gbc_returnTextArea.fill = GridBagConstraints.BOTH;
        gbc_returnTextArea.gridx = 1;
        gbc_returnTextArea.gridy = 3;
        this.add(scrollPane, gbc_returnTextArea);
        
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));
        
		ArrayList<Book> books = new ArrayList<Book>();
		Book book = null;
		try {
			book = BookFinder.getBookFromGoogle("054792822X");
		} catch (InvalidISBNException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < 12; i++)
			books.add(book);
		this.showBooks(books);
	}
	
	public void showBooks(ArrayList<Book> books) {
		ArrayList<BookLabel> labels = new ArrayList<BookLabel>();
		for(Book b : books)
			labels.add(new BookLabel(b));
		
		System.out.println("panel width = " + returnPanel.getPreferredSize().getWidth());
		System.out.println("book width = " + labels.get(0).getPreferredSize().getWidth());
		
		int booksPerPanel = returnPanel.getWidth() / (labels.get(0).getWidth() + 10);
		System.out.println("booksPerPanel = " + booksPerPanel);
		booksPerPanel = 8;
		int numPanels = (int)Math.ceil((double)labels.size() / (double)booksPerPanel);
		
		int pos = 0;
		returnPanel.add(Box.createVerticalGlue());
		returnPanel.add(Box.createVerticalStrut(10));
		for(int i = 0; i < numPanels; i++) {
			JPanel panel = new JPanel();
		    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			for(int j = 0; j < booksPerPanel; j++) {
				if(pos < labels.size()) {
					panel.add(labels.get(pos));
					panel.add(Box.createHorizontalStrut(5));
					System.out.println("i = " + i + ", j = " + j);
					pos++;
				}
			}
			returnPanel.add(panel);
			returnPanel.add(Box.createVerticalStrut(10));
		}
		returnPanel.add(Box.createVerticalGlue());
		returnPanel.revalidate();
		returnPanel.repaint();
	}
}