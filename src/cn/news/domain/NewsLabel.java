package cn.news.domain;

import java.util.HashSet;
import java.util.Set;

public class NewsLabel {
	private Integer newsLabelId;
	private String newsLabelName;
	private Set<UserLabel> userLabels = new HashSet<UserLabel>();
	private Set<News> news = new HashSet<News>();
	
	
	public NewsLabel() {
		super();
	}
	
	public NewsLabel(String newsLabelName, Set<UserLabel> userLabels,
			Set<News> news) {
		super();
		this.newsLabelName = newsLabelName;
		this.userLabels = userLabels;
		this.news = news;
	}

	public Integer getNewsLabelId() {
		return newsLabelId;
	}
	public void setNewsLabelId(Integer newsLabelId) {
		this.newsLabelId = newsLabelId;
	}
	public String getNewsLabelName() {
		return newsLabelName;
	}
	public void setNewsLabelName(String newsLabelName) {
		this.newsLabelName = newsLabelName;
	}
	
	public Set<UserLabel> getUserLabels() {
		return userLabels;
	}
	public void setUserLabels(Set<UserLabel> userLabels) {
		this.userLabels = userLabels;
	}
	public Set<News> getNews() {
		return news;
	}
	public void setNews(Set<News> news) {
		this.news = news;
	}
	
	
}
