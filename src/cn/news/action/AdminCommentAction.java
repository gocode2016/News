package cn.news.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import cn.news.base.BaseAction;
import cn.news.domain.Comment;
import cn.news.tool.ResultUtils;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class AdminCommentAction extends BaseAction<Comment> {
	private String commentCont;
	private Integer newsId;
	private Integer userId;
	private Integer commentId;
	private Integer page; // 页码
	// private ArrayList<Integer> commentIds = new ArrayList<Integer>();
	private String commentIds;

	private Date startTime;
	private Date endTime;
	private String sql;

	/**
	 * 得到所有评论按时间先后 参数 page
	 * 
	 * @return
	 */
	public String getAllComment() throws Exception {
//		System.out.println("所有评论" + page);
		List<Comment> commentList = commentService.getAllComment(page);

		ActionContext.getContext().put("commentList", commentList);
		return "list";
	}

	/**
	 * 按id删除评论 参数 page
	 * 
	 * @return
	 */
	public String deleteCommentById() throws Exception {

		// System.out.println("刪除评论commentId"+userId);
		// System.out.println("刪除评论page"+page);
		commentService.delete(userId);
		ActionContext.getContext().put("page", page);
		return "delete";
	}

	/**
	 * 按ids删除评论 参数 page
	 * 
	 * @return
	 */
	public String deleteCommentByIds() throws Exception {

		System.out.println("刪除评论commentIds" + commentIds);
		String[] ids;
		ids = commentIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			System.out.println("刪除评论commentIds.get(i)" + ids[i]);
			commentService.delete(Integer.parseInt(ids[i]));
		}
		ActionContext.getContext().put("page", page);
		return "delete";
	}

	/**
	 * 按ids删除评论 参数 page
	 * 
	 * @return
	 */
	public String getCommentBySql() throws Exception {

		System.out.println("getCommentBySql" + commentIds);
		System.out.println("startTime" + startTime);
		System.out.println("endTime" + endTime);
		// if()
		// List<Comment> commentList = commentService.getCommentBySql(sql,
		// page);
		// ActionContext.getContext().put("commentList", commentList);
		return "list";
	}

	public String getCommentCont() {
		return commentCont;
	}

	public void setCommentCont(String commentCont) {
		this.commentCont = commentCont;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getCommentIds() {
		return commentIds;
	}

	public void setCommentIds(String commentIds) {
		this.commentIds = commentIds;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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

}
