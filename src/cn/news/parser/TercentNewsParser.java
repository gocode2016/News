package cn.news.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jodd.jerry.Jerry;
import jodd.lagarto.dom.Node;


import cn.news.domain.ParserNews;
import cn.news.domain.ParserPic;
import cn.news.util.WebUtil;

public class TercentNewsParser {
	private String basicUrl="";
	public Jerry doc;
	private ParserNews news = new ParserNews();
	public ParserNews parser(String url){
		String code  = null;
		try {
			code = WebUtil.getWebContent(url);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		Jerry doc = Jerry.jerry(code);
		String title = doc.$("#C-Main-Article-QQ .hd h1").text();
		news.setNewsTitle(title);
		String laiyuan = doc.$("#C-Main-Article-QQ span.color-a-1").text();
		news.setNewsSources(laiyuan);
		String time = doc.$("#C-Main-Article-QQ span.article-time").text();
		news.setNewsPubTime(time);
		List<ParserPic> newPic = new ArrayList<ParserPic>();
		for(int i = 0 ; i < doc.$("#C-Main-Article-QQ p img").length() ; i++){
			ParserPic parserPic = new ParserPic();
			String picUrl = basicUrl + doc.$("#C-Main-Article-QQ p img").get(i).getAttribute("src");
			parserPic.setPicUrl(picUrl);
			if(doc.$("#C-Main-Article-QQ p.pictext").get(i) != null) {
				parserPic.setPicIntroduction(doc.$("#C-Main-Article-QQ p.pictext").get(i).getTextContent());
			}
			newPic.add(parserPic);
		}
		news.setNewPic(newPic);
		String content = "";
		String introduction = doc.$("#C-Main-Article-QQ p.titdd-Article").length() != 0 ? doc.$("#C-Main-Article-QQ p.titdd-Article").get(0).getTextContent() : "";
		for(int i= 0 ;i <doc.$("#C-Main-Article-QQ p").length();i++  ){
			Node node = doc.$("#C-Main-Article-QQ p").get(i);
			if( node.hasAttribute("style") || node.isAttributeContaining("class", "titdd-Article")){
				if(introduction.equals(""))introduction = node.getTextContent().trim();
				content += node.getTextContent().trim() +"\n";
			}
		}
		news.setNewsIntroduction(introduction);
		news.setNewsContent(content);
		return news;
	}

}
