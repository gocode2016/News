package cn.news.luceneUtil;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.news.base.BaseAction;
import cn.news.domain.News;
import cn.news.luceneUtil.LuceneData;;

public class TestSearch extends BaseAction<News>{

	private SearchUtil su = null;

	@Before
	public void init() {
		su = new SearchUtil();
	}

	@Test
	public void testInex() throws Exception{
		LuceneData data = new LuceneData("1", "媒体盘点2015上半年习近平都在忙什么", " 李克强同俄总理通话  欧洲“强行军”  坚定资本市场稳定发展信心 ", "眨眼间，2015年一半的时间过去了，你在上半年做了什么呢？有没有做过梳理呢？我们的习大大依然很忙。");
			su.index(data);
	}

	@Test
	public void testSearchByTerm() {
		su.searchByTerm("content", "新闻", 10);
	}

	@Test
	public void testSearchPage() {
		
		su.searchPage("新闻", 1, 2);
	}

	@Test
	public void testSearchPageByAfter() {
		su.searchPageByAfter("新闻", 1, 20);
	}

	public void testInex(LuceneData data)  throws Exception{
		su.index(data);
	}
	
}
