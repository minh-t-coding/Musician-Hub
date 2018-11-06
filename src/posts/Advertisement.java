package posts;

public class Advertisement extends Post{
	private String link; //link to businesses/companies website
	private String genre; //used to display ads based on similar genres
	
	public Advertisement() {
		link = "";
		genre = "";
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
