package cn.news.parser;

import java.util.List;

import org.junit.Test;

public class ParserTest {
	@Test
	public void test1(){
		FengHuangNewsParser fhnParser = new FengHuangNewsParser();
		System.out.println(fhnParser.parser("http://ent.ifeng.com/a/20150714/42452996_0.shtml"));
	}
	
	@Test
	public void test2(){
		AutoXinHua auto = new AutoXinHua();
		List<String> urlString = auto.getAutoUrl();
		for(String str : urlString){
			System.out.println(str);
		}
	}
	
	@Test
	public void test3(){
		XinHuaNewsParser xhnParser = new XinHuaNewsParser();
		System.out.println(xhnParser.parser("http://news.xinhuanet.com/politics/2015-08/08/c_128107228.htm"));
	}
}
