package cn.news.domain;

import java.util.HashSet;
import java.util.Set;

public class User {
	private Integer userId;
	private String userName;
	private String userPwd;
	private String userNickName ;
	private String userHeadPicture;
	private Boolean userGender;
	private String userLocation;
	private Set<News> news = new HashSet<News>();
	private Set<UserLabel> userLabels = new HashSet<UserLabel>();
	private Set<Develop> develops = new HashSet<Develop>();
	private Set<Comment> comments = new HashSet<Comment>();
	private Set<UserKeyWord> userKeyWords = new HashSet<UserKeyWord>();
	
	public User() {
		super();
	}
	

	


	public User(Integer userId, String userName, String userPwd,
			String userNickName, String userHeadPicture, Boolean userGender,
			String userLocation, Set<News> news, Set<UserLabel> userLabels,
			Set<Develop> develops, Set<Comment> comments,
			Set<UserKeyWord> userKeyWords) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userNickName = userNickName;
		this.userHeadPicture = userHeadPicture;
		this.userGender = userGender;
		this.userLocation = userLocation;
		this.news = news;
		this.userLabels = userLabels;
		this.develops = develops;
		this.comments = comments;
		this.userKeyWords = userKeyWords;
	}





	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserHeadPicture() {
		return userHeadPicture;
	}

	public void setUserHeadPicture(String userHeadPicture) {
		this.userHeadPicture = userHeadPicture;
	}

	public Boolean getUserGender() {
		return userGender;
	}

	public void setUserGender(Boolean userGender) {
		this.userGender = userGender;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

	public Set<UserLabel> getUserLabels() {
		return userLabels;
	}

	public void setUserLabels(Set<UserLabel> userLabels) {
		this.userLabels = userLabels;
	}

	public Set<Develop> getDevelops() {
		return develops;
	}

	public void setDevelops(Set<Develop> develops) {
		this.develops = develops;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}





	public Set<UserKeyWord> getUserKeyWords() {
		return userKeyWords;
	}





	public void setUserKeyWords(Set<UserKeyWord> userKeyWords) {
		this.userKeyWords = userKeyWords;
	}
	
	
}
