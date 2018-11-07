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
					else if(type.equals("admin")){
						Admin a = new Admin();
						a.setUsername(user);
						a.setPassword(passw);
						a.setRealName(fullName);
						return a;
					}
					else if(type.equals("company")) {
						Company c = new Company(user,passw);
						
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
	public SuperUser createAccount() throws FileNotFoundException {
		System.out.println("Please specify what type of account is going to be created: \n");
		Scanner input = new Scanner (System.in);
		String type = input.nextLine();
		while(type.equals("admin")) {
			System.out.println("Cannot create admin account, try again: \n");
			input = new Scanner (System.in);
			type = input.nextLine();
		}
		
		
		System.out.println("Please enter your full name or company name\n");
		input = new Scanner (System.in);
		String realName = input.nextLine();
		System.out.println("Please enter a username \n");
		input = new Scanner (System.in);
		String username = input.nextLine();
		while(!checkAvailability(username)) {
			System.out.println("Username is already taken! Choose another: \n");
			input = new Scanner (System.in);
			username = input.nextLine();
		}
		
		System.out.println("Please enter a password: \n");
		input = new Scanner (System.in);
		String password = input.nextLine();
		//Writing to the file...
		String newDB = username + ":{" + type + "," + password + "," + realName + "}";
		PrintWriter pw = new PrintWriter(new FileOutputStream(
			    new File("Database.txt"), 
			    true /* append = true */)); 
		pw.append(newDB);
		pw.close();
		if(type.equals("musician")) {
			
			Musician m = new Musician();
			m.setUsername(username);
			m.setPassword(password);
			m.setRealName(realName);
			return m;
		}
		else if(type.equals("company")) {
			Company m = new Company(username,password);
			m.setRealName(realName);
			return m;
		}
		return null;
		
		
	}
	public Boolean checkAvailability(String user) throws FileNotFoundException {
		File database = new File("Database.txt");
		Scanner inputFile = new Scanner(database);
		while(inputFile.hasNext()) {
			if(inputFile.nextLine().split(":")[0] == user) {
				return false;
			}
		}
		return true;
	}
}
