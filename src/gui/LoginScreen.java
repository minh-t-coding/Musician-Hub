package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import program.*;

public class LoginScreen extends JFrame{

	/**
	 * need to add save/load serializable
	 */
	
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
		JTextField userInput = new JTextField();
		userInput.setColumns(20);
		JLabel password = new JLabel("Password: ");
		JTextField passInput = new JTextField();
		passInput.setColumns(20);
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
			
		}
		
	}
	private class cancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			dispose();
			new WelcomeScreen();
		}
		
	}
}
