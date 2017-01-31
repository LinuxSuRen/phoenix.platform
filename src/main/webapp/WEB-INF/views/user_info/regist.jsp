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
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
<title>注册</title>
<su:link href="/static/bootstrap/css/bootstrap.min.css"></su:link>
<su:script src="/static/jquery/jquery.min.js"></su:script>
<su:script src="/static/bootstrap/js/bootstrap.min.js"></su:script>
</head>

<body>
<form class="form-horizontal" action="registProcess.su" role="form" method="post">
	<div class="form-group">
		<label class="col-sm-4 control-label">登录名</label>
		<div class="col-sm-2">
	    	<input name="loginName" class="form-control" type="text"
	    		required>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">昵称</label>
		<div class="col-sm-2">
	    	<input name="nickName" class="form-control" type="text">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">密码</label>
		<div class="col-sm-2">
	    	<input name="password" class="form-control" type="password"
	    		required>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">E-mail</label>
		<div class="col-sm-2">
	    	<input name="email" class="form-control" type="email"
	    		required>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-4 control-label"></label>
		<div class="col-sm-2">
			<button type="submit" class="btn btn-primary btn-block">注册</button>
		</div>
	</div>
</form>
</body>
</html>