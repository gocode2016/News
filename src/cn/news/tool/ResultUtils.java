package cn.news.tool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public class ResultUtils {
    public static void toJson(HttpServletResponse response, Object data) 
        throws IOException {
//        Gson gson = new Gson();
//        String result = gson.toJson(data);
        response.setContentType("text/json; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache"); //ȡ�����������
        PrintWriter out = response.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

  
}
