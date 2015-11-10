package cn.news.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.News;
import cn.news.service.NewsService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class NewsServiceImpl extends DaoSupportImpl<News> implements NewsService{
	
	@Override
	public List<News> showAll(Integer newsTypeId,String newsLabel,Integer count,Integer pageNum) {
		// TODO Auto-generated method stub
		return getSession().createQuery("select n from News n,NewsLabel nl where n.newsLabel=nl.newsLabelId and n.newsType = "+newsTypeId+" and nl.newsLabelName like '"+newsLabel+"' order by n.newsPubTime desc")
				/*.setParameter(0, newsTypeId)
				.setParameter(1, newsLabelId)*/
				.setFirstResult((count-1)*pageNum)
				.setMaxResults(pageNum)
				.list();
	}

	@Override
	public List<News> selectAll() {
		return getSession().createQuery("select n from News n")
				.list();
	}
	@Override
	public List<News> getAllNews(Integer page) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from News n order by n.newsPubTime desc")
				.setFirstResult((page-1)*10)
				.setMaxResults(10)
				.list();
	}

	@Override
	public Set<News> findByTopicId(Integer topicId) {
		Set<News> news = new HashSet<News>();
		news.addAll(getSession().createQuery("from News n where n.topic.topicId = " + topicId).list());
		return news;
	}
}
