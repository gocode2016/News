package cn.news.service;

import java.util.List;
import java.util.Set;

import cn.news.base.DaoSupport;
import cn.news.domain.News;

public interface NewsService extends DaoSupport<News> {
	/**
	 * 根据条件查找所有新闻并按时间排序
	 * 
	 * @param type
	 * @param lable
	 * @param count
	 * @param page
	 * @return新闻列表
	 */
	List<News> showAll(Integer type, String lable, Integer count, Integer page);

	/**
	 * 查找所有新闻
	 * 
	 * @return新闻列表
	 */
	List<News> selectAll();

	/**
	 * 根据页码查找新闻
	 * 
	 * @return新闻列表
	 */
	List<News> getAllNews(Integer page);

	/**
	 * 根据专题标识获取新闻列表
	 * 
	 * @param topicId
	 *            专题标识
	 * @return 新闻列表
	 */
	Set<News> findByTopicId(Integer topicId);
}
