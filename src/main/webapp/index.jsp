<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<% response.setHeader("x-frame-options","SAMEORIGIN"); %>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Phoenix自动化测试平台</title>
	<su:link href="/static/bootstrap/css/bootstrap.min.css"></su:link>
	<su:link href="/static/intro/introjs.css"></su:link>
	<su:script src="/static/jquery/jquery.min.js"></su:script>
	<su:script src="/static/bootstrap/js/bootstrap.min.js"></su:script>
	<su:script src="/static/intro/intro.js"></su:script>
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
        <a class="navbar-brand" href="project/list" data-step="1" data-intro="查看项目列表，这里是平台的入口！当您第一次访问页面时，会自动弹出帮助信息，之后可以通过右上角的帮助按钮来触发。" data-position="right">查看项目列表</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="#">留白</a></li>
        </ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">在线用户 <span class="badge"></span></a></li>
			<li data-step="2" data-intro="全局的系统配置" data-position="left"><su:anchor href="/sys/edit.su" innerHtml="系统配置"></su:anchor></li>
			<li data-step="3" data-intro="每个页面都会有帮助按钮，方便对系统不熟悉的童鞋可以尽快上手！" data-position="left">
				<a href="#" onclick="sysHelp();">帮助</a>
			</li>
		</ul>
    </div>
    </div>
</nav>

<iframe src="demo/demo"></iframe>
    
<script src='http://git.oschina.net/arch2surenpi/autotest.platform/widget_preview'></script>

<style>
.pro_name a{color: #4183c4;}
.osc_git_title{background-color: #d8e5f1;}
.osc_git_box{background-color: #fafafa;}
.osc_git_box{border-color: #ddd;}
.osc_git_info{color: #666;}
.osc_git_main a{color: #4183c4;}
</style>

<script type="text/javascript">
function sysHelp(){
	introJs().setOption('done', 'next').start().oncomplete(function(){
	});
}

$(function(){
	$.ajax({
		url : '<%=basePath%>/user_manager/onLineCount.su',
		success : function(count){
			$('a:contains("在线用户")').find('.badge').html(count);
		}
	});

	if(typeof sysHelp == 'function'){
		var pathname = location.pathname;
		if(!localStorage.getItem(pathname)){
			sysHelp();
			localStorage.setItem(pathname, 'http://surenpi.com');
		}
	}
});
</script>

</body>
</html>