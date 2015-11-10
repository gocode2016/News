package cn.news.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Topic {
	private Integer topicId;
	private String topicName;
	private Date updateTime;
	private Date createTime;
	private Set<News> news = new HashSet<News>();

	public Topic() {
		super();
	}

	public Topic(Integer topicId, String topicName, Date updateTime,
			Date createTime, Set<News> news) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.news = news;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

}
