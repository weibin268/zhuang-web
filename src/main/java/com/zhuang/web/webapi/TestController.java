package com.zhuang.web.webapi;

public class TestController extends  BaseController{

	public void test(WebApiContext context) {
		
		System.out.println(context.getRequest().getParameter("action"));
		
		context.getResult().setMessage("zwb---");
		
		throw new WebApiException("×¯Î°±ó");
		
		//return true;
		
	}
	
}
