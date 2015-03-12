package com.team1.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Extremely temporary GUI designed look absolutely terrible, and help fulfill
 * User Stories 2.1 - 2.3 for release 1.
 * 
 * @author Brandon
 *
 */
public class View extends JFrame {
    private static final long serialVersionUID = 1L;

    protected JTextField usernameField;
    protected JPasswordField passwordField;
    protected JTextField isbnField;
    protected JTextField isbnField2;
    protected JTextField patronField;
    protected JTextField isbnField3;
    protected JTextField patronField2;
    protected JButton submitButton;
    protected JButton searchButton;
    protected JButton checkOutButton;
    protected JButton checkInButton;
    protected JTextArea loginResponse;
    protected JTextArea getBookResponse;
    protected JTextArea checkOutResponse;
    protected JTextArea checkInResponse;

    public View() {
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

        this.setTitle("Workstation");
        this.setSize(800, 600);
        getContentPane().setLayout(null);

        /*
         * This section makes a panel to contain the components required to
         * logging in.
         */

        // Beware: sea of magic numbers below

        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(10, 11, 217, 127);
        getContentPane().add(loginPanel);
        loginPanel.setLayout(null);

        JLabel textLogin = new JLabel("Login");
        textLogin.setBounds(10, 11, 46, 14);
        loginPanel.add(textLogin);

        JLabel textUsername = new JLabel("Username");
        textUsername.setBounds(10, 36, 55, 14);
        loginPanel.add(textUsername);

        JLabel textPassword = new JLabel("Password");
        textPassword.setBounds(10, 61, 55, 14);
        loginPanel.add(textPassword);

        usernameField = new JTextField();
        usernameField.setBounds(75, 33, 125, 20);
        loginPanel.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(75, 58, 125, 20);
        loginPanel.add(passwordField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(111, 89, 89, 23);
        loginPanel.add(submitButton);

        /*
         * This section makes a panel designed to look up a book by ISBN
         */

        JPanel bookSearchPanel = new JPanel();
        bookSearchPanel.setBounds(10, 149, 217, 127);
        getContentPane().add(bookSearchPanel);
        bookSearchPanel.setLayout(null);

        JLabel textBookLookup = new JLabel("Get Book Info");
        textBookLookup.setBounds(10, 11, 76, 14);
        bookSearchPanel.add(textBookLookup);

        JLabel textISBN = new JLabel("ISBN");
        textISBN.setBounds(10, 36, 46, 14);
        bookSearchPanel.add(textISBN);

        isbnField = new JTextField();
        isbnField.setBounds(66, 33, 123, 20);
        bookSearchPanel.add(isbnField);
        isbnField.setColumns(10);

        searchButton = new JButton("Search");
        searchButton.setBounds(100, 64, 89, 23);
        bookSearchPanel.add(searchButton);

        /*
         * This section creates a panel designed to contain the components
         * required to check out a book to a patron
         */

        JPanel checkOutPanel = new JPanel();
        checkOutPanel.setBounds(237, 149, 217, 127);
        getContentPane().add(checkOutPanel);
        checkOutPanel.setLayout(null);

        JLabel textCheckOut = new JLabel("Check Out Book");
        textCheckOut.setBounds(10, 11, 83, 14);
        checkOutPanel.add(textCheckOut);

        JLabel textISBN2 = new JLabel("ISBN");
        textISBN2.setBounds(10, 36, 46, 14);
        checkOutPanel.add(textISBN2);

        JLabel textPatron = new JLabel("Patron");
        textPatron.setBounds(10, 61, 46, 14);
        checkOutPanel.add(textPatron);

        isbnField2 = new JTextField();
        isbnField2.setBounds(64, 33, 125, 20);
        checkOutPanel.add(isbnField2);
        isbnField2.setColumns(10);

        patronField = new JTextField();
        patronField.setBounds(64, 58, 125, 20);
        checkOutPanel.add(patronField);
        patronField.setColumns(10);

        checkOutButton = new JButton("Check Out");
        checkOutButton.setBounds(100, 89, 89, 23);
        checkOutPanel.add(checkOutButton);

        /*
         * This section creates a panel designed to contain the components
         * required to check in a book to a patron
         */

        JPanel checkInPanel = new JPanel();
        checkInPanel.setBounds(464, 149, 217, 127);
        getContentPane().add(checkInPanel);
        checkInPanel.setLayout(null);

        JLabel textCheckIn = new JLabel("Check In Book");
        textCheckIn.setBounds(10, 11, 75, 14);
        checkInPanel.add(textCheckIn);

        JLabel textISBN3 = new JLabel("ISBN");
        textISBN3.setBounds(10, 36, 46, 14);
        checkInPanel.add(textISBN3);

        JLabel textPatron2 = new JLabel("Patron");
        textPatron2.setBounds(10, 61, 46, 14);
        checkInPanel.add(textPatron2);

        isbnField3 = new JTextField();
        isbnField3.setBounds(64, 33, 125, 20);
        checkInPanel.add(isbnField3);
        isbnField3.setColumns(10);

        patronField2 = new JTextField();
        patronField2.setBounds(64, 58, 125, 20);
        checkInPanel.add(patronField2);
        patronField2.setColumns(10);

        checkInButton = new JButton("Check In");
        checkInButton.setBounds(100, 89, 89, 23);
        checkInPanel.add(checkInButton);

        /*
         * These text areas are designed to display the responses to the user.
         */

        loginResponse = new JTextArea();
        loginResponse.setBounds(233, 11, 217, 127);
        loginResponse.setLineWrap(true);
        loginResponse.setWrapStyleWord(true);
        getContentPane().add(loginResponse);

        getBookResponse = new JTextArea();
        getBookResponse.setBounds(10, 282, 217, 270);
        getBookResponse.setLineWrap(true);
        getBookResponse.setWrapStyleWord(true);
        getContentPane().add(getBookResponse);

        checkOutResponse = new JTextArea();
        checkOutResponse.setBounds(237, 282, 217, 270);
        checkOutResponse.setLineWrap(true);
        checkOutResponse.setWrapStyleWord(true);
        getContentPane().add(checkOutResponse);

        checkInResponse = new JTextArea();
        checkInResponse.setBounds(464, 282, 217, 270);
        checkInResponse.setLineWrap(true);
        checkInResponse.setWrapStyleWord(true);
        getContentPane().add(checkInResponse);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}