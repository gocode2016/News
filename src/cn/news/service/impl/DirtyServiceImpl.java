package cn.news.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.Dirty;
import cn.news.service.DirtyService;

@Service("dirtyService")
@Transactional
@SuppressWarnings("unchecked")
public class DirtyServiceImpl extends DaoSupportImpl<Dirty> implements DirtyService {

	@Override
	public List<Dirty> getPage(Integer currentPage, Integer pageSize) {
		List<Dirty> list = getSession().createQuery("from Dirty order by dirtyId desc")
				.setFirstResult((currentPage - 1) * pageSize)
				.setMaxResults(pageSize).list();
		return list;
	}

}
