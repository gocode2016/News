package cn.news.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class News {
	
	private Integer newsId;
	private Advice advice;//���һ
	private NewsType newsType;//���һ
	private NewsLabel newsLabel;//���һ
	private Set<PicCollection> picCollection;
	private Set<User> users = new HashSet<User>();
	private Set<Comment> comments = new HashSet<Comment>();
	
	private String newsTitle;
	private String newsIntroduction;
	private String newsContent;
	private Date newsPubTime;
	private String newsSources ;
	private Long newsCommentNum;
	private Boolean newsTopShow ;
	private Topic topic;
	private Set<KeyWord> keyWords = new HashSet<KeyWord>();
	
	public News() {
		super();
	}
	
	



	public News(Integer newsId, Advice advice, NewsType newsType,
			NewsLabel newsLabel, Set<PicCollection> picCollection,
			Set<User> users, Set<Comment> comments, String newsTitle,
			String newsIntroduction, String newsContent, Date newsPubTime,
			String newsSources, Long newsCommentNum, Boolean newsTopShow,
			Topic topic, Set<KeyWord> keyWords) {
		super();
		this.newsId = newsId;
		this.advice = advice;
		this.newsType = newsType;
		this.newsLabel = newsLabel;
		this.picCollection = picCollection;
		this.users = users;
		this.comments = comments;
		this.newsTitle = newsTitle;
		this.newsIntroduction = newsIntroduction;
		this.newsContent = newsContent;
		this.newsPubTime = newsPubTime;
		this.newsSources = newsSources;
		this.newsCommentNum = newsCommentNum;
		this.newsTopShow = newsTopShow;
		this.topic = topic;
		this.keyWords = keyWords;
	}





	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Advice getAdvice() {
		return advice;
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}

	public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public NewsLabel getNewsLabel() {
		return newsLabel;
	}

	public void setNewsLabel(NewsLabel newsLabel) {
		this.newsLabel = newsLabel;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsIntroduction() {
		return newsIntroduction;
	}

	public void setNewsIntroduction(String newsIntroduction) {
		this.newsIntroduction = newsIntroduction;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Date getNewsPubTime() {
		return newsPubTime;
	}

	public void setNewsPubTime(Date newsPubTime) {
		this.newsPubTime = newsPubTime;
	}

	public String getNewsSources() {
		return newsSources;
	}

	public void setNewsSources(String newsSources) {
		this.newsSources = newsSources;
	}

	public Long getNewsCommentNum() {
		return newsCommentNum;
	}

	public void setNewsCommentNum(Long newsCommentNum) {
		this.newsCommentNum = newsCommentNum;
	}

	public Boolean getNewsTopShow() {
		return newsTopShow;
	}

	public void setNewsTopShow(Boolean newsTopShow) {
		this.newsTopShow = newsTopShow;
	}



	public Set<PicCollection> getPicCollection() {
		return picCollection;
	}



	public void setPicCollection(Set<PicCollection> picCollection) {
		this.picCollection = picCollection;
	}






	public Topic getTopic() {
		return topic;
	}






	public void setTopic(Topic topic) {
		this.topic = topic;
	}





	public Set<KeyWord> getKeyWords() {
		return keyWords;
	}





	public void setKeyWords(Set<KeyWord> keyWords) {
		this.keyWords = keyWords;
	}
	
	
}
