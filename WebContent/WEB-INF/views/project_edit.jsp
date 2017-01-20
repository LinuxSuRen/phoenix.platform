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

<form class="form-horizontal" role="form" method="post">
	<input type="hidden" name="id"/>
	<div class="form-group">
		<label class="col-sm-2 control-label">项目名称</label>
		<div class="col-sm-3">
	    	<input name="name" value="" class="form-control" type="text">
		</div>
		<label class="col-sm-2 control-label">备注</label>
		<div class="col-sm-3">
	    	<input name="remark" value="" class="form-control" type="text">
		</div>
	</div>
	
	<button type="submit" class="btn btn-default">保存</button>
</form>

</body>