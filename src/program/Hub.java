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
		Boolean exists = false;
		while(inputFile.hasNext()) {
			String line = inputFile.nextLine();
			String user = line.split(":")[0];
			if (user.toLowerCase().equals(username.toLowerCase())) {
				exists = true;
				String passw = line.split(",")[1].substring(0, line.split(",")[1].length() -1);
				if(passw.toLowerCase().equals(password.toLowerCase())){
					if(line.split(":{")[1].split(",")[0] == "musician") {
						
					}
					else if(line.split(":{")[1].split(",")[0] == "admin"){
						
					}
					else if(line.split(":{")[1].split(",")[0] == "company") {
						
					}
				}
			}
		}
		System.out.println("Username does not exist.");
	}
	public void createAccount() {
		System.out.println("yooo x 2");
	}
}
