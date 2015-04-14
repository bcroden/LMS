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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.team1.books.Book;
import com.team1.books.BookFinder;
import com.team1.books.InvalidISBNException;
import com.team1.formatting.queries.BookInfoQuery;
import com.team1.formatting.responses.BookInfoResponse;
import com.team1.formatting.responses.Response;
import java.awt.Component;

public class TestFrame2 extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private static final String SUBMIT_BUTTON_TEXT = "Search";
	private static final String[] COMBO_BOX_OPTIONS = {	"ISBN", "Title", "Author", "Publisher", "Genre", "Date"};
	
	public Controller controller;
	
	private JComboBox<String> comboBox;
	private JTextField searchField;
	private JButton submitButton;
//	private JTextArea returnTextArea;
	private JPanel returnPanel;
	private JPanel panel;
	private JPanel panel2;
	private Component horizontalStrut;
	
	public TestFrame2() {
		super();

		this.setSize(800, 600);
		
//		this.setPreferredSize(new Dimension(800, 600));
		
        GridBagLayout gbl_lookupPanel = new GridBagLayout();
        gbl_lookupPanel.columnWidths = new int[]{10, 10, 0, 0, 0, 0, 10, 0};
        gbl_lookupPanel.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
        gbl_lookupPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_lookupPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gbl_lookupPanel);
        
        comboBox = new JComboBox<String>();
        for(String s : COMBO_BOX_OPTIONS)
        	comboBox.addItem(s);
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 2;
        gbc_comboBox.gridy = 1;
        getContentPane().add(comboBox, gbc_comboBox);
        
        searchField = new JTextField();
        GridBagConstraints gbc_searchField = new GridBagConstraints();
        gbc_searchField.insets = new Insets(0, 0, 5, 5);
        gbc_searchField.fill = GridBagConstraints.HORIZONTAL;
        gbc_searchField.gridx = 3;
        gbc_searchField.gridy = 1;
        getContentPane().add(searchField, gbc_searchField);
        searchField.setColumns(10);
        
        submitButton = new JButton(SUBMIT_BUTTON_TEXT);
        submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				returnPanel.removeAll();
				
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
						if(books == null)
							System.out.println("Books = null");
						else
							System.out.println("Books != null");
						returnPanel.add(Box.createHorizontalGlue());
						int i = 0;
						for(;i < books.size()-1; i++) {
							System.out.println(books.get(i).toString());
	//						returnTextArea.setText(b.toString());
							returnPanel.add(new BookLabel(books.get(i)));
							returnPanel.add(Box.createHorizontalStrut(20));
						}
						returnPanel.add(new BookLabel(books.get(i)));
						returnPanel.add(Box.createHorizontalGlue());
					}
				}
				else {
					//TODO: Show error message
				}
				setCursor(Cursor.getDefaultCursor());
			}
        });
        GridBagConstraints gbc_submitButton = new GridBagConstraints();
        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
        gbc_submitButton.gridx = 4;
        gbc_submitButton.gridy = 1;
        getContentPane().add(submitButton, gbc_submitButton);
        
//        returnTextArea = new JTextArea();
//        returnTextArea.setEditable(false);
        
//        JScrollPane scrollPane = new JScrollPane(returnTextArea,
//        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//        		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        returnPanel = new JPanel();
        
        GridBagConstraints gbc_returnTextArea = new GridBagConstraints();
        gbc_returnTextArea.gridwidth = 5;
        gbc_returnTextArea.insets = new Insets(0, 0, 5, 5);
        gbc_returnTextArea.fill = GridBagConstraints.BOTH;
        gbc_returnTextArea.gridx = 1;
        gbc_returnTextArea.gridy = 3;
        getContentPane().add(returnPanel, gbc_returnTextArea);
        
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));
        
//        panel = new JPanel();
//        
//        returnPanel.add(panel);
//        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
//        
//        try {
//			panel.add(new BookLabel(BookFinder.getBookFromGoogle("054792822X")));
//			panel.add(Box.createHorizontalStrut(10));
//			panel.add(new BookLabel(BookFinder.getBookFromGoogle("054792822X")));
//			panel.add(Box.createHorizontalStrut(10));
//			panel.add(new BookLabel(BookFinder.getBookFromGoogle("054792822X")));
//			panel.add(Box.createHorizontalStrut(10));
//			panel.add(new BookLabel(BookFinder.getBookFromGoogle("054792822X")));
//		} catch (InvalidISBNException e) {
//			e.printStackTrace();
//		}
//        
//        panel2 = new JPanel();
//        returnPanel.add(panel2);
//        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
//        
//        try {
//        	panel2.add(new BookLabel(BookFinder.getBookFromGoogle("054792822X")));
//        	panel2.add(Box.createHorizontalStrut(10));
//        	panel2.add(new BookLabel(BookFinder.getBookFromGoogle("054792822X")));
//        	panel2.add(Box.createHorizontalStrut(10));
//        	panel2.add(new BookLabel(BookFinder.getBookFromGoogle("054792822X")));
//        	panel2.add(Box.createHorizontalStrut(10));
//        	panel2.add(new BookLabel(BookFinder.getBookFromGoogle("054792822X")));
//		} catch (InvalidISBNException e) {
//			e.printStackTrace();
//		}
        
        this.setVisible(true);
	}
	
	public void showBooks(ArrayList<Book> books) {
		ArrayList<BookLabel> labels = new ArrayList<BookLabel>();
		for(Book b : books)
			labels.add(new BookLabel(b));
		
		int booksPerPanel = returnPanel.getWidth() / (labels.get(0).getWidth() + 10);
		booksPerPanel = 5;
		int numPanels = (int)Math.ceil((double)labels.size() / (double)booksPerPanel);
		
		int pos = 0;
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
			returnPanel.revalidate();
			returnPanel.repaint();
		}
	}
	
	public static void main(String[] args) throws InvalidISBNException {
		TestFrame2 test = new TestFrame2();
		ArrayList<Book> books = new ArrayList<Book>();
		Book book = BookFinder.getBookFromGoogle("054792822X");
		for(int i = 0; i < 12; i++)
			books.add(book);
		test.showBooks(books);
	}
}