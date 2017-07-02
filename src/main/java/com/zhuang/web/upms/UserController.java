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
	DbAccessor dbAccessor=DbAccessor.get();
	
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
	
	public boolean listPage(RestApiContext context) throws IOException {

		Integer page= Integer.parseInt(context.getRequest().getParameter("page"));
		Integer rows= Integer.parseInt(context.getRequest().getParameter("rows"));		
		
		EasyUIPaginationResult<User> result=new EasyUIPaginationResult<User>();
		
		result.setTotal(page);
	
		PageInfo pageInfo=new PageInfo(1, 10, "Id");
		List<User> users = dbAccessor.pageQueryEntities("com.zhuang.upms.sqlxml.SysUser.selectPage", pageInfo, null, User.class);
		result.setRows(users);

		String strResult=super.getGson().toJson(result);
		context.getResponse().getWriter().append(strResult);
	
		return false;
	}

}
