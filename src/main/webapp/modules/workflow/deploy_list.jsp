
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
                        <th style="width: 100px;">部暑名称</th>
                        <th style="width: 80px;">部暑时间</th>
                        <th style="width: 100px;">流程定义名称</th>
                        <th style="width: 100px;">流程定义版本</th>
                        <th style="width: 100px;">流程定义描述</th>
                    </tr>
                </thead>
                <tbody>
                    {{if list.length==0}}
                    <tr>
                        <td colspan="5" style="text-align: center">没有查询到相关数据</td>
                    </tr>
                    {{else}} {{each list}}
                    <tr>
                        <td class="overflow center" title="{{$value.deployName}}">{{$value.deployName}}</td>
                        <td class="overflow center" title="{{$value.deployTime}}">{{$value.deployTime}}</td>
                        <td class="overflow center" title="{{$value.procDefName}}">{{$value.procDefName}}</td>
                        <td class="overflow center" title="{{$value.procDefVersion}}">{{$value.procDefVersion}}</td>
                        <td class="overflow center" title="{{$value.procDefDescription}}">{{$value.procDefDescription}}</td>
                    </tr>
                    {{/each}} {{/if}}
                </tbody>
            </table>

            {{include 'pager_template'}}
        </script>

<script type="text/javascript">
            $(function() {

                $("#btnSearch").trigger("click");

                
                $("#btnAddDeploy").click(function(){
                	
                	var url="deploy_form.jsp";
                	
                	openWindow(url,500,200);
                	
                });
            });

            function doSearch() {
                renderList("conditionForm", "list-container", "list-template", 1);
            }
            
            function reflashList()
            {
                $("#btnSearch").trigger("click");
            }
            
            function openWindow(url, iWidth, iHeight) {
                var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
                var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
                window.open(url, '_blank', 'width=' + iWidth + ',height=' + iHeight + ',resizable=no,top=' + iTop + ',left=' + iLeft);
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
				action="<%=request.getContextPath()%>/wf/deploy?actionType=list"
				method="POST">
				<div class="search-form">

					<a href='javascript:void(0);' data-event="expand"
						data-target=".search-part"><span class="icon-chevron-right"
						style="height: 14px;"></span><span style="margin: 0px;">展开</span></a>
						<span>部暑名称：</span><input type="text" name="deployName"
						class="input-medium" />
					
						<input type="text" name="不知道为什么只一个 Text的时候回车会提交表单" style="display:none;"
						class="input-medium" />
					
						<span>流程定义：</span> <select
						name="procDefKey" class="input-small">
						<option value="">全部</option>
						<%
                            List<ProcDefModel> procDefModels=WorkflowBeansFactory.getWorkflowQueryManager().getProcDefList();
                            for(ProcDefModel procDefModel:procDefModels)
                            {%>
						<option value="<%=procDefModel.getKey()%>"><%=procDefModel.getName() %></option>
						<%}%>
					</select>
					
					
					 <a id="btnSearch" href="javascript:void(0);"
						class="btn btn-success" onclick="doSearch()">查询</a>
					<div class="search-part hide"></div>
				</div>
			</form>
		</div>

		<div class="grid-panel">
			<div class="title">
				<span style="float: right"> <a href="javascript:void(0);"
					id="btnAddDeploy"><span class="icon-plus"></span>添加部暑</a>
				</span>流程部暑列表
			</div>
			<div>
				<div id="list-container" class="list-container"></div>
			</div>
		</div>
	</div>
</body>

</html>
