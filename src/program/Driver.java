package program;

import java.io.FileNotFoundException;
import java.util.Scanner;
import posts.*;
import users.*;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		
		Hub newHub = new Hub();
		
		SuperUser signedInUser = null;
		Scanner key = new Scanner (System.in);
		do {
		
		System.out.println("Welcome to Musician Hub! \nType s for sign-in (if you already have an account)"
				+ " or c to create an account\n");

		char option = key.next().charAt(0);
		
		if (option == 's') {
			signedInUser = newHub.signIn();
		}
		else if (option == 'c') {
			signedInUser = newHub.createAccount();
		}
		}while(signedInUser == null);
		
		//main menu...
		System.out.println("Redirecting to main menu\n");
		char session = 'y';
		
		while(session != 'l') {
			System.out.println("The Musicians Hub. Press 1 to create a new post, 2 to change"
					+ " your account features, or 9 to log out.");
			session = key.next().charAt(0);
			if(session == 1) {
				
			}
			else if(session == 2) {
				
			}
			else {
				continue;
			}
		}
		System.out.println("Logged out successfully! Have a good day!");
		
		//testing functionality...
		Musician x = new Musician();
		x.setUsername("mel");
		x.setPassword("truffle69");
		try {
			x.changePassword("pleasework");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Musician user = new Musician("randomUser","yeahboi");
		
		Admin a = new Admin();
		a.setUsername("miranda");
		a.setPassword("yo");
		a.makeNewAdmin(user);
	}
}