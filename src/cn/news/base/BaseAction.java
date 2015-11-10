package cn.news.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.news.service.AdminService;
import cn.news.service.AdviceService;
import cn.news.service.CommentService;
import cn.news.service.DevelopService;
import cn.news.service.DirtyService;
import cn.news.service.NewsLabelService;
import cn.news.service.NewsService;
import cn.news.service.NewsTypeService;
import cn.news.service.PicCollectionService;
import cn.news.service.TopicService;
import cn.news.service.UserLabelService;
import cn.news.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	private static final long serialVersionUID = -3193831934967998994L;

	protected T model;
	
	public BaseAction(){
		try{
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			
			model = clazz.newInstance();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public T getModel() {
		return model;
	}
	
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
	@Resource
	protected DevelopService developService;
	@Resource
	protected NewsTypeService newsTypeService;
	@Resource
	protected AdminService adminService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected DirtyService dirtyService;
	
}
