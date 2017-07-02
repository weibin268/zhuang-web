package com.zhuang.web.upms;

import java.io.IOException;
import java.util.List;

import com.zhuang.data.DbAccessor;
import com.zhuang.data.models.PageInfo;
import com.zhuang.web.restapi.RestApiContext;
import com.zhuang.web.restapi.results.EasyUIPaginationResult;

public class BaseController extends com.zhuang.web.restapi.BaseController {
	
	DbAccessor dbAccessor=DbAccessor.get();
	
	public boolean pageList(RestApiContext context) throws IOException {

		Integer page= Integer.parseInt(context.getRequest().getParameter("page"));
		Integer rows= Integer.parseInt(context.getRequest().getParameter("rows"));		
		String sql=context.getRequest().getParameter("sql");
		
		EasyUIPaginationResult<Object> result=new EasyUIPaginationResult<Object>();
	
		PageInfo pageInfo=new PageInfo(page, rows, "Id");
		
		List<Object> models = dbAccessor.pageQueryEntities(sql, pageInfo, null, Object.class);
		result.setRows(models);
		
		result.setTotal(pageInfo.getTotalRowCount());
	
		String strResult=super.getGson().toJson(result);
		
		context.getResponse().setHeader("Content-type", "text/plain;charset=UTF-8");
		context.getResponse().setCharacterEncoding("UTF-8");
		context.getResponse().getWriter().append(strResult);
	
		return false;
	}
}
