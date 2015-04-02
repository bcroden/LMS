package com.team1.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
    
    private static final String TAB_NAME_LOOKUP = 	"Book Lookup";
    private static final String TAB_NAME_REGISTER = "Register New Book";
    private static final String TAB_NAME_CKECKOUT = "Check Out Book";
    private static final String TAB_NAME_CKECKIN = 	"Check In Book";
    private static final String TAB_NAME_PAYMENT = 	"Handle Payment";
    
    private static final String TOOLTIP_LOOKUP = 	null;
    private static final String TOOLTIP_REGISTER = 	null;
    private static final String TOOLTIP_CHECKOUT = 	null;
    private static final String TOOLTIP_CKECKIN = 	null;
    private static final String TOOLTIP_PAYMENT = 	null;
    
    public MainWindow(Controller controller, Model model) {
        super(controller, model);
        
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(IS_RESIZABLE);
        this.setLocationRelativeTo(null);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{10, 0, 10, 0};
        gridBagLayout.rowHeights = new int[]{50, 0, 10, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JPanel topPanel = new JPanel();
        GridBagConstraints gbc_topPanel = new GridBagConstraints();
        gbc_topPanel.gridwidth = 3;
        gbc_topPanel.insets = new Insets(0, 0, 5, 0);
        gbc_topPanel.fill = GridBagConstraints.BOTH;
        gbc_topPanel.gridx = 0;
        gbc_topPanel.gridy = 0;
        getContentPane().add(topPanel, gbc_topPanel);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        
        Component horizontalGlue = Box.createHorizontalGlue();
        topPanel.add(horizontalGlue);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
        });
        topPanel.add(logoutButton);
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        topPanel.add(horizontalStrut);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
        gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
        gbc_tabbedPane.fill = GridBagConstraints.BOTH;
        gbc_tabbedPane.gridx = 1;
        gbc_tabbedPane.gridy = 1;
        getContentPane().add(tabbedPane, gbc_tabbedPane);
        
        tabbedPane.addTab(TAB_NAME_LOOKUP, 	 null, new LookupPanel(this.controller), 	TOOLTIP_LOOKUP);
        tabbedPane.addTab(TAB_NAME_REGISTER, null, new RegisterPanel(this.controller), 	TOOLTIP_REGISTER);
        tabbedPane.addTab(TAB_NAME_CKECKOUT, null, new CheckOutPanel(this.controller), 	TOOLTIP_CHECKOUT);
        tabbedPane.addTab(TAB_NAME_CKECKIN,  null, new CheckInPanel(this.controller), 	TOOLTIP_CKECKIN);
        tabbedPane.addTab(TAB_NAME_PAYMENT,  null, new PaymentPanel(this.controller), 	TOOLTIP_PAYMENT);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);
        
        JMenuItem changePassword = new JMenuItem("Change Password");
        changePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Enter Current Password");
				JOptionPane.showInputDialog("Enter New Password");
				JOptionPane.showInputDialog("Re-enter New Password");
				JOptionPane.showMessageDialog(null, "Done!");
			}
        });
        optionsMenu.add(changePassword);
        
        JMenuItem addLibrarianAccount = new JMenuItem("Add Librarian Account");
        optionsMenu.add(addLibrarianAccount);
        addLibrarianAccount.setEnabled(false);
        
        JMenuItem removeLibrarianAccount = new JMenuItem("Remove Librarian Account");
        optionsMenu.add(removeLibrarianAccount);
        
        JMenuItem feesFines = new JMenuItem("Fees/Fines");
        optionsMenu.add(feesFines);
        
        this.setVisible(true);
    }
}
