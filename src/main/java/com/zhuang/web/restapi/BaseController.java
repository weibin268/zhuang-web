package com.zhuang.web.restapi;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseController {

	Gson gson = new GsonBuilder().serializeNulls().create();
	
	public Gson getGson() {
		return gson;
	}
	
	public <T extends BaseArgs> T getArgs(RestApiContext context, Class<T> argsClass) {

		return getArgsInternal(context, argsClass);
	}

	public <T extends BaseArgs> T getArgs(RestApiContext context, Type argsType) {

		return getArgsInternal(context, argsType);
	}

	private <T extends BaseArgs> T getArgsInternal(RestApiContext context, Object argsObject) {

		if (context.getArgs() == null || context.getArgs() == "") {

			throw new RuntimeException("��args����������Ϊ�գ�");
		}

		T result;
		
		if (argsObject instanceof Class) {

			result = gson.fromJson(context.getArgs(), (Class<T>) argsObject);

		} else if (argsObject instanceof Type) {
			
			result = gson.fromJson(context.getArgs(), (Type) argsObject);

		}else {
			throw new RuntimeException("argsObject�����ͱ���Ϊ��Class����Type��!");
		}

		result.init();

		return result;
	}

}
