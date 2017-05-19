package com.zhuang.web.restapi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuang.web.models.MyJsonResult;

public class RestApiContext {

	private HttpServletRequest request;

	private HttpServletResponse response;

	private RestApiJsonResult result;

	private String action;
	
	private String args;
	
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

	public RestApiJsonResult getResult() {
		return result;
	}

	public void setResult(RestApiJsonResult result) {
		this.result = result;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}
	
	
}
