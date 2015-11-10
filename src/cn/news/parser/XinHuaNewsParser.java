package cn.news.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jodd.jerry.Jerry;
import jodd.lagarto.dom.Node;


import cn.news.domain.ParserNews;
import cn.news.domain.ParserPic;
import cn.news.util.WebUtil;

public class XinHuaNewsParser {
	private String basicUrl="";
	public Jerry doc;
	private ParserNews news = new ParserNews();
	public XinHuaNewsParser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ParserNews parser(String url){
		if(url.matches(".*index.*")){
			return null;
		}
		if(url.matches(".*video.*")){
			return null;
		}
		if(url.matches(".*www.*")){
			return null;
		}
		if(url.lastIndexOf("/") != -1)
			basicUrl = url.substring(0, url.lastIndexOf("/")+1);
		//System.out.println(basicUrl);
		String code = "";
		try{
			code = WebUtil.getWebContent(url);
			news.setUrl(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		doc= Jerry.jerry(code);
		if(doc.$("div.main_tit").html() == null){
			parserType1();
		}
		else parserType2();
		return news;
	}
	public void parserType1(){
		String title =  doc.$("#article h1#title") == null ? "" : doc.$("#article h1#title").text().trim();
		news.setNewsTitle(title);
		String laiyuan = doc.$(".source #source") == null ? "": doc.$(".source #source").text().trim();
		news.setNewsSources(laiyuan);
		String time = doc.$("span.time") == null ? "" : doc.$("span.time").text().trim();
		news.setNewsPubTime(time);
		String content = "";
		String introduction = "";
		for(int i = 0 ; i < doc.$("#article .article p").length() ; i ++){
			Node node = doc.$("#article .article p").get(i);
			if( node.hasChildNodes() && node.getFirstChild().getNodeName() != null && node.getFirstChild().getNodeName().equalsIgnoreCase("img") && !node.hasAttributes()){
				break;	
			}
			else if(!node.hasAttribute("align"))
			{
				//System.out.println(node.getTextContent());
				if(node.getChild(0) != null && node.getChild(0).getChild(0) != null && node.getChild(0).getChild(0).hasAttribute("src"))
					break;
				if(introduction.equals(""))introduction = node.getTextContent().trim();
				content+=node.getTextContent().trim()+"\n";
				//System.out.println(node.getFirstChild().getNodeName());
			}
		}
		news.setNewsIntroduction(introduction);
		news.setNewsContent(content);
		List<ParserPic> newPic = new ArrayList<ParserPic>();
		for(int i = 0 ; i < doc.$("#article .article p img").length() ;  i++){
			ParserPic parserPic = new ParserPic();
			String picUrl =basicUrl + doc.$("#article .article p img").get(i).getAttribute("src");
			String picIntroduction = "";
			if(doc.$("#article .article p.pictext").get(i) != null)
				picIntroduction = doc.$("#article .article p.pictext").get(i).getTextContent();
			if(picIntroduction.equals(""))
				picIntroduction = title;
			parserPic.setPicUrl(picUrl);
			parserPic.setPicIntroduction(picIntroduction);
			newPic.add(parserPic);
			news.setNewPic(newPic);
		}
	}
	public void parserType2(){
		System.out.println(2);
		String title =  doc.$("div.main_tit h1#title") == null ? "" : doc.$("div.main_tit h1#title").text().trim();
		news.setNewsTitle(title);
		String laiyuan = doc.$("div.main_tit div.info span#source") == null ? "" : doc.$("div.main_tit div.info span#source").text().split("来源：")[1].trim();
		//System.out.println(laiyuan);
		news.setNewsSources(laiyuan);
		String time = doc.$("div.main_tit div.info span#pubtime") == null ? "" : doc.$("div.main_tit div.info span#pubtime").text().trim();
		//System.out.println(time);
		news.setNewsPubTime(time);
		String content = "";
		String introduction = "";
		for(int i = 0 ; i < doc.$("div#content p").length() ; i ++){
			Node node = doc.$("div#content p").get(i);
			if( !node.hasAttribute("align"))
			{
				if(introduction.equals(""))introduction = node.getTextContent().trim();
				content+=node.getTextContent().trim()+"\n";
				//System.out.println(node.getFirstChild().getNodeName());
			}
		}
		news.setNewsIntroduction(introduction);
		news.setNewsContent(content);
		List<ParserPic> newPic = new ArrayList<ParserPic>();
		for(int i = 0 ; i < doc.$("div#content p img").length() ; i ++){
			Node node = doc.$("div#content p img").get(i);
			ParserPic parserPic = new ParserPic();
			String picUrl = basicUrl + node.getAttribute("src");
			String picIntroduction = "";
			if( doc.$("div#content p.pictext").get(i) != null){
				picIntroduction = doc.$("div#content p.pictext").get(i).getTextContent().trim();
			}else{
				picIntroduction = node.getAttribute("title");
			}
			if(picIntroduction.equals(""))
				picIntroduction = title;
			parserPic.setPicUrl(picUrl);
			parserPic.setPicIntroduction(picIntroduction);
			newPic.add(parserPic);
		}
		news.setNewPic(newPic);
	}
}
