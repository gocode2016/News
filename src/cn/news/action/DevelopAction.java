package cn.news.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import com.opensymphony.xwork2.ActionContext;

import cn.news.base.BaseAction;
import cn.news.domain.Develop;
import cn.news.tool.ResultUtils;

@Controller
@Scope("prototype")
public class DevelopAction extends BaseAction<Develop> {
	private Integer id = null;
	private Integer uid = null;
	private String key = null;
	private String sequence = null;
	private Integer num = null;
	private Integer page = null;
	private Integer flag = null;

	public String developKey() throws Exception {
		key = ServletActionContext.getRequest().getParameter("key");
		key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
		sequence = ServletActionContext.getRequest().getParameter("sequence");
		sequence = new String(sequence.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("定制关键字");
		System.out.println(id + "*****" + key + "*****" + sequence + "*****"
				+ num);
		Develop develop = new Develop();
		develop.setUser(userService.getById(id));
		develop.setDevelopKeyWord(key);
		develop.setDevelopNum(num);
		if (sequence.equals("按跟帖数")) {
			flag = 0;
		} else if (sequence.equals("按更新时间")) {
			flag = 1;
		} else {
			flag = -1;
		}
		develop.setDevelopShowWay(flag);
		developService.save(develop);

		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("totalnum", 1); // list����
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "developKey";
	}

	/**
	 * 根据用户Id展示关键字列表
	 * 
	 * @return
	 */
	public String list() throws Exception {
		System.out.println("展示关键字");
		System.out.println(uid);
		List<Develop> developList = developService.findById(uid);
		JSONArray array = new JSONArray();
		for (Develop develop : developList) {
			JSONObject json = new JSONObject();
			json.put("developId", develop.getDevelopId());
			json.put("developKeyWord", develop.getDevelopKeyWord());
			json.put("developNum", develop.getDevelopNum());
			String str=null;
			if(develop.getDevelopShowWay()==1){
				str = "按更新时间";
			}else if(develop.getDevelopShowWay()==0){
				str = "按跟帖数";
			}else{
				str = "-1";
			}
			json.put("developShowWay", str);
			array.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("success", developList.size() != 0);
		json.put("totalnum", developList.size());
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "list";
	}
	
	/**
	 * 删除定制
	 * @return
	 */
	public String delete() throws Exception{
		System.out.println("删除定制");
		System.out.println(id);
		developService.delete(id);
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("totalnum", 1); // list����
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "delete";
	}
	/**
	 * 修改定制
	 * @return
	 */
	public String update() throws Exception{
		key = ServletActionContext.getRequest().getParameter("key");
		key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
		sequence = ServletActionContext.getRequest().getParameter("sequence");
		sequence = new String(sequence.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("修改定制");
		System.out.println(id+"****"+key+"****"+sequence+"****"+num);
		Develop develop = developService.getById(id);
		develop.setDevelopKeyWord(key);
		if (sequence.equals("按跟帖数")) {
			flag = 0;
		} else if (sequence.equals("按更新时间")) {
			flag = 1;
		} else {
			flag = -1;
		}
		develop.setDevelopShowWay(flag);
		develop.setDevelopNum(num);
		developService.update(develop);
		
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("totalnum", 1); // list����
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "update";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
