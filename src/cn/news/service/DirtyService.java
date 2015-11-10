package cn.news.service;

import java.util.List;

import cn.news.base.DaoSupport;
import cn.news.domain.Dirty;

public interface DirtyService extends DaoSupport<Dirty> {

	/**
	 * 获取一组过滤字
	 * 
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            页码大小
	 * @return 过滤字集合
	 */
	List<Dirty> getPage(Integer currentPage, Integer pageSize);
}
