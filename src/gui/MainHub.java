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
	
	private JFrame meetupFrame;
	private JTextField dateText;
	private JTextField locText;
	private JTextField meetupInfo;
	
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

		exit.addActionListener(new MenuListener());
		lookup = new JMenu("Lookup");
		memberLookup = new JMenuItem("Member Lookup");
		memberLookup.addActionListener(new MenuListener());
		lookup.add(memberLookup);
		
		populatePosts();
		
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
		populatePosts();
	}
	private void handleMeetup() {
		meetupFrame = new JFrame("Meetup");
		meetupFrame.setSize(400, 400);
		JPanel mainArea = new JPanel();
		mainArea.setLayout(new BoxLayout(mainArea, BoxLayout.Y_AXIS));
		JPanel infoField = new JPanel();
		infoField.setLayout(new FlowLayout());
		JLabel info = new JLabel("Please enter date and location of the meetup:");
		JPanel dateField = new JPanel();
		dateField.setLayout(new FlowLayout());
		JPanel locField = new JPanel();
		locField.setLayout(new FlowLayout());
		JLabel date = new JLabel("Date: ");
		dateText = new JTextField();
		dateText.setColumns(20);
		JLabel location = new JLabel("Location: ");
		locText = new JTextField();
		locText.setColumns(20);
		JPanel info2Field = new JPanel();
		JLabel info2 = new JLabel("Anything else you would like to say?:");
		meetupInfo = new JTextField();
		meetupInfo.setColumns(20);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton submit = new JButton("Submit");
		submit.addActionListener(new meetupListener());
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new cancelListener());
		
		infoField.add(info);
		info2Field.add(info2);
		
		dateField.add(date);
		dateField.add(dateText);
		locField.add(location);
		locField.add(locText);
		buttonPanel.add(submit);
		buttonPanel.add(cancel);
		mainArea.add(infoField);
		mainArea.add(dateField);
		mainArea.add(locField);
		mainArea.add(buttonPanel);
		mainArea.add(info2Field);
		mainArea.add(meetupInfo);
		meetupFrame.add(mainArea);
		meetupFrame.setVisible(true);
	}
	private class meetupListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String date = dateText.getText();
			String location = locText.getText();
			String info = meetupInfo.getText(); 
			((Musician) signedIn).createMeetUp(info, date, location, session);
			meetupFrame.setVisible(false);
			meetupFrame.dispose();
		}
	}
	private class cancelListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			meetupFrame.setVisible(false);
			meetupFrame.dispose();
		}
	}
	
	private void populatePosts() {
		feed = new JPanel();
		user = new ArrayList<JLabel>();
		content = new ArrayList<JTextArea>();
		likes = new ArrayList<JPanel>();
		
		
		Hub loadPosts = Hub.loadData();
		for(Post post: loadPosts.allPosts) {
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
	}
}
