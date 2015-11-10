package cn.news.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;

import cpdetector.io.ASCIIDetector;
import cpdetector.io.ByteOrderMarkDetector;
import cpdetector.io.CodepageDetectorProxy;
import cpdetector.io.JChardetFacade;
import cpdetector.io.ParsingDetector;
import cpdetector.io.UnicodeDetector;


public class WebUtil {
	
	 public static String getWebContent(String URL) throws IOException {
	        String s = "";
	        try {
	            //从url打开stream
	            InputStream in = null;
	            HttpURLConnection conn = (HttpURLConnection) new URL(URL).openConnection();
	            in = conn.getInputStream();
	            //尝试从http头中获取字符集
	            String contentType = conn.getHeaderField("Content-Type").toLowerCase();
	            String encoding = "GB2312";
	            boolean charsetFound = false;
	            if (contentType.contains("charset")) {
	                encoding = contentType.split("charset=")[1];
	                charsetFound = true;  
	            }
	            if (!charsetFound) {
	                CodepageDetectorProxy codepageDetectorProxy = CodepageDetectorProxy
							.getInstance();
					codepageDetectorProxy.add(JChardetFacade.getInstance());
					codepageDetectorProxy.add(ASCIIDetector.getInstance());
					codepageDetectorProxy.add(UnicodeDetector.getInstance());
					codepageDetectorProxy.add(new ParsingDetector(false));
					codepageDetectorProxy.add(new ByteOrderMarkDetector());
					Charset charset = codepageDetectorProxy
							.detectCodepage(new URL(URL));
					if(charset.name().startsWith("UTF")){
						encoding = charset.name();
					}
	            }
	            System.out.println(encoding);
	            BufferedReader br = new BufferedReader(new InputStreamReader(in, encoding));
	            StringBuffer sb = new StringBuffer();
	            char[] charBuf = new char[2048];
	            int len = br.read(charBuf);
	            while (len != -1) {
	                sb.append(charBuf, 0, len);
	                len = br.read(charBuf);
	            }
	            br.close();
	            s = sb.toString();
	            if(!s.trim().startsWith("<")){
	                s = "<"+s.trim();
	            }
	        } catch (IOException ex) {
	            Logger.getLogger(WebUtil.class.getName()).log(Level.SEVERE, null, ex);
	            System.out.println("------------------------网络异常，请重试-------------------------------------------------------");
	            throw new IOException(ex);
	        }
	        //s = s
	        String code = s;
	      //  code = code.replaceAll("\n", "");
			//code = code.replaceAll("\t", "");
			code = code.replaceAll("<!--\\s+(.*?)[^-]-->", "");
		
			code = code.replaceAll("<link\\s+.*?[^>]>", "");
			
			
			 //过滤所有的js
				code = code.replaceAll("<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>", "");
				
				code = code.replaceAll("<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>", "");
	      
	        return code;
	    }
	 
	 public static String getWebContentNoFilter(String URL) throws IOException {
	        String s = "";
	        try {
	            //从url打开stream
	            InputStream in = null;
	            HttpURLConnection conn = (HttpURLConnection) new URL(URL).openConnection();
	            in = conn.getInputStream();
	            //尝试从http头中获取字符集
	            String contentType = conn.getHeaderField("Content-Type").toLowerCase();
	            String encoding = "utf-8";
	            boolean charsetFound = false;
	            if (contentType.contains("charset")) {
	                encoding = contentType.split("charset=")[1];
	                charsetFound = true;
	               
	            }
	            //如果没有的话,读取头1024个字符，检查html的header
	            byte[] buf = new byte[1024];
	            if (!charsetFound) {
	                int len = in.read(buf);
	                while (len <= 32) {
	                    len = in.read(buf);
	                }
	                String header = new String(buf, 0, len);
	                Pattern p = Pattern.compile(".*<meta.*content=.*charset=.*", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
	                Matcher m = p.matcher(header);
	                if (m.matches()) {
	                    encoding = header.toLowerCase().split("charset=")[1].replaceAll("[^a-z|1-9|\\-]", " ").split("\\s+")[0];
	                } else if(header.toLowerCase().indexOf("utf-8")!=-1){
	                    //如果没有的话，直接用gb2312解码
	                    encoding = "utf-8";
		              //  System.out.println("-----------------utf-8 2----------------------");
	                }else{
	                	
	                	 encoding = "gb2312";
	                	// System.out.println("-----------------2"+encoding+"2----------------------");
	                }
	            }
	            //开始读取内容正文。
	            BufferedReader br = new BufferedReader(new InputStreamReader(in, encoding));
	            String header = new String(buf, encoding);
	            //add the header to our content
	            StringBuffer sb = new StringBuffer(header);
	            char[] charBuf = new char[2048];
	            int len = br.read(charBuf);
	            while (len != -1) {
	                sb.append(charBuf, 0, len);
	                len = br.read(charBuf);
	            }
	            br.close();
	            s = sb.toString();
	            if(!s.trim().startsWith("<")){
	                s = "<"+s.trim();
	            }
	        } catch (IOException ex) {
	            Logger.getLogger(WebUtil.class.getName()).log(Level.SEVERE, null, ex);
	            System.out.println("------------------------网络异常，请重试-------------------------------------------------------");
	            throw new IOException(ex);
	        }
	        //s = s
	        String code = s;
	      //  code = code.replaceAll("\n", "");
			//code = code.replaceAll("\t", "");
	        return code;
	    }

}
