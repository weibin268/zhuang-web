package com.zhuang.web.test;

import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.zhuang.workflow.impl.ActivitiWorkflowEngine;
import com.zhuang.workflow.util.ApplicationContextUtil;

public class WebApp {

	public static void main(String[] args) {


		Map<String, String> workflowFormMappings = (Map<String, String>) ApplicationContextUtil.GetApplicationContext()
				.getBean("workflowFormMappings");

		System.out.println(workflowFormMappings);
		System.out.println("end");

		
	}

}
