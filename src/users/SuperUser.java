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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public void changePassword() throws FileNotFoundException {
		System.out.println("Enter new password: \n");
		Scanner input = new Scanner (System.in);
		String newPass = input.nextLine();
		String oldContent = "";
		File database = new File("Database.txt");
		BufferedReader reader = null;
		FileWriter writer = null;
		
		try {
			reader = new BufferedReader(new FileReader(database));
			String line = reader.readLine();
			
			while (line != null) {
				//System.out.println(line);
				if (line.startsWith(this.username)) {
					line = line.replace(this.password, newPass);
					this.setPassword(newPass);
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

	public void changeUserName() {
		// TODO Auto-generated method stub
		
	}

	public void changeName() {
		// TODO Auto-generated method stub
		
	}

	
}


