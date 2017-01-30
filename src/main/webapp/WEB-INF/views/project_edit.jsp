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
<title>项目编辑</title>
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
            <li><a href="<%=basePath%>/page_info/list.su?projectId=${project.id}">查看页面集列表</a></li>
            <li><a href="<%=basePath%>/data_source_info/list.su?projectId=${project.id}">查看数据源列表</a></li>
            <li><a href="<%=basePath%>/suite_runner_info/list.su?projectId=${project.id}">查看运行套件列表</a></li>
        </ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#" onclick="deploy()">部署</a></li>
		</ul>
    </div>
    </div>
</nav>

<div class="alert alert-success alert-dismissable hide fade" id="deployTipId">
<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>部署成功!
</div>

<form class="form-horizontal" action="save.su" role="form" method="post">
	<input type="hidden" name="id" value="${project.id }" />
	<input type="hidden" name="ownerId" value="${project.ownerId }" />
	<div class="form-group">
		<label class="col-sm-2 control-label">项目名称</label>
		<div class="col-sm-3">
	    	<input name="name" value="${project.name }" class="form-control" type="text">
		</div>
		<label class="col-sm-2 control-label">备注</label>
		<div class="col-sm-3">
	    	<input name="remark" value="${project.remark }" class="form-control" type="text">
		</div>
	</div>
	
	<button type="submit" class="btn btn-default">保存</button>
</form>

<script type="text/javascript">
function deploy(){
	$.post('deploy.su?id=${project.id }', function(){
		$('#deployTipId').removeClass('hide fade');
		window.setTimeout(function(){
			$('#deployTipId').addClass('hide fade');
		}, 1000);
	});
}
</script>

</body>