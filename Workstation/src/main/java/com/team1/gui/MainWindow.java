package com.team1.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import com.team1.books.Book;
import com.team1.books.BookFinder;
import com.team1.books.InvalidISBNException;
import com.team1.formatting.queries.PasswordChangeQuery;
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
    
    private static final String ICON_PATH_OPTIONS = 	"/flat-ui-icons-free/gearicon32.png";
    private static final String ICON_PATH_PASSWORD = 	"/flat-ui-icons-free/lockicon16.png";
    private static final String ICON_PATH_ADD_USER = 	"/flat-ui-icons-free/usericon16g.png";
    private static final String ICON_PATH_REM_USER = 	"/flat-ui-icons-free/usericon16r.png";
    private static final String ICON_PATH_LOGOUT = 		"/flat-ui-icons-free/xicon16.png";
    
    private static final String TOOLTIP_LOOKUP = 		null;
    private static final String TOOLTIP_REGISTER = 		null;
    private static final String TOOLTIP_CHECKOUT = 		null;
    private static final String TOOLTIP_CKECKIN = 		null;
    private static final String TOOLTIP_PAYMENT = 		null;
    private static final String TOOLTIP_OPTIONS = 		"Options";
    
    private LMSButton optionsButton;
    
    private MainWindow that;
    
    public MainWindow(final Controller controller, Model model) {
        super(controller, model);
        
        this.that = this;
        
        this.setSize(WIDTH, HEIGHT);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(IS_RESIZABLE);
        this.setLocationRelativeTo(null);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{10, 0, 10, 0};
        gridBagLayout.rowHeights = new int[]{10, 50, 0, 10, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JPanel topPanel = new JPanel();
        GridBagConstraints gbc_topPanel = new GridBagConstraints();
        gbc_topPanel.insets = new Insets(0, 0, 5, 0);
        gbc_topPanel.fill = GridBagConstraints.BOTH;
        gbc_topPanel.gridx = 1;
        gbc_topPanel.gridy = 1;
        getContentPane().add(topPanel, gbc_topPanel);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        
        Component horizontalGlue = Box.createHorizontalGlue();
        topPanel.add(horizontalGlue);
        
        final JPopupMenu menu = new JPopupMenu();
        
        JMenuItem changePassword = new JMenuItem(MENU_TEXT_PASSWORD);
        changePassword.setIcon(new ImageIcon(getClass().getResource(ICON_PATH_PASSWORD)));
        changePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String password = JOptionPane.showInputDialog("Enter Current Password");
				String newPassword = JOptionPane.showInputDialog("Enter New Password");
				String newPassword2 = JOptionPane.showInputDialog("Re-enter New Password");
				
				if(newPassword.equals(newPassword2)) {
					PasswordChangeQuery query = new PasswordChangeQuery(controller.model.sessionId, password, newPassword, controller.model.username);
					
					String r = controller.sendMessage(query.toString());
					
					if(r == null) {
						JOptionPane.showMessageDialog(null, "Failed");
						return;
					}
					
					Response response = Response.stringToResponse(r);
					if(response.wasSuccessful)
						JOptionPane.showMessageDialog(null, "Done!");
					else
						JOptionPane.showMessageDialog(null, "Failed");
				}
				else
					JOptionPane.showMessageDialog(null, "Failed");
			}
        });
        menu.add(changePassword);
        
        JMenuItem addLibrarianAccount = new JMenuItem(MENU_TEXT_ADD_ACCOUNT);
        addLibrarianAccount.setIcon(new ImageIcon(getClass().getResource(ICON_PATH_ADD_USER)));
        if(controller.model.status < 3)
        	addLibrarianAccount.setEnabled(false);
        addLibrarianAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
        menu.add(addLibrarianAccount);
        
        JMenuItem removeLibrarianAccount = new JMenuItem(MENU_TEXT_REM_ACCOUNT);
        removeLibrarianAccount.setIcon(new ImageIcon(getClass().getResource(ICON_PATH_REM_USER)));
        if(controller.model.status < 3)
        	removeLibrarianAccount.setEnabled(false);
        removeLibrarianAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
        });
        menu.add(removeLibrarianAccount);
        
        JMenuItem feesFines = new JMenuItem(MENU_TEXT_FINES);
        if(controller.model.status < 3)
        	feesFines.setEnabled(false);
        feesFines.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
        });
        menu.add(feesFines);
        
        JMenuItem logout = new JMenuItem(MENU_TEXT_LOGOUT);
        logout.setIcon(new ImageIcon(getClass().getResource(ICON_PATH_LOGOUT)));
        logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
        });
        menu.add(logout);
        
        optionsButton = new LMSButton(new ImageIcon(getClass().getResource(ICON_PATH_OPTIONS)));
        optionsButton.setToolTipText(TOOLTIP_OPTIONS);
        optionsButton.setMargin(new Insets(4, 4, 4, 4));
        optionsButton.setFocusPainted(false);
        
        optionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				optionsButton.setFocusPainted(false);
				//TODO: This is kind of a hack, fix this
				menu.show(that, optionsButton.getX() - menu.getWidth() + 2 * optionsButton.getWidth(), optionsButton.getY() + 2 * optionsButton.getHeight());
			}
        });
        topPanel.add(optionsButton);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
        gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
        gbc_tabbedPane.fill = GridBagConstraints.BOTH;
        gbc_tabbedPane.gridx = 1;
        gbc_tabbedPane.gridy = 2;
        getContentPane().add(tabbedPane, gbc_tabbedPane);
        
        Book book = null;
		try {
			book = BookFinder.getBookFromGoogle("054792822X");
		} catch (InvalidISBNException e1) {
			e1.printStackTrace();
		}
        
        BookLabel bl1 = new BookLabel(book);
        BookLabel bl2 = new BookLabel(book);
        BookLabel bl3 = new BookLabel(book);
		
        JPanel testPanel = new JPanel();
        testPanel.setLayout(new BoxLayout(testPanel, BoxLayout.X_AXIS));
        
        Component horizontalGlue_1 = Box.createHorizontalGlue();
        testPanel.add(horizontalGlue_1);
        testPanel.add(bl1);
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        testPanel.add(horizontalStrut);
        testPanel.add(bl2);
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        testPanel.add(horizontalStrut_1);
        testPanel.add(bl3);
        
        Component horizontalGlue_2 = Box.createHorizontalGlue();
        testPanel.add(horizontalGlue_2);
        
        tabbedPane.addTab(TAB_NAME_LOOKUP, 	 null, new LookupPanel(this.controller), 	TOOLTIP_LOOKUP);
        tabbedPane.addTab(TAB_NAME_REGISTER, null, new RegisterPanel(this.controller), 	TOOLTIP_REGISTER);
        tabbedPane.addTab(TAB_NAME_CKECKOUT, null, new CheckOutPanel(this.controller), 	TOOLTIP_CHECKOUT);
        tabbedPane.addTab(TAB_NAME_CKECKIN,  null, new CheckInPanel(this.controller), 	TOOLTIP_CKECKIN);
        tabbedPane.addTab(TAB_NAME_PAYMENT,  null, new PaymentPanel(this.controller), 	TOOLTIP_PAYMENT);
        tabbedPane.addTab("test", testPanel);
        
        this.setVisible(true);
    }
}
