package cn.news.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jodd.jerry.Jerry;
import jodd.lagarto.dom.Node;


import cn.news.domain.ParserNews;
import cn.news.domain.ParserPic;
import cn.news.util.WebUtil;

public class FengHuangNewsParser {
	private String basicUrl="";
	public Jerry doc;
	private ParserNews news = new ParserNews();
	public ParserNews parser(String url){
		String code = "";
		try {
			code = WebUtil.getWebContent(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Jerry doc = Jerry.jerry(code);
		String title = "";
		title  = doc.$("#artical h1#artical_topic").text();
		news.setNewsTitle(title);
		String laiyuan = "";
		
		laiyuan = doc.$("#artical #artical_sth p.p_time span.ss03 a").text();
		news.setNewsSources(laiyuan);
		String time = "";
		time = doc.$("#artical #artical_sth p.p_time span.ss01").text();
		news.setNewsPubTime(time);
		String content = "";
		String introduction = "";
		for(int i = 0 ; i < doc.$("#artical #artical_real #main_content p").length() ; i ++){
			Node node = doc.$("#artical #artical_real #main_content p").get(i);
			if(node.hasChildNodes() && node.getFirstChild() != null && node.getFirstChild().getNodeName()!= null && node.getFirstChild().getNodeName().equals("img"))
				continue;
			else if(!node.hasAttribute("class")){
				content += node.getTextContent().trim()+"\n";
				if(introduction.equals(""))introduction = node.getTextContent().trim();
			}
		}
		news.setNewsContent(content);
		news.setNewsIntroduction(introduction);
		List<ParserPic> newPic = new ArrayList<ParserPic>();
		for(int i = 0 ; i < doc.$("#artical #artical_real #main_content p img").length() ; i ++){
			Node node = doc.$("#artical #artical_real #main_content p img").get(i);
			if(!node.getParentNode().getNodeName().equalsIgnoreCase("a")){
				ParserPic parserPic = new ParserPic();
				String picUrl = basicUrl+node.getAttribute("src");
				if(doc.$("#main_content p.picIntro").get(i) != null)
					parserPic.setPicIntroduction(doc.$("#main_content p.picIntro").get(i).getTextContent());
				parserPic.setPicUrl(picUrl);
				newPic.add(parserPic);
			}
		}
		news.setNewPic(newPic);
		return news;
	}
}
