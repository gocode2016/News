package cn.news.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.Develop;
import cn.news.service.DevelopService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class DevelopServiceImpl extends DaoSupportImpl<Develop> implements DevelopService{

	@Override
	public List<Develop> findById(Integer uid) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Develop d where d.user = "+uid+"")
				.list();
	}

}
