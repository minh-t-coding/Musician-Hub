package users;

import java.util.*;

import posts.*;
import program.Hub;

import java.io.*;
import java.time.*;

public class Musician extends SuperUser{
	private String musicGenre;
	private ArrayList<Instrument> instrumentsPlayed;
	//maybe? private ArrayList<Musician> friendsList;
	
	public Musician() {
		musicGenre = "None";
	}
	
	public Musician(String username, String password) {
		this.username = username;
		this.password = password;
		this.musicGenre = "None";
	}
	
	public String getMusicGenre() {
		return musicGenre;
	}

	public void setMusicGenre(String musicGenre) {
		this.musicGenre = musicGenre;
	}
	
	public ArrayList<Instrument> getInstrumentsPlayed() {
		return instrumentsPlayed;
	}
	public void addInstrument(Instrument i) {
		instrumentsPlayed.add(i);
	}
	
	public void removeInstrument(Instrument i) {
		instrumentsPlayed.remove(i);
	}
	
	public void createStatusUpdate(String input, Hub hub){
		StatusUpdate update = new StatusUpdate();
		update.setContent(input);
		update.setOwner(this);
		this.AddPost(update);
		hub.addPost(update);
	}
	
	public void createMeetUp(String input, Hub hub){
		MeetUp update = new MeetUp();
		//date, location, info
		update.setContent(input);
		update.setOwner(this);
		//update.setDate(details.split(",")[0]);
		//update.setLocation(details.split(",")[1]);
		this.AddPost(update);
		hub.addPost(update);
	}

	
}
