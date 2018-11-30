package gui;

import javax.swing.*;

import program.*;

public class LoginScreen extends JFrame{

	/**
	 * need to add save/load serializable
	 */
	
	private Hub hub;
	private JTextPane screen;
	
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
		screen.setText(hub.allUsers.get(0).getRealName());
		add(screen);
	}
}
