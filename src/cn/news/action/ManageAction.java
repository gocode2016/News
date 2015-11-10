package cn.news.action;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.news.base.BaseAction;
import cn.news.domain.Dirty;
import cn.news.domain.News;
import cn.news.domain.NewsLabel;
import cn.news.domain.Topic;

@Controller
@Scope("prototype")
public class ManageAction extends BaseAction<NewsLabel> {

	private static final long serialVersionUID = -7816350136040508318L;

	private Integer currentPage = 1;
	private Integer pageSize = 10;
	private String updateTo;
	private Integer topicId;
	private Integer newsId;
	private Topic topic;
	private List<NewsLabel> newsLabels;
	private List<Topic> topics;
	private List<Dirty> dirties;

	public String label() {
		newsLabels = newsLabelService.getPage(currentPage, pageSize);
		return "label";
	}

	public String labeldelete() {
		newsLabelService.delete(model.getNewsLabelId());
		newsLabels = newsLabelService.getPage(currentPage, pageSize);
		return "label";
	}

	public String labeladd() {
		try {
			model.setNewsLabelName(new String(model.getNewsLabelName()
					.getBytes("ISO-8859-1"), "UTF-8"));
			newsLabelService.save(model);
			newsLabels = newsLabelService.getPage(currentPage, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "label";
	}

	public String topic() {
		topics = topicService.getPage(currentPage, pageSize);
		return "topic";
	}
	
	public String topicSearch() {
		topics = topicService.findByTopicName(model.getNewsLabelName());
		return "topic";
	}

	public String topicadd() {
		try {
			Topic topic = new Topic();
			topic.setTopicName(new String(model.getNewsLabelName().getBytes(
					"ISO-8859-1"), "UTF-8"));
			topic.setCreateTime(new Date());
			topic.setUpdateTime(new Date());
			topicService.save(topic);
			topics = topicService.getPage(currentPage, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "topic";
	}

	public String topicdelete() {
		Set<News> set = newsService.findByTopicId(model.getNewsLabelId());
		for (News news : set) {
			news.setTopic(null);
			newsService.update(news);
		}
		topicService.delete(model.getNewsLabelId());
		topics = topicService.getPage(currentPage, pageSize);
		return "topic";
	}

	public String topicNews() {
		topic = topicService.getById(topicId);
		topic.setNews(newsService.findByTopicId(topicId));
		return "topicNews";
	}

	public String dirty() {
		dirties = dirtyService.getPage(currentPage, pageSize);
		return "dirty";
	}

	public String dirtydelete() {
		dirtyService.delete(model.getNewsLabelId());
		dirties = dirtyService.getPage(currentPage, pageSize);
		return "dirty";
	}

	public String dirtyadd() {
		try {
			Dirty dirty = new Dirty();
			dirty.setContent(new String(model.getNewsLabelName().getBytes(
					"ISO-8859-1"), "UTF-8"));
			dirty.setUpdateTo(new String(updateTo.getBytes("ISO-8859-1"),
					"UTF-8"));
			dirtyService.save(dirty);
			dirties = dirtyService.getPage(currentPage, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dirty";
	}

	public String addTotopic() {
		topic = topicService.getById(topicId);
		News news = newsService.getById(newsId);
		news.setTopic(topic);
		newsService.update(news);
		topic.setUpdateTime(new Date());
		topicService.update(topic);
		topics = topicService.getPage(currentPage, pageSize);
		return "topic";
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setUpdateTo(String updateTo) {
		this.updateTo = updateTo;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public List<NewsLabel> getNewsLabels() {
		return newsLabels;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public List<Dirty> getDirties() {
		return dirties;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

}
