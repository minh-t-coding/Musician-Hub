package program;
import java.util.*;

import users.Admin;
import users.Company;
import users.Musician;
import users.SuperUser;

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
				String passw = line.split(",")[1].substring(0, line.split(",")[1].length() -1);
				if(passw.toLowerCase().equals(password.toLowerCase())){
					if(line.split(":{")[1].split(",")[0] == "musician") {
						Musician mus = new Musician(username,password);
						return mus;
					}
					else if(line.split(":{")[1].split(",")[0] == "admin"){
						Admin adm = new Admin(username,password);
						return adm;
					}
					else if(line.split(":{")[1].split(",")[0] == "company") {
						Company comp = new Company(username,password);
						return comp;
					}
				}
			}
		}
		System.out.println("Username does not exist.");
		return new Musician("None","None");
	}
	public void createAccount() {
		System.out.println("yooo x 2");
	}
}
