package com.team1.gui;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class LMSBookScrollPane extends JScrollPane{
	private static final long serialVersionUID = 1L;
	
	public static final int UNIT_INCREMENT = 20;
	
	LMSBookDisplayPanel panel;
	
	public LMSBookScrollPane(String title) {
		super(  		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panel = new LMSBookDisplayPanel();
		
		this.add(panel);
        this.getVerticalScrollBar().setUnitIncrement(UNIT_INCREMENT);
        this.setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}
}