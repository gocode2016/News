package cn.news.action;

import java.io.BufferedReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import cn.news.base.BaseAction;
import cn.news.domain.Comment;
import cn.news.domain.News;
import cn.news.tool.ResultUtils;

@Controller
@Scope("prototype")
public class CommentAction extends BaseAction<Comment>{
	private String commentCon;
	private Integer id;
	private Integer newsId;
	private Integer userId;
	private Integer page;  //页码
	
	/**
	 * 添加评论 
	 * 参数 newsId  userId   commentCon
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		System.out.println("添加评论");
		Comment comment = new Comment();
		News news = newsService.getById(newsId);
		comment.setNews(news);
		Long num = news.getNewsCommentNum()+1;
		news.setNewsCommentNum(num);
		newsService.update(news);
		comment.setUser(userService.getById(userId));
		String commentCon2 = ServletActionContext.getRequest().getParameter("commentCon");
		String commentCon = new String(commentCon2.getBytes("ISO-8859-1"),"UTF-8");
		//System.out.println(commentCon);
		comment.setCommentContent(commentCon);
		comment.setCommentPraiseNum(0l);
		comment.setCommentTime(new Date());
		commentService.save(comment);
		ResultUtils.toJson(ServletActionContext.getResponse(), 1);
		return "add";
	}
	
	/**
	 * 点赞
	 * @return
	 */
	public String praise() throws Exception{
		System.out.println("点赞"+id);
		Comment comment = commentService.getById(id);
		comment.setCommentPraiseNum(comment.getCommentPraiseNum()+1);
		commentService.update(comment);
		
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("totalnum", 1); // list����
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "praise";
	}
	
	/**
	 * 取得点赞数最高的评论
	 * 参数 newsId
	 * @return
	 * @throws Exception
	 */
	public String getPraiseComment() throws Exception{
		System.out.println(newsId);
		Comment comment = commentService.getPraiseComment(newsId);
		List<Comment> commentList = new ArrayList<Comment>();
		commentList.add(comment);
		JSONArray array = new JSONArray();
		for(Comment com : commentList){
			JSONObject json = new JSONObject();
			json.put("commentId", com.getCommentId());
			json.put("newsId", com.getNews().getNewsId());
			json.put("userId", com.getUser().getUserId());
			json.put("userHeadPicture", com.getUser().getUserHeadPicture());
			json.put("userNickName", com.getUser().getUserNickName());
			json.put("commentTime", com.getCommentTime());
			json.put("commentContent", com.getCommentContent());
			json.put("commentPraiseNum", com.getCommentPraiseNum());
			json.put("userLocation", com.getUser().getUserLocation());
			
			array.put(json);
			
		}
		JSONObject json = new JSONObject();
		json.put("success", commentList.size()!=0);
		json.put("totalnum", commentList.size());  //list����
		json.put("Array", array);
		
		System.out.println(json.toString());
		
		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		
		return "getPraiseComment";
	}
	
	/**
	 * 按时间和点赞数获得评论
	 * 参数 newsId
	 * @return
	 * @throws Exception
	 */
	public String getByTimeAndPraise() throws Exception{
		System.out.println(newsId);
		List<Comment> commentList = commentService.getByTime(newsId);
		JSONArray array = new JSONArray();
		List<Comment> commentList2 = commentService.getByPriase(newsId);
		for(Comment com : commentList2){
			JSONObject json = new JSONObject();
			json.put("commentId", com.getCommentId());
			json.put("newsId", com.getNews().getNewsId());
			json.put("userId", com.getUser().getUserId());
			json.put("userHeadPicture", com.getUser().getUserHeadPicture());
			json.put("userNickName", com.getUser().getUserNickName());
			json.put("commentTime", com.getCommentTime());
			json.put("commentContent", com.getCommentContent());
			json.put("commentPraiseNum", com.getCommentPraiseNum());
			json.put("userLocation", com.getUser().getUserLocation());
			
			array.put(json);
		}
		for(Comment com : commentList){
			JSONObject json = new JSONObject();
			json.put("commentId", com.getCommentId());
			json.put("newsId", com.getNews().getNewsId());
			json.put("userId", com.getUser().getUserId());
			json.put("userHeadPicture", com.getUser().getUserHeadPicture());
			json.put("userNickName", com.getUser().getUserNickName());
			json.put("commentTime", com.getCommentTime());
			json.put("commentContent", com.getCommentContent());
			json.put("commentPraiseNum", com.getCommentPraiseNum());
			json.put("userLocation", com.getUser().getUserLocation());
			
			array.put(json);
		}
		
		JSONObject json = new JSONObject();
		json.put("success", commentList.size()!=0);
		json.put("totalnum", commentList.size()+commentList2.size());  
		json.put("Array", array);
		
		System.out.println(json.toString());
		
		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "getByTimeAndPraise";
	}
	
	/**
	 * 热门评论
	 * 参数 page
	 * @return
	 */
	public String getAllCommentByPraise() throws Exception{
		List<Comment> commentList = commentService.getAllCommentByPraise(page);
		JSONArray array = new JSONArray();
		for(Comment com : commentList){
			JSONObject json = new JSONObject();
			json.put("commentId", com.getCommentId());
			json.put("newsId", com.getNews().getNewsId());
			json.put("userId", com.getUser().getUserId());
			json.put("userHeadPicture", com.getUser().getUserHeadPicture());
			json.put("userNickName", com.getUser().getUserNickName());
			json.put("commentTime", com.getCommentTime());
			json.put("commentContent", com.getCommentContent());
			json.put("commentPraiseNum", com.getCommentPraiseNum());
			json.put("userLocation", com.getUser().getUserLocation());
			
			array.put(json);
			
		}
		JSONObject json = new JSONObject();
		json.put("success", commentList.size()!=0);
		json.put("totalnum", commentList.size());  //list����
		json.put("Array", array);
		
		System.out.println(json.toString());
		
		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "getAllCommentByPraise";
	}

	

	public String getCommentCon() {
		return commentCon;
	}

	public void setCommentCon(String commentCon) {
		this.commentCon = commentCon;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
