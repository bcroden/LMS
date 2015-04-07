package com.team1.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.team1.formatting.queries.LoginQuery;
import com.team1.formatting.responses.LogInResponse;
import com.team1.formatting.responses.Response;

/**
 * A class representing a login window. This class will create and display a new frame
 * that allows a user to enter a username and password and submit those values to the
 * LMS. If the values match a user in the database, the user will be allowed to continue.
 * If the values do not match a user in the database the user will be prompted with an error
 * message and asked to try again.
 * 
 * @author Brandon
 *
 */
public class LoginWindow extends LMSWindow {
	private static final long serialVersionUID = 1L;
	
	private static final int HEIGHT = 400;
	private static final int WIDTH = 300;
	private static final boolean IS_RESIZABLE = false;
	
	private static final String LMS_LABEL_TEXT = "Library Management System";
	private static final String USERNAME_LABEL_TEXT = "Username";
	private static final String PASSWORD_LABEL_TEXT = "Password";
	private static final String LOGIN_BUTTON_TEXT = "Login";
	private static final String ERROR_MESSAGE_TEXT = "Invalid Username/Password";
	private static final String ERROR_MESSAGE_INITIAL_TEXT = "";
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JLabel errorMessage;
	
	public LoginWindow(final Controller controller, Model model) {
		super(controller, model);
		
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
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				LoginQuery query = new LoginQuery(usernameField.getText(), new String(passwordField.getPassword()));
				
				String r = controller.sendMessage(query.toString());
				
				System.out.println("Recieved string from login Window: " + r);
				
				if(r == null) {
					errorMessage.setText(ERROR_MESSAGE_TEXT);
					//controller.showMainWindow();
					return;
				}
				
				System.out.println("Before response");
				Response response = Response.stringToResponse(r);
				System.out.println("After response" + ((LogInResponse)response).toString());
				
				if(response.wasSuccessful) {
					controller.model.sessionId = ((LogInResponse)response).sessionID;
					System.out.println(controller.model.sessionId);
					controller.model.status = ((LogInResponse)response).status;
					controller.model.username = usernameField.getText();
					controller.showMainWindow();
				}
			}
		});
		
		errorMessage = new JLabel(ERROR_MESSAGE_INITIAL_TEXT);
		errorMessage.setForeground(Color.RED);
		GridBagConstraints gbc_errorMessage = new GridBagConstraints();
		gbc_errorMessage.insets = new Insets(0, 0, 5, 5);
		gbc_errorMessage.gridx = 2;
		gbc_errorMessage.gridy = 7;
		getContentPane().add(errorMessage, gbc_errorMessage);
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_loginButton.gridx = 3;
		gbc_loginButton.gridy = 7;
		getContentPane().add(loginButton, gbc_loginButton);
		
		usernameField.setText("CadeG");
		passwordField.setText("test");

		this.setVisible(true);
	}
}
