package program;
import java.util.*;

import users.*;

import java.io.*;

public class Hub {
	
	public SuperUser signIn() throws FileNotFoundException {
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
				String passw = line.split(",")[1].substring(0, line.split(",")[1].length());
				
				if(passw.toLowerCase().equals(password.toLowerCase())){
					System.out.println("Sign-in successful!\n");
					String temp = line.split(":")[1]; //splits the line from file at the colon
					//System.out.println(temp.split(",")[0].substring(1, temp.split(",")[0].length()));
					String type = temp.split(",")[0].substring(1, temp.split(",")[0].length());
					String fullName = temp.split(",")[2].substring(0, temp.split(",")[2].length()-1);
					if(type.equals("musician")) {
						Musician nm = new Musician();
						nm.setUsername(user);
						nm.setPassword(passw);
						nm.setRealName(fullName);
						return nm;
					}
					else if(temp.split(",")[0] == "admin"){
						Admin a = new Admin();
						a.setUsername(user);
						a.setPassword(passw);
						a.setRealName(fullName);
						return a;
					}
					else if(temp.split(",")[0] == "company") {
						Company c = new Company();
						c.setUsername(user);
						c.setPassword(passw);
						c.setRealName(fullName);
						return c;
					}
					else {
						System.out.println("code is messed up.");
						return null;
					}
				}
			}
		}
		System.out.println("Username does not exist.");
		return null;
	}
	public SuperUser createAccount() {
		System.out.println("yooo x 2");
		return null;
	}
}
