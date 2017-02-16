package com.zhuang.web.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class WorkflowUtil {

	public static Map<String, Object> getFormData(HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();

		Enumeration<String> pNames = request.getParameterNames();

		while (pNames.hasMoreElements()) {
			String pName = (String) pNames.nextElement();
			result.put(pName, request.getParameter(pName));
		}
		return result;
	}
	
	public static String getCurrentUserId() {
		return "zwb";
	}
}
