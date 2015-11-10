package cn.news.domain;

import java.util.HashSet;
import java.util.Set;

public class NewsType {
	private Integer newsTypeId;
	private String newsTypeName;
	private Set<News> news = new HashSet<News>();
	
	public NewsType() {
		super();
	}
	
	public NewsType(String newsTypeName, Set<News> news) {
		super();
		this.newsTypeName = newsTypeName;
		this.news = news;
	}

	public Integer getNewsTypeId() {
		return newsTypeId;
	}
	public void setNewsTypeId(Integer newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
	public String getNewsTypeName() {
		return newsTypeName;
	}
	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}
	public Set<News> getNews() {
		return news;
	}
	public void setNews(Set<News> news) {
		this.news = news;
	}
	
	
	
}
