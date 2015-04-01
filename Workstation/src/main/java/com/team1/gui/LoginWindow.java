package com.team1.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoginWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    
    public LoginWindow() {
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
        this.setSize(300, 400);
        this.setTitle("Workstation");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{40, 57, 113, 57, 40, 0};
        gridBagLayout.rowHeights = new int[]{36, 61, 33, 14, 20, 14, 20, 33, 23, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.gridwidth = 3;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 1;
        getContentPane().add(panel, gbc_panel);
        
        JLabel lblLibrary = new JLabel("Library Management System");
        panel.add(lblLibrary);
        
        JLabel lblUsername = new JLabel("Username");
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.anchor = GridBagConstraints.NORTHWEST;
        gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsername.gridx = 1;
        gbc_lblUsername.gridy = 3;
        getContentPane().add(lblUsername, gbc_lblUsername);
        
        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.anchor = GridBagConstraints.NORTH;
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.gridwidth = 3;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 4;
        getContentPane().add(textField, gbc_textField);
        textField.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.NORTHWEST;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 1;
        gbc_lblPassword.gridy = 5;
        getContentPane().add(lblPassword, gbc_lblPassword);
        
        passwordField = new JPasswordField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.anchor = GridBagConstraints.NORTH;
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.insets = new Insets(0, 0, 5, 5);
        gbc_passwordField.gridwidth = 3;
        gbc_passwordField.gridx = 1;
        gbc_passwordField.gridy = 6;
        getContentPane().add(passwordField, gbc_passwordField);
        
        JButton btnNewButton = new JButton("Login");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnNewButton.gridx = 3;
        gbc_btnNewButton.gridy = 7;
        getContentPane().add(btnNewButton, gbc_btnNewButton);
        
        this.setVisible(true);
    }
    
    public void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    
    public static void main(String[] args) {
        new LoginWindow();
    }
}
