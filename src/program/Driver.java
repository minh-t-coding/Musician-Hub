package program;

import java.io.FileNotFoundException;
import java.time.*;
import java.util.Scanner;
import posts.*;
import users.*;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		
		Hub newHub = new Hub();
		
		Scanner keyboard = new Scanner (System.in);
		System.out.println("Welcome to Musician Hub! \n Type s for sign-in (if you already have an account)"
				+ " or c to create an account\n");

		char option = keyboard.next().charAt(0);
		
		
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
			System.out.println("The Musicians Hub. Press 'p' to create a new post, 'f' to change"
					+ " your account features, or 'l' to log out.");
			Scanner key2 = new Scanner(System.in);
			session = key2.next().charAt(0);
			
			if(session == 'p') {
				System.out.println("Posting");
				LocalDateTime time = LocalDateTime.now();
				System.out.println(time);
			}
			else if(session == 'f') {
				System.out.println("changing features");
			}
			else {
				continue;
			}
		}
		else if (option == 'c') {
			newHub.createAccount();
		}
		
		Musician x = new Musician();
		x.setUsername("mel");
		x.setPassword("truffle69");
		try {
			x.changePassword("pleasework");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Musician user = new Musician("randomUser","yeahboi");
		
		Admin a = new Admin("mirandahampton", "miranda11");
		//a.makeNewAdmin(x);

		a.makeNewAdmin(user);
		
		
		
	}
}
