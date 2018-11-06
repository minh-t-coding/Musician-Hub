package posts;
import users.*;

public abstract class Post {
	protected SuperUser owner;
	protected Boolean inappropriate;
	protected String content;
	protected int likes;
	
	public Post() {
		owner = null;
		inappropriate = false;
		content = "";
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
