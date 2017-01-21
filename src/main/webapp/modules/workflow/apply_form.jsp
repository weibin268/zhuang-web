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
<title>Insert title here</title>

<%@ include file="/commons/jslibs.jsp"%>


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

<script type="text/javascript">

var isFirstTask = ${isFirstTask};
var isRunningTask = ${isRunningTask}
var currentTaskKey="${currentTaskKey}";
var currentTaskName="${currentTaskName}";

$(function(){

	if (isFirstTask==false || isRunningTask==false) {
		setFormReadonly();
	}
	
});
</script>

<script type="text/javascript">

	$(function() {
		
		var $actionType = $("#actionType");

		$("#submit").click(function() {
			$actionType.val("submit");
			doPost();
		});

		$("#back").click(function() {
			$actionType.val("back");
			doPost();
		});

		$("#save").click(
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
		<a class="brand" href="#">${proDefName}</a>
		<div class="btn-group pull-right">
			<input type="button" id="save" value="保存" class="btn btn-primary"></input>
			<input type="button" id="back" value="退回" class="btn btn-primary"></input>
			<input type="button" id="submit" value="提交" class="btn btn-primary"></input>
			</div>
		</div>
	</div>

	<form id="applyForm" name="applyForm" action="">
		<div>
			<input id="actionType" name="actionType" type="hidden"></input> <input
				id="defKey" name="defKey" type="hidden"
				value="<%=defKey%>"></input> <input
				id="taskId" name="taskId" type="hidden" value="<%=taskId%>"></input>

			<table class="table table-bordered table-condensed">
				<caption><span class="lead">基本信息</span></caption>
				<tbody>
					<tr>
						<td >标题：</td>
						<td colspan="3"><input type="text" id="env_PROC_TITLE"
							name="env_PROC_TITLE" value="${env_PROC_TITLE}"></input></td>

					</tr>

					<tr>
						<td>申请人：</td>
						<td>aaaaa</td>
						<td>申请人部门：</td>
						<td>bbb</td>
					</tr>
				</tbody>
			</table>
		</div>
		<%
			Map<String, String> workflowFormMappings = (Map<String, String>) ApplicationContextUtil
					.GetApplicationContext().getBean("workflowFormMappings");

			String formName ="";
			
			Object objPage = workflowFormMappings.get(defKey);
			if (objPage != null) {
				formName = objPage.toString();
			} else {
				formName = defKey + ".jsp";
			}

			String url = "/modules/workflow/forms/" + formName;
		%>
			<jsp:include page="<%=url%>"></jsp:include>
	</form>
	
	<div>
	
		<input id="nextUserIds" name="nextUserIds" type="hidden" value="user1,user2"></input>
		
	</div>


</body>
</html>