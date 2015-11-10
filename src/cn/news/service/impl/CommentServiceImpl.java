package cn.news.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.Comment;
import cn.news.service.CommentService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class CommentServiceImpl extends DaoSupportImpl<Comment> implements CommentService{

	@Override
	public Comment getPraiseComment(Integer newsId) {
		// TODO Auto-generated method stub
		return (Comment) getSession().createQuery("from Comment c where c.news = "+newsId+" order by c.commentPraiseNum desc")
				.setMaxResults(1)
				.uniqueResult();
	}

	@Override
	public List<Comment> getByTime(Integer newsId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Comment c where c.news = "+newsId+" order by c.commentTime desc")
				.list();
	}

	@Override
	public List<Comment> getByPriase(Integer newsId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Comment c where c.news = "+newsId+" order by c.commentPraiseNum desc")
				.setMaxResults(10)
				.list();
	}

	@Override
	public List<Comment> getAllCommentByPraise(Integer page) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Comment c order by c.commentPraiseNum desc")
				.setFirstResult((page-1)*10)
				.setMaxResults(10)
				.list();
	}	

	@Override
	public List<Comment> getAllComment(Integer page) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Comment c order by c.commentTime desc")
				.setFirstResult((page-1)*10)
				.setMaxResults(10)
				.list();
	}

	@Override
	public List<Comment> getCommentBySelect(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
}
