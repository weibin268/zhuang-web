
<%@page import="com.zhuang.workflow.models.ProcDefModel"%>
<%@page import="java.util.List"%>
<%@page import="com.zhuang.workflow.WorkflowBeansFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="base_list.jsp" %>

<script id="list-template" type="text/html">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th style="width: 100px;">标题</th>
                        <th style="width: 80px;">申请人</th>
                        <th style="width: 100px;">申请时间</th>
                        <th style="width: 100px;">当前步骤</th>
                        <th style="width: 100px;">类型</th>
                    </tr>
                </thead>
                <tbody>
                    {{if list.length==0}}
                    <tr>
                        <td colspan="5" style="text-align: center">没有查询到相关数据</td>
                    </tr>
                    {{else}} {{each list}}
                    <tr>
                        <td class="overflow center" title="{{$value.title}}">

                            <a href="javascript:void(0);" onclick="openApplyForm('{{$value.defKey}}','{{$value.taskId}}')">
                            {{$value.title}}
                            </a>

                        </td>
                        <td class="overflow center" title="{{$value.createUser}}">{{$value.createUser}}</td>
                        <td class="overflow center" title="{{$value.createTime}}">{{$value.createTime}}</td>
                        <td class="overflow center" title="{{$value.currentActivityName}}">{{$value.currentActivityName}}</td>
                        <td class="overflow center" title="{{$value.type}}">{{$value.type}}</td>
                    </tr>
                    {{/each}} {{/if}}
                </tbody>
            </table>

            {{include 'pager_template'}}
        </script>

<script type="text/javascript">
            $(function() {

                $("#btnSearch").trigger("click");

            });

            function doSearch() {
                renderList("conditionForm", "list-container", "list-template", 1);
            }

            function openApplyForm(defKey, taskId) {
                //defKey=defKey.split(":")[0];

                var applyFormUrl = contextPath + "/modules/workflow/apply_form.jsp?defKey=" + defKey + "&taskId=" + taskId;

                window.open(applyFormUrl);
            }
        </script>

</head>

<body>
	<div class="page-panel">

		<div class="search-panel">
			<div class="title">
				<span class="icon-search"></span>查询条件
			</div>
			<form id="conditionForm" class="form-inline"
				action="<%=request.getContextPath()%>/wf/query?actionType=mydone"
				method="POST">
				<div class="search-form">

					<a href='javascript:void(0);' data-event="expand"
						data-target=".search-part"><span class="icon-chevron-right"
						style="height: 14px;"></span><span style="margin: 0px;">展开</span></a>
					<span>标题：</span><input type="text" name="PROC_TITLE"
						class="input-medium" /> <span>申请时间：</span><input
						class="input-small" name="PROC_CREATE_TIME_START" type="text"
						value=""
						onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', readOnly: true })"
						readonly="readonly" /> - <input class="input-small"
						name="PROC_CREATE_TIME_END" type="text" value=""
						onclick="WdatePicker({ dateFmt:'yyyy-MM-dd' , readOnly:true })"
						readonly="readonly" /> <span>类型：</span> <select
						name="PROC_DEF_KEY" class="input-small">
						<option value="">全部</option>
						<%
                            List<ProcDefModel> procDefModels=WorkflowBeansFactory.getWorkflowQueryManager().getProcDefList();
                            for(ProcDefModel procDefModel:procDefModels)
                            {%>
						<option value="<%=procDefModel.getKey()%>"><%=procDefModel.getName() %></option>
						<%}%>
					</select> <a id="btnSearch" href="javascript:void(0);"
						class="btn btn-success" onclick="doSearch()">查询</a>
					<div class="search-part hide"></div>
				</div>
			</form>
		</div>

		<div class="grid-panel">
			<div class="title">
				<span style="float: right"> <a href="javascript:void(0);"
					id="btnExport" class="applybtn"><span class="icon-plus"></span>导出</a>
				</span> 我的已办列表
			</div>
			<div>
				<div id="list-container" class="list-container"></div>
			</div>
		</div>
	</div>
</body>

</html>
