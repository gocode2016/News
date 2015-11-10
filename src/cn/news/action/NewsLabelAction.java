package cn.news.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.news.base.BaseAction;
import cn.news.domain.NewsLabel;

@Controller
@Scope("prototype")
public class NewsLabelAction extends BaseAction<NewsLabel>{
	
	private Integer UserId;
	
	public String showSelectedLabel(){
		
		return "showSelectedLabel";
	}
	
	public String showUnselectLabel(){
		return "showUnselectLabel";
		
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}
	
	
}
