package users;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class SuperUser {
	protected String username;
	protected String password;
	protected String realName;
	
	public SuperUser() {
		this.username = "No Username";
		this.password = "No Password";
		this.realName = "No RealName";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}
	
	public String getPassword() {
		return password;
	}


	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public abstract ArrayList<SuperUser> memberLookup(String lookupUser);
	
	public void changePassword(String newPass) throws FileNotFoundException {
		String oldContent = "";
		File database = new File("Database.txt");
		BufferedReader reader = null;
		FileWriter writer = null;
		
		try {
			reader = new BufferedReader(new FileReader(database));
			String line = reader.readLine();
			
			while (line != null) {
				if (line.startsWith(this.username)) {
					line.replace(this.password, newPass);
				}
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
		}
		
		writer = new FileWriter(database);
		writer.write(oldContent);
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	finally {
		try {
			reader.close();
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
