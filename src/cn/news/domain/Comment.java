package cn.news.domain;

import java.util.Date;

public class Comment {
	private Integer commentId;
	private User user ;//多对一
	private News news;//多对一
	
	private String commentContent;
	private Date commentTime;
	private Long commentPraiseNum;
	
	public Comment() {
		super();
	}
	
	public Comment(User user, News news, String commentContent,
			Date commentTime, Long commentPraiseNum) {
		super();
		this.user = user;
		this.news = news;
		this.commentContent = commentContent;
		this.commentTime = commentTime;
		this.commentPraiseNum = commentPraiseNum;
	}

	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public Long getCommentPraiseNum() {
		return commentPraiseNum;
	}
	public void setCommentPraiseNum(Long commentPraiseNum) {
		this.commentPraiseNum = commentPraiseNum;
	}
	
}
