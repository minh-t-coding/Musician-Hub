package users;

import posts.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class SuperUser implements Serializable{
	protected String username;
	protected String password;
	protected String realName;
	protected ArrayList<Post> posts;
	
	public SuperUser() {
		this.username = "No Username";
		this.password = "No Password";
		this.realName = "No RealName";
		this.posts = new ArrayList<Post>();
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
	
	public void AddPost(Post post) {
		this.posts.add(post);
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

	public void changeUserName() throws FileNotFoundException {
		System.out.println("Enter new username: \n");
		Scanner input = new Scanner (System.in);
		String newUserName = input.nextLine();
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
					line = line.replace(this.username, newUserName);
					this.setPassword(newUserName);
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

	public void changeName() {
		System.out.println("Enter new Name: \n");
		Scanner input = new Scanner (System.in);
		String newName = input.nextLine();
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
					line = line.replace(this.realName, newName);
					this.setPassword(newName);
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
	

	
}


