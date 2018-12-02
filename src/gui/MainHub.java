package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import program.*;
import users.*;

public class MainHub extends JFrame{
	private Hub session;
	private SuperUser signedIn;
	private JMenuBar menuBar;
	private JMenu signOut;
	private JMenuItem exit;
	
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
		signOut = new JMenu("Sign Out");
		menuBar = new JMenuBar();
		signOut.addActionListener(new MenuListener());
		menuBar.add(signOut);
		setJMenuBar(menuBar);
	}
	private class MenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JMenuItem source = (JMenuItem)(e.getSource());
			if(source.equals(exit)) {
				session.saveData();
				setVisible(false);
				dispose();
			}
		}
	}
}
