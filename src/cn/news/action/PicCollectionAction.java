package cn.news.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import cn.news.base.BaseAction;
import cn.news.domain.PicCollection;
import cn.news.tool.ResultUtils;

@Controller
@Scope("prototype")
public class PicCollectionAction extends BaseAction<PicCollection> {
	private Integer newsId;

	/**
	 * �������ID���������е�ͼƬ
	 * @return
	 * @throws Exception
	 */
	public String showPic() throws Exception {
		System.out.println(newsId);
		List<PicCollection> picList = picCollectionService.showPic(newsId);
		JSONArray array = new JSONArray();
		for (PicCollection pic : picList) {
			JSONObject json = new JSONObject();
			json.put("picId", pic.getPicId());
			json.put("newsId", pic.getNews().getNewsId());
			json.put("picUrl", pic.getPicUrl());
			json.put("picIntroduction", pic.getPicIntroduction());

			array.add(json);
		}
		JSONObject json = new JSONObject();
		json.put("success", picList.size() != 0);
		json.put("totalnum", picList.size());
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);

		return "showPic";
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	
	
}
