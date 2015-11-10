package cn.news.domain;

import java.util.HashSet;
import java.util.Set;



public class Advice {
	private Integer advId;
	private String advUrl;
	private String advContent;
	private String advPicture;
	private Set<News> news = new HashSet<News>();
	
	
	public Advice() {
		super();
	}
	
	public Advice(String advUrl, String advContent, String advPicture,
			Set<News> news) {
		super();
		this.advUrl = advUrl;
		this.advContent = advContent;
		this.advPicture = advPicture;
		this.news = news;
	}

	public Integer getAdvId() {
		return advId;
	}
	public void setAdvId(Integer advId) {
		this.advId = advId;
	}
	public String getAdvUrl() {
		return advUrl;
	}
	public void setAdvUrl(String advUrl) {
		this.advUrl = advUrl;
	}
	public String getAdvContent() {
		return advContent;
	}
	public void setAdvContent(String advContent) {
		this.advContent = advContent;
	}
	public String getAdvPicture() {
		return advPicture;
	}
	public void setAdvPicture(String advPicture) {
		this.advPicture = advPicture;
	}
	public Set<News> getNews() {
		return news;
	}
	public void setNews(Set<News> news) {
		this.news = news;
	}
	
	
}
