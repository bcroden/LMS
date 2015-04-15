package com.team1.gui;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.team1.books.Book;

public class LMSBookDisplayPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public LMSBookDisplayPanel() {
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void showBooks(ArrayList<Book> books) {
		ArrayList<BookLabel> labels = new ArrayList<BookLabel>();
		for(Book b : books)
			labels.add(new BookLabel(b));
		
//		System.out.println("panel width = " + frame.getPreferredSize().getWidth());
//		System.out.println("book width = " + labels.get(0).getPreferredSize().getWidth());
		
//		int booksPerPanel = this.getWidth() / (labels.get(0).getWidth() + 10);
//		System.out.println("booksPerPanel = " + booksPerPanel);
		int booksPerPanel = 8;
		int numPanels = (int)Math.ceil((double)labels.size() / (double)booksPerPanel);
		
		int pos = 0;
		this.add(Box.createVerticalGlue());
		this.add(Box.createVerticalStrut(10));
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
			this.add(panel);
			this.add(Box.createVerticalStrut(10));
		}
		this.add(Box.createVerticalGlue());
		this.revalidate();
		this.repaint();
	}
}