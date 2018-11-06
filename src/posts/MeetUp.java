package posts;

import java.util.*;

import users.*;

public class MeetUp extends Post{
	private String location;
	private ArrayList<Comment> comments;
	private ArrayList<Musician> canGo;
	
	public MeetUp() {
		location = "";
		comments = null;
		canGo = null;
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

	public void addComments(Comment comments) {
		this.comments.add(comments);
	}

	public ArrayList<Musician> getCanGo() {
		return canGo;
	}

	public void addMusician(Musician m) {
		this.canGo.add(m);
	}
	
}
