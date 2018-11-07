package program;
import java.util.*;
import java.io.*;

public class Hub {
	
	public void signIn() throws FileNotFoundException {
		System.out.println("Please enter your username: \n");
		Scanner scannedUsername = new Scanner (System.in);
		String username = scannedUsername.nextLine();
		System.out.println("Please enter your password: \n");
		Scanner scannedPassword = new Scanner (System.in);
		String password = scannedUsername.nextLine();
		
		File database = new File("Database.txt");
		Scanner inputFile = new Scanner(database);
		while(inputFile.hasNext()) {
			
		}
	}
	public void createAccount() {
		System.out.println("yooo x 2");
	}
}
