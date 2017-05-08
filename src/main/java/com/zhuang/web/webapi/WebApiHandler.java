package com.zhuang.web.webapi;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.zhuang.web.models.MyJsonResult;
import com.zhuang.web.workflow.WorkflowUtil;
import com.zhuang.workflow.WorkflowBeansFactory;
import com.zhuang.workflow.WorkflowEngine;
import com.zhuang.workflow.models.NextTaskInfoModel;

public class WebApiHandler {

	public static final String CONTROLER_SUFFIX = "Controller";
	
	public  static void handle(HttpServletRequest request, HttpServletResponse response) throws JsonIOException, IOException {
		
		MyJsonResult myJsonResult = new MyJsonResult();
		String action = request.getParameter("action");
		String args = request.getParameter("args");

		myJsonResult.setSuccess(true);
		
		
		try {
			
			String[] arrAction = action.split("-");
			String controllerName=arrAction[0];
			String actionName=arrAction[0];
			
			

		} catch (Exception e) {
			
			myJsonResult.setSuccess(false);
			myJsonResult.setMessage(e.getMessage());
			myJsonResult.setData(ExceptionUtils.getStackTrace(e));
		
		}finally {
			
			response.setCharacterEncoding("UTF-8");
			Gson gson=new GsonBuilder().create();
			gson.toJson(myJsonResult,response.getWriter());
		
		}
	}
	
}
