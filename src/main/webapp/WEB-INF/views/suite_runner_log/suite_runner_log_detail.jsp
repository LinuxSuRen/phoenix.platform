<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>日志详情</title>
</head>
<body>

<form class="form-horizontal" role="form" method="post">
	<div class="form-group">
		<label class="col-sm-2 control-label" for="beginTime">开始</label>
		<div class="col-sm-3">
			<input id="beginTime" class="form-control" value="${log.beginTime }" readonly="readonly"></input>
		</div>
		<label class="col-sm-2 control-label" for="endTime">结束</label>
		<div class="col-sm-3">
			<input id="endTime" class="form-control" value="${log.endTime }" readonly="readonly"></input>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label" for="message">消息</label>
		<div class="col-sm-8">
			<textarea id="message" class="form-control" rows="20" readonly="readonly">${log.message }</textarea>
		</div>
	</div>
</form>

</body>