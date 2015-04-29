package com.team1.gui;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.team1.books.Book;

public class LMSBookScrollPane extends JScrollPane{
	private static final long serialVersionUID = 1L;
	
	public static final int UNIT_INCREMENT = 20;
	
	private JPanel panel;
	
	private void init() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		this.setViewportView(panel);
        this.getVerticalScrollBar().setUnitIncrement(UNIT_INCREMENT);
	}
	
	public LMSBookScrollPane() {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        	  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		init();
	}
	
	public LMSBookScrollPane(String title) {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        	  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		init();
        this.setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}
	
	public void showBooks(ArrayList<Book> books) {
		ArrayList<BookLabel> labels = new ArrayList<BookLabel>();
		for(Book b : books)
			labels.add(new BookLabel(b));
		
		this.panel.removeAll();
		this.panel.revalidate();
		this.panel.repaint();
		
//		//System.out.println("panel width = " + frame.getPreferredSize().getWidth());
//		//System.out.println("book width = " + labels.get(0).getPreferredSize().getWidth());
		
//		int booksPerPanel = this.getWidth() / (labels.get(0).getWidth() + 10);
//		//System.out.println("booksPerPanel = " + booksPerPanel);
		int booksPerPanel = 8;
		int numPanels = (int)Math.ceil((double)labels.size() / (double)booksPerPanel);
		
		int pos = 0;
		this.panel.add(Box.createVerticalGlue());
		this.panel.add(Box.createVerticalStrut(10));
		for(int i = 0; i < numPanels; i++) {
			JPanel panel = new JPanel();
		    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			for(int j = 0; j < booksPerPanel; j++) {
				if(pos < labels.size()) {
					panel.add(labels.get(pos));
					panel.add(Box.createHorizontalStrut(5));
					//System.out.println("i = " + i + ", j = " + j);
					pos++;
				}
			}
			this.panel.add(panel);
			this.panel.add(Box.createVerticalStrut(10));
		}
		this.panel.add(Box.createVerticalGlue());
		this.panel.revalidate();
		this.panel.repaint();
	}
}