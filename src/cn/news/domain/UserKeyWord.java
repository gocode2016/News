package cn.news.domain;

public class UserKeyWord {
	private Integer id;
	private User user;
	private KeyWord keyWord;
	private String keyValue;
	public UserKeyWord() {
		super();
	}
	public UserKeyWord(Integer id, User user, KeyWord keyWord, String keyValue) {
		super();
		this.id = id;
		this.user = user;
		this.keyWord = keyWord;
		this.keyValue = keyValue;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public KeyWord getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(KeyWord keyWord) {
		this.keyWord = keyWord;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	
	
	
	
}
