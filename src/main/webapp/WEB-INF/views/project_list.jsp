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
<script src="<%=basePath %>/static/jquery/jquery.min.js"></script>
<script src="<%=basePath %>/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

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
        <a class="navbar-brand" href="list.su">刷新</a>
        <a class="navbar-brand" href="<%=basePath %>/user_info/logout.su">退出</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="edit.su">新增</a></li>
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="项目名称">
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
			</form>
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
			<td>${status.index+1 }</td>
			<td>${project.name }</td>
			<td>${project.ownerId }</td>
			<td>${project.createTime }</td>
			<td>${project.remark }</td>
			<td>
				<a href="edit.su?id=${project.id }">编辑</a>
				<a href="del.su?id=${project.id }">删除</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

</body>