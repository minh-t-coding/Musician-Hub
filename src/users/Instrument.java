package users;
import java.io.*;

public class Instrument implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5897489381990088831L;
	private String name;
	private float yearsPlayed;
	
	public Instrument() {
		name = "NoName";
		yearsPlayed = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getYearsPlayed() {
		return yearsPlayed;
	}
	public void setYearsPlayed(float yearsPlayed) {
		this.yearsPlayed = yearsPlayed;
	}
	
}
