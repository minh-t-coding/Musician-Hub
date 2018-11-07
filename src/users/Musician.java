package users;

import java.util.*;

import posts.*;

import java.io.*;
import java.time.*;

public class Musician extends SuperUser{
	private String musicGenre;
	
	public Musician() {
		
	}
	
	public Musician(String username, String password) {
		this.username = username;
		this.password = password;
		this.setMusicGenre("None");
	}
	
	public String getMusicGenre() {
		return musicGenre;
	}

	public void setMusicGenre(String musicGenre) {
		this.musicGenre = musicGenre;
	}
	
	public void createStatusUpdate() throws FileNotFoundException {
		StatusUpdate update = new StatusUpdate();
		System.out.println("Status Update: \n");
		Scanner scanned = new Scanner (System.in);
		String status = scanned.nextLine();
		update.setContent(status);
		update.setOwner(this);
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
			String content = this.getUsername() + ":" + LocalDateTime.now() + ":status" + System.lineSeparator()
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
