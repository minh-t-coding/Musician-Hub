package program;
import java.util.*;

import posts.Post;
import users.*;


import java.io.*;

public class Hub {
	
	public static ArrayList<SuperUser> allUsers;
	public static ArrayList<Post> allPosts;
	
	public Hub() {
		allUsers = new ArrayList<SuperUser>();
		allPosts = new ArrayList<Post>();
	}
	public static void addPost(Post p) {
		allPosts.add(p);
	}
	public static void addUser(SuperUser u) {
		allUsers.add(u);
	}
	public SuperUser signIn(){
		System.out.println("Please enter your username: \n");
		Scanner scanned = new Scanner (System.in);
		String username = scanned.nextLine();
		
		for(SuperUser user : allUsers) {	//checks every line until it can't
			
			if (user.getUsername().toLowerCase().equals(username.toLowerCase())) {
				System.out.println("Please enter your password: \n");
				//scanned = new Scanner (System.in);
				String password = scanned.nextLine();
				//checks password validility
				while(!user.getPassword().equals(password)) {
					System.out.println("Incorrect password, try again: ");
					//scanned = new Scanner (System.in);
					password = scanned.nextLine();
				}
				System.out.println("Sign-in successful!\n");
				return user;
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
		if(inputChar == 'm') {
			System.out.println("Please enter your full name: \n");
			type = "musician";
		}else {
			System.out.println("Please enter your company's name: \n");
			type = "company";
		}
		String realName = input.nextLine();
		System.out.println("Please enter a username \n");
		String username = input.nextLine();
		while(!checkAvailability(username)) {
			System.out.println("Username is already taken! Choose another: \n");
			username = input.nextLine();
		}
		System.out.println("Please enter a password: \n");
		String password = input.nextLine();
		
		if(inputChar == 'm') {
			System.out.println("Enter preffered music genre: \n");
			String genre = input.nextLine();
			Musician m = new Musician(username,password);
			m.setRealName(realName);
			m.setMusicGenre(genre);
			allUsers.add(m);
			return m;
		}
		else if(inputChar == 'c') {
			Company m = new Company(username,password);
			m.setRealName(realName);
			allUsers.add(m);
			return m;
		}
		return null;
		
	}
	public static Boolean checkAvailability(String user){
		for(SuperUser u : allUsers) {
			if(user.equals(u.getUsername())) {
				return false;
			}
		}
		return true;
	}
	
	public SuperUser memberLookup(String user){
		/*
		System.out.println("Input the username to search: \n");
		Scanner input = new Scanner (System.in);
		String user = input.nextLine();
		*/
		for (SuperUser u : allUsers) {
			System.out.println(u.getUsername());//debug
			if(u.getUsername().startsWith(user)) {
				return u;
			}
		}
		return null;
	}
	
	public void loadData() {
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		
		try {
			File f = new File("Users.ser");
			if(f.exists() && !f.isDirectory()) { 
				fileIn = new FileInputStream("Users.ser");
				objIn = new ObjectInputStream(fileIn);
				allUsers = (ArrayList<SuperUser>) objIn.readObject();
				fileIn.close();
				objIn.close();
			}
			f = new File("Posts.ser");
			
			if(f.exists() && !f.isDirectory()) { 
				fileIn = new FileInputStream("Posts.ser");
				objIn = new ObjectInputStream(fileIn);
				allPosts = (ArrayList<Post>) objIn.readObject();
				fileIn.close();
				objIn.close();
			}
			
		}
		catch(IOException ex) {
			ex.printStackTrace();
			
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	public void saveData() {
		try {
			FileOutputStream fileOut = null;
			ObjectOutputStream objOut = null;
			if(!allUsers.isEmpty()) {
				fileOut = new FileOutputStream("Users.ser");
				objOut = new ObjectOutputStream(fileOut);
				objOut.writeObject(allUsers);
				fileOut.close();
			}
			if(!allPosts.isEmpty()) {
				fileOut = new FileOutputStream("Posts.ser");
				objOut.writeObject(allPosts);
				objOut.close();
				fileOut.close();
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
			
		}
	}
}
