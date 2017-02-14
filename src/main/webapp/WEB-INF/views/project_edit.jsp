<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>项目编辑</title>
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
        <a class="navbar-brand" href="list.su">项目列表</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
            <li>
            	<a href="<%=basePath%>/page_info/list.su?projectId=${project.id}" data-step="3" data-intro="查看、添加元素定位信息"
            		data-position="right">查看页面集列表 <span class="badge"></span></a>
            </li>
            <li>
            	<a href="<%=basePath%>/data_source_info/list.su?projectId=${project.id}" data-step="4" data-intro="查看、添加数据源信息"
            		data-position="right">查看数据源列表 <span class="badge"></span></a>
            </li>
            <li>
            	<a href="<%=basePath%>/suite_runner_info/list.su?projectId=${project.id}" data-step="5" data-intro="查看、添加测试套件信息，这里还可以对套件进行调试，查看套件的运行日志"
            		data-position="right">查看运行套件列表 <span class="badge"></span></a>
            </li>
        </ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#" onclick="deploy()" data-step="6" data-intro="这个操作会把当前项目中的元素定位、数据源、测试套件以及附件等都拷贝、编译到指定目录下，为测试套件的运行做好准备！"
            		data-position="left">部署</a></li>
			<li><a href="#" onclick="sysHelp();">帮助</a></li>
		</ul>
    </div>
    </div>
</nav>

<div class="alert alert-success alert-dismissable hide fade" id="deployTipId">
<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>部署成功!
</div>

<ul class="nav nav-tabs" id="project_tabs">
	<li class="active">
		<a href="#project_basic" data-toggle="tab">基本信息</a>
	</li>
</ul>

<div class="tab-content">
	<div class="tab-pane fade in active" id="project_basic">
		<form class="form-horizontal" action="save.su" role="form" method="post">
			<input type="hidden" name="id" value="${project.id }" />
			<input type="hidden" name="ownerId" value="${project.ownerId }" />
			<div class="form-group">
				<label class="col-sm-2 control-label">项目名称</label>
				<div class="col-sm-3" data-step="1" data-intro="项目名称，不能重复哦"
            		data-position="right">
			    	<input name="name" value="${project.name }" class="form-control" type="text">
				</div>
				<label class="col-sm-2 control-label">备注</label>
				<div class="col-sm-3">
			    	<input name="remark" value="${project.remark }" class="form-control" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Java包名</label>
				<div class="col-sm-3" data-step="2" data-intro="如果还不知道Java的包（package）是什么的话（例如：org.suren.demo，通常是小写字母，英文句号分割），抓紧时间补补课吧！"
            		data-position="right">
			    	<input name="pkgName" value="${project.pkgName }" class="form-control" type="text" />
				</div>
				<label class="col-sm-2 control-label">创建时间</label>
				<div class="col-sm-3">
			    	<input value="${project.createTime }" class="form-control" type="text" readonly="readonly" />
				</div>
			</div>
			
			<div class="form-group">
				<span class="col-sm-12" style="text-align: center;">
					<button type="submit" class="btn btn-primary" >保存</button>
				</span>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
function deploy(){
	$.post('deploy.su?id=${project.id }', function(){
		$('#deployTipId').removeClass('hide fade');
		window.setTimeout(function(){
			$('#deployTipId').addClass('hide fade');
		}, 1000);
	});
}

function updateBadge(url, text){
	$.ajax({
		url : url,
		dataType : 'json',
		success : function(count){
			$('a:contains("' + text + '")').find('.badge').html(count);
		}
	});
}

$(function(){
	updateBadge('<%=basePath%>/page_info/count.su?projectId=${project.id}', '查看页面集列表');
	updateBadge('<%=basePath%>/data_source_info/count.su?projectId=${project.id}', '查看数据源列表');
	updateBadge('<%=basePath%>/suite_runner_info/count.su?projectId=${project.id}', '查看运行套件列表');
});

function sysHelp(){
	introJs().setOption('done', 'next').start().oncomplete(function(){
	});
}
</script>

</body>