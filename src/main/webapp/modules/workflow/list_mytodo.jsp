<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false"%>

        <div>
            <div id="list-container" class="">
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
                renderList("mytodo", "list-container", "list-template");

            });
        </script>