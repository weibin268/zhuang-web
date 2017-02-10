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
		<thead>
			<tr>
				<th style="width:20%">步骤名</th>
				<th style="width:20%">处理人</th>
				<th style="width:20%">处理意见</th>
				<th style="width:20%">开始时间</th>
				<th style="width:20%">结束时间</th>
			</tr>
		</thead>
		<tbody>
			<%
				String taskId2 = request.getParameter("taskId");
				if (taskId2!=null && taskId2 != "") {
					List<TaskInfoModel> taskInfoModels = WorkflowBeansFactory.getWorkflowQueryManager()
							.getHistoryTaskInfoList(taskId2);
					for (TaskInfoModel taskInfoModel : taskInfoModels) {
					
			%>
			<tr>
				<td><%=taskInfoModel.getName()%></td>
				<td><%=taskInfoModel.getUserName()%></td>
				<td><%=taskInfoModel.getComment()%></td>
				<td><%=taskInfoModel.getStartTime()%></td>
				<td><%=taskInfoModel.getEndTime()%></td>
			</tr>
			<%
				}
				}
			%>
		</tbody>
	</table>
</div>
