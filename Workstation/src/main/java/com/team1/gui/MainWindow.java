package com.team1.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
    
    public MainWindow() {
        super();
        
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
        
        tabbedPane.addTab(TAB_NAME_LOOKUP, 	 null, new LookupPanel(), 	TOOLTIP_LOOKUP);
        tabbedPane.addTab(TAB_NAME_REGISTER, null, new RegisterPanel(), TOOLTIP_REGISTER);
        tabbedPane.addTab(TAB_NAME_CKECKOUT, null, new CheckOutPanel(), TOOLTIP_CHECKOUT);
        tabbedPane.addTab(TAB_NAME_CKECKIN,  null, new CheckInPanel(), 	TOOLTIP_CKECKIN);
        tabbedPane.addTab(TAB_NAME_PAYMENT,  null, new PaymentPanel(), 	TOOLTIP_PAYMENT);
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new MainWindow();
    }
}
