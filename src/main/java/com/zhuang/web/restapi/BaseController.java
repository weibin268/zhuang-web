package com.zhuang.web.restapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseController {

    public <T extends BaseArgs> T getArgs(RestApiContext context,Class<T> argsType)
    {
    	Gson gson = new GsonBuilder().serializeNulls().create();
    	
    	if(context.getArgs()==null || context.getArgs()=="")
    	{
    		
    		throw new RuntimeException("“args”参数不能为空！");
    	}
    	
		T result = gson.fromJson(context.getArgs(), argsType);

		result.init();
		
    	return result;
    }
}
