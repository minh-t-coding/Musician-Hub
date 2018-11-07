package users;

import java.util.ArrayList;
import java.io.*;

public class Admin extends SuperUser {

	public Admin() {
		
	}
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void makeNewAdmin(Musician mus) throws FileNotFoundException {
			Admin admin = new Admin();
			File db = new File("Database.txt");
			String old = "";
			BufferedReader br = new BufferedReader(new FileReader(db));
			try {
				String line = br.readLine();
				while (line != null) {
					old = old + line + System.lineSeparator();
					line = br.readLine();
				}
				int UsernameIndex = old.indexOf(mus.getUsername());
				int start = old.indexOf('{', UsernameIndex);
				int end = old.indexOf('}', UsernameIndex);
				String[] info = old.substring(start+1, end).split(",");
				if (info[0].trim() == "musician") {
					admin.setUsername(old.substring(UsernameIndex,start-1).trim());
					admin.setPassword(info[1].trim());
				}
				String output = old.substring(0,start+1) + "admin, " + info[1] + old.substring(end);
				
				System.out.println(output);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
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
