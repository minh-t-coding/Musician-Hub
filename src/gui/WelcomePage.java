package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomePage extends JFrame{
	private JFrame mainPage;
	private JLabel welcomeMessage;
	
	public WelcomePage(String windowName) {
		super(windowName);
		setSize(500,500);
		
		welcomeMessage = new JLabel();
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
