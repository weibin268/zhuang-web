<%@page import="com.zhuang.workflow.enums.WorkflowChoiceOptions"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

	<div class="toolbar">
		<div class="title">快速菜单</div>
		<a href="javascript:void(0);" id="toolbar_close">
			<div class="button-panel">
				<div class="pic">
					<img src="<%=request.getContextPath()%>/images/workflow/close.png" height="29" width="29" />
				</div>
				<div class="content">
					<span>关闭</span>
				</div>
			</div>
		</a>
		<%if (request.getAttribute(WorkflowChoiceOptions.SAVE).toString()=="true"){%>
		<a href="javascript:void(0);" id="toolbar_save">
			<div class="button-panel">

				<div class="pic">
					<img src="<%=request.getContextPath()%>/images/workflow/save.png" height="29" width="29" />
				</div>
				<div class="content">
					<span>保存</span>
				</div>
			</div>
		</a>
		<% }%>
		<%if (request.getAttribute(WorkflowChoiceOptions.SUBMIT).toString()=="true"){%>
		<a href="javascript:void(0);" id="toolbar_submit">
			<div class="button-panel">

				<div class="pic">
					<img src="<%=request.getContextPath()%>/images/workflow/submit.png" height="29" width="29" />
				</div>
				<div class="content">
					<span>提交</span>
				</div>
			</div>
		</a>
		<% }%>
		<%if (request.getAttribute(WorkflowChoiceOptions.BACK).toString()=="true"){%>
		<a href="javascript:void(0);" id="toolbar_back">
			<div class="button-panel">

				<div class="pic">
					<img src="<%=request.getContextPath()%>/images/workflow/back.png" height="29" width="29" />
				</div>
				<div class="content">
					<span>退回</span>
				</div>
			</div>
		</a>
		<% }%>
		<%if (request.getAttribute(WorkflowChoiceOptions.DELETE).toString()=="true"){%>
		<a href="javascript:void(0);" id="toolbar_delete">
			<div class="button-panel">

				<div class="pic">
					<img src="<%=request.getContextPath()%>/images/workflow/delete.png" height="29" width="29" />
				</div>
				<div class="content">
					<span>删除</span>
				</div>
			</div>
		</a>
		<% }%>
	</div>


	<div class="return-top">
		<a href="javascript:scroll(0,0)"> <img class="return-top-pic"
		style="height: 25px;" src="<%=request.getContextPath()%>/images/workflow/returntop.png" />
	<span class="return-top-content">返回顶部</span>
	</a>
	</div>