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
<su:script src="/static/autotest/msgTip.js"></su:script>
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
        <a class="navbar-brand" href="list">项目列表</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
		<c:if test="${!empty project.id }">
        <ul class="nav navbar-nav">
            <li>
            	<a href="<%=basePath%>/page_info/table?projectId=${project.id}" data-step="3" data-intro="查看、添加元素定位信息"
            		data-position="right">查看页面集列表 <span class="badge"></span></a>
            </li>
            <li>
            	<a href="<%=basePath%>/data_source_info/list?projectId=${project.id}" data-step="4" data-intro="查看、添加数据源信息"
            		data-position="right">查看数据源列表 <span class="badge"></span></a>
            </li>
            <li>
            	<a href="<%=basePath%>/suite_runner_info/list?projectId=${project.id}" data-step="5" data-intro="查看、添加测试套件信息，这里还可以对套件进行调试，查看套件的运行日志"
            		data-position="right">查看运行套件列表 <span class="badge"></span></a>
            </li>
        </ul>
        </c:if>
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${!empty project.id }">
			<li><a data-toggle="modal" data-target="#attachUploadDialogId" href="#">添加附件</a></li>
			<li><a data-toggle="modal" data-target="#projectUploadDialogId" href="#">导入</a></li>
			<li><a href="#" onclick="deploy()" data-step="6" data-intro="这个操作会把当前项目中的元素定位、数据源、测试套件以及附件等都拷贝、编译到指定目录下，为测试套件的运行做好准备！"
            		data-position="left">部署</a></li>
        	</c:if>
			<li><a href="#" onclick="sysHelp();">帮助</a></li>
		</ul>
    </div>
    </div>
</nav>

<ul class="nav nav-tabs" id="project_tabs">
	<li class="active">
		<a href="#project_basic" data-toggle="tab">基本信息</a>
	</li>
	<li>
		<a href="#project_attach" data-toggle="tab">附件 <span class="badge"></span></a>
	</li>
</ul>

<div class="tab-content">
	<div class="tab-pane fade in active" id="project_basic">
		<form class="form-horizontal" role="form" method="post" id="projectForm">
			<input type="hidden" name="id" value="${project.id }" />
			<input type="hidden" name="ownerId" value="${project.ownerId }" />
			<div class="form-group">
				<label class="col-sm-2 control-label">项目名称</label>
				<div class="col-sm-3" data-step="1" data-intro="项目名称，不能重复哦"
            		data-position="right">
			    	<input name="name" value="${project.name }" class="form-control" type="text" required="required" />
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
					<button type="submit" class="btn btn-primary" onclick="projectSave();return false;">保存</button>
				</span>
			</div>
		</form>
	</div>
	<div class="tab-pane fade" id="project_attach">
		<table class="table">
			<thead>
				<tr>
					<th>序号</th>
					<th>名称</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${project.attachList}" varStatus="i" var="attach" >
				<tr>
					<td>${status.index+1 }</td>
					<td>${attach.fileName }</td>
					<td>${attach.remark }</td>
					<td>
						<a href="#" data-href="${pageContext.request.contextPath }/attachment/del?id=${attach.id }" data-toggle="modal" data-target="#attachDelDialogId">删除</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<su:dialog dialogId="attachDelDialogId" ajaxDel="true"></su:dialog>

<div class="modal fade" id="attachUploadDialogId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form class="form-horizontal" role="form" action="<%=basePath%>/attachment/upload.su" method="post" enctype="multipart/form-data">
			<input type="hidden" name="belongId" value="${project.id }" />
			<input type="hidden" name="redirectPath" value="/project/edit?id=${project.id }" />
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					添加附件
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label class="col-sm-2 control-label">附件名称</label>
					<div class="col-sm-5">
				    	<input name="fileName" type="text" placeholder="Java类文件名，例如：Test.java" required="required" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">文件</label>
					<div class="col-sm-5">
				    	<input name="file" type="file" accept="text/java" required="required" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-5">
				    	<input name="remark" type="text" placeholder="Java类包（package）" />
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="submit" class="btn btn-primary">上传</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
	
<div class="modal fade" id="projectUploadDialogId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="import?id=${project.id }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					项目压缩包(.zip)导入
				</h4>
			</div>
			<div class="modal-body">
				<input name="file" type="file" accept="application/x-zip-compressed" required="required" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="submit" class="btn btn-primary">导入</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<script type="text/javascript">
function deploy(){
	$.post('deploy?id=${project.id }', function(){
		tip('部署成功！');
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

function projectSave(){
	var data = $('#projectForm').serialize();
	$.ajax({
		url: '<%=basePath%>/api/projects',
		type: 'POST',
		data: data,
		success: function(){
			window.location = '<%=basePath%>/project/list';
		},
		error: function(){
			tip('保存失败！');
		}
	});
	
	return false;
}

$(function(){
	if('${project.id}' != ''){
		updateBadge('<%=basePath%>/page_info/count?projectId=${project.id}', '查看页面集列表');
		updateBadge('<%=basePath%>/data_source_info/count?projectId=${project.id}', '查看数据源列表');
		updateBadge('<%=basePath%>/suite_runner_info/count?projectId=${project.id}', '查看运行套件列表');
		updateBadge('<%=basePath%>/attachment/count?belongId=${project.id}', '附件');
	}
});

function sysHelp(){
	introJs().setOption('done', 'next').start().oncomplete(function(){
	});
}
</script>

</body>