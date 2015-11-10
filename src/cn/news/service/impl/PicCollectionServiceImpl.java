package cn.news.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.PicCollection;
import cn.news.service.PicCollectionService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PicCollectionServiceImpl extends DaoSupportImpl<PicCollection> implements PicCollectionService{

	@Override
	public PicCollection getCover(Integer newsId) {
		return (PicCollection) getSession().createQuery("from PicCollection p where p.news = "+newsId+"")
				.setMaxResults(1)
				.uniqueResult();
	}

	@Override
	public List<PicCollection> showPic(Integer newsId) {
		return getSession().createQuery("from PicCollection p where p.news = "+newsId+"")
				.list();
	}

}
