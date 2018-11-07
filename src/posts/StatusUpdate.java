package posts;

import java.util.*;

public class StatusUpdate extends Post{
	private ArrayList<Post> comments; //list of comments from other users
	
	public StatusUpdate() {
		comments = null;
	}

	public ArrayList<Post> getComments() {
		return comments;
	}

	public void addComment(Post comment) {
		this.comments.add(comment);
	}
	
}
