package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import program.*;
import users.*;
import posts.*;

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
	private JPanel feed;
	private ArrayList<JLabel> user;
	private ArrayList<JTextArea> content;
	private ArrayList<JPanel> likes;
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
		session = hub;
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
		feed = new JPanel();
		user = new ArrayList<JLabel>();
		content = new ArrayList<JTextArea>();
		likes = new ArrayList<JPanel>();
		
		exit.addActionListener(new MenuListener());
		for(int i=0; i<10; i++) {
			Post post = new StatusUpdate();
			post.setContent("test" + Integer.toString(i));
			Musician m = new Musician();
			m.setRealName(Integer.toString(i));
			post.setOwner(m);
			String numLikes = Integer.toString(post.getLikes());
			JLabel labelUser = new JLabel(post.getOwner().getRealName());
			JTextArea contentArea = new JTextArea(post.getContent());
			JPanel likePanel = new JPanel();
			JLabel likeLabel = new JLabel(numLikes);
			JCheckBox click = new JCheckBox();
			user.add(labelUser);
			content.add(contentArea);
			likePanel.add(click);
			likePanel.add(likeLabel);
			likePanel.add(new JSeparator());
			likes.add(likePanel);
			
		}
		
		for(int i = 0; i<user.size(); i++) {
			feed.add(user.get(i));
			feed.add(content.get(i));
			feed.add(likes.get(i));
			feed.add(likes.get(i));
			//feed.add(new JSeparator());
		}
		GridLayout gl = new GridLayout(0,1);
		feed.setLayout(gl);
		add(feed);
		
		
		
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
				handleMemberLookup();
			}
			else if(source.equals(status)) {
				handleStatusUpdate();
			}
			else if(source.equals(meetup)) {
				handleMeetup();
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
		System.out.println("Username: " + signedIn.getUsername());
		System.out.println("Name: " + signedIn.getRealName());
		
		if (signedIn instanceof Musician || signedIn instanceof Admin) {
			System.out.println("Preferred Genre: " + ((Musician)signedIn).getMusicGenre());
			for (Instrument ins : ((Musician)signedIn).getInstrumentsPlayed()) {
				System.out.println("Instrument: " + ins.getName());
				System.out.println("Years played: " + ins.getYearsPlayed());
			}
		}
	}
	
	private void handleChangeInfo() {
		JTextField genreField = null;
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
			genreField = new JTextField(10);
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
		if (result == JOptionPane.OK_OPTION) {
			String usernameString = usernameField.getText();
			String passwordString = String.valueOf(passwordField.getPassword());
			String realNameString = realNameField.getText();
			
			
			if (usernameString != null && !usernameString.trim().equals("")) {
				signedIn.setUsername(usernameString);
			}
			if (passwordString != null && !passwordString.trim().equals("")) {
				signedIn.setPassword(passwordString);
			}
			if (realNameString != null && !realNameString.trim().equals("")) {
				signedIn.setRealName(realNameString);
			}
			
			if (signedIn instanceof Musician || signedIn instanceof Admin) {
				String genreString = genreField.getText();
				if (genreString != null && !genreString.trim().equals("")) {
					((Musician)signedIn).setMusicGenre(genreString);
				}
			}
		}
	}
	
	public boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	} 
	public void printInstruments(Musician m) {
		for (Instrument ins : m.getInstrumentsPlayed()) {
			System.out.println(ins.getName());
			System.out.println(ins.getYearsPlayed());
		}
	}
	public boolean instrumentAlreadyIn(String ins) {
		for (Instrument in : ((Musician)signedIn).getInstrumentsPlayed()) {
			if (ins.toLowerCase().equals(in.getName().toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	private class addInstrumentButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			printInstruments((Musician)signedIn);
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
						} 
						else if (instrumentAlreadyIn(nameStr)) {
							for (Instrument in : ((Musician)signedIn).getInstrumentsPlayed()) {
								if (nameStr.toLowerCase().equals(in.getName().toLowerCase())) {
									in.setYearsPlayed(Float.parseFloat(years));
									break;
								}
							}
						}
						else {
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
							if ((ins.getName()).toLowerCase().equals(instStr.toLowerCase())) {
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
	private void handleMemberLookup() {
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
	private void handleStatusUpdate() {
		String input = JOptionPane.showInputDialog(
                null, "What do you have to say?");
		((Musician) signedIn).createStatusUpdate(input, session);
	}
	private void handleMeetup() {
		JFrame meetupFrame = new JFrame("Meetup");
		
	}
}
