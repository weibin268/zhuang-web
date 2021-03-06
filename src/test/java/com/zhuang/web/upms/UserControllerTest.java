package com.zhuang.web.upms;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.apache.bcel.generic.NEW;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuang.upms.models.User;
import com.zhuang.web.http.HttpClient;
import com.zhuang.web.restapi.args.DataArgs;

public class UserControllerTest {

	HttpClient httpClient=new HttpClient("http://localhost:8080/zhuang-web/");
	
	
	@Test
	public void testSave() {
		
		Map<String, Object> params=new HashMap<String, Object>();


		DataArgs dataArgs=new DataArgs();
		dataArgs.setUserId("zwb");
		User user=new User();
		
		user.setId("01");
		user.setName("zwb");
		
		dataArgs.setData(user);

		Gson gson = new GsonBuilder().serializeNulls().create();

		String strArgs = "args="+gson.toJson(dataArgs);

		System.out.println(httpClient.sendPost("upmsapi?action=User-save", strArgs));
		
	}
	
}
