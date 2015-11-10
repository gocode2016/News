package cn.news.domain;

import java.util.HashSet;
import java.util.Set;

public class KeyWord {
	private Integer wordId;
	private Set<News> news = new HashSet<News>();
	private String keyWord;	
	private Set<UserKeyWord> userKeyWords = new HashSet<UserKeyWord>();
	
	public KeyWord() {
		super();
	}
	
	
	public KeyWord(Integer wordId, Set<News> news, String keyWord,
			Set<UserKeyWord> userKeyWords) {
		super();
		this.wordId = wordId;
		this.news = news;
		this.keyWord = keyWord;
		this.userKeyWords = userKeyWords;
	}


	public Integer getWordId() {
		return wordId;
	}
	public void setWordId(Integer wordId) {
		this.wordId = wordId;
	}
	
	public Set<News> getNews() {
		return news;
	}


	public void setNews(Set<News> news) {
		this.news = news;
	}


	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Set<UserKeyWord> getUserKeyWords() {
		return userKeyWords;
	}

	public void setUserKeyWords(Set<UserKeyWord> userKeyWords) {
		this.userKeyWords = userKeyWords;
	}
	
	
	
}
