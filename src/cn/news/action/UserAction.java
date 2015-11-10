package cn.news.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import se.akerfeldt.com.google.gson.JsonObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import cn.news.base.BaseAction;
import cn.news.domain.User;
import cn.news.service.UserService;
import cn.news.tool.ResultUtils;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private Integer id;
	private String location;
	private String account;
	private String password;
	private String picture;
	private Boolean gender;
	private String nick;
	private String head;
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

	/**
	 * 登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		System.out.println("登录");
		User user2 = userService.getByAccount(account, password);
		List<User> userList = new ArrayList<User>();
		userList.add(user2);
		JSONArray array = new JSONArray();
		for (User user : userList) {
			JSONObject json = new JSONObject();
			json.put("userId", user.getUserId());
			json.put("userName", user.getUserName());
			json.put("userPwd", user.getUserPwd());
			json.put("userNickName", user.getUserNickName());
			json.put("userHeadPicture", user.getUserHeadPicture());
			json.put("userGender", user.getUserGender());
			json.put(
					"userLocation",
					"null".equals(user.getUserLocation()) ? "-1" : user
							.getUserLocation());
			array.put(json);
		}

		JSONObject json2 = new JSONObject();
		json2.put("success", user2.getUserId() != null);
		json2.put("totalnum", user2.getUserId() != null ? 1 : 0); // list����
		json2.put("Array", array);
		System.out.println(json2);

		ResultUtils.toJson(ServletActionContext.getResponse(), json2);
		ActionContext.getContext().put("json", json2);

		return null;
	}

	/**
	 * 修改用户所在地
	 * 
	 * @return
	 */
	public String updateLocation() throws Exception {
		System.out.println("修改用户所在地");
		location = ServletActionContext.getRequest().getParameter("location");
		location = new String(location.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(id + "*****" + location);
		User user = userService.getById(id);
		user.setUserLocation(location);
		userService.update(user);
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("totalnum", 1); // list����
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "updateLocation";
	}

	/**
	 * 注册
	 * 
	 * @return
	 */
	public String register() throws Exception {
		User user2 = userService.getByAccount(account);
		location = ServletActionContext.getRequest().getParameter("location");
		location = new String(location.getBytes("ISO-8859-1"), "UTF-8");
		nick = ServletActionContext.getRequest().getParameter("nick");
		nick = new String(nick.getBytes("ISO-8859-1"), "UTF-8");
		if (user2 == null) {
			User user = new User();
			user.setUserName(account);
			user.setUserPwd(password);
			user.setUserGender(gender);
			user.setUserHeadPicture(picture);
			user.setUserNickName(nick);
			user.setUserLocation(location);
			userService.save(user);
			JSONArray array = new JSONArray();
			JSONObject json = new JSONObject();
			json.put("success", true);
			json.put("totalnum", 1); // list����
			json.put("Array", array);

			System.out.println(json.toString());

			ResultUtils.toJson(ServletActionContext.getResponse(), json);
			ActionContext.getContext().put("json", json);
		} else {
			System.out.println("用户名重复");
			JSONArray array = new JSONArray();
			JSONObject json = new JSONObject();
			json.put("success", true);
			json.put("totalnum", 0); // list
			json.put("Array", array);

			System.out.println(json.toString());

			ResultUtils.toJson(ServletActionContext.getResponse(), json);
			ActionContext.getContext().put("json", json);
		}

		return "register";
	}

	/**
	 * 设置头像
	 * 
	 * @return
	 */
	public String headPicSet() throws Exception {
		System.out.println("修改头像");
		System.out.println(id + "*****" + head);
		byte[] bytes1 = decoder.decodeBuffer(head);

		ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
		BufferedImage bi1 = ImageIO.read(bais);
		File w2 = new File("E:\\服创项目\\News\\WebRoot\\image\\" + id + ".jpg");// 可以是jpg,png,gif格式
		ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动

		User user = userService.getById(id);
		user.setUserHeadPicture("/image/" + id + ".jpg");
		userService.save(user);

		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("totalnum", 1); // list����
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "headPicSet";
	}

	/**
	 * 修改用户资料
	 * 
	 * @return
	 */
	public String updateUserInfo() throws Exception {
		System.out.println("修改用户资料");
		nick = ServletActionContext.getRequest().getParameter("nick");
		nick = new String(nick.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(id + "*****" + gender + "*****" + nick);
		User user = userService.getById(id);

		user.setUserGender(gender);
		user.setUserNickName(nick);
		userService.save(user);

		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("totalnum", 1); // list����
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);

		return "updateUserInfo";
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

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

}
