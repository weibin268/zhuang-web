<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@ include file="/commons/jslibs.jsp"%>

<script type="text/javascript">
	$(function() {

		$("#submit").click(function() {
			
			var url=contextPath+"/wf/engine";
			
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
				error : function(XMLHttpRequest, textStatus,
						errorThrown) {
					alert("XMLHttpRequest.status:"
							+ XMLHttpRequest.status
							+ "\nXMLHttpRequest.readyState:"
							+ XMLHttpRequest.readyState
							+ "\ntextStatus:" + textStatus);
				}
			});

		});

	});
</script>

</head>
<body>

	<form id="applyForm" name="applyForm" action="">
		<input id="amount" name="amount" type="text"></input>
	</form>

	<div>
		<input type="button" id="submit" value="提交"></input>
	</div>

</body>
</html>