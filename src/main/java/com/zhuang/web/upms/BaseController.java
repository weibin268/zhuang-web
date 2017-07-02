package com.zhuang.web.upms;

import java.io.IOException;
import java.util.List;

import com.zhuang.data.DbAccessor;
import com.zhuang.data.models.PageInfo;
import com.zhuang.upms.models.User;
import com.zhuang.web.restapi.RestApiContext;
import com.zhuang.web.restapi.results.EasyUIPaginationResult;

public class BaseController extends com.zhuang.web.restapi.BaseController {
	
	DbAccessor dbAccessor=DbAccessor.get();
	
	public boolean pageList(RestApiContext context) throws IOException {

		Integer page= Integer.parseInt(context.getRequest().getParameter("page"));
		Integer rows= Integer.parseInt(context.getRequest().getParameter("rows"));		
		String sql=context.getRequest().getParameter("sql");
		
		EasyUIPaginationResult<User> result=new EasyUIPaginationResult<User>();
	
		PageInfo pageInfo=new PageInfo(page, rows, "Id");
		List<User> users = dbAccessor.pageQueryEntities(sql, pageInfo, null, User.class);
		result.setRows(users);
		
		result.setTotal(pageInfo.getTotalRowCount());
	
		String strResult=super.getGson().toJson(result);
		
		context.getResponse().setHeader("Content-type", "text/plain;charset=UTF-8");
		context.getResponse().setCharacterEncoding("UTF-8");
		context.getResponse().getWriter().append(strResult);
	
		return false;
	}
}
