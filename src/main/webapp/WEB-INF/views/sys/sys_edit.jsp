<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>系统配置</title>
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
	        <span data-step="3" data-intro="项目中允许添加的附件类型配置"
				data-position="down">
	        	<su:anchor cssClass="navbar-brand" href="/attach_config/list.su" innerHtml="附件类型"></su:anchor>
	        </span>
	    </div>
	    <div class="collapse navbar-collapse" id="example-navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" onclick="sysHelp();">帮助</a></li>
			</ul>
	    </div>
    </div>
</nav>

<form class="form-horizontal" role="form" method="post" action="save.su">
	<div class="form-group">
		<label class="col-sm-2 control-label">GIF图片路径</label>
		<div class="col-sm-3" data-step="1" data-intro="自动化UI测试过程录制的GIF图片在服务器上存放的位置"
			data-position="right">
	    	<input name="gifPath" value="${sysConf.gifPath }" class="form-control" type="input" />
		</div>
		<label class="col-sm-2 control-label">密钥</label>
		<div class="col-sm-3" data-step="2" data-intro="用于保存加密数据的密钥"
			data-position="left">
	    	<input name="securtyKey" value="${sysConf.securtyKey }" class="form-control" type="password" />
		</div>
	</div>
	
	<button type="submit" class="btn btn-primary">保存</button>
</form>

<script type="text/javascript">
function sysHelp(){
	introJs().setOption('done', 'next').start().oncomplete(function(){
	});
}
</script>

</body>