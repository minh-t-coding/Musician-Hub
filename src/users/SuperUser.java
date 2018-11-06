package users;

import java.util.ArrayList;

public abstract class SuperUser {
	protected String username;
	protected String password;
	protected String realName;
	
	public SuperUser() {
		this.username = "No Username";
		this.password = "No Password";
		this.realName = "No RealName";
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


	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public abstract ArrayList<SuperUser> memberLookup(String lookupUser);
	
	public abstract void changePassword(String newPass);

	
	
	
}
