package users;

import java.util.ArrayList;
import java.io.*;

public class Admin extends SuperUser{
	
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void makeNewAdmin(Musician mus) {
		
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
