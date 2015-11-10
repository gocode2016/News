package cn.news.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.news.domain.NewsLink;
import cn.news.util.WebUtil;

import jodd.jerry.Jerry;
import jodd.lagarto.dom.Node;

public class AutoXinHua {

	public static List<String> getAutoUrl() {
		String code = "";
		try {
			code = WebUtil.getWebContent("http://www.xinhuanet.com/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Jerry doc = Jerry.jerry(code);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < doc.$("div#focusItem div.borderCont a").length(); i++) {
			Node node = doc.$("div#focusItem div.borderCont a").get(i);
			list.add(node.getAttribute("href"));
		}
		return list;
	}

	public static List<NewsLink> getNewsLink() {
		String code = "";
		try {
			code = WebUtil.getWebContent("http://www.xinhuanet.com/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Jerry doc = Jerry.jerry(code);
		List<NewsLink> list = new ArrayList<NewsLink>();
		for (int i = 0; i < doc.$("div#focusItem div.borderCont a").length(); i++) {
			Node node = doc.$("div#focusItem div.borderCont a").get(i);
			NewsLink news = new NewsLink();
			news.setUrl(node.getAttribute("href"));
			news.setTitle(node.getHtml());
			list.add(news);
		}
		return list;
	}
}
