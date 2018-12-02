package program;

import java.io.*;

import java.time.*;
import java.util.*;

import gui.WelcomeScreen;
import posts.*;
import users.*;


public class Driver {
	public static void main(String[] args) throws IOException {
		
		new WelcomeScreen();
		
		//while(true) {
		/*
		Hub newHub = new Hub();
		newHub = Hub.loadData();
		
		Admin connor = new Admin();
		Admin miranda = new Admin();
		Admin mel = new Admin();
		
		connor.setUsername("aaa");
		miranda.setUsername("boopbopp");
		mel.setUsername("mellyboi");
		connor.setPassword("test");
		miranda.setPassword("isuck");
		mel.setPassword("truffles69");
		connor.setRealName("connor");
		miranda.setRealName("miranda");
		mel.setRealName("mel");
		newHub.allUsers.add(connor);
		newHub.allUsers.add(miranda);
		newHub.allUsers.add(mel);
		Hub.saveData(newHub);
		
		/*
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
						+ "Update profile (press 'f')\n"
						+ "Logout (press 'x')\n");
				Scanner key2 = new Scanner(System.in);
				char option = key2.next().charAt(0);
				

				if(option == 's') {
					((Musician)signedInUser).createStatusUpdate();
				}
				else if(option == 'f') {
					System.out.println("What profile aspect would you like to change: \n"
							+ "Username (press 'u')\n"
							+ "Password (press 'p')\n"
							+ "Name (press 'n')\n"
							+ "Favorite Music (press 'f')\n");
					Scanner key = new Scanner (System.in);
					decision = key.next().charAt(0);
					if(decision == 'u') {
						System.out.println("Enter new username: \n");
						Scanner input = new Scanner (System.in);
						String newUserName = input.nextLine();
						signedInUser.setUsername(newUserName);
						
					}
					else if(decision == 'p') {
						System.out.println("Enter new password: \n");
						Scanner input = new Scanner (System.in);
						String newPass = input.nextLine();
						signedInUser.setPassword(newPass);;
					}
					else if(decision == 'n') {
						System.out.println("Enter new Name: \n");
						Scanner input = new Scanner (System.in);
						String newName = input.nextLine();
						signedInUser.setRealName(newName);
					}
					else if(decision == 'f') {
						System.out.println("Whats your new favorite kind of music: \n");
						Scanner n = new Scanner(System.in);
						String s = n.nextLine();
						((Musician) signedInUser).setMusicGenre(s);

					}
					else {
						System.out.println("not a command\n");
					}
				}
				else if(option == 'l'){
					newHub.memberLookup();
				}

				else if(option ==  'm') {
					
					((Musician)signedInUser).createMeetUp();
				}
				else if(option == 'x') {
					session = 'l';

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
						+ "Update profile (press 'f')\n"
						+ "Logout (press 'x')\n");
				Scanner key2 = new Scanner(System.in);
				char option = key2.next().charAt(0);
				if(option == 'a') {
					((Company)signedInUser).createAdvertisement();

				}
				else if(option == 'l') {
					newHub.memberLookup();
				}
				else if(option == 'f') {
					System.out.println("What profile aspect would you like to change: \n"
							+ "Username (press 'u')\n"
							+ "Password (press 'p')\n"
							+ "Name (press 'n')\n"
							);
					Scanner key = new Scanner (System.in);
					decision = key.next().charAt(0);
					if(decision == 'u') {
						System.out.println("Enter new username: \n");
						Scanner input = new Scanner (System.in);
						String newUserName = input.nextLine();
						signedInUser.setUsername(newUserName);
						
					}
					else if(decision == 'p') {
						System.out.println("Enter new password: \n");
						Scanner input = new Scanner (System.in);
						String newPass = input.nextLine();
						signedInUser.setPassword(newPass);;
					}
					else if(decision == 'n') {
						System.out.println("Enter new Name: \n");
						Scanner input = new Scanner (System.in);
						String newName = input.nextLine();
						signedInUser.setRealName(newName);
					}
			
					else {
						System.out.println("not a command\n");
					}
				}
				else if(option == 'x') {
					session = 'l';
				}
				else {
					continue;
				}
			}
			else if(signedInUser instanceof Admin) {
				char decision = 'z';
				System.out.println("Welcome Admin! Would you like to:\n"
						+ "Lookup member (press 'l')\n"
						+ "Update profile (press 'f')\n"
						+ "Make a user an admin (press 'm')\n"
						+ "Delete user (press 'd')\n"
						+ "Logout (press 'x')\n");
				Scanner key2 = new Scanner(System.in);
				char option = key2.next().charAt(0);
				if(option == 'm') {
					//((Admin) signedInUser).makeNewAdmin();
				}
				else if(option == 'l') {
					newHub.memberLookup();
				}
				else if(option == 'f') {
					System.out.println("What profile aspect would you like to change: \n"
							+ "Username (press 'u')\n"
							+ "Password (press 'p')\n"
							+ "Name (press 'n')\n"
							);
					Scanner key = new Scanner (System.in);
					decision = key.next().charAt(0);
					if(decision == 'u') {
						System.out.println("Enter new username: \n");
						Scanner input = new Scanner (System.in);
						String newUserName = input.nextLine();
						signedInUser.setUsername(newUserName);
						
					}
					else if(decision == 'p') {
						System.out.println("Enter new password: \n");
						Scanner input = new Scanner (System.in);
						String newPass = input.nextLine();
						signedInUser.setPassword(newPass);;
					}
					else if(decision == 'n') {
						System.out.println("Enter new Name: \n");
						Scanner input = new Scanner (System.in);
						String newName = input.nextLine();
						signedInUser.setRealName(newName);
					}
					else {
						System.out.println("not a command\n");
					}
				}
				else if(option == 'd') {
					
					((Admin) signedInUser).deleteUser();
				}
				else if(option == 'x') {
					session = 'l';
				}
				else {
					continue;
				}
			}
			
			
		}
		System.out.println("Logged out successfully! Have a good day!\n");
		
		
		
		//}
	*/
}
}



