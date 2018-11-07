package users;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import posts.*;

public class Company extends SuperUser{
	public Company() {
		
	}
	
	public Company (String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void createAdvertisement() throws FileNotFoundException {
		Advertisement update = new Advertisement();
		System.out.println("Advertisement: \n");
		Scanner scanned = new Scanner (System.in);
		String status = scanned.nextLine();
		update.setContent(status);
		update.setOwner(this);
		this.AddPost(update);
		File posts = new File("posts.txt");
		BufferedReader bf = null;
		FileWriter writer = null;
		String oldContent ="";
		try {
			bf = new BufferedReader(new FileReader(posts));
			String line = bf.readLine();
			while(line!= null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = bf.readLine();
			}
			String content = this.getUsername() + ":" + LocalDateTime.now() + ":advert" + System.lineSeparator()
					+"=================================================================="+ System.lineSeparator()
					+ status + System.lineSeparator()+ "==================================================================\n"
					+ System.lineSeparator();
			oldContent = oldContent + content;
			writer = new FileWriter(posts);
			writer.write(oldContent);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
