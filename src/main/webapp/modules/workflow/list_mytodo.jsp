<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>

        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Insert title here</title>

            <%@ include file="/commons/jslibs.jsp"%>
                <%@ include file="apply_form_init.jsp" %>

                    <script id="t_list" type="text/html">
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
                                {{if empty}}
                                <tr>
                                    <td colspan="5" style="text-align: center">没有查询到相关数据</td>
                                </tr>
                                {{else}} {{each List}}
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
                    </script>
        </head>

        <body>

        </body>

        </html>