package users;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import posts.*;
import program.Hub;

public class Company extends SuperUser{
	public Company() {
		
	}
	
	public Company (String username, String password) {
		this.username = username;
		this.password = password;
	}
	public void createAdvertisement(String status, String genre, String link, Hub hub){
		Advertisement update = new Advertisement();
		update.setContent(status);
		update.setGenre(genre);
		update.setLink(link);
		update.setOwner(this);
		hub.allPosts.add(update);
		
		
	}

}
