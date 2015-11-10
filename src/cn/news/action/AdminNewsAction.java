package cn.news.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.wltea.analyzer.lucene.IKAnalyzer;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

import com.opensymphony.xwork2.ActionContext;

import cn.news.base.BaseAction;
import cn.news.domain.News;
import cn.news.domain.NewsLabel;
import cn.news.domain.NewsLink;
import cn.news.domain.ParserNews;
import cn.news.domain.ParserPic;
import cn.news.domain.PicCollection;
import cn.news.domain.Topic;
import cn.news.luceneUtil.LuceneData;
import cn.news.luceneUtil.SearchUtil;
import cn.news.luceneUtil.Transformation;
import cn.news.parser.AutoXinHua;
import cn.news.parser.XinHuaNewsParser;
import cn.news.tool.ResultUtils;

@Controller
@Scope("prototype")
public class AdminNewsAction extends BaseAction<News>{
	
	private Integer type;  
	private String lable; 
	private Integer count; 
	private Integer page;  
	private Integer id;
	private String url="http://www.xinhuanet.com/";
	//private String href="http://www.xinhuanet.com/";
	private SearchUtil su = new SearchUtil();
	
	/**
	 * 获取图文新闻列表
	 * @return
	 * @throws Exception
	 */
	public String getAllNews() throws Exception{
		
		List<News> newsList = newsService.getAllNews(page);
		List<NewsLabel> newsLabelList = newsLabelService.findAll();
		//List<News> newList = newsService.getAllNews(page);
		AutoXinHua auto = new AutoXinHua();
		List<NewsLink> linkList = auto.getNewsLink();
		
		ActionContext.getContext().put("newsList", newsList);
		ActionContext.getContext().put("newsLabelList", newsLabelList);
		ActionContext.getContext().put("linkList", linkList);
		ActionContext.getContext().put("url", url);
		
		System.out.println("allNews="+newsList.size());
		System.out.println("url="+url);
		return "list";
	}
	
	/**
	 * 抓取单个新闻
	 */
	public String getNewsByUrl() throws Exception{
		XinHuaNewsParser xhnParser = new XinHuaNewsParser();
		ParserNews parserNews = xhnParser.parser(url);
		
		News news = new News();
		// 标题
		news.setNewsTitle(parserNews.getNewsTitle());
		
		// 导语
		news.setNewsIntroduction(parserNews.getNewsIntroduction());
		// 内容
		news.setNewsContent(parserNews.getNewsContent());
		
		// 发布时间
		SimpleDateFormat formatter2 = new SimpleDateFormat(
				"yyyy年MM月dd日 HH:mm:ss");
		try {
			formatter2.parse(parserNews.getNewsPubTime());
			String time = parserNews.getNewsPubTime();
			String time1 = time.split("年")[0];
			String time2 = time.split("年")[1].split("月")[0];
			String time3 = time.split("年")[1].split("月")[1].split("日")[0];
			String time4 = time.split(" ")[1];
			time = time1 + "-" + time2 + "-" + time3 + " " + time4;
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			news.setNewsPubTime(formatter.parse(time));
		} catch (Exception e) {
			news.setNewsPubTime(new Date());
		}

		// 来源
		news.setNewsSources(parserNews.getNewsSources());
		// 评论数
		news.setNewsCommentNum(0l);
		// 设置标签为未定义
		news.setNewsLabel(newsLabelService.getById(1));
		// 不置顶显示
		news.setNewsTopShow(false);
		// 广告
		news.setAdvice(adviceService.getById(1));
		// 新闻类型
		news.setNewsType(newsTypeService.getById(1));
		// 评论
		news.setComments(null);
		// 收藏的用户
		news.setUsers(null);

		newsService.save(news);

		// 图片
		Set<PicCollection> picCollectionList = new HashSet<PicCollection>();
		List<ParserPic> picList = null;
		picList = parserNews.getNewPic();
		if (picList == null) {
			news.setPicCollection(picCollectionList);
			// picList.add(new ParserPic());
		} else {

			for (ParserPic pic : picList) {
				PicCollection picCollection = new PicCollection();
				picCollection.setNews(news);
				picCollection.setPicIntroduction(pic.getPicIntroduction());
				picCollection.setPicUrl(pic.getPicUrl());
				picCollectionService.save(picCollection);
				picCollectionList.add(picCollection);
			}
			news.setPicCollection(picCollectionList);
		}
		newsService.update(news);
		System.out.println("news get");
		ActionContext.getContext().put("url", url);
		return "getNewsByUrl";
	}
	
	/**
	 * 自动抓取新闻
	 * 
	 * @throws ParseException
	 */
	public String getNewsList() throws Exception {
		List<ParserNews> parserNewsList = new ArrayList<ParserNews>();
		AutoXinHua auto = new AutoXinHua();
		List<String> urlString = auto.getAutoUrl();

		for (String str : urlString) {
			System.out.println(str);
			XinHuaNewsParser xhnParser = new XinHuaNewsParser();
			if (xhnParser.parser(str) != null) {
				parserNewsList.add(xhnParser.parser(str));
			}
		}
		for (ParserNews parserNews : parserNewsList) {
			System.out.println(parserNews);
			News news = new News();
			// 标题
			news.setNewsTitle(parserNews.getNewsTitle());
			if ("".equals(news.getNewsTitle()))
				continue;
			// 导语
			news.setNewsIntroduction(parserNews.getNewsIntroduction());
			// 内容
			news.setNewsContent(parserNews.getNewsContent());
			if ("".equals(news.getNewsContent()))
				continue;
			// 发布时间
			SimpleDateFormat formatter2 = new SimpleDateFormat(
					"yyyy年MM月dd日 HH:mm:ss");
			try {
				formatter2.parse(parserNews.getNewsPubTime());
				String time = parserNews.getNewsPubTime();
				String time1 = time.split("年")[0];
				String time2 = time.split("年")[1].split("月")[0];
				String time3 = time.split("年")[1].split("月")[1].split("日")[0];
				String time4 = time.split(" ")[1];
				time = time1 + "-" + time2 + "-" + time3 + " " + time4;
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				news.setNewsPubTime(formatter.parse(time));
			} catch (Exception e) {
				news.setNewsPubTime(new Date());
			}

			// 来源
			news.setNewsSources(parserNews.getNewsSources());
			// 评论数
			news.setNewsCommentNum(0l);
			// 设置标签为未定义
			news.setNewsLabel(newsLabelService.getById(1));
			// 不置顶显示
			news.setNewsTopShow(false);
			// 广告
			news.setAdvice(adviceService.getById(1));
			// 新闻类型
			news.setNewsType(newsTypeService.getById(1));
			// 评论
			news.setComments(null);
			// 收藏的用户
			news.setUsers(null);

			news.setTopic(null);
			
			newsService.save(news);

			// 图片
			Set<PicCollection> picCollectionList = new HashSet<PicCollection>();
			List<ParserPic> picList = null;
			picList = parserNews.getNewPic();
			if (picList == null) {
				PicCollection collection = new PicCollection();
				collection.setNews(news);
				collection.setPicUrl("/image/3.png");
				picCollectionService.save(collection);
				picCollectionList.add(collection);
				news.setPicCollection(picCollectionList);
				// picList.add(new ParserPic());
			} else {
				
				for (ParserPic pic : picList) {
					PicCollection picCollection = new PicCollection();
					picCollection.setNews(news);
					picCollection.setPicIntroduction(pic.getPicIntroduction());
					picCollection.setPicUrl(pic.getPicUrl());
					picCollectionService.save(picCollection);
					picCollectionList.add(picCollection);
				}
				news.setPicCollection(picCollectionList);
			}
			newsService.update(news);
		}
		deletefile();
		List<News> newsList = newsService.findAll();
		List<LuceneData> luceneDataList = Transformation
				.transformations(newsList);
		for (LuceneData data : luceneDataList) {
			su = new SearchUtil();
			IKAnalyzer analyzer = new IKAnalyzer();
			su.index(data, analyzer);
		}
		ActionContext.getContext().put("url", url);
		return "getNewsList";
	}
	
	/**
	 * 获取图文新闻
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getNewsById() throws Exception {
		
		News news = newsService.getById(id);
		ActionContext.getContext().put("news", news);
		
		List<NewsLabel> newsLabelList = newsLabelService.findAll();
		
		ActionContext.getContext().put("newsLabelList", newsLabelList);
		
		List<Topic> topics = topicService.findAll();
		ActionContext.getContext().put("topics", topics);
		
		//System.out.println(id+news.getNewsContent());
		return "getNewsById";
	}
	
	@Test
	public void deletefile() {
		File file = new File("E:\\服创项目\\News\\index");
		
		if(file.exists()){
			File[] files = file.listFiles();
			for (File f : files) {
				f.delete();
			}
		}
		
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SearchUtil getSu() {
		return su;
	}

	public void setSu(SearchUtil su) {
		this.su = su;
	}


}
