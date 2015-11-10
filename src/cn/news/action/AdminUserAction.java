package cn.news.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import se.akerfeldt.com.google.gson.JsonObject;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import cn.news.base.BaseAction;
import cn.news.domain.User;
import cn.news.service.UserService;
import cn.news.tool.ResultUtils;

@Controller
@Scope("prototype")
public class AdminUserAction extends BaseAction<User>{
	private Integer id;
	private String location;
	private String account;
	private String password;
	private String picture;
	private Boolean gender;
	private String nick;
	private Integer page;
	
	/**
	 * 得到所有用户
	 * @return
	 * @throws Exception
	 */
	public String getAllUser() throws Exception{
		System.out.println("登录");
		List<User> userList = userService.getAllUser(page);
		userList.remove(0);
		ActionContext.getContext().put("userList", userList);
		System.out.println(userList.size());
		return "list";
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}



	public Integer getPage() {
		return page;
	}



	public void setPage(Integer page) {
		this.page = page;
	}
	
	
	
}
