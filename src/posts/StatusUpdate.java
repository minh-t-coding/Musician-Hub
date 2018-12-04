package posts;

import java.util.*;

public class StatusUpdate extends Post{
	/**
	 * 
	 */
	private static final long serialVersionUID = 796262775301956155L;
	private ArrayList<Comment> comments; //list of comments from other users
	
	public StatusUpdate() {
		comments = new ArrayList<Comment>();
	}

	public ArrayList<Comment> getComments() {
		return this.comments;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	
}
