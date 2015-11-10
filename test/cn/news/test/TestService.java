package cn.news.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.domain.Admin;
import cn.news.domain.Advice;
import cn.news.domain.Develop;
import cn.news.domain.News;
import cn.news.domain.NewsLabel;
import cn.news.domain.NewsType;
import cn.news.domain.User;
import cn.news.domain.UserLabel;


@Service("testService")
public class TestService {
	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void saveTwoUsers() {
		Session session = sessionFactory.getCurrentSession();

		session.save(new User());
		session.save(new NewsLabel());
		session.save(new Admin());
		session.save(new Advice());
		session.save(new NewsType());
		session.save(new Develop());
		session.save(new UserLabel());
		session.save(new News());

		// int a = 1 / 0; // 这行会抛异常
	}
}
