package cn.news.service;

import java.util.List;

import cn.news.base.DaoSupport;
import cn.news.domain.NewsLabel;

public interface NewsLabelService extends DaoSupport<NewsLabel> {

	List<NewsLabel> unselectLabel(Integer userId);

	/**
	 * 获取一组标签
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            页码大小
	 * @return 标签集合
	 */
	List<NewsLabel> getPage(Integer currentPage, Integer pageSize);
}
