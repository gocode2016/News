package cn.news.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.Admin;
import cn.news.service.AdminService;

@Service("adminService")
@Transactional
public class AdminServiceImpl extends DaoSupportImpl<Admin> implements
		AdminService {

	@Override
	public void updatePass(String account, String password) {

	}

	@Override
	public Admin login(String admName, String admPwd) {
		Admin admin = null;
		return admin;
	}

}
