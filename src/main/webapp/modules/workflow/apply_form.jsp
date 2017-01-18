<%@page import="java.util.Map.Entry"%>
<%@page import="com.zhuang.workflow.util.ApplicationContextUtil"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@ include file="/commons/jslibs.jsp"%>

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
		
		$("#save").click(function() {
			$actionType.val("save");
			doPost();
		});

	});

	function doPost() {
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
				debugger;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("XMLHttpRequest.status:" + XMLHttpRequest.status
						+ "\nXMLHttpRequest.readyState:"
						+ XMLHttpRequest.readyState + "\ntextStatus:"
						+ textStatus);
			}
		});
	}
</script>

</head>
<body>

	<form id="applyForm" name="applyForm" action="">
		<input id="actionType" name="actionType" type="hidden"></input>
		<%
			String defKey = request.getParameter("defKey");
				
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

	<div>
		<input type="button" id="save" value="保存"></input> 
		<input type="button" id="back" value="退回"></input>
		<input type="button" id="submit" value="提交"></input>
	</div>

</body>
</html>