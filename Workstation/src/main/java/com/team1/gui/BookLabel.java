package com.team1.gui;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolTip;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import com.team1.books.Book;

public class BookLabel extends JLabel{
	private static final long serialVersionUID = 1L;
	
	private BookLabel that;
	private Book book;
	private Popup popup;
	private PopupFactory factory;
	private JToolTip tooltip;
	
	@SuppressWarnings("unused")
	private boolean show;
	
	//TODO: make books contain image URLs
	public BookLabel(Book book) {
		super();
		
		try {
			this.setIcon(new ImageIcon(new URL(book.url)));
		} catch (MalformedURLException e1) {
			this.setIcon(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_DEFAULT_BOOK)));
		}
		
		this.that = this;
		this.show = false;
		this.book =  book;
		this.factory = PopupFactory.getSharedInstance();
		this.tooltip = this.createToolTip();
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		String text = "<html>" 	+ book.title + "</html>";
		this.tooltip.setTipText(text);
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(that.show = true)
					JOptionPane.showOptionDialog(that, 
							"Title: " + that.book.title +
							"\nAuthor: " + that.book.author +
							"\nGenre: " + that.book.genre +
							"\nISBN: " + that.book.isbn + 
							"\nPublisher: " + that.book.publisher +
							"\nDate: " + that.book.datePublished,
							that.book.title,
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE,
							that.getIcon(),
							new Object[] {},
							null);
				that.show = false;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				that.show = true;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(popup != null)
					popup.hide();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				popup = factory.getPopup(that, tooltip, that.getLocationOnScreen().x, that.getLocationOnScreen().y - tooltip.getPreferredSize().height);
				popup.show();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
}