package com.zhuang.web.webapi;

public class TestController extends  BaseController{

	public boolean test(WebApiContext context) {
		
		System.out.println("webapi test!");
		return true;
		
	}
	
}
