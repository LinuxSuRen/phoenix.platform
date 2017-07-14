<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>项目列表</title>
<su:link href="/static/bootstrap-table/bootstrap-table.css"></su:link>
<su:script src="/static/bootstrap-table/bootstrap-table.min.js"></su:script>
<su:script src="/static/autotest/msgTip.js"></su:script>
<script type="text/javascript">
function timeFormatter(data){
	return new Date(data);	
}

function operationEdit(data){
	return '<a href="edit?id=' + data + '" class="glyphicon glyphicon-edit"></a>';
}

function operationDel(data){
	return '<a href="#" data-href="<%=basePath%>/api/projects/' + data + '" data-toggle="modal"' + 
		'data-target="#projectDelDialogId" class="glyphicon glyphicon-trash"></a>';
}

function queryParams(params) {
    return {};
}
</script>
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
        <su:anchor cssClass="navbar-brand" href="/" innerHtml="首页"></su:anchor>
        <su:anchor cssClass="navbar-brand" href="/user_info/logout" innerHtml="退出"></su:anchor>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="edit" data-step="2" data-intro="从这里新增一个项目，之后就可以添加元素定位、数据源等"
            	data-position="right">新增</a></li>
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="项目名称">
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
			</form>
        </ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#" onclick="sysHelp();">帮助</a></li>
		</ul>
    </div>
    </div>
</nav>

<table data-toggle="table"
    data-search="true"
    data-query-params="queryParams"
	data-url="<%=basePath %>/api/projects"
	data-show-refresh="true">
    <thead>
        <tr>
            <th data-field="name">名称</th>
            <th data-field="createTime" data-formatter="timeFormatter">创建时间</th>
            <th data-field="id" data-formatter="operationEdit">编辑</th>
            <th data-field="id" data-formatter="operationDel">删除</th>
        </tr>
    </thead>
</table>

<su:dialog dialogId="projectDelDialogId" ajaxDel="true" callback="window.location.reload();"></su:dialog>

<script type="text/javascript">
function sysHelp(){
	introJs().setOption('done', 'next').start().oncomplete(function(){
	});
}
</script>

</body>