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
		exit = new JMenuItem("Exit");
		lookup = new JMenu("Lookup");
		memberLookup = new JMenuItem("Member Lookup");
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
			if(source.equals(showInfo)) {
				handleShowInfo();
			}
			if(source.equals(changeInfo)) {
				handleChangeInfo();
			}
			else if(source.equals(memberLookup)) {
				//handle lookup
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
	
	private void handleShowInfo() {
		
	}
	
	private void handleChangeInfo() {
		JPanel info = new JPanel();
		info.setLayout(new GridBagLayout());
		
		JTextField usernameField = new JTextField(10);
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setEchoChar('*');
		JTextField realNameField = new JTextField(10);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setLabelFor(usernameField);
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setLabelFor(passwordField);
		
		JLabel realNameLabel = new JLabel("Real/Company Name: ");
		realNameLabel.setLabelFor(realNameField);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.insets = new Insets(4,4,4,4);
		
		c.gridwidth = 1;
		info.add(usernameLabel,c);
		c.gridwidth = GridBagConstraints.REMAINDER;
		info.add(usernameField,c);
		
		c.gridwidth = 1;
		info.add(passwordLabel,c);
		c.gridwidth = GridBagConstraints.REMAINDER;
		info.add(passwordField,c);
		
		c.gridwidth = 1;
		info.add(realNameLabel,c);
		c.gridwidth = GridBagConstraints.REMAINDER;
		info.add(realNameField,c);
		
		if (signedIn instanceof Musician || signedIn instanceof Admin) {
			JTextField genreField = new JTextField(10);
			JLabel genreLabel = new JLabel("Music Genre: ");
			genreLabel.setLabelFor(genreField);
			
			c.gridwidth = 1;
			info.add(genreLabel,c);
			c.gridwidth = GridBagConstraints.REMAINDER;
			info.add(genreField,c);
			
			JLabel instrumentsLabel = new JLabel("Instruments: ");
			JButton addInstrument = new JButton("Add");
			JButton deleteInstrument = new JButton("Delete");
			
			info.add(instrumentsLabel,c);
			info.add(addInstrument,c);
			info.add(deleteInstrument,c);
			
			addInstrument.addActionListener(new addInstrumentButton());
			deleteInstrument.addActionListener(new deleteInstrumentButton());
		}
		
		int result = JOptionPane.showConfirmDialog(null, info, "Edit Information", JOptionPane.PLAIN_MESSAGE);
	}
	
	public boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	} 
	
	private class addInstrumentButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JPanel popUp = new JPanel();
			JTextField instrumentField = new JTextField();
			JTextField yearsField = new JTextField();

			popUp.setLayout(new GridLayout(2, 2));
			popUp.add(new JLabel("Instrument name:"));
			popUp.add(instrumentField);

			popUp.add(new JLabel("# of years played:"));
			popUp.add(yearsField);

			int result = JOptionPane.showConfirmDialog(null, popUp, "Add Instrument", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				String nameStr = instrumentField.getText();
				String years = yearsField.getText();
				if (nameStr != null) {
					if (nameStr.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter an instrument name.",
								"ERROR: No name entered", JOptionPane.ERROR_MESSAGE);
					}
					if (years.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter years played.", "ERROR: No number entered",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (!isNumeric(years)) {
							JOptionPane.showMessageDialog(null, "Input \"" + years + "\" is not a valid number.",
									"ERROR: Input years played", JOptionPane.ERROR_MESSAGE);
						} else if (Integer.parseInt(years) < 0) {
							JOptionPane.showMessageDialog(null, "Years played cannot be negative!",
									"ERROR: Input years played", JOptionPane.ERROR_MESSAGE);
						} else {
							Instrument newInstrument = new Instrument();
							newInstrument.setName(nameStr);
							newInstrument.setYearsPlayed(Float.parseFloat(years));
							((Musician) signedIn).addInstrument(newInstrument);
						}
					}
				}
			}
		}
	}

	private class deleteInstrumentButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JTextField instField = new JTextField(10);
			JLabel instLabel = new JLabel("Instrument to Remove: ");
			instLabel.setLabelFor(instField);

			JPanel popUp = new JPanel();
			popUp.add(instLabel);
			popUp.add(instField);
			int result = JOptionPane.showConfirmDialog(null, popUp, "Remove Instrument", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {
				String instStr = instField.getText();

				if (instStr != null) {
					if (instStr.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Field was empty.", "ERROR: No instrument entered",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Instrument toDelete = null;
						for (Instrument ins : ((Musician) signedIn).getInstrumentsPlayed()) {
							if (ins.getName().equals(instStr)) {
								toDelete = ins;
								((Musician) signedIn).removeInstrument(toDelete);
								break;
							}
						}
						if (toDelete == null) {
							JOptionPane.showMessageDialog(null,
									"Intrument \"" + instStr + "\" was not in your known instruments.",
									"ERROR: Remove Instrument", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}
	}
}
