package com.team1.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        
        this.view.submitButton.addActionListener(this);
        this.view.searchButton.addActionListener(this);
        this.view.checkOutButton.addActionListener(this);
        this.view.checkInButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.view.submitButton) {
            
        }
        else if(e.getSource() == this.view.searchButton) {
            
        }
        else if(e.getSource() == this.view.checkOutButton) {
            
        }
        else if(e.getSource() == this.view.checkInButton) {
            
        }
    }
}
