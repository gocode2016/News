package cn.news.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;
import javax.management.RuntimeMBeanException;
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
import cn.news.domain.ParserNews;
import cn.news.domain.ParserPic;
import cn.news.domain.PicCollection;
import cn.news.luceneUtil.TestSearch;
import cn.news.luceneUtil.Transformation;
import cn.news.luceneUtil.LuceneData;
import cn.news.luceneUtil.SearchUtil;
import cn.news.parser.AutoXinHua;
import cn.news.parser.XinHuaNewsParser;
import cn.news.tool.ResultUtils;
import cn.news.util.ComparatorNewsComment;
import cn.news.util.ComparatorNewsTime;

@Controller
@Scope("prototype")
public class NewsAction extends BaseAction<News> {

	private Integer type;
	private String lable;
	private Integer count;
	private Integer page;
	private Integer id;
	private String key;
	private String sequence;
	private Integer num;
	private Integer flag;
	private SearchUtil su = new SearchUtil();
	private Integer[] ids = { 0 };

	/**
	 * 获取图文新闻列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showAll() throws Exception {

		lable = ServletActionContext.getRequest().getParameter("lable");
		lable = new String(lable.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(type + "\t" + lable + "\t" + count + "\t" + page);
		List<News> newsList = newsService.showAll(type, lable, count, page);

		ActionContext.getContext().put("newsList", newsList);

		JSONArray array = new JSONArray();
		for (News news : newsList) {
			JSONObject json = new JSONObject();
			PicCollection pic = picCollectionService.getCover(news.getNewsId());
			// System.out.println(pic);
			List<PicCollection> listPic = picCollectionService.showPic(news
					.getNewsId());
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
			json.put("advId", news.getAdvice().getAdvId());
			json.put("imageNum", listPic.size());

			array.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("success", newsList.size() != 0);
		json.put("totalnum", newsList.size()); // list����ȡֵ
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);

		return "showAll";
	}

	/**
	 * 获取图文新闻
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getNewsById() throws Exception {
		System.out.println("获取新闻");
		System.out.println(id);
		News anews = newsService.getById(id);
		List<News> newsList = new ArrayList<News>();
		newsList.add(anews);
		JSONArray array = new JSONArray();
		for (News news : newsList) {
			JSONObject json = new JSONObject();
			PicCollection pic = picCollectionService.getCover(news.getNewsId());
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
			json.put("advId", news.getAdvice().getAdvId());

			array.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("success", newsList.size() != 0);
		json.put("totalnum", newsList.size()); // list����ȡֵ
		json.put("Array", array);

		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "getNewsById";
	}

	/**
	 * 根据关键字定制获取新闻列表
	 * 
	 * @return
	 */
	public String getNewsByDevelop() throws Exception {
		System.out.println("根据关键字定制获取新闻列表");
		key = ServletActionContext.getRequest().getParameter("key");
		key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
		sequence = ServletActionContext.getRequest().getParameter("sequence");
		sequence = new String(sequence.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(key + "****" + sequence + "****" + page + "****"
				+ num);
		ids[0] = 0;
		ids = searchIndex(key, page, num);
		List<News> newsList = null;
		JSONObject json;
		if (ids.length == 0) {
			newsList = null;
			JSONArray array = new JSONArray();
			json = new JSONObject();
			json.put("success",true);
			json.put("totalnum", 0); // list����ȡֵ
			json.put("Array", array);
		} else {
			newsList = newsService.getByIds(ids);

			if (sequence.equals("按更新时间")) {
				ComparatorNewsTime comparator = new ComparatorNewsTime();
				Collections.sort(newsList, comparator);
			} else {
				ComparatorNewsComment comparator = new ComparatorNewsComment();
				Collections.sort(newsList, comparator);
			}

			JSONArray array = new JSONArray();
			for (News news : newsList) {
				json = new JSONObject();
				PicCollection pic = picCollectionService.getCover(news
						.getNewsId());
				List<PicCollection> listPic = picCollectionService.showPic(news
						.getNewsId());
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
				json.put("advId", news.getAdvice().getAdvId());
				json.put("imageNum", listPic.size());

				array.put(json);
			}
			json = new JSONObject();
			json.put("success", newsList.size() != 0);
			json.put("totalnum", newsList.size()); // list����ȡֵ
			json.put("Array", array);
		}
		System.out.println(json.toString());

		ResultUtils.toJson(ServletActionContext.getResponse(), json);
		ActionContext.getContext().put("json", json);
		return "getNewsByDevelop";
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
		return "getNewsList";
	}

	/**
	 * 搜索
	 * 
	 * @return
	 */
	public Integer[] searchIndex(String str, int page, int size) {
		return su.searchPage(str, page, size);
	}

	/**
	 * 搜索
	 * 
	 * @return
	 */
	@Test
	public void searchIndex() {
		su.searchPage("新闻", 1, 2);
	}

	@Test
	public void deletefile() {
		File file = new File("E:\\服创项目\\News\\index");
		File[] files = file.listFiles();
		for (File f : files) {
			f.delete();
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
