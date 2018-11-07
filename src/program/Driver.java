package program;

import java.io.FileNotFoundException;
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
		
		if (option == 's') {
			newHub.signIn();
		}
		else if (option == 'c') {
			newHub.createAccount();
		}
		
		Musician x = new Musician("mel","truffle69");
	
		try {
			x.changePassword("newpasswordisGREAT");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Admin a = new Admin("miranda", "yo");
		//a.makeNewAdmin(x);
	}
}
