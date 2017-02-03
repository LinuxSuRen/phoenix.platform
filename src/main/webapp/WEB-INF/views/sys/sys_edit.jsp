<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>系统配置</title>
<su:link href="/static/bootstrap/css/bootstrap.min.css"></su:link>
<su:script src="/static/jquery/jquery.min.js"></su:script>
<su:script src="/static/bootstrap/js/bootstrap.min.js"></su:script>
</head>
<body>

<form class="form-horizontal" role="form" method="post" action="save.su">
	<div class="form-group">
		<label class="col-sm-2 control-label">Gif图片路径</label>
		<div class="col-sm-3">
	    	<input name="gifPath" value="${sysConf.gifPath }" class="form-control" type="input" />
		</div>
		<label class="col-sm-2 control-label">密钥</label>
		<div class="col-sm-3">
	    	<input name="securtyKey" value="${sysConf.securtyKey }" class="form-control" type="password" />
		</div>
	</div>
	
	<button type="submit" class="btn btn-primary">保存</button>
</form>

</body>