package cn.news.domain;

import java.util.List;

public class ParserNews {
	private String newsTitle;
	private String newsPubTime;
	private String newsIntroduction;
	private String newsSources;
	private String newsContent;
	private List<ParserPic> newPic;
	private String url;
	public ParserNews() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ParserNews(String newsTitle, String newsPubTime, String newsIntroduction, String newsSources,
			String newsContent, List<ParserPic> newPic) {
		super();
		this.newsTitle = newsTitle;
		this.newsPubTime = newsPubTime;
		this.newsIntroduction = newsIntroduction;
		this.newsSources = newsSources;
		this.newsContent = newsContent;
		this.newPic = newPic;
	}


	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsPubTime() {
		return newsPubTime;
	}
	public void setNewsPubTime(String newsPubTime) {
		this.newsPubTime = newsPubTime;
	}
	public String getNewsSources() {
		return newsSources;
	}
	public void setNewsSources(String newsSources) {
		this.newsSources = newsSources;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public List<ParserPic> getNewPic() {
		return newPic;
	}
	public void setNewPic(List<ParserPic> newPic) {
		this.newPic = newPic;
	}
	
	public String getNewsIntroduction() {
		return newsIntroduction;
	}


	public void setNewsIntroduction(String newsIntroduction) {
		this.newsIntroduction = newsIntroduction;
	}


	@Override
	public String toString() {
		return "ParserNews [newsTitle=" + newsTitle + ", newsPubTime=" + newsPubTime + ", newsIntroduction="
				+ newsIntroduction + ", newsSources=" + newsSources + ", newsContent=" + newsContent + ", newPic="
				+ newPic + "]";
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}



	
}
