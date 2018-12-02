package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import program.*;
import users.*;

public class CreateAccountScreen extends JFrame{
	private JTextField userInput;
	private JPasswordField passInput;
	private JTextField realNameInput;
	private ButtonGroup buttonGroup;
	private JRadioButton musician;
	private JRadioButton company;
	private Hub hub;
	public CreateAccountScreen(){
		super("Create Account");
		hub = new Hub();
		hub.loadData();
		setSize(350,300);
		buildGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void buildGUI() {
		JPanel mainArea = new JPanel();
		mainArea.setLayout(new BoxLayout(mainArea, BoxLayout.Y_AXIS));
		JPanel infoField = new JPanel();
		infoField.setLayout(new FlowLayout());
		JLabel info = new JLabel("Please create your username and password:");
		JPanel userField = new JPanel();
		userField.setLayout(new FlowLayout());
		JPanel passField = new JPanel();
		passField.setLayout(new FlowLayout());
		JPanel realNameField = new JPanel();
		realNameField.setLayout(new FlowLayout());
		JLabel username = new JLabel("Username: ");
		userInput = new JTextField();
		userInput.setColumns(20);
		JLabel password = new JLabel("Password: ");
		passInput = new JPasswordField();
		passInput.setColumns(20);
		passInput.setEchoChar('*');
		
		JLabel realName = new JLabel("Real/Company Name: ");
		realNameInput = new JTextField();
		realNameInput.setColumns(14);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton submit = new JButton("Submit");
		submit.addActionListener(new submitListener());
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new cancelListener());
		
		//Radio Buttons
		JPanel radioField = new JPanel();
		musician = new JRadioButton("Musician", true);
		company = new JRadioButton("Company");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(musician);
		buttonGroup.add(company);
		radioField.setLayout(new FlowLayout());
		radioField.add(musician);
		radioField.add(company);
		
		infoField.add(info);
		userField.add(username);
		userField.add(userInput);
		passField.add(password);
		passField.add(passInput);
		realNameField.add(realName);
		realNameField.add(realNameInput);
		buttonPanel.add(submit);
		buttonPanel.add(cancel);
		mainArea.add(infoField);
		mainArea.add(radioField);
		mainArea.add(userField);
		mainArea.add(passField);
		mainArea.add(realNameField);
		mainArea.add(buttonPanel);
		add(mainArea);
	}
	private class submitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = userInput.getText();
			String password = passInput.getText();
			String realName = realNameInput.getText();
			if(hub.checkAvailability(username)) {
				if(musician.isSelected()) {
					Musician account = new Musician();
					account.setUsername(username);
					account.setPassword(password);
					account.setRealName(realName);
					hub.addUser(account);
					new MainHub(hub, account);
					setVisible(false);
					dispose();
				}
				else {
					Company account = new Company();
					account.setUsername(username);
					account.setPassword(password);
					account.setRealName(realName);
					hub.addUser(account);
					new MainHub(hub, account);
					setVisible(false);
					dispose();
				}
			}else {
				JOptionPane.showMessageDialog(null, "Username is already taken." ,
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	private class cancelListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			dispose();
			new WelcomeScreen();
		}
	}
}
