package users;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Admin extends SuperUser {

	public Admin() {
		
	}
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void makeNewAdmin(Musician mus) throws FileNotFoundException {

			Admin admin = new Admin();
			admin.setUsername(mus.getUsername());
			admin.setPassword(mus.getPassword());
			admin.setRealName(mus.getRealName());
			File db = new File("Database.txt");
			String old = "";
			BufferedReader br = new BufferedReader(new FileReader(db));
			try {
				String line = br.readLine();
				while (line != null) {
					if(line.startsWith(mus.getUsername())) {
						String newStr = line.replaceFirst("musician", "admin");
						old = old + newStr+ System.lineSeparator();;
					}
					
					else {
						old = old + line+ System.lineSeparator();;
					}
					line = br.readLine();
				}
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	public void takeAwayAdmin(Admin admin) throws FileNotFoundException {
		Musician mus = new Musician();
		mus.setUsername(admin.getUsername());
		mus.setPassword(admin.getPassword());
		mus.setRealName(admin.getRealName());
		File db = new File("Database.txt");
		String old = "";
		BufferedReader br = new BufferedReader(new FileReader(db));
		try {
			String line = br.readLine();
			while (line != null) {
				if(line.startsWith(admin.getUsername())) {
					String newStr = line.replaceFirst("admin", "musician");
					old = old + newStr+ System.lineSeparator();
				}
				
				else {
					old = old + line+ System.lineSeparator();
				}
				line = br.readLine();
			}

			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(old);
	}
	public void deleteUser() throws IOException {
		System.out.println("Enter the username of the user to be deleted: \n");
		Scanner key = new Scanner (System.in);
		String wanted = key.nextLine();
		File inputFile = new File("Database.txt");
		File tempFile = new File("myTempFile.txt");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();
		    if(trimmedLine.startsWith(wanted)) continue;
		    writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.close(); 
		reader.close(); 
		boolean successful = tempFile.renameTo(inputFile);
		
		//should also delete all posts from post database???
	}

}
