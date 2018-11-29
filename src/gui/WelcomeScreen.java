package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeScreen extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainArea;
	private JLabel welcomeMessage;
	private JButton loginButton;
	private JButton createAccountButton;
	
	public WelcomeScreen() {
		super("Welcome");
		setSize(650,100);
		
		getContentPane().setLayout( new BoxLayout(getContentPane(), BoxLayout.Y_AXIS) );
		buildGUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void buildGUI() {
		mainArea = new JPanel();
		mainArea.setBackground(Color.decode("#E0FFFF"));
		mainArea.setAlignmentX(CENTER_ALIGNMENT);
		mainArea.setLayout(new BoxLayout(mainArea, BoxLayout.Y_AXIS));
		welcomeMessage = new JLabel("Welcome to the Musicians Hub! Kowabunga!");
		welcomeMessage.setFont(new Font("Monaco", Font.PLAIN, 20));
		JPanel buttonSpace = new JPanel();
		buttonSpace.setBackground(Color.decode("#E0FFFF"));
		buttonSpace.setLayout(new FlowLayout());
		
		loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(100,40));
		loginButton.addActionListener(new loginButtonPress());
		createAccountButton = new JButton("Create Account");
		createAccountButton.setPreferredSize(new Dimension(150,40));
		
		buttonSpace.add(loginButton);
		buttonSpace.add(createAccountButton);
		mainArea.add(welcomeMessage);
		mainArea.add(buttonSpace);
		add(mainArea, BorderLayout.CENTER);
	}
	private class loginButtonPress implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new LoginScreen();
		}
		
	}
}
