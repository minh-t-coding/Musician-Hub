package users;

import java.util.*;

import posts.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
		File posts = new File("posts.txt");
		FileWriter writer = null;
		System.out.println("Status Update: \n");
		Scanner scanned = new Scanner (System.in);
		String status = scanned.nextLine();
		update.setContent(status);
		update.setOwner(this.getUsername());
		String content = this.getUsername() + ":" + LocalDateTime.now() + ":status\n"
				+"==================================================================\n"
				+ status + "\n==================================================================\n";
		try {
			writer = new FileWriter(posts);
			writer.write(content);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void createMeetUp() {
		// TODO Auto-generated method stub
		
	}

	public void changeMusicPreference() {
		// TODO Auto-generated method stub
		
	}

	
}
