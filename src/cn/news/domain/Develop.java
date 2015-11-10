package cn.news.domain;

public class Develop {
	private Integer developId;
	private User user;
	private String developKeyWord;
	private Integer developNum;
	private Integer developShowWay;
	
	
	public Develop() {
		super();
	}
	
	public Develop(User user, String developKeyWord, Integer developNum,
			Integer developShowWay) {
		super();
		this.user = user;
		this.developKeyWord = developKeyWord;
		this.developNum = developNum;
		this.developShowWay = developShowWay;
	}

	public Integer getDevelopId() {
		return developId;
	}
	public void setDevelopId(Integer developId) {
		this.developId = developId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDevelopKeyWord() {
		return developKeyWord;
	}
	public void setDevelopKeyWord(String developKeyWord) {
		this.developKeyWord = developKeyWord;
	}
	public Integer getDevelopNum() {
		return developNum;
	}
	public void setDevelopNum(Integer developNum) {
		this.developNum = developNum;
	}
	public Integer getDevelopShowWay() {
		return developShowWay;
	}
	public void setDevelopShowWay(Integer developShowWay) {
		this.developShowWay = developShowWay;
	}
	
	
	
}
