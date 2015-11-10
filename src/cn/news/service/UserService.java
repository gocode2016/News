package cn.news.service;

import java.util.List;

import cn.news.base.DaoSupport;
import cn.news.domain.User;

public interface UserService extends DaoSupport<User>{
	/**
	 * 根据账号和密码查找用户
	 * @return 用户
	 */
	User getByAccount(String account, String password);

	/**
	 * 根据账号查找用户
	 * @return 用户
	 */
	User getByAccount(String account);
	
	/**
	 * 根据页码查看用户列表
	 * @return 用户列表
	 */
	List<User> getAllUser(Integer page);
}
