package com.zhuang.web.upms;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.zhuang.data.DbAccessor;
import com.zhuang.data.models.PageInfo;
import com.zhuang.upms.models.User;
import com.zhuang.upms.services.UserService;
import com.zhuang.web.restapi.BaseArgs;
import com.zhuang.web.restapi.RestApiContext;
import com.zhuang.web.restapi.args.DataArgs;
import com.zhuang.web.restapi.results.EasyUIPaginationResult;

public class UserController extends BaseController {

	UserService userService = new UserService();
	
	public void test(RestApiContext context) {

		/*
		 * BaseArgs args = super.getArgs(context, BaseArgs.class);
		 */
		User user = userService.get(1);

		context.getResult().setData(user);

	}

	public void save(RestApiContext context) {

		DataArgs<User> args = super.getArgs(context, new TypeToken<DataArgs<User>>(){}.getType());

		User user=(User)args.getData();
		
		System.out.println(user);
	}
	
}
