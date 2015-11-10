package cn.news.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;
import cn.news.base.BaseAction;
import cn.news.domain.NewsLabel;
import cn.news.domain.UserLabel;
import cn.news.tool.ResultUtils;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserLabelAction extends BaseAction<UserLabel> {

	private Integer userId;

	/**
	 * 根据用户ID返回用户已选择的标签
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showSelectedLabel() throws Exception {
		System.out.println(userId);
		List<UserLabel> userLabelList = userLabelService.selectedLabel(userId);
		JSONArray array = new JSONArray();
		for (UserLabel userLabel : userLabelList) {
			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("newsLabelId", userLabel.getNewsLabel().getNewsLabelId());
			json.put("newsLabelName", userLabel.getNewsLabel()
					.getNewsLabelName());
			json.put("showSequence", userLabel.getShowSequence());

			array.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("success", userLabelList.size() != 0);
		json.put("totalnum", userLabelList.size());
		json.put("Array", array);
		System.out.println(json);
		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);

		return "showSelectedLabel";
	}

	/**
	 * 根据用户ID返回用户未选择的标签
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showUnselectLabel() throws Exception {
		// System.out.println(userId);
		List<NewsLabel> newsLabelList = newsLabelService.unselectLabel(userId);

		JSONArray array = new JSONArray();
		for (NewsLabel newsLabel : newsLabelList) {
			JSONObject json = new JSONObject();
			json.put("userId", -1);
			json.put("newsLabelId", newsLabel.getNewsLabelId());
			json.put("newsLabelName", newsLabel.getNewsLabelName());
			json.put("showSequence", newsLabel.getNewsLabelId());

			array.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("success", newsLabelList.size() != 0);
		json.put("totalnum", newsLabelList.size());
		json.put("Array", array);
		System.out.println(json);
		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "showUnselectLabel";

	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
