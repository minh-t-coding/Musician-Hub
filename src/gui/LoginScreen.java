package gui;

import javax.swing.*;

import program.*;

public class LoginScreen extends JFrame{

	/**
	 * need to add save/load serializable
	 */
	private static final long serialVersionUID = 1L;
	private Hub hub;
	
	public LoginScreen() {
		super("Login");
		setSize(300,300);
		buildGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void buildGUI() {
		
	}
}
