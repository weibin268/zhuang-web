package com.zhuang.web.test;

import org.junit.Test;

import com.zhuang.web.http.HttpClient;

public class HttpClientTest {

	@Test
	public void testSendGet() throws Exception
	{
		HttpClient httpClient=new HttpClient("http://www.baidu.com");
		
		System.out.println(httpClient.sendGet("", ""));
		
	}
	
}
