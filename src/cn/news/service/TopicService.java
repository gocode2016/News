package cn.news.service;

import java.util.List;

import cn.news.base.DaoSupport;
import cn.news.domain.Topic;

public interface TopicService extends DaoSupport<Topic> {

	/**
	 * 获取一组专题
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            页码大小
	 * @return 专题集合
	 */
	List<Topic> getPage(Integer currentPage, Integer pageSize);

	/**
	 * 根据专题名模糊查找一组专题
	 * 
	 * @param topicName
	 *            专题名
	 * @return 专题集合
	 */
	List<Topic> findByTopicName(String topicName);

}