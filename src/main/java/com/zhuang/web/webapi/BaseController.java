package com.zhuang.web.webapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseController {

    public <T extends BaseArgs> T getArgs(WebApiContext context,Class<T> argsType)
    {
    	Gson gson = new GsonBuilder().serializeNulls().create();
    	
		T result = gson.fromJson(context.getArgs(), argsType);

		result.init();
		
    	return result;
    }
}
