package com.team1.gui;

import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A class which all windows used in LMS inherit from.
 * Sets default values and functionality common to all
 * windows used by LMS.
 * 
 * @author Brandon
 *
 */
public class LMSWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final String WINDOW_TITLE = "Workstation";
	
	protected Controller controller;
	protected Model model;
	
	public LMSWindow(Controller controller, Model model) {
		super();
		
		this.controller = controller;
		this.model = model;
		
		//Set Look And Feel to the system default L&F.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		this.setIconImage(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_FRAME_ICON)).getImage());
		
        this.setTitle(WINDOW_TITLE);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Closes the window without exiting the application.
	 */
	public void close() {
		//Dispatch a new window closing event targeted at this window
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}