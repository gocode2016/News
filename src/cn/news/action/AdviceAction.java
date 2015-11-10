package cn.news.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import com.opensymphony.xwork2.ActionContext;

import cn.news.base.BaseAction;
import cn.news.domain.Advice;
import cn.news.domain.News;
import cn.news.domain.PicCollection;
import cn.news.tool.ResultUtils;

@Controller
@Scope("prototype")
public class AdviceAction  extends BaseAction<Advice>{
	private Integer adviceId;
	/**
	 * 根据广告id显示广告
	 * @return
	 * @throws Exception
	 */
	public String showAdvice() throws Exception{
		//System.out.println("advicelService******"+advicelService);
		List<Advice> advLsit = adviceService.showAdvice(adviceId);
		System.out.println("advId******"+adviceId);
		JSONArray jsonArray = new JSONArray();
		JSONArray array = new JSONArray();
		for(Advice adv : advLsit){
			JSONObject json = new JSONObject();
			json.put("advId",adv.getAdvId());
			json.put("advUrl", adv.getAdvUrl());
			json.put("advContent", adv.getAdvContent());
			json.put("advPicture", adv.getAdvPicture());
			
			array.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("success", advLsit.size()!=0);
		json.put("totalnum", advLsit.size());  //list最后的取值
		json.put("Array", array);
		
		System.out.println(json.toString());
		
		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "showAdvice";
	}
	public Integer getAdviceId() {
		return adviceId;
	}
	public void setAdviceId(Integer adviceId) {
		this.adviceId = adviceId;
	}
	
	
}
