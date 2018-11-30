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
		setSize(300,300);
		hub = new Hub();
		hub.loadData();
		
		buildGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void buildGUI() {
		JPanel mainArea = new JPanel();
		JLabel info = new JLabel("Please enter your username and password:");
		JPanel inputField = new JPanel();
		inputField.setLayout(new BoxLayout(inputField, BoxLayout.Y_AXIS));
		JLabel username = new JLabel("Username: ");
		JTextField userInput = new JTextField();
		JLabel password = new JLabel("Password: ");
		JTextField passInput = new JTextField();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton submit = new JButton();
		submit.addActionListener(new submitListener());
		JButton cancel = new JButton();
		
		inputField.add(username);
		inputField.add(userInput);
		inputField.add(password);
		inputField.add(passInput);
		buttonPanel.add(submit);
		buttonPanel.add(cancel);
		mainArea.add(info);
		mainArea.add(inputField);
		add(mainArea, BorderLayout.CENTER);
	}
	private class submitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	private class cancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
}
