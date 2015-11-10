package cn.news.luceneUtil;

import java.util.ArrayList;
import java.util.List;

import cn.news.domain.News;
import cn.news.luceneUtil.LuceneData;

public class Transformation {
	public static List<LuceneData> transformations(List<News> newsList) {
		List<LuceneData> dataList = new ArrayList<LuceneData>();
		LuceneData data = new LuceneData();
		for (News news : newsList) {
			data = new LuceneData();
			data.setId(news.getNewsId().toString());
			data.setTitle(news.getNewsTitle());
			data.setIntroduction(news.getNewsIntroduction());
			data.setContent(news.getNewsContent());
			dataList.add(data);
		}
		return dataList;
	}
}
