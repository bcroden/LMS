package com.team1.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class LMSButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	public LMSButton(String str) {
		super(str);
		init();
	}
	
	public LMSButton(Icon icon) {
		super(icon);
		init();
	}
	
	private void init(){
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setContentAreaFilled(false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
	}
}