package com.zhuang.web.upms;

import java.io.IOException;

import com.zhuang.web.restapi.BaseController;
import com.zhuang.web.restapi.RestApiContext;
import com.zhuang.web.util.FileUtil;

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
	


	public boolean includeHtml(RestApiContext context) throws IOException  {

		String url=context.getRequest().getParameter("url");
		
		String realPath =context.getRequest().getSession().getServletContext().getRealPath(url);
		
		String strHtml=FileUtil.readHtmlInOneLine(realPath, "utf-8");

		String content="document.write(\""+strHtml.replace("\"", "\\\"")+"\")";
		
		context.getResponse().setHeader("Content-type", "text/plain;charset=UTF-8");
		context.getResponse().setCharacterEncoding("UTF-8");
		context.getResponse().getWriter().append(content);
		
		return false;
	}
	

}
