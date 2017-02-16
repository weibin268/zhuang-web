<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/commons/jslibs.jsp"%>

<link type="text/css" rel="stylesheet" href="./css/list_base.css">
<script type="text/javascript" src="./js/list_base.js"></script>

</head>
<body>
<%
	String pageUrl=request.getParameter("page")+".jsp" ;
%>
		<jsp:include page="<%=pageUrl%>"></jsp:include>
</body>
</html>