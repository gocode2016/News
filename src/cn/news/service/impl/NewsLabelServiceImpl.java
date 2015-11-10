package cn.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.NewsLabel;
import cn.news.service.NewsLabelService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class NewsLabelServiceImpl extends DaoSupportImpl<NewsLabel> implements
		NewsLabelService {

	@Override
	public List<NewsLabel> unselectLabel(Integer userId) {
		// TODO Auto-generated method stub
		List<NewsLabel> list = getSession().createQuery(
				"select u.newsLabel from UserLabel u where u.user=" + userId
						+ "").list();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			ids.add(list.get(i).getNewsLabelId());
		}
		// System.out.println(ids.toString());

		return getSession()
				.createQuery(
						"from NewsLabel n where n.newsLabelId not in(:ids)")
				.setParameterList("ids", ids).list();
	}

	@Override
	public List<NewsLabel> getPage(Integer currentPage, Integer pageSize) {
		List<NewsLabel> list = getSession().createQuery("from NewsLabel order by newsLabelId desc")
				.setFirstResult((currentPage - 1) * pageSize)
				.setMaxResults(pageSize).list();
		return list;
	}

}
