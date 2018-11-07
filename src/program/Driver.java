package program;

import java.io.FileNotFoundException;
import java.util.Scanner;
import posts.*;
import users.*;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		
		Hub newHub = new Hub();
		
		SuperUser signedInUser = null;
		
		
		do {
			
			System.out.println("Welcome to Musician Hub! \nType s for sign-in (if you already have an account)"
					+ " or c to create an account\n");
			Scanner key = new Scanner (System.in);
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
			System.out.println("The Musicians Hub."); 
			
			if(signedInUser instanceof Musician) {
				char decision = 'z';
				System.out.println("Welcome Musician! Would you like to:\n"
						+ "Post Status Update (press 's')\n"
						+ "Post a MeetUp (press 'm')\n"
						+ "Lookup member (press 'l')\n"
						+ "Update profile (press 'f')\n");
				Scanner key2 = new Scanner(System.in);
				session = key2.next().charAt(0);
				
				if(session == 's') {
					signedInUser.createStatusUpdate();
				}
				else if(session == 'f') {
					System.out.println("What profile aspect would you like to change: \n"
							+ "Username (press 'u')\n"
							+ "Password (press 'p')\n"
							+ "Name (press 'n')\n"
							+ "Favorite Music (press 'f')\n");
					Scanner key = new Scanner (System.in);
					decision = key.next().charAt(0);
					if(decision == 'u') {
						signedInUser.changeUserName();
					}
					else if(decision == 'p') {
						signedInUser.changePassword();
					}
					else if(decision == 'n') {
						signedInUser.changeName();
					}
					else if(decision == 'f') {
						signedInUser.changeMusicPreference();
					}
					else {
						System.out.println("not a command\n");
					}
				}
				else if(session == 'l'){
					newHub.memberLookup();
				}
				else if(session ==  'm') {
					signedInUser.createMeetUp();
				}
				else {
					continue;
				}
			}
			else if(signedInUser instanceof Company) {
				char decision = 'z';
				System.out.println("Welcome Company! Would you like to:\n"
						+ "Post an Advertisement (press 'a')\n"
						+ "Lookup member (press 'l')\n"
						+ "Update profile (press 'f')\n");
				Scanner key2 = new Scanner(System.in);
				session = key2.next().charAt(0);
				if(session == 'a') {
					
				}
			}
			
		}
		System.out.println("Logged out successfully! Have a good day!\n");
		
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