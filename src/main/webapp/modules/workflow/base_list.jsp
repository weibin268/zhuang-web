<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%@ include file="/commons/base_js_libs.jsp"%>

<link type="text/css" rel="stylesheet" href="./css/base_list.css">
<script type="text/javascript" src="./js/base_list.js"></script>

<script id="pager_template" type="text/html">

                    <div id="list-pager" class="list-pager">
                        <span class="stat">第<span class="page">{{pageNo}}</span>页/共<span class="page">{{totalPages}}</span>页 每页<span class="page">{{pageSize}}</span>条 共<span class="page">{{totalRows}}</span>条</span>
                        <ul>
                            <li><a href="javascript:void(0);" data-pager="1" data-enabled="{{pageNo>1?'true':'false'}}"><span class="icon-fast-backward"></span>首页</a></li>
                            <li><a href="javascript:void(0);" data-pager="{{pageNo-1}}" data-enabled="{{pageNo-1>0?'true':'false'}}"><span class="icon-backward"></span>上一页</a></li>
                            <li><a href="javascript:void(0);" data-pager="{{pageNo+1}}" data-enabled="{{pageNo+1<=totalPages?'true':'false'}}"><span class="icon-forward"></span>下一页</a></li>
                            <li><a href="javascript:void(0);" data-pager="{{totalPages}}" data-enabled="{{pageNo<totalPages?'true':'false'}}"><span class="icon-fast-forward"></span>末页</a></li>
                        </ul>
                    </div>


                </script>

