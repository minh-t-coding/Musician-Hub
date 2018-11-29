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
	
	public void createStatusUpdate(){
		StatusUpdate update = new StatusUpdate();
		System.out.println("Status Update: \n");
		Scanner scanned = new Scanner (System.in);
		String status = scanned.nextLine();
		update.setContent(status);
		update.setOwner(this);
		this.AddPost(update);
		Hub.addPost(update);
	}
	
	public void createMeetUp(){
		MeetUp update = new MeetUp();
		System.out.println("Enter the Date and Location of meetup seperated by comma(,): \n");
		Scanner scanned = new Scanner (System.in);
		String details = scanned.nextLine();
		System.out.println("Enter the information for the MeetUp: \n");
		scanned = new Scanner (System.in);
		String status = scanned.nextLine();
		update.setContent(status);
		update.setOwner(this);
		update.setDate(details.split(",")[0]);
		update.setLocation(details.split(",")[1]);
		this.AddPost(update);
		Hub.addPost(update);
	}

	
}
