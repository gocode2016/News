package cn.news.action;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.news.base.BaseAction;
import cn.news.domain.Admin;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<Admin> {

	private static final long serialVersionUID = 3428213485300617566L;

	/**
	 * 登录
	 * 
	 * @return
	 */
	public String login() {
		model = adminService.getById(1);
		ActionContext.getContext().getSession().put("user", model);
		model.setLastTime(new Timestamp(new Date().getTime()));
		adminService.update(model);
		return "index";
	}

}
