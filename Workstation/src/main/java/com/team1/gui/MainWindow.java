package com.team1.gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;

import com.team1.formatting.queries.AddLibrarianQuery;
import com.team1.formatting.queries.PasswordChangeQuery;
import com.team1.formatting.queries.RemoveLibrarianQuery;
import com.team1.formatting.queries.SetFineQuery;
import com.team1.formatting.responses.Response;

/**
 * The main window for the workstation. Allows a user to perform librarian activities
 * as specified in the Formal Requirements Specification Document.
 * 
 * @author Brandon
 *
 */
public class MainWindow extends LMSWindow {
    private static final long serialVersionUID = 1L;
    
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private static final boolean IS_RESIZABLE = true;
    
    private static final String TAB_NAME_LOOKUP = 		"Book Lookup";
    private static final String TAB_NAME_REGISTER = 	"Register New Book";
    private static final String TAB_NAME_CKECKOUT = 	"Check Out Book";
    private static final String TAB_NAME_CKECKIN = 		"Check In Book";
    private static final String TAB_NAME_PAYMENT = 		"Handle Payment";
    
    private static final String MENU_TEXT_PASSWORD = 	"Change Password";
    private static final String MENU_TEXT_ADD_ACCOUNT = "Add Librarian Account";
    private static final String MENU_TEXT_REM_ACCOUNT = "Remove Librarian Account";
    private static final String MENU_TEXT_FINES = 		"Fees/Fines";
    private static final String MENU_TEXT_LOGOUT = 		"Logout";
    
    private static final String TOOLTIP_LOOKUP = 		"Lookup Books";
    private static final String TOOLTIP_REGISTER = 		"Register New Books";
    private static final String TOOLTIP_CHECKOUT = 		"Check Out Books";
    private static final String TOOLTIP_CKECKIN = 		"Check In Books";
    private static final String TOOLTIP_PAYMENT = 		"Manage Payments From Patrons";
    private static final String TOOLTIP_OPTIONS = 		"Options";
    
    private LMSButton optionsButton;
    
    private JPanel topPanel;
    private JPanel mainPanel;
    private JPanel buttonsPanel;
    private JPanel contentPanel;
    
    private MainWindow that;
    
    public MainWindow(final Controller controller, Model model) {
        super(controller, model);
        
        this.that = this;
        
        this.setSize(WIDTH, HEIGHT);
        //Maximize
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(IS_RESIZABLE);
        //Start in center of screen
        this.setLocationRelativeTo(null);
        
        //Initialize layout manager
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{10, 0, 10, 0};
        gridBagLayout.rowHeights = new int[]{10, 50, 0, 10, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        //Create top panel w/ layout manager
        topPanel = new JPanel();
        GridBagConstraints gbc_topPanel = new GridBagConstraints();
        gbc_topPanel.insets = new Insets(0, 0, 5, 0);
        gbc_topPanel.fill = GridBagConstraints.BOTH;
        gbc_topPanel.gridx = 1;
        gbc_topPanel.gridy = 1;
        getContentPane().add(topPanel, gbc_topPanel);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        
        //Add glue to top panel to make option button appear at right side
        Component horizontalGlue = Box.createHorizontalGlue();
        topPanel.add(horizontalGlue);
        
        //Create popupmenu for options button
        final JPopupMenu menu = new JPopupMenu();
        
        //Create menu item for changing passwords
        JMenuItem changePassword = new JMenuItem(MENU_TEXT_PASSWORD);
        changePassword.setIcon(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_PASSWORD)));
        changePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String password = " ";
				String newPassword = " ";
				String newPassword2 = " ";
				try{
				password = JOptionPane.showInputDialog("Enter Current Password");
				}
				catch(NullPointerException e2){
					password = " ";
					JOptionPane.showMessageDialog(that, "Warning, you did not enter a Password");
				}
				try{
				newPassword = JOptionPane.showInputDialog("Enter New Password");
				}
				catch(NullPointerException e3){
					newPassword = " ";
					JOptionPane.showMessageDialog(that, "Warning, you did not enter a New Password");
				}
				try{
				newPassword2 = JOptionPane.showInputDialog("Re-enter New Password");
				}
				catch(NullPointerException e4){
					newPassword2 = " ";
					JOptionPane.showMessageDialog(that, "Warning, you did not re-enter your New Password");
				}
				
				if(newPassword.equals(newPassword2)) {
					PasswordChangeQuery query = new PasswordChangeQuery(controller.model.sessionId, password, newPassword, controller.model.username);
					
					String r = controller.sendMessage(query.toString());
					
					if(r == null) {
						JOptionPane.showMessageDialog(that, "Database failed to message back. Database may not be running");
						return;
					}
					
					Response response = Response.stringToResponse(r);
					if(response.wasSuccessful)
						JOptionPane.showMessageDialog(that, "The Password was successfully changed");
					else
						JOptionPane.showMessageDialog(that, "The Database manager failed to change the password");
				}
				else
					JOptionPane.showMessageDialog(that, "The passwords did not match");
			}
        });
        menu.add(changePassword);
        
        //Create menu item for adding librarian accounts
        JMenuItem addLibrarianAccount = new JMenuItem(MENU_TEXT_ADD_ACCOUNT);
        addLibrarianAccount.setIcon(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_ADD_USER)));
        //if(controller.model.status < 3)
        //	addLibrarianAccount.setEnabled(false);
        addLibrarianAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newUsername = " ";
				String newPassword = " ";
				String newPassword2 = " ";
				String newEmail = " ";
				String newFname = " ";
				String newLname = " ";
				try{
				newUsername = JOptionPane.showInputDialog("Enter New Librarian Username");
				}
				catch(NullPointerException e2){
					newUsername = " ";
					JOptionPane.showMessageDialog(that, "Warning, you did not enter a Librarian Username");
				}
				try{
				newPassword = JOptionPane.showInputDialog("Enter New Librarian Password");
				}
				catch(NullPointerException e3){
					JOptionPane.showMessageDialog(that, "Warning, you did not enter a Password");
				}
				try{
				newPassword2 = JOptionPane.showInputDialog("Re-enter New Librarian Password");
				}
				catch(NullPointerException e4){
					newPassword2 = " ";
					JOptionPane.showMessageDialog(that, "Warning, you did not re-enter Password");
				}
				try{
				newEmail = JOptionPane.showInputDialog("Enter New Librarian Email");
				}
				catch(NullPointerException e5){
					newEmail = " ";
					JOptionPane.showMessageDialog(that, "Warning, you did not enter a valid email");			
				}
				try{
				newFname = JOptionPane.showInputDialog("Enter New Librarian First Name");
				}
				catch(NullPointerException e6){
					newFname = " ";
					JOptionPane.showMessageDialog(that, "Warning, you did not enter a Librarian First Name");
				}
				try{
				newLname = JOptionPane.showInputDialog("Enter new Librarian Last Name");
				}
				catch(NullPointerException e7){
					newLname = " ";
					JOptionPane.showMessageDialog(that, "Warning, you did not enter a Librarian Last Name");
				}
				
				if(newPassword.equals(newPassword2)) {
					AddLibrarianQuery query = new AddLibrarianQuery(controller.model.sessionId, newUsername, newPassword, newEmail, newFname, newLname, 0);
					
					String r = controller.sendMessage(query.toString());
					
					if(r == null) {
						JOptionPane.showMessageDialog(that, "Database failed to message back. Database may not be running");
						return;
					}
					
					Response response = Response.stringToResponse(r);
					if(response.wasSuccessful)
						JOptionPane.showMessageDialog(that, "The Librarian account was successfully added");
					else
						JOptionPane.showMessageDialog(that, "The Database manager failed to add the Librarian account");
				}
				else
					JOptionPane.showMessageDialog(that, "The passwords did not match");
			}
		});
        menu.add(addLibrarianAccount);
        
        //Create menu item for removing librarian accounts
        JMenuItem removeLibrarianAccount = new JMenuItem(MENU_TEXT_REM_ACCOUNT);
        removeLibrarianAccount.setIcon(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_REM_USER)));
        //if(controller.model.status < 3)
        //	removeLibrarianAccount.setEnabled(false);
        removeLibrarianAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newUsername = " ";
				try{
				newUsername = JOptionPane.showInputDialog("Enter New Librarian Username");
				}
				catch(NullPointerException e2){
					newUsername = " ";
					JOptionPane.showMessageDialog(that, "Warning, you did not enter a Librarian Uswername");
				}

					RemoveLibrarianQuery query = new RemoveLibrarianQuery(controller.model.sessionId, newUsername);
					
					String r = controller.sendMessage(query.toString());
					
					if(r == null) {
						JOptionPane.showMessageDialog(that, "Database failed to message back. Database may not be running");
						return;
					}
					
					Response response = Response.stringToResponse(r);
					if(response.wasSuccessful)
						JOptionPane.showMessageDialog(that, "The Librarian account was successfully removed");
					else
						JOptionPane.showMessageDialog(that, "The Database Manager failed to remove the Librarian account");
				}
        });
        menu.add(removeLibrarianAccount);
        
        //Create menu item for setting fines and fees
        JMenuItem feesFines = new JMenuItem(MENU_TEXT_FINES);
        feesFines.setIcon(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_FINES)));
        //if(controller.model.status < 3)
        //	feesFines.setEnabled(false);
        feesFines.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rate = JOptionPane.showInputDialog("Enter a new fine rate");
				float newRate = 0.0f;
				try{
					newRate = Float.parseFloat(rate);
				}
				catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(that, "You did not enter a valid number");
				}
				catch(NullPointerException e3){
					JOptionPane.showMessageDialog(that, "You did not enter anything");
				}
				
				SetFineQuery query = new SetFineQuery(controller.model.sessionId, newRate);
				
				String r = controller.sendMessage(query.toString());
				
				if(r == null) {
					JOptionPane.showMessageDialog(that, "Database failed to message back. Database may not be running");
					return;
				}
				
				Response response = Response.stringToResponse(r);
				if(response.wasSuccessful)
					JOptionPane.showMessageDialog(that, "The new Fine rate was successfully set");
				else
					JOptionPane.showMessageDialog(that, "The new Fine rate was not set");
			}
        });
        menu.add(feesFines);
        
        //Create menu item for logging out
        JMenuItem logout = new JMenuItem(MENU_TEXT_LOGOUT);
        logout.setIcon(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_LOGOUT)));
        logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
        });
        menu.add(logout);
        
        //Create options button to display popup menu
        optionsButton = new LMSButton(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_OPTIONS)));
        optionsButton.setToolTipText(TOOLTIP_OPTIONS);
        optionsButton.setMargin(new Insets(4, 4, 4, 4));
        optionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Open popup menu
				optionsButton.setFocusPainted(false);
				menu.show(that, 0, 0);
				menu.setLocation(optionsButton.getLocationOnScreen().x - menu.getWidth() + optionsButton.getWidth(), optionsButton.getLocationOnScreen().y + optionsButton.getHeight());
			}
        });
        topPanel.add(optionsButton);
        
        //Create panel to hold buttons and content panels
        mainPanel = new JPanel();
        getContentPane().add(mainPanel);
        GridBagLayout gbl_mainPanel = new GridBagLayout();
        gbl_mainPanel.columnWidths = new int[]{0, 0, 0};
        gbl_mainPanel.rowHeights = new int[]{0, 0};
        gbl_mainPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_mainPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        mainPanel.setLayout(gbl_mainPanel);

        GridBagConstraints gbc_mainPanel = new GridBagConstraints();
        gbc_mainPanel.insets = new Insets(0, 0, 5, 5);
        gbc_mainPanel.fill = GridBagConstraints.BOTH;
        gbc_mainPanel.gridx = 1;
        gbc_mainPanel.gridy = 2;
        getContentPane().add(mainPanel, gbc_mainPanel);
        
        //Create panel to hold buttons
        buttonsPanel = new JPanel();
        GridBagConstraints gbc_buttonsPanel = new GridBagConstraints();
        gbc_buttonsPanel.insets = new Insets(0, 0, 0, 5);
        gbc_buttonsPanel.fill = GridBagConstraints.BOTH;
        gbc_buttonsPanel.gridx = 0;
        gbc_buttonsPanel.gridy = 0;
        mainPanel.add(buttonsPanel, gbc_buttonsPanel);
        
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        
        //Make button to set view to lookups
        LMSButton lookupButton = new LMSButton(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_SEARCH)));
        lookupButton.setToolTipText(TOOLTIP_LOOKUP);
        lookupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CardLayout)contentPanel.getLayout()).show(contentPanel, TAB_NAME_LOOKUP);
			}
		});
        buttonsPanel.add(lookupButton);
        
        ///Make button to set view to checkouts
        LMSButton checkOutButton = new LMSButton(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_BOOK)));
        checkOutButton.setToolTipText(TOOLTIP_CHECKOUT);
        checkOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CardLayout)contentPanel.getLayout()).show(contentPanel, TAB_NAME_CKECKOUT);
			}
		});
        buttonsPanel.add(checkOutButton);
        
      //Make button to set view to checkins
        LMSButton checkInButton = new LMSButton(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_BOOK)));
        checkInButton.setToolTipText(TOOLTIP_CKECKIN);
        checkInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CardLayout)contentPanel.getLayout()).show(contentPanel, TAB_NAME_CKECKIN);
			}
		});
        buttonsPanel.add(checkInButton);
        
      //Make button to set view to register
        LMSButton registerButton = new LMSButton(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_BOOK)));
        registerButton.setToolTipText(TOOLTIP_REGISTER);
        registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CardLayout)contentPanel.getLayout()).show(contentPanel, TAB_NAME_REGISTER);
			}
		});
        buttonsPanel.add(registerButton);
        
        ////Make button to set view to payments
        LMSButton paymentButton = new LMSButton(new ImageIcon(getClass().getResource(ResourceHelper.ICON_PATH_BOOK)));
        paymentButton.setToolTipText(TOOLTIP_PAYMENT);
        paymentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CardLayout)contentPanel.getLayout()).show(contentPanel, TAB_NAME_PAYMENT);
			}
		});
        buttonsPanel.add(paymentButton);
        
        //Create panel to hold content
        contentPanel = new JPanel();
        GridBagConstraints gbc_contentPanel = new GridBagConstraints();
        gbc_contentPanel.fill = GridBagConstraints.BOTH;
        gbc_contentPanel.gridx = 1;
        gbc_contentPanel.gridy = 0;
        mainPanel.add(contentPanel, gbc_contentPanel);
        contentPanel.setLayout(new CardLayout(0, 0));
        
        //Make panels and add borders
        LookupPanel lookupPanel = new LookupPanel(this.controller);
        lookupPanel.setBorder(new TitledBorder(null, TAB_NAME_LOOKUP, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        RegisterPanel registerPanel = new RegisterPanel(this.controller);
        registerPanel.setBorder(new TitledBorder(null, TAB_NAME_REGISTER, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        CheckOutPanel checkOutPanel = new CheckOutPanel(this.controller);
        checkOutPanel.setBorder(new TitledBorder(null, TAB_NAME_CKECKOUT, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        CheckInPanel checkInPanel = new CheckInPanel(this.controller);
        checkInPanel.setBorder(new TitledBorder(null, TAB_NAME_CKECKIN, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        PaymentPanel paymentPanel = new PaymentPanel(this.controller);
        paymentPanel.setBorder(new TitledBorder(null, TAB_NAME_PAYMENT, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        
        //Add panels to card layouts
        contentPanel.add(TAB_NAME_LOOKUP, lookupPanel);
        contentPanel.add(TAB_NAME_CKECKOUT, checkOutPanel);
        contentPanel.add(TAB_NAME_CKECKIN, checkInPanel);
        contentPanel.add(TAB_NAME_REGISTER, registerPanel);
        contentPanel.add(TAB_NAME_PAYMENT, paymentPanel);
        
        this.setVisible(true);
    }
}
