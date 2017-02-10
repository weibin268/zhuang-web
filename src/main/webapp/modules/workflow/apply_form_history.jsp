<%@page import="com.zhuang.workflow.models.TaskInfoModel"%>
<%@page import="java.util.List"%>
<%@page import="com.zhuang.workflow.WorkflowBeansFactory"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<table class="table table-condensed">
		<caption>
			<span class="lead">审批记录</span>
		</caption>
		<tbody>
			<%
				//String taskId = request.getParameter("taskId");
				if (taskId != "") {
					List<TaskInfoModel> taskInfoModels = WorkflowBeansFactory.getWorkflowQueryManager()
							.getHistoryTaskInfoList(taskId);
					for (TaskInfoModel taskInfoModel : taskInfoModels) {
					
			%>
			<td><%=taskInfoModel.getName()%></td>
			<td><%=taskInfoModel.getUserName()%></td>
			<td><%=taskInfoModel.getComment()%></td>
			<td><%=taskInfoModel.getStartTime()%></td>
			<td><%=taskInfoModel.getEndTime()%></td>
			<%
				}
				}
			%>
		</tbody>
	</table>
</div>
