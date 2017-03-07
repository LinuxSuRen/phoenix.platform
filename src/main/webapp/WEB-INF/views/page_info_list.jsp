<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>页面集列表</title>
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
        <a class="navbar-brand" href="<%=basePath %>/page_info/list.su?projectId=${projectId }">页面集列表</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="add.su?projectId=${projectId }">新增</a></li>
			<li>
				<a data-toggle="modal" data-target="#autotestUploadDialogId" href="#" data-step="1" data-intro="导入.xml格式的元素定位"
            		data-position="right">导入</a>
			</li>
        </ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="<%=basePath%>/project/edit.su?id=${projectId}">当前项目</a></li>
			<li><a href="<%=basePath%>/project/list.su">项目列表</a></li>
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
			<th>项目</th>
			<th>创建时间</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfoList }" var="pageInfo" varStatus="status">
		<tr>
			<td>${status.index+1 }</td>
			<td><a href="test.su?id=${pageInfo.id }">${pageInfo.name }</a></td>
			<td>${pageInfo.projectId }</td>
			<td>${pageInfo.createTime }</td>
			<td>${pageInfo.remark }</td>
			<td>
				<a href="#" data-href="del.su?id=${pageInfo.id }" data-toggle="modal"
					data-target="#pageInfoDelDialogId" class="glyphicon glyphicon-trash"></a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
	
<div class="modal fade" id="autotestUploadDialogId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="import.su?projectId=${projectId }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					元素定位信息导入
				</h4>
			</div>
			<div class="modal-body">
				<input name="file" type="file" accept="text/xml" required="required" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="submit" class="btn btn-primary">导入</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<su:dialog dialogId="pageInfoDelDialogId"></su:dialog>

<script type="text/javascript">
function sysHelp(){
	introJs().setOption('done', 'next').start().oncomplete(function(){
	});
}
</script>

</body>