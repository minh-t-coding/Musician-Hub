package posts;
import java.io.Serializable;

import users.*;

public abstract class Post implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8925430732324858777L;
	protected SuperUser owner;
	protected Boolean inappropriate;
	protected String content;
	protected int likes;
	
	public Post() {
		owner = null;
		inappropriate = false;
		content = "";
		likes = 0;
	}
	
	//getters and setters
	public SuperUser getOwner() {
		return owner;
	}

	public void setOwner(SuperUser owner) {
		this.owner = owner;
	}

	public Boolean isInappropriate() {
		return inappropriate;
	}

	public void setIsInappropriate(Boolean isInappropriate) {
		this.inappropriate = isInappropriate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikes() {
		return likes;
	}
	//end getters and setters
	
	public void addLike() {
		this.likes++;
	}
	public void removeLike() {
		this.likes--;
	}
	
}
