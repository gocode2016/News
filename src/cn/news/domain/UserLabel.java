package cn.news.domain;

public class UserLabel {
	private Integer userLabelId;
	private User user;
	private NewsLabel newsLabel;
	private Integer showSequence;
	
	public UserLabel() {
		super();
	}
	
	public UserLabel(User user, NewsLabel newsLabel, Integer showSequence) {
		super();
		this.user = user;
		this.newsLabel = newsLabel;
		this.showSequence = showSequence;
	}

	public Integer getUserLabelId() {
		return userLabelId;
	}
	public void setUserLabelId(Integer userLabelId) {
		this.userLabelId = userLabelId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public NewsLabel getNewsLabel() {
		return newsLabel;
	}
	public void setNewsLabel(NewsLabel newsLabel) {
		this.newsLabel = newsLabel;
	}
	public Integer getShowSequence() {
		return showSequence;
	}
	public void setShowSequence(Integer showSequence) {
		this.showSequence = showSequence;
	}
	
	
}