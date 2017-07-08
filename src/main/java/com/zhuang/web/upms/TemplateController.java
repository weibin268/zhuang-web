package com.zhuang.web.upms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zhuang.web.http.HttpClient;
import com.zhuang.web.restapi.BaseController;
import com.zhuang.web.restapi.RestApiContext;

public class TemplateController extends BaseController {


	public boolean commonLibs(RestApiContext context) throws IOException {
		
		StringBuilder sbHtml=new StringBuilder();

	    sbHtml.append("<link rel='stylesheet' href='../../js/jquery-easyui/themes/bootstrap/easyui.css'>");	    
	    sbHtml.append("<link rel='stylesheet' href='../../js/jquery-easyui/themes/icon.css'>");
	    sbHtml.append( "<script src='../../js/jquery-easyui/jquery.min.js'></script>");
	    sbHtml.append("<script src='../../js/jquery-easyui/jquery.easyui.min.js'></script>");
	    sbHtml.append("<script src='../../js/jquery-easyui/locale/easyui-lang-zh_CN.js'></script>");

		String content="document.write(\""+sbHtml.toString()+"\")";
		context.getResponse().getWriter().append(content);
		
		return false;
	}
	


	public boolean includeHtml(RestApiContext context) throws IOException {

		String url=context.getRequest().getParameter("url");
		
		StringBuilder sbHtml=new StringBuilder();

		HttpClient httpClient=new HttpClient();
		
		String realPath =context.getRequest().getSession().getServletContext().getRealPath(url);
		
		FileInputStream fileInputStream=new FileInputStream(new File(realPath));
		InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
		
		BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

	    String tempString;
		while ((tempString=bufferedReader.readLine())!=null) {
			sbHtml.append(tempString+"\n");
		}
		
		bufferedReader.close();
		inputStreamReader.close();
		fileInputStream.close();
		
		String content="document.write(\""+sbHtml.toString().replace("\"", "\\\"")+"\")";
		context.getResponse().getWriter().append(content);
		
		return false;
	}
	

}
