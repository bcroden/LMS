package com.team1.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    public MainWindow() {
        super();
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Workstation");
//        this.setResizable(false);
        this.setLocationRelativeTo(null);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{30, 0, 30, 0, 0};
        gridBagLayout.rowHeights = new int[]{30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JPanel topPanel = new JPanel();
        GridBagConstraints gbc_topPanel = new GridBagConstraints();
        gbc_topPanel.gridheight = 2;
        gbc_topPanel.gridwidth = 4;
        gbc_topPanel.insets = new Insets(0, 0, 5, 5);
        gbc_topPanel.fill = GridBagConstraints.BOTH;
        gbc_topPanel.gridx = 0;
        gbc_topPanel.gridy = 0;
        getContentPane().add(topPanel, gbc_topPanel);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        
        Component horizontalGlue = Box.createHorizontalGlue();
        topPanel.add(horizontalGlue);
        
        JButton logoutButton = new JButton("Logout");
        topPanel.add(logoutButton);
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        topPanel.add(horizontalStrut);
        
        JButton button1 = new JButton("New button");
        GridBagConstraints gbc_button1 = new GridBagConstraints();
        gbc_button1.insets = new Insets(0, 0, 5, 5);
        gbc_button1.gridx = 1;
        gbc_button1.gridy = 2;
        getContentPane().add(button1, gbc_button1);
        
        JPanel mainPanel = new JPanel();
        GridBagConstraints gbc_mainPanel = new GridBagConstraints();
        gbc_mainPanel.gridheight = 10;
        gbc_mainPanel.fill = GridBagConstraints.BOTH;
        gbc_mainPanel.gridx = 3;
        gbc_mainPanel.gridy = 2;
        getContentPane().add(mainPanel, gbc_mainPanel);
        
        JButton button2 = new JButton("New button");
        GridBagConstraints gbc_button2 = new GridBagConstraints();
        gbc_button2.insets = new Insets(0, 0, 5, 5);
        gbc_button2.gridx = 1;
        gbc_button2.gridy = 3;
        getContentPane().add(button2, gbc_button2);
        
        JButton button3 = new JButton("New button");
        GridBagConstraints gbc_button3 = new GridBagConstraints();
        gbc_button3.insets = new Insets(0, 0, 5, 5);
        gbc_button3.gridx = 1;
        gbc_button3.gridy = 4;
        getContentPane().add(button3, gbc_button3);
        
        JButton button4 = new JButton("New button");
        GridBagConstraints gbc_button4 = new GridBagConstraints();
        gbc_button4.insets = new Insets(0, 0, 5, 5);
        gbc_button4.gridx = 1;
        gbc_button4.gridy = 5;
        getContentPane().add(button4, gbc_button4);
        
        JButton button5 = new JButton("New button");
        GridBagConstraints gbc_button5 = new GridBagConstraints();
        gbc_button5.insets = new Insets(0, 0, 5, 5);
        gbc_button5.gridx = 1;
        gbc_button5.gridy = 6;
        getContentPane().add(button5, gbc_button5);
        
        JButton button6 = new JButton("New button");
        GridBagConstraints gbc_button6 = new GridBagConstraints();
        gbc_button6.insets = new Insets(0, 0, 5, 5);
        gbc_button6.gridx = 1;
        gbc_button6.gridy = 7;
        getContentPane().add(button6, gbc_button6);
        
        JButton button7 = new JButton("New button");
        GridBagConstraints gbc_button7 = new GridBagConstraints();
        gbc_button7.insets = new Insets(0, 0, 5, 5);
        gbc_button7.gridx = 1;
        gbc_button7.gridy = 8;
        getContentPane().add(button7, gbc_button7);
        
        JButton button8 = new JButton("New button");
        GridBagConstraints gbc_button8 = new GridBagConstraints();
        gbc_button8.insets = new Insets(0, 0, 5, 5);
        gbc_button8.gridx = 1;
        gbc_button8.gridy = 9;
        getContentPane().add(button8, gbc_button8);
        
        JButton button9 = new JButton("New button");
        GridBagConstraints gbc_button9 = new GridBagConstraints();
        gbc_button9.insets = new Insets(0, 0, 5, 5);
        gbc_button9.gridx = 1;
        gbc_button9.gridy = 10;
        getContentPane().add(button9, gbc_button9);
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new MainWindow();
    }
}
