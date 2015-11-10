package cn.news.domain;

public class PicCollection {
	private Integer picId;
	private News news;
	private String picIntroduction;
	private String picUrl;
	
	

	public PicCollection() {
		super();
	}
	public PicCollection(Integer picId, News news, String picIntroduction,
			String picUrl) {
		super();
		this.picId = picId;
		this.news = news;
		this.picIntroduction = picIntroduction;
		this.picUrl = picUrl;
	}
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public String getPicIntroduction() {
		return picIntroduction;
	}
	public void setPicIntroduction(String picIntroduction) {
		this.picIntroduction = picIntroduction;
	}
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
