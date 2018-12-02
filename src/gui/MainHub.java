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
	private JMenu signOut;
	private JMenu lookup;
	private JMenuItem exit;
	private JMenuItem memberLookup;
	
	private JTextField lookupName;
	private JButton searchLookup;
	private JButton cancelLookup;
	private JTextArea lookupResults;
	
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
		//System.out.println(signedIn.getUsername());
		signOut = new JMenu("Sign Out");
		exit = new JMenuItem("Exit");
		lookup = new JMenu("Lookup");
		memberLookup = new JMenuItem("Member Lookup");
		menuBar = new JMenuBar();
		exit.addActionListener(new MenuListener());
		memberLookup.addActionListener(new MenuListener());
		
		signOut.add(exit);
		lookup.add(memberLookup);
		menuBar.add(lookup);
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
				new WelcomeScreen();
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
		}
	
	}
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
	private class cancelButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			lookupFrame.setVisible(false);
			lookupFrame.dispose();
		}
	}
}
