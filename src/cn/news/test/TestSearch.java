package cn.news.test;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;

public class TestSearch {

	private SearchUtil su = null;

	@Before
	public void init() {
		su = new SearchUtil();
	}

	@Test
	public void testInex() {
		LuceneData data = new LuceneData("4", "4", "第四条新闻", "第二新闻内容");

		su.index(data);
	}
	
	public void testInex(LuceneData data) {
		su.index(data);
	}

	@Test
	public void testSearchByTerm() {
		su.searchByTerm("content", "习近平", 10);
	}

	@Test
	public void testSearchPage() {
		su.searchPage("新闻", 1, 3);
	}

	@Test
	public void testSearchPageByAfter() {
		su.searchPageByAfter("新闻", 1, 20);
	}
}
