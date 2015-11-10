package cn.news.service;

import cn.news.base.DaoSupport;
import cn.news.domain.Admin;

public interface AdminService extends DaoSupport<Admin> {
	/**
	 * 根据账号和密码修改密码
	 * 
	 * @param account
	 * @param password
	 */
	void updatePass(String account, String password);

	/**
	 * 登录
	 * 
	 * @param admName
	 *            用户名
	 * @param admPwd
	 *            密码
	 */
	Admin login(String admName, String admPwd);
}
