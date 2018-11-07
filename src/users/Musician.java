package users;

import java.util.ArrayList;

public class Musician extends SuperUser{
	private String musicGenre;
	
	public Musician() {
		
	}
	
	public Musician(String username, String password) {
		this.username = username;
		this.password = password;
		this.setMusicGenre("None");
	}
	
	@Override
	public ArrayList<SuperUser> memberLookup(String lookupUser) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMusicGenre() {
		return musicGenre;
	}

	public void setMusicGenre(String musicGenre) {
		this.musicGenre = musicGenre;
	}

	
}
