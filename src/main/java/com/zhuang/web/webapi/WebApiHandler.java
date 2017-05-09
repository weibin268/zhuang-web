package com.zhuang.web.webapi;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.weaver.ast.Var;

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
	
	public static final String ACTION_NAME = "action";
	
	public static final String ARGS_NAME = "args";
	
	public static final String CONTROLLER_ACTION_SEPARATOR = "-";
	
	
	
	public  static void handle(HttpServletRequest request, HttpServletResponse response) throws JsonIOException, IOException {
		
		MyJsonResult myJsonResult = new MyJsonResult();
		String action = request.getParameter(ACTION_NAME);
		String args = request.getParameter(ARGS_NAME);
		
		myJsonResult.setSuccess(true);
		
		try {
			
			String[] arrAction = action.split(CONTROLLER_ACTION_SEPARATOR);
			String controllerName=arrAction[0];
			String actionName=arrAction[1];

			String controllerFullName = getControllerFullName(controllerName);


			Class controllerClass= Class.forName(controllerFullName);
			
			Method actionMethod = controllerClass.getMethod(actionName, WebApiContext.class);
			
			WebApiContext context=new WebApiContext();
			
			Object objActionResult = actionMethod.invoke(controllerClass.newInstance(), context);
			
			if(objActionResult !=null && objActionResult instanceof Boolean)
			{
				if((Boolean)objActionResult==false)
				{
					return;
				}
			}
			
			//myJsonResult.setData(objActionResult);
			

		} catch (Exception e) {
			
			myJsonResult.setSuccess(false);
			myJsonResult.setMessage(e.getMessage());
			myJsonResult.setData(ExceptionUtils.getStackTrace(e));
		
		}finally {
			
		
		}

		
		response.setCharacterEncoding("UTF-8");
		Gson gson=new GsonBuilder().serializeNulls().create();
		gson.toJson(myJsonResult,response.getWriter());
		
	}
	
	private static String getControllerFullName(String controllerName) {
		
		String pkgName = BaseController.class.getPackage().getName();
		return pkgName+"."+controllerName+"Controller";
	
	}
	
}
