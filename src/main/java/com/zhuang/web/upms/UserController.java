package com.zhuang.web.upms;

import com.zhuang.upms.models.User;
import com.zhuang.upms.services.UserService;
import com.zhuang.web.restapi.BaseArgs;
import com.zhuang.web.restapi.RestApiContext;

public class UserController extends  BaseController{

	UserService userService=new UserService();
	
	public void test(RestApiContext context) {
		
		/*BaseArgs args = super.getArgs(context, BaseArgs.class);
		*/
		User user=userService.get(1);
		
		context.getResult().setData(user);
		
	}
	
}
