package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
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
	private JScrollPane scrollPane;
	private ArrayList<JLabel> user;
	private ArrayList<JLabel> dateTime;
	private ArrayList<JPanel> canGo;
	private ArrayList<JTextArea> content;
	private ArrayList<JPanel> likes;
	private ArrayList<JPanel> comments;
	private ArrayList<JLabel> c;
	private JMenu posts;
	private ArrayList<String> type;
	
	
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
	
	private JFrame adFrame;
	private JTextField adText;
	private JTextField genreText;
	private JTextField linkText;
	
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
			ad.addActionListener(new MenuListener());
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
		feed = new JPanel();
		GridLayout gl = new GridLayout(0,1);
		feed.setLayout(gl);
		add(feed);
		populatePosts();
		
		
		
		
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
			else if(source.equals(ad)) {
				handleAdvertisement();
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
	
	public class JTextAreaOutputStream extends OutputStream {
	    private final JTextArea destination;

	    public JTextAreaOutputStream (JTextArea destination)
	    {
	        if (destination == null)
	            throw new IllegalArgumentException ("Destination is null");

	        this.destination = destination;
	    }

	    @Override
	    public void write(byte[] buffer, int offset, int length) throws IOException
	    {
	        final String text = new String (buffer, offset, length);
	        SwingUtilities.invokeLater(new Runnable ()
	            {
	                @Override
	                public void run() 
	                {
	                    destination.append (text);
	                }
	            });
	    }

	    @Override
	    public void write(int b) throws IOException
	    {
	        write (new byte [] {(byte)b}, 0, 1);
	    }
	}
	private void handleShowInfo() {
		JTextArea textArea = new JTextArea (10,20);

        textArea.setEditable (false);

        JFrame frame = new JFrame ("User Information");
        //frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane ();
        contentPane.setLayout (new BorderLayout ());
        contentPane.add (
            new JScrollPane (
                textArea, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
            BorderLayout.CENTER);
        frame.pack ();
        frame.setVisible (true);

        JTextAreaOutputStream out = new JTextAreaOutputStream (textArea);
        System.setOut (new PrintStream (out));
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
	
	/*public void printInstruments(Musician m) {
		for (Instrument ins : m.getInstrumentsPlayed()) {
			System.out.println(ins.getName());
			System.out.println(ins.getYearsPlayed());
		}
	}*/
	
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
			//printInstruments((Musician)signedIn);
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
		Hub.saveData(session);
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
			Hub.saveData(session);
			populatePosts();
		}
	}
	private class cancelListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			meetupFrame.setVisible(false);
			meetupFrame.dispose();
		}
	}
	private void handleAdvertisement() {
		adFrame = new JFrame("Advertisement");
		adFrame.setSize(400, 400);
		JPanel mainArea = new JPanel();
		mainArea.setLayout(new BoxLayout(mainArea, BoxLayout.Y_AXIS));
		JPanel infoField = new JPanel();
		infoField.setLayout(new FlowLayout());
		JLabel info = new JLabel("Please enter advertisement info:");
		adText = new JTextField();
		adText.setColumns(20);
		JPanel linkField = new JPanel();
		linkField.setLayout(new FlowLayout());
		JPanel genreField = new JPanel();
		genreField.setLayout(new FlowLayout());
		JLabel genre = new JLabel("Genre of music: ");
		genreText = new JTextField();
		genreText.setColumns(20);
		JLabel link = new JLabel("Link: ");
		linkText = new JTextField();
		linkText.setColumns(20);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton submit = new JButton("Submit");
		submit.addActionListener(new adListener());
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new cancel2Listener());
		
		infoField.add(info);
		infoField.add(adText);
		genreField.add(genre);
		genreField.add(genreText);
		linkField.add(link);
		linkField.add(linkText);
		buttonPanel.add(submit);
		buttonPanel.add(cancel);
		mainArea.add(infoField);
		mainArea.add(genreField);
		mainArea.add(linkField);
		mainArea.add(buttonPanel);
		adFrame.add(mainArea);
		adFrame.setVisible(true);
	}
	private class adListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String ad = adText.getText();
			String genre = genreText.getText();
			String link = linkText.getText();
			
			((Company) signedIn).createAdvertisement(ad, genre, link, session);
			
			adFrame.setVisible(false);
			adFrame.dispose();
			Hub.saveData(session);
			populatePosts();
		}
	}
	private class cancel2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			adFrame.setVisible(false);
			adFrame.dispose();
		}
	}
	private void populatePosts() {
		feed.removeAll();
		user = new ArrayList<JLabel>();
		dateTime = new ArrayList<JLabel>();
		canGo = new ArrayList<JPanel>();
		content = new ArrayList<JTextArea>();
		likes = new ArrayList<JPanel>();
		comments = new ArrayList<JPanel>();
		type = new ArrayList<String>();

		
		Hub loadPosts = Hub.loadData();
		for(Post post: loadPosts.allPosts) {
			String numLikes = Integer.toString(post.getLikes());
			JLabel labelUser = new JLabel(post.getOwner().getRealName());
			user.add(labelUser);
			JTextArea contentArea = new JTextArea(post.getContent());
			content.add(contentArea);
			JPanel likePanel = new JPanel();
			JLabel likeLabel = new JLabel(numLikes);
			JCheckBox click = new JCheckBox();
			likePanel.add(click);
			likePanel.add(likeLabel);
			likes.add(likePanel);

			JButton addComment = new JButton("Comment");
			if(post instanceof StatusUpdate) {
				type.add("StatusUpdate");
				JPanel comPan = new JPanel();
				JLabel comment = new JLabel();
				comPan.add(addComment);
				if(!(((StatusUpdate)post).getComments().isEmpty())) {
					for(Comment cmt: ((StatusUpdate)post).getComments()) {
						comment.setText(cmt.getOwner().getRealName() + ": " + cmt.getContent());
						c.add(comment);
					}
					for(JLabel jlbl: c) {
						comPan.add(jlbl);
						comPan.add(new JSeparator());
					}
				}
				
					dateTime.add(new JLabel());
					comments.add(comPan);
					canGo.add(new JPanel());
			}
			
			else if(post instanceof MeetUp) {
				type.add("MeetUp");
				JPanel comPan = new JPanel();
				JLabel comment = new JLabel();
				JLabel dt = new JLabel("Date: " + ((MeetUp) post).getDate() + " Location: " + ((MeetUp)post).getLocation());
				dateTime.add(dt);
				JLabel cg = new JLabel("Going?");
				JCheckBox clk = new JCheckBox();
				JButton whosGoing = new JButton("See who's going");
				JPanel meetUpAttendees = new JPanel();
				meetUpAttendees.add(cg);
				meetUpAttendees.add(clk);
				meetUpAttendees.add(whosGoing);

				canGo.add(meetUpAttendees);
				comPan.add(addComment);
				if(!(((MeetUp)post).getComments().isEmpty())){
					for(Post cmt: ((MeetUp)post).getComments()) {
						comment.setText(cmt.getOwner().getRealName() + ": " + cmt.getContent());
						c.add(comment);
					}
					for(JLabel jlbl: c) {
						comPan.add(jlbl);
						comPan.add(new JSeparator());
					}
				}
				comments.add(comPan);
			}
			else if(post instanceof Advertisement) {
				
			}
			
			
		}
		
		for(int i = 0; i<user.size(); i++) {
			feed.add(user.get(i));
			if(type.get(i) == "MeetUp") {
				feed.add(dateTime.get(i));
				feed.add(canGo.get(i));
			}
			feed.add(content.get(i));
			feed.add(likes.get(i));
			if(type.get(i) != "Advertisement") {
				feed.add(comments.get(i));
			}
			feed.add(new JSeparator());
		}
		feed.validate();
        feed.repaint();
		add(new JScrollPane(feed));
	}
}
