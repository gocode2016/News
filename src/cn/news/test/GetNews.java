package cn.news.test;

import org.junit.Test;

import cn.news.util.WebUtil;

import jodd.jerry.Jerry;
import jodd.lagarto.dom.Node;

public class GetNews {
	@Test
	public void test() throws Exception{
		
		String url = "http://news.sina.com.cn/c/2015-07-06/162832078202.shtml";
		String code = WebUtil.getWebContent(url);
		
		
			 Jerry doc = Jerry.jerry(code);
		 //标题
		 String title = doc.$("#artibodyTitle").text();
		  System.out.println(title);
		  //时间
		  String time = doc.$(".time-source").text().trim();
		  System.out.println(time);
		  //来源
		  String laiYuan = doc.$("span#media_name span a").text();
		  System.out.println(laiYuan);
		  
		  //视频
		  String video = doc.$("#myMovie").html();
		  System.out.println(video);
		  
		  //图片路径
		  for(int i= 0; i < doc.$("div.img_wrapper").find("img").length(); i++ )
	        {
	        	Node temp = doc.$("div.img_wrapper").find("img").get(i);
	        	
	        	System.out.println(temp.getAttribute("src"));
	        	System.out.println(temp.getAttribute("title"));
	        }
		  
	
		    //正文   
		        
		          for(int i= 0; i <  doc.$("div#artibody").find("div").length(); i++ )
			        {
			        	Node temp =  doc.$("div#artibody").find("div").get(i);
			        	
			        	temp.getParentNode().removeChild(temp);
			        }
		          doc.$("div#artibody").find("div").remove();
		        System.out.println( doc.$("div#artibody").html()  );	        
		 		
		 		
		    }
	
	
	@Test
	public void test2() throws Exception{
		
		String url = "http://news.sina.com.cn/c/2015-07-05/084532074373.shtml";
		String code = WebUtil.getWebContent(url);
		 Jerry doc = Jerry.jerry(code);
		System.out.println("所以的长度： "+ doc.length());
		
		System.out.println( doc.$("style").length());
		for( int i= 0; i <doc.$("script").length(); i++ ){
			//System.out.println(i+"-------------"+  doc.$("script").get(i).getHtml()  );
		}
		
		
	}
		
		
	
	

}
