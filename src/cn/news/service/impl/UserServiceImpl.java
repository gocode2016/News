package cn.news.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.User;
import cn.news.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService{

	@Override
	public User getByAccount(String account, String password) {
		return (User) getSession().createQuery("from User u where u.userName='"+account+"' and u.userPwd = '"+password+"'")
				.uniqueResult();
	}

	@Override
	public User getByAccount(String account) {
		return (User) getSession().createQuery("from User u where u.userName='"+account+"'")
				.uniqueResult();
	}
	
	@Override
	public List<User> getAllUser(Integer page) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from User")
				.setFirstResult((page-1)*10)
				.setMaxResults(10)
				.list();
	}
}
