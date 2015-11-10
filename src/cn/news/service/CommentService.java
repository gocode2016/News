package cn.news.service;

import java.util.List;

import cn.news.base.DaoSupport;
import cn.news.domain.Comment;

public interface CommentService extends DaoSupport<Comment>{
	/**
	 * 根据新闻id得到赞最多的评论
	 * @param 评论
	 * @return
	 */
	Comment getPraiseComment(Integer newsId);
	
	/**
	 * 根据新闻id按时间排序的评论列表
	 * @param newsId
	 * @return
	 */
	List<Comment> getByTime(Integer newsId);
	
	/**
	 * 根据新闻id按时间排序的评论列表
	 * @param newsId
	 * @return
	 */
	List<Comment> getByPriase(Integer newsId);

	/**
	 * 根据新闻id按时间排序的评论列表
	 * @param newsId
	 * @return
	 */
	List<Comment> getAllCommentByPraise(Integer page);

	/**
	 * 根据新闻id按时间排序的评论列表
	 * @param newsId
	 * @return
	 */
	List<Comment> getAllComment(Integer page);
	/**
	 * 根据查询条件的评论列表
	 * @param 查询条件的sql部分语句
	 * @return
	 */
	List<Comment> getCommentBySelect(String sql);
}
