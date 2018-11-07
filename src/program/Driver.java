package program;

import java.util.Scanner;
import posts.*;
import users.*;

public class Driver {
	public static void main(String[] args) {
		
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
	}
}
