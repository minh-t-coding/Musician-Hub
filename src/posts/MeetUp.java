package posts;

import java.util.*;

import users.*;

public class MeetUp extends Post{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4505519322292027156L;
	private String location; //where the meetup will be
	private String date;     //when the meetup will go on
	private ArrayList<Comment> comments; //comments by other users
	private ArrayList<Musician> canGo; //list of other users that said that they can go 
	
	public MeetUp() {
		location = "";
		comments = new ArrayList<Comment>();
		canGo = null;
		date = "";
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void addComments(Comment comment) {
		this.comments.add(comment);
	}

	public ArrayList<Musician> getMusicians() {
		return canGo;
	}

	public void addMusician(Musician m) {
		this.canGo.add(m);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void printAttendees() { //prints people who said they can go, for driver purposes
		
	}
}
