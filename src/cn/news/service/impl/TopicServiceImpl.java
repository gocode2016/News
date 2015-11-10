package cn.news.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.Topic;
import cn.news.service.TopicService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements
		TopicService {

	@Override
	public List<Topic> getPage(Integer currentPage, Integer pageSize) {
		List<Topic> list = getSession()
				.createQuery("from Topic order by topicId desc")
				.setFirstResult((currentPage - 1) * pageSize)
				.setMaxResults(pageSize).list();
		return list;
	}

	@Override
	public List<Topic> findByTopicName(String topicName) {
		List<Topic> list = getSession().createQuery(
				"from Topic where topicName like '%" + topicName
						+ "%' order by topicId desc").list();
		return list;
	}

}
