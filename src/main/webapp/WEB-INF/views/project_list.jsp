<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<!DOCTYPE html>
<html lang="zh-cn" manifest="project.manifest">
<head>
<title>项目列表</title>
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
        <a class="navbar-brand" href="list.su" data-step="1" data-intro="刷新当前页面" data-position="right">刷新</a>
        <su:anchor cssClass="navbar-brand" href="/user_info/logout.su" innerHtml="退出"></su:anchor>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="edit.su" data-step="2" data-intro="从这里新增一个项目，之后就可以添加元素定位、数据源等"
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
			<td><a href="edit.su?id=${project.id }">${project.name }</a></td>
			<td>${project.ownerId }</td>
			<td>${project.createTime }</td>
			<td>${project.remark }</td>
			<td>
				<a href="#" data-href="del.su?id=${project.id }" data-toggle="modal"
					data-target="#projectDelDialogId" class="glyphicon glyphicon-trash"></a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<su:dialog dialogId="projectDelDialogId"></su:dialog>

<script type="text/javascript">
function sysHelp(){
	introJs().setOption('done', 'next').start().oncomplete(function(){
	});
}
</script>

</body>