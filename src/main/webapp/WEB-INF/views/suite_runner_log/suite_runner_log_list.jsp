<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>日志列表</title>
</head>
<body>

<table class="table">
	<thead>
		<tr>
			<th>序号</th>
			<th>消息</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${logList }" var="log" varStatus="status">
		<tr>
			<td>${status.index+1 }</td>
			<td>${log.message }</td>
			<td>${log.beginTime }</td>
			<td>${log.endTime }</td>
			<td>
				<a href="detail.su?id=${log.id }" data-toggle="modal" data-target="#logDetailId">详情</a>
				<a href="#" data-href="del.su?id=${log.id }" data-toggle="modal" data-target="#logDelDialogId">删除</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<su:dialog dialogId="logDelDialogId"></su:dialog>

<div class="modal fade" id="logDetailId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	$("#logDetailId").on("hidden.bs.modal", function(){
		$(this).removeData("bs.modal");
	})
});
</script>

</body>