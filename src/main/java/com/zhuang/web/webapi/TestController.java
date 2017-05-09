package com.zhuang.web.webapi;

public class TestController extends  BaseController{

	public boolean test(WebApiContext context) {
		
		System.out.println(context.getRequest().getParameter("action"));
		
		context.getResult().setMessage("zwb---");
		
		return true;
		
	}
	
}
