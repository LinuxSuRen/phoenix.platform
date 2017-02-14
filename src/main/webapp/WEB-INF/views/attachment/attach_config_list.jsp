<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>附件配置列表</title>
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
        <a class="navbar-brand" href="list.su" data-step="1" data-intro="刷新当前页面" data-position="right">刷新</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="edit.su" data-step="2" data-intro="从这里新增一个项目，之后就可以添加元素定位、数据源等" data-position="right">新增</a></li>
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
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${attachConfigList }" var="attachConfig" varStatus="status">
		<tr>
			<td>${status.index+1 }</td>
			<td>${attachConfig.name }</td>
			<td>${attachConfig.remark }</td>
			<td>
				<a href="edit.su?id=${attachConfig.id }">编辑</a>
				<a href="#" data-href="del.su?id=${attachConfig.id }" data-toggle="modal" data-target="#attachConfigDelDialogId">删除</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<su:dialog dialogId="attachConfigDelDialogId"></su:dialog>

<script type="text/javascript">
function sysHelp(){
	introJs().setOption('done', 'next').start().oncomplete(function(){
	});
}
</script>

</body>