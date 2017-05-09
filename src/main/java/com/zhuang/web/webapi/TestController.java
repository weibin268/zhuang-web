package com.zhuang.web.webapi;

public class TestController extends  BaseController{

	public void test(WebApiContext context) {
		
		BaseArgs args = super.getArgs(context, BaseArgs.class);
		
		System.out.println(args.getUserId());
		
		//context.getResult().setMessage("zwb---");

		//throw new WebApiException("×¯Î°±ó");
		
		//return true;
		
	}
	
}
