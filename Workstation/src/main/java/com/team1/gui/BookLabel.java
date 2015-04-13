package com.team1.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import com.team1.books.Book;

public class BookLabel extends JLabel{
	private static final long serialVersionUID = 1L;
	
	private BookLabel that;
	private Popup popup;
	private PopupFactory factory;
	private JToolTip tooltip;
	
	//TODO: make books contain image URLs
	public BookLabel(Book book) {
		super();
		
		try {
			this.setIcon(new ImageIcon(new URL(book.url)));
		} catch (MalformedURLException e1) {
			this.setIcon(new ImageIcon(getClass().getResource("/double-j-design/defaultbookicon.png")));
//			e1.printStackTrace();
		}
		
		this.that = this;
		this.factory = PopupFactory.getSharedInstance();
		this.tooltip = this.createToolTip();
		
		String text = "<html>" 	+ book.title
				    + "<br>" 	+ "Author: " 	+ book.author
				    + "<br>" 	+ "Genre: " 	+ book.genre
				    + "<br>" 	+ "ISBN: " 		+ book.isbn
				    + "<br>" 	+ "Publisher: " + book.publisher
				    + "<br>" 	+ "Date: " 		+ book.datePublished
				    + "</html>";
		this.tooltip.setTipText(text);
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
//				setCursor(Cursor.getDefaultCursor());
				if(popup != null)
					popup.hide();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
//				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				popup = factory.getPopup(that, tooltip, e.getXOnScreen(), e.getYOnScreen());
				popup.show();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked");
			}
		});
	}
}