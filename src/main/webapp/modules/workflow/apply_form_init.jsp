<%@page import="org.activiti.engine.repository.ProcessDefinition"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.zhuang.workflow.activiti.ProcessDefinitionManager"%>
<%@page import="com.zhuang.workflow.WorkflowEngine"%>
<%@page import="com.zhuang.workflow.WorkflowBeansFactory"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.zhuang.workflow.util.ApplicationContextUtil"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	String taskId = request.getParameter("taskId");
	String defKey = request.getParameter("defKey");

	if(taskId==null)
	{
		taskId="";	
	}
	
	Map<String, Object> formData = new HashMap<String, Object>();

	if (taskId != "") {

		WorkflowEngine workflowEngine = WorkflowBeansFactory.getWorkflowEngine();
		formData = workflowEngine.retrieveFormData(taskId);

	} else {
		ProcessDefinitionManager processDefinitionManager = WorkflowBeansFactory.getProcessDefinitionManager();
		ProcessDefinition processDefinition = processDefinitionManager.getProcessDefinitionEntityByKey(defKey);
		formData.put("proDefKey", processDefinition.getKey());
		formData.put("proDefName", processDefinition.getName());

	}
	
	for (Entry<String, Object> entry : formData.entrySet()) {
		request.setAttribute(entry.getKey(), entry.getValue());
	}
	
%>
