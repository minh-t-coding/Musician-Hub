package program;

import java.io.FileNotFoundException;
import java.time.*;
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
					((Musician) signedInUser).createStatusUpdate();
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
						((Musician) signedInUser).changeMusicPreference();
					}
					else {
						System.out.println("not a command\n");
					}
				}
				else if(session == 'l'){
					newHub.memberLookup();
				}
				else if(session ==  'm') {
					((Musician) signedInUser).createMeetUp();
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
					((Company) signedInUser).createAdvertisement();
				}
				else if(session == 'l') {
					newHub.memberLookup();
				}
				else if(session == 'f') {
					System.out.println("What profile aspect would you like to change: \n"
							+ "Username (press 'u')\n"
							+ "Password (press 'p')\n"
							+ "Name (press 'n')\n"
							);
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
					else {
						System.out.println("not a command\n");
					}
				}
			}
			else if(signedInUser instanceof Admin) {
				char decision = 'z';
				System.out.println("Welcome Admin! Would you like to:\n"
						+ "Lookup member (press 'l')\n"
						+ "Update profile (press 'f')\n"
						+ "Make a user an admin (press 'm')\n");
				Scanner key2 = new Scanner(System.in);
				session = key2.next().charAt(0);
				if(session == 'm') {
					((Admin) signedInUser).makeNewAdmin();
				}
			}
			
			
		}
		System.out.println("Logged out successfully! Have a good day!\n");
		
		Musician x = new Musician("mel","truffle69");
	
		//testing functionality...
		try {
			x.changePassword("newpasswordisGREAT");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Musician user = new Musician("randomUser","yeahboi");
		
		Admin a = new Admin("mirandahampton", "miranda11");
		//a.makeNewAdmin(x);

		a.makeNewAdmin(user);
		
		
		
	}
}