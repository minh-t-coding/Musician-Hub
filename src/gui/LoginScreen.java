package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import program.*;
import users.*;

public class LoginScreen extends JFrame{
	private JTextField userInput;
	private JPasswordField passInput;
	
	private Hub hub;
	
	public LoginScreen() {
		super("Login");
		setSize(350,300);
		hub = new Hub();
		hub.loadData();
		
		buildGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void buildGUI() {
		JPanel mainArea = new JPanel();
		mainArea.setLayout(new BoxLayout(mainArea, BoxLayout.Y_AXIS));
		JPanel infoField = new JPanel();
		infoField.setLayout(new FlowLayout());
		JLabel info = new JLabel("Please enter your username and password:");
		JPanel userField = new JPanel();
		userField.setLayout(new FlowLayout());
		JPanel passField = new JPanel();
		passField.setLayout(new FlowLayout());
		JLabel username = new JLabel("Username: ");
		userInput = new JTextField();
		userInput.setColumns(20);
		JLabel password = new JLabel("Password: ");
		passInput = new JPasswordField();
		passInput.setColumns(20);
		passInput.setEchoChar('*');//hides password
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton submit = new JButton("Submit");
		submit.addActionListener(new submitListener());
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new cancelListener());
		
		infoField.add(info);
		userField.add(username);
		userField.add(userInput);
		passField.add(password);
		passField.add(passInput);
		buttonPanel.add(submit);
		buttonPanel.add(cancel);
		mainArea.add(infoField);
		mainArea.add(userField);
		mainArea.add(passField);
		mainArea.add(buttonPanel);
		add(mainArea);
	}
	private class submitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = userInput.getText();
			String password = passInput.getText();
			SuperUser lookup = hub.memberLookup(username);
			if(lookup != null) {
				if(password.equals(lookup.getPassword())){
					setVisible(false);
					dispose();
					new MainHub(hub, lookup);
				}else {
					JOptionPane.showMessageDialog(null, "Wrong Password." ,
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Username does not exist." ,
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
