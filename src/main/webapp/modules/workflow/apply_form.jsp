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

<%@ include file="apply_form_init.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/commons/jslibs.jsp"%>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/workflow.css">
<style type="text/css">
	
</style>
<script type="text/javascript">

	var hasTask = "${isFirstTask}" == "" ? true : false;
	var isFirstTask = "${isFirstTask}" == "true" ? true : false;
	var isRunningTask = "${isRunningTask}" == "true" ? true : false;
	var currentTaskKey = "${currentTaskKey}";
	var currentTaskName = "${currentTaskName}";

	$(function() {

		if ((isFirstTask == true && isRunningTask == true)) {
			setFormReadonly();
		}

	});
</script>

<script type="text/javascript">

	$(function() {
		
		var $actionType = $("#actionType");

		$("#toolbar_submit").click(function() {
			$actionType.val("submit");
			$("#submit-dialog").modal("show");
			//doPost();
		});

		$("#toolbar_back").click(function() {
			$actionType.val("back");
			doPost();
		});

		$("#toolbar_save").click(
				function() {
					$actionType.val("save");
					doPost(function(data) {
						var objData = eval("(" + data + ")");
						if (objData.success) {
							var objResult = eval("(" + objData.data + ")");
							alert("保存成功！");
							if (objResult.isNew) {
								var newUrl = window.location.href + "&taskId="
										+ objResult.taskId;
								location.replace(newUrl);
							}
						} else {
							debugger;
							alert(objData.message);
						}

					});
				});

	});

	function doPost(success) {
		var url = contextPath + "/wf/engine";

		$.ajax(url, {
			type : "POST",
			data : $(document.forms["applyForm"]).serialize(),
			beforeSend : function() {
				if (false)
					return false;

			},
			complete : function() {

			},
			success : function(data, textStatus, jqXHR) {
				if (success) {
					success(data);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("XMLHttpRequest.status:" + XMLHttpRequest.status
						+ "\nXMLHttpRequest.readyState:"
						+ XMLHttpRequest.readyState + "\ntextStatus:"
						+ textStatus);
			}
		});
	}

	function setFormReadonly() {
		$("#applyForm input").attr("disabled", "true")
	}
</script>

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
			<input id="actionType" name="actionType" type="hidden"></input> <input
				id="defKey" name="defKey" type="hidden" value="<%=defKey%>"></input>
			<input id="taskId" name="taskId" type="hidden" value="<%=taskId%>"></input>
		</div>

		<%@ include file="apply_form_baseinfo.jsp" %>
		
		<jsp:include page="<%= formUrl %>"></jsp:include>

		<%@ include file="apply_form_submit.jsp" %>
	</form>

<%@ include file="apply_form_toolbar.jsp" %>
</body>
</html>