package users;

import posts.*;
import java.io.*;
import java.util.ArrayList;

public abstract class SuperUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4286721299698077473L;
	protected String username;
	protected String password;
	protected String realName;
	protected ArrayList<Post> posts;
	
	public SuperUser() {
		this.username = "No Username";
		this.password = "No Password";
		this.realName = "No RealName";
		this.posts = new ArrayList<Post>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public void AddPost(Post post) {
		this.posts.add(post);
	}
}


