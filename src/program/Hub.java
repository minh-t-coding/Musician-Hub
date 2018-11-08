package program;
import java.util.*;
import users.*;


import java.io.*;

public class Hub {
	
	public SuperUser signIn() throws FileNotFoundException {
		System.out.println("Please enter your username: \n");
		Scanner scanned = new Scanner (System.in);
		String username = scanned.nextLine();
		
		File database = new File("Database.txt");
		Scanner inputFile = new Scanner(database);
		while(inputFile.hasNext()) {	//checks every line until it can't
			String line = inputFile.nextLine(); //gets entire line as string
			String user = line.split(":")[0];	//will automatically get username
			if (user.toLowerCase().equals(username.toLowerCase())) {
				String passw = line.split(",")[1];	//password on file
				System.out.println("Please enter your password: \n");
				scanned = new Scanner (System.in);
				String password = scanned.nextLine();
				//checks password validility
				while(!passw.equals(password)) {
					System.out.println("Incorrect password, try again: ");
					scanned = new Scanner (System.in);
					password = scanned.nextLine();
				}
				
				System.out.println("Sign-in successful!\n");
				String temp = line.split(":")[1]; //splits the line from file at the colon
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
					System.out.println("code/database is messed up.");
					return null;
				}
			}
		}
		
		System.out.println("Username does not exist.");
		return null;
	}
	public SuperUser createAccount() throws FileNotFoundException {
		System.out.println("Please specify what type of account is going to be created ('m' for musician, 'c' for "
				+ "company): \n");
		Scanner input = new Scanner (System.in);
		char inputChar = input.next().charAt(0);
		String type = null;
		//maybe check for incorrect input
		if(inputChar == 'm') {
			System.out.println("Please enter your full name: \n");
			type = "musician";
		}else {
			System.out.println("Please enter your companie's name: \n");
			type = "company";
		}
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
		String newDB = username + ":{" + type + "," + password + "," + realName + "}\n";
		PrintWriter pw = new PrintWriter(new FileOutputStream(
			    new File("Database.txt"), 
			    true /* append = true */)); 
		pw.append(newDB);
		pw.close();
		//input.close();
		if(inputChar == 'm') {
			
			Musician m = new Musician();
			m.setUsername(username);
			m.setPassword(password);
			m.setRealName(realName);
			return m;
		}
		else if(inputChar == 'c') {
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
				inputFile.close();
				return false;
			}
		}
		inputFile.close();
		return true;
	}
	
	public void memberLookup() throws FileNotFoundException{
		System.out.println("Input the username to search: \n");
		Scanner input = new Scanner (System.in);
		String user = input.nextLine();
		
		File database = new File("Database.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(database));
			String line = reader.readLine();
			while (line != null) {
				if (line.toLowerCase().startsWith(user.toLowerCase())) {
					System.out.println(line.split(":")[0]);
				}
				line = reader.readLine();
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
