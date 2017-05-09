package com.zhuang.web.webapi;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hamcrest.core.IsInstanceOf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;


public class WebApiHandler {

	public static final String CONTROLER_SUFFIX = "Controller";

	public static final String ACTION_NAME = "action";

	public static final String ARGS_NAME = "args";

	public static final String CONTROLLER_ACTION_SEPARATOR = "-";

	public static void handle(HttpServletRequest request, HttpServletResponse response)
			throws JsonIOException, IOException {

		WebApiJsonResult jsonResult = new WebApiJsonResult();
		String action = request.getParameter(ACTION_NAME);
		String args = request.getParameter(ARGS_NAME);

		jsonResult.setSuccess(true);
		jsonResult.setValid(true);

		try {

			String[] arrAction = action.split(CONTROLLER_ACTION_SEPARATOR);
			String controllerName = arrAction[0];
			String actionName = arrAction[1];

			String controllerFullName = getControllerFullName(controllerName);

			Class controllerClass = Class.forName(controllerFullName);

			Method actionMethod = controllerClass.getMethod(actionName, WebApiContext.class);

			WebApiContext context = new WebApiContext();
			context.setRequest(request);
			context.setResponse(response);
			context.setResult(jsonResult);
			

			Object objActionResult = actionMethod.invoke(controllerClass.newInstance(), context);

			if (objActionResult != null && objActionResult instanceof Boolean) {
				if ((Boolean) objActionResult == false) {
					return;
				}
			}

		} catch (Throwable ex) {

			Throwable innerEx = ex.getCause();
			
			if(innerEx!=null)
			{
				ex=innerEx;
			}
			
			if(ex  instanceof WebApiException)
			{
				jsonResult.setValid(false);
			}else {
				jsonResult.setSuccess(false);
				jsonResult.setValid(false);
				jsonResult.setData(ExceptionUtils.getStackTrace(ex));
			}
			
			jsonResult.setMessage(ex.getMessage());

		} finally {

		}

		response.setHeader("Content-type", "text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().serializeNulls().create();
		gson.toJson(jsonResult, response.getWriter());

	}

	private static String getControllerFullName(String controllerName) {

		String pkgName = BaseController.class.getPackage().getName();
		return pkgName + "." + controllerName + "Controller";

	}

}
