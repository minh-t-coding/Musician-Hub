package users;

import java.util.ArrayList;

public class Company extends SuperUser{
	
	public Company (String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public ArrayList<SuperUser> memberLookup(String lookupUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(String newPass) {
		// TODO Auto-generated method stub
		
	}

}
