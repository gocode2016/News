package cn.news.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import cn.news.base.BaseAction;
import cn.news.domain.News;
import cn.news.domain.PicCollection;
import cn.news.domain.User;
import cn.news.service.AdviceService;
import cn.news.service.CommentService;
import cn.news.service.NewsLabelService;
import cn.news.service.NewsService;
import cn.news.service.PicCollectionService;
import cn.news.service.UserLabelService;
import cn.news.service.UserService;
import cn.news.tool.ResultUtils;

@Controller
@Scope("prototype")
public class CollectionAction extends ActionSupport{
	
	private Integer newsId;
	private Integer userId;

	
	@Resource
	protected UserService userService;
	@Resource
	protected NewsService newsService;
	@Resource
	protected PicCollectionService picCollectionService;
	@Resource
	protected NewsLabelService newsLabelService;
	@Resource
	protected UserLabelService userLabelService;
	@Resource
	protected CommentService commentService;
	@Resource
	protected AdviceService adviceService;
	
	/**
	 * �������ID���������е�ͼƬ
	 * @return
	 * @throws Exception
	 */
	public String getCollection() throws Exception {
		User user = userService.getById(userId);
		Set<News> newsList= user.getNews();
		
		JSONArray array = new JSONArray();
		for (News news : newsList) {
			JSONObject json = new JSONObject();
			PicCollection pic = picCollectionService.getCover(news.getNewsId());
			//System.out.println(pic);
			List<PicCollection> listPic = picCollectionService.showPic(news.getNewsId());
			json.put("newsId", news.getNewsId());
			json.put("newsTitle", news.getNewsTitle());
			json.put("newsIntroduction", news.getNewsIntroduction());
			json.put("imageurl", pic.getPicUrl());
			json.put("newsContent", news.getNewsContent());
			json.put("newsPubTime", news.getNewsPubTime().toString());
			json.put("newsSources", news.getNewsSources());
			json.put("newsCommentNum", news.getNewsCommentNum());
			json.put("newsLabelId", news.getNewsLabel().getNewsLabelId());
			json.put("newsTypeId", news.getNewsType().getNewsTypeId());
			json.put("newsTopShow", news.getNewsTopShow());
			json.put("advId",news.getAdvice().getAdvId());
			json.put("imageNum", listPic.size());
			
			array.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("success", newsList.size() != 0);
		json.put("totalnum", newsList.size());
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);

		return "showCollection";
	}

	public String addCollection() throws Exception {
		User user = userService.getById(userId);
		News news = newsService.getById(newsId);
		Set<News> ns= user.getNews(); 
		ns.add(news);
		user.setNews(ns);
		
		//System.out.println(ns);
//		Set<User> us= news.getUsers(); 
//		us.add(user);
//		news.setUsers(us);
		
		userService.save(user);
		//newsService.save(news);

		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("totalnum", 1); // list����
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		
		return "addCollection";
	}
	
	public String delete() throws Exception{
		System.out.println("删除收藏"+userId+"******"+newsId);
		User user = userService.getById(userId);
		News news = newsService.getById(newsId);
		Set<News> ns = user.getNews();
		ns.remove(news);
		user.setNews(ns);
		
		userService.save(user);
		
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
}
