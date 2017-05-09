package com.zhuang.web.webapi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuang.web.models.MyJsonResult;

public class WebApiContext {

	private HttpServletRequest request;

	private HttpServletResponse response;

	private MyJsonResult result;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public MyJsonResult getResult() {
		return result;
	}

	public void setResult(MyJsonResult result) {
		this.result = result;
	}
}
