package cn.news.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.UserLabel;
import cn.news.service.UserLabelService;
import cn.news.service.UserService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class UserLabelServiceImpl extends DaoSupportImpl<UserLabel> implements UserLabelService{

	@Override
	public List<UserLabel> selectedLabel(Integer userId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from UserLabel u where u.user = "+userId+"")
				.list();
	}


}
