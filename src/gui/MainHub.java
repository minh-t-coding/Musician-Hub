package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import program.*;
import users.*;

public class MainHub extends JFrame{
	private Hub session;
	private SuperUser signedIn;
	private JFrame lookupFrame;
	private JMenuBar menuBar;
	
	private JMenu profileOptions;
	private JMenu signOut;
	private JMenuItem changeInfo;
	private JMenuItem showInfo;
	private JMenu lookup;
	private JMenuItem exit;
	private JMenuItem memberLookup;
	private JMenu posts;
	
	private JTextField lookupName;
	private JButton searchLookup;
	private JButton cancelLookup;
	private JTextArea lookupResults;
	
	private JMenuItem status;
	private JMenuItem meetup;
	private JMenuItem ad;
	
	public MainHub(Hub hub, SuperUser signedInUser){
		super("Musicians Hub");
		session = Hub.loadData();
		signedIn = signedInUser;
		setSize(700,900);
		buildGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void buildGUI() {
		menuBar = new JMenuBar();
				
		profileOptions = new JMenu("Profile");
		posts = new JMenu("Posts");
		if(signedIn instanceof Musician) {
			status = new JMenuItem("Status Update");
			status.addActionListener(new MenuListener());
			meetup = new JMenuItem("Meetup");
			meetup.addActionListener(new MenuListener());
			posts.add(status);
			posts.add(meetup);
		}
		else if(signedIn instanceof Company){
			ad = new JMenuItem("Advertisement");
			meetup.addActionListener(new MenuListener());
			posts.add(ad);
		}
		showInfo = new JMenuItem("Show Information");
		changeInfo = new JMenuItem("Edit Information");
		
		profileOptions.add(showInfo);
		profileOptions.add(changeInfo);
		showInfo.addActionListener(new MenuListener());
		changeInfo.addActionListener(new MenuListener());		

		signOut = new JMenu("Sign Out");
		exit = new JMenuItem("Exit");
		
		lookup = new JMenu("Lookup");
		memberLookup = new JMenuItem("Member Lookup");
		memberLookup.addActionListener(new MenuListener());
		lookup.add(memberLookup);
		
		exit.addActionListener(new MenuListener());
		signOut.add(exit);
		
		menuBar.add(profileOptions);
		menuBar.add(posts);
		menuBar.add(lookup);
		menuBar.add(signOut);
		setJMenuBar(menuBar);
	}
	private class MenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JMenuItem source = (JMenuItem)(e.getSource());
			if(source.equals(exit)) {
				Hub.saveData(session);
				setVisible(false);
				dispose();
				new WelcomeScreen();
			}
			else if(source.equals(showInfo)) {
				handleShowInfo();
			}
			else if(source.equals(changeInfo)) {
				handleChangeInfo();
			}
			else if(source.equals(memberLookup)) {
				lookupFrame = new JFrame();
				lookupFrame.setSize(500,150);
				lookupFrame.setLayout(new FlowLayout());
				JLabel lookupDir = new JLabel("Please enter a username to lookup: ");
				lookupName = new JTextField();
				lookupName.setColumns(15);
				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());
				searchLookup = new JButton("Search");
				searchLookup.addActionListener(new searchButton());
				cancelLookup = new JButton("Cancel");
				cancelLookup.addActionListener(new cancelButton());
				buttonPanel.add(searchLookup);
				buttonPanel.add(cancelLookup);
				JPanel view = new JPanel();
				
				view.add(lookupDir);
				view.add(lookupName);
				lookupFrame.add(view);
				lookupFrame.add(buttonPanel);
				
				lookupFrame.setVisible(true);
			}
			else if(source.equals(status)) {
				handleStatusUpdate();
			}
			
		}
	
	}
	//handles search button for lookup
	private class searchButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String lookup = lookupName.getText();
			lookupResults = new JTextArea();
			ArrayList<SuperUser> listOfLikeUsers = session.findLikeUsers(lookup);
			if(listOfLikeUsers != null) {
				String results = "";
				for(SuperUser u : listOfLikeUsers) {
					results += u.getUsername() + ": " + u.getRealName() + "\n";
				}
				lookupResults.setText(results);
				
				lookupResults.setEditable(false);
				JScrollPane scroll = new JScrollPane(lookupResults);
				scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
				lookupFrame.add(scroll);
				lookupFrame.setSize(500,300);
			}
			else {
				JOptionPane.showMessageDialog(null, "No such user exists." ,
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	//cancel button for the lookup frame
	private class cancelButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			lookupFrame.setVisible(false);
			lookupFrame.dispose();
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
	private void handleStatusUpdate() {
		String input = JOptionPane.showInputDialog(
                null, "What do you have to say?");
		((Musician) signedIn).createStatusUpdate(input);
	}
}
