package cn.news.test;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.news.domain.News;

@SuppressWarnings("deprecation")
public class LuceneUtil {
	public static String INDEX_PATH = "E:\\服创项目\\News\\index";

	public static IKAnalyzer analyzer = new IKAnalyzer(true);

	public static synchronized boolean createIndex(LuceneData data)
			throws Exception {

		Directory index = FSDirectory.open(new File(INDEX_PATH));
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43,
				analyzer);

		Document doc = getDocument(data);

		IndexWriter w = new IndexWriter(index, config);
		w.addDocument(doc);
		System.out.println("共有索引" + w.numDocs() + "个");

		w.commit();

		return true;
	}

	private static Document getDocument(LuceneData data) {
		Document doc = new Document();
		doc.add(new Field("id", data.getId(), Store.YES, Index.ANALYZED));
		doc.add(new Field("title", data.getTitle(), Store.YES, Index.ANALYZED));
		doc.add(new Field("introduction", data.getIntroduction(), Store.YES,
				Index.ANALYZED));
		doc.add(new Field("content", data.getContent(), Store.YES,
				Index.ANALYZED));
		return doc;
	}

	private static ArrayList<LuceneData> transformation(ArrayList<News> newsList) {
		ArrayList<LuceneData> dataList = new ArrayList<LuceneData>();
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

	public void search(String str) throws Exception {
		String queryString = str;

		analyzer.setUseSmart(true);

		String[] fields = { "title", "introduction", "content" };
		Occur[] occurs = { Occur.SHOULD, Occur.SHOULD, Occur.SHOULD };

		Directory directory = FSDirectory.open(new File(INDEX_PATH));
		IndexReader reader = DirectoryReader.open(directory);

		IndexSearcher indexSearcher = new IndexSearcher(reader);
		Query query = MultiFieldQueryParser.parse(Version.LUCENE_43,
				queryString, fields, occurs, analyzer);
		//Query q = new QueryParser(Version.LUCENE_43,"content",analyzer).parse(queryString);

		TopDocs topDocs = indexSearcher.search(query, 10000);
		//TopDocs topDocs = indexSearcher.search(q, 10);
		
		System.out.println("共有【" + topDocs.scoreDocs.length + "】条结果");

		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			int id = scoreDoc.doc;
			Document doc = indexSearcher.doc(id);
			printDoc(doc);
		}
	}

	public void printDoc(Document doc) {
		System.out.println("id\t=\t" + doc.get("id"));
		System.out.println("title\t=\t" + doc.get("title"));
		System.out.println("introduction\t=\t" + doc.get("introduction"));
		System.out.println("content\t=\t" + doc.get("content"));
	}

	@Test
	public void test() throws Exception {
		LuceneData data = new LuceneData("1", "sss新闻标题 ", "sss新闻导语 ", "加载扩展停止词典");
		//printAnalysisResult(analyzer, data.getContent());
		//createIndex(data);
		search("新闻");
		// printAnalysisResult(analyzer, "新闻");
	}

	private void printAnalysisResult(Analyzer analyzer, String keyWord)
			throws Exception {

		System.out.println("当前使用的分词器：" + analyzer.getClass().getSimpleName());

		TokenStream tokenStream = analyzer.tokenStream("content",
				new StringReader(keyWord));

		tokenStream.addAttribute(CharTermAttribute.class);

		while (tokenStream.incrementToken()) {

			CharTermAttribute charTermAttribute = tokenStream
					.getAttribute(CharTermAttribute.class);

			System.out.println(new String(charTermAttribute.buffer()));

		}

	}

}
