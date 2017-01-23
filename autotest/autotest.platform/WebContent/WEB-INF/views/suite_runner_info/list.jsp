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
<title>测试套件列表</title>
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
        <a class="navbar-brand" href="<%=basePath %>/suite_runner_info/list.su?projectId=${projectId }">测试套件列表</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="add.su?projectId=${projectId }">新增</a></li>
			<li>
				<a data-toggle="modal" data-target="#suiteRunnerUploadDialogId" href="#">导入</a>
			</li>
        </ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="<%=basePath%>/project/edit.su?id=${projectId}">当前项目</a></li>
			<li><a href="<%=basePath%>/project/list.su">项目列表</a></li>
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
		<c:forEach items="${suiteRunnerInfoList }" var="item" varStatus="status">
		<tr>
			<td>${status.index+1 }</td>
			<td>${item.name }</td>
			<td>${item.name }</td>
			<td>${item.name }</td>
			<td>${item.name }</td>
			<td>
				<a href="run.su?id=${item.id }">运行</a>
				<a href="edit.su?id=${item.id }">编辑</a>
				<a href="del.su?id=${item.id }">删除</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
	
<div class="modal fade" id="suiteRunnerUploadDialogId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="import.su?projectId=${projectId }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					测试套件信息导入
				</h4>
			</div>
			<div class="modal-body">
				<input name="file" type="file" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="submit" class="btn btn-primary">导入</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

</body>