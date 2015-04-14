package com.team1.gui;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LookupDialog  {
	
	public LookupDialog(Controller controller) {
		
		JOptionPane.showOptionDialog(null,
				null,
				"Lookup",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(""),
				new Object[] {new LookupPanel(controller)},
				null);
	}
}