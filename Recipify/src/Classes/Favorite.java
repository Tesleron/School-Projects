package Classes;

import javafx.scene.control.Hyperlink;

public class Favorite {
	private String title;
	private String url;
	
	public Favorite(String t , String u) {
		title = t;
		url = u;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}
	
	
}
