package posts;

import java.util.*;

public class StatusUpdate extends Post{
	private ArrayList<Comment> comments;
	
	public StatusUpdate() {
		comments = null;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void addComments(Comment comments) {
		this.comments.add(comments);
	}
	
}
