package cn.news.domain;

public class NewsLink {
	private String Title;
	private String url;
	
	
	public NewsLink() {
		super();
	}


	public NewsLink(String title, String url) {
		super();
		Title = title;
		this.url = url;
	}


	public String getTitle() {
		return Title;
	}


	public void setTitle(String title) {
		Title = title;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
