package com.team1.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends LMSWindow {
	private static final long serialVersionUID = 1L;
	
	private static final int HEIGHT = 400;
	private static final int WIDTH = 300;
	private static final boolean IS_RESIZABLE = false;
	
	private static final String LMS_LABEL_TEXT = "Library Management System";
	private static final String USERNAME_LABEL_TEXT = "Username";
	private static final String PASSWORD_LABEL_TEXT = "Password";
	private static final String LOGIN_BUTTON_TEXT = "Login";
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	
	public LoginWindow() {
		super();
		
		this.setSize(WIDTH, HEIGHT);
        this.setResizable(IS_RESIZABLE);
		this.setLocationRelativeTo(null);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 40, 57, 113, 57, 40, 0 };
		gridBagLayout.rowHeights = new int[] { 36, 61, 33, 14, 20, 14, 20, 33, 23, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridwidth = 3;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);

		JLabel lmsLabel = new JLabel(LMS_LABEL_TEXT);
		panel.add(lmsLabel);

		JLabel usernameLabel = new JLabel(USERNAME_LABEL_TEXT);
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 1;
		gbc_usernameLabel.gridy = 3;
		getContentPane().add(usernameLabel, gbc_usernameLabel);

		usernameField = new JTextField();
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.anchor = GridBagConstraints.NORTH;
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.gridwidth = 3;
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 4;
		getContentPane().add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);

		JLabel passwordLabel = new JLabel(PASSWORD_LABEL_TEXT);
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 5;
		getContentPane().add(passwordLabel, gbc_passwordLabel);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.NORTH;
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 6;
		getContentPane().add(passwordField, gbc_passwordField);

		loginButton = new JButton(LOGIN_BUTTON_TEXT);
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_loginButton.gridx = 3;
		gbc_loginButton.gridy = 7;
		getContentPane().add(loginButton, gbc_loginButton);

		this.setVisible(true);
	}

	public static void main(String[] args) {
		new LoginWindow();
	}
}
