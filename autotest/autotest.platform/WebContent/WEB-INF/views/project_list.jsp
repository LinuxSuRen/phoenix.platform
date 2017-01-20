<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>项目列表</title>
<link href="<%=basePath %>/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<script src="<%=basePath %>/static/jquery/jquery.min.js"></script>
<script src="<%=basePath %>/static/bootstrap/js/bootstrap.min.js"></script>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#example-navbar-collapse">
            <span class="sr-only">切换导航</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="list.su">项目列表</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="edit.su">新增</a></li>
            <li><a href="#">SVN</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Java <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">jmeter</a></li>
                    <li><a href="#">EJB</a></li>
                    <li><a href="#">Jasper Report</a></li>
                    <li class="divider"></li>
                    <li><a href="#">分离的链接</a></li>
                    <li class="divider"></li>
                    <li><a href="#">另一个分离的链接</a></li>
                </ul>
            </li>
        </ul>
    </div>
    </div>
</nav>

<table class="table">
	<thead>
		<tr>
			<th>序号</th>
			<th>名称</th>
			<th>拥有者</th>
			<th>创建时间</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${projects }" var="project" varStatus="status">
		<tr>
			<td>${status.index }</td>
			<td>${project.name }</td>
			<td>${project.ownerId }</td>
			<td>${project.createTime }</td>
			<td>${project.remark }</td>
			<td><a href="del.su?id=${project.id }">删除</a></td>
		</tr>
		</c:forEach>
	</tbody>
</table>

</body>