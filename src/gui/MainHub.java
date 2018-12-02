package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import program.*;
import users.*;

public class MainHub extends JFrame{
	private Hub session;
	private SuperUser signedIn;
	
	private JMenuBar menuBar;
	
	private JMenu profileOptions;
	private JMenu signOut;
	
	private JMenuItem exit;
	private JMenuItem changeInfo;
	private JMenuItem showInfo;
	
	public MainHub(Hub hub, SuperUser signedInUser){
		super("Musicians Hub");
		session = hub;
		signedIn = signedInUser;
		setSize(700,900);
		buildGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void buildGUI() {
		signOut = new JMenu("Sign Out");
		
		profileOptions = new JMenu("Profile");
		showInfo = new JMenuItem("Show Information");
		changeInfo = new JMenuItem("Edit Information");
		profileOptions.add(showInfo);
		profileOptions.add(changeInfo);
		showInfo.addActionListener(new MenuListener());
		changeInfo.addActionListener(new MenuListener());
		
		menuBar = new JMenuBar();
		//signOut.addActionListener(new MenuListener());
		menuBar.add(profileOptions);
		menuBar.add(signOut);
		setJMenuBar(menuBar);
	}
	private class MenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JMenuItem source = (JMenuItem)(e.getSource());
			if(source.equals(exit)) {
				session.saveData();
				setVisible(false);
				dispose();
			}
			if(source.equals(showInfo)) {
				handleShowInfo();
			}
			if(source.equals(changeInfo)) {
				handleChangeInfo();
			}
		}
	}
	
	private void handleShowInfo() {
		
	}
	
	private void handleChangeInfo() {
		JPanel info = new JPanel();
		info.setLayout(new GridBagLayout());
		
		JTextField usernameField = new JTextField(10);
		JTextField passwordField = new JTextField(10);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setLabelFor(usernameField);
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setLabelFor(passwordField);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.insets = new Insets(4,4,4,4);
		
		c.gridwidth = 1;
		
		
		if (signedIn instanceof Musician) {
			
		}
		
		int result = JOptionPane.showConfirmDialog(null, info, "Edit Information", JOptionPane.PLAIN_MESSAGE);
	
	}
}
