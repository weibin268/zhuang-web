<%@page import="com.zhuang.workflow.models.WorkflowChoiceOptions"%>
<%@page import="com.zhuang.workflow.impl.ActivitiWorkflowEngine"%>
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
<%@ page isELIgnored="false" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/commons/base_js_libs.jsp"%>
<%@ include file="apply_form_init.jsp" %>
<link type="text/css" rel="stylesheet" href="./css/apply_form.css">
<script type="text/javascript" src="./js/apply_form.js"></script>
<script type="text/javascript" src="./js/apply_form_submit.js"></script>

</head>
<body>

	<div class="navbar">
		<div class="navbar-inner">	
	<h4>
		${proDefName}
	</h4>
	
	</div>

	<form id="applyForm" name="applyForm" action="" class="container-fluid"
		style="padding-left: 130px; padding-right: 130px;">

		<div>
			<input id="actionType" name="actionType" type="hidden"></input>
			<input id="env_choice" 
			name="<%=ActivitiWorkflowEngine.ACTIVITI_ENV_VAR_KEY_PREFIX%><%=WorkflowChoiceOptions.getStoreKey()%>" type="hidden"></input>
			<input id="defKey" name="defKey" type="hidden" value="<%=defKey%>"></input>
			<input id="taskId" name="taskId" type="hidden" value="<%=taskId%>"></input>
		</div>

		<%@ include file="apply_form_baseinfo.jsp" %>
		
		<jsp:include page="<%= formUrl %>"></jsp:include>

		<%@ include file="apply_form_history.jsp" %>
		
		<%@ include file="apply_form_submit.jsp" %>
	</form>

<%@ include file="apply_form_toolbar.jsp" %>
</body>
</html>