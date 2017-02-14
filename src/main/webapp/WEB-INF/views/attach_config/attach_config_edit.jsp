<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>附件配置编辑</title>
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
	        <a class="navbar-brand" href="list.su">列表</a>
	    </div>
    </div>
</nav>

<form class="form-horizontal" action="save.su" role="form" method="post">
	<input type="hidden" name="id" value="${attachConfig.id }" />
	<div class="form-group">
		<label class="col-sm-2 control-label">名称</label>
		<div class="col-sm-3">
	    	<input name="name" value="${attachConfig.name }" class="form-control" type="text">
		</div>
		<label class="col-sm-2 control-label">备注</label>
		<div class="col-sm-3">
	    	<input name="remark" value="${attachConfig.remark }" class="form-control" type="text">
		</div>
	</div>
	
	<button type="submit" class="btn btn-default">保存</button>
</form>

<script type="text/javascript">
</script>

</body>