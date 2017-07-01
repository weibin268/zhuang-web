package com.zhuang.web.restapi.args;

import com.zhuang.web.restapi.BaseArgs;

public class DataArgs<T> extends BaseArgs {

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
