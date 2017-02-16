<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false"%>

        <div class="page-panel">
        
            <div class="search-panel">            
            	<div class="title"><span class="icon-search"></span>查询条件</div>
                <form id="conditionForm" class="form-inline" action="" method="POST">
                    <div class="search-form">
                          <a href='javascript:void(0);' data-event="expand" data-target=".search-part"><span class="icon-chevron-right" style="height: 14px;"></span><span style="margin: 0px;">展开</span></a>
                          <span>日期：</span><input class="input-small" name="beginDate" type="text" value="" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', readOnly: true })" readonly="readonly" /> - <input class="input-small" name="endDate" type="text"
                              value="" onclick="WdatePicker({ dateFmt:'yyyy-MM-dd' , readOnly:true })" readonly="readonly" />
                          <button type="submit" class="btn btn-success">查询</button>
                          <div class="search-part hide">
                          
                          </div>
                     </div>
				</form>
            </div>

            <div class="grid-panel">
                <div class="title">
                    <span style="float:right">
                        <a href="javascript:void(0);" id="btnExport" class="applybtn"><span class="icon-plus"></span>导出</a>
                    </span>
                    我的待办列表
                </div>
                <div>
                    <div id="list-container" class="list-container">
                    </div>
                </div>
            </div>
        </div>
        
        
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
                        <td class="overflow center" title="{{$value.title}}">{{$value.title}}</td>
                        <td class="overflow center" title="{{$value.applyUser}}">{{$value.applyUser}}</td>
                        <td class="overflow center" title="{{$value.applyTime}}">{{$value.applyTime}}</td>
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

                var url = contextPath + "/wf/query?actionType=mytodo";

                renderList(url, "list-container", "list-template");

            });
        </script>