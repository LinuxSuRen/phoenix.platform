<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>测试套件列表</title>
<su:script src="/static/autotest/suiteDebug.js"></su:script>
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
        <a class="navbar-brand" href="<%=basePath %>/suite_runner_info/list.su?projectId=${projectId }">测试套件列表</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="add.su?projectId=${projectId }">新增</a></li>
			<li>
				<a data-toggle="modal" data-target="#suiteRunnerUploadDialogId" href="#" data-step="1" data-intro="把.xml格式的测试套件导入"
            	data-position="right">导入</a>
			</li>
        </ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="<%=basePath%>/project/edit.su?id=${projectId}">当前项目</a></li>
			<li><a href="<%=basePath%>/project/list.su">项目列表</a></li>
			<li><a href="#" onclick="sysHelp();">帮助</a></li>
		</ul>
    </div>
    </div>
</nav>

<table class="table">
	<thead>
		<tr>
			<th>序号</th>
			<th>名称</th>
			<th>项目</th>
			<th>创建时间</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${suiteRunnerInfoList }" var="item" varStatus="status">
		<tr>
			<td>${status.index+1 }</td>
			<td><a href="edit.su?id=${item.id }">${item.name }</a></td>
			<td>${item.projectId }</td>
			<td>${item.createTime }</td>
			<td>${item.remark }</td>
			<td>
				<a href="run.su?id=${item.id }">运行</a>
				<a href="#" data-href="${item.id }" data-toggle="modal" data-target="#debugRunDialogId">调试</a>
				<a href="<%=basePath %>/suite_runner_log/list.su?runnerId=${item.id }">日志</a>
				<a href="#" data-href="del.su?id=${item.id }" data-toggle="modal"
					data-target="#suiteRunnerInfoDelDialogId" class="glyphicon glyphicon-trash"></a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<su:dialog dialogId="suiteRunnerInfoDelDialogId"></su:dialog>
	
<div class="modal fade" id="suiteRunnerUploadDialogId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="import.su?projectId=${projectId }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					测试套件信息导入
				</h4>
			</div>
			<div class="modal-body">
				<input name="file" type="file" accept="text/xml" required="required" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="submit" class="btn btn-primary">导入</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
	
<div class="modal fade" id="runInfoDialogId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					信息
				</h4>
			</div>
			<div class="modal-body">
				<div id="messageBody"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
	
<div class="modal fade" id="debugRunDialogId" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					调试设置
				</h4>
			</div>
			<form class="form-horizontal" role="form" method="post" action="<%=basePath%>/suite_runner_info/run.su">
				<input name="id" type="hidden" />
				<input name="deployUrl" type="hidden" value="<%=basePath %>/project/deploy.su?id=${projectId }" />
				<input name="progressUrl" type="hidden" value="<%=basePath %>/progress/info.su" />
				<input name="progress_key" type="hidden" />
				<div class="modal-body">
					<div class="form-group">
						<label class="col-sm-2 control-label">运行次数</label>
						<div class="col-sm-3">
					    	<input name="normalTimes" class="form-control" type="number" value="1"
					    		required="required" min="1" max="100" />
						</div>
						<label class="col-sm-3 control-label">当前</label>
						<div class="col-sm-2">
					    	<input name="currentIndex" class="form-control" type="number" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">并发数</label>
						<div class="col-sm-3">
					    	<input name="concurrentTimes" class="form-control" type="number" value="1"
					    		required="required" min="1" max="6"/>
						</div>
						<label class="col-sm-3 control-label">失败后继续</label>
						<div class="col-sm-2">
					    	<input name="retryTimes" class="form-control" type="number" value="0"
					    		required="required" min="0" max="100"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">成功数</label>
						<div class="col-sm-3">
					    	<input name="successTimes" class="form-control" type="number" value="0"
					    		 readonly="readonly" />
						</div>
						<label class="col-sm-3 control-label">失败数</label>
						<div class="col-sm-2">
					    	<input name="failureTimes" class="form-control" type="number" value="0"
					    		 readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">备注</label>
						<div class="col-sm-8">
					    	<input name="remark" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">进度信息</label>
						<div class="col-sm-8">
							<div class="progress progress-striped active" style="height: 34px;">
								<div class="progress-bar progress-bar-success" role="progressbar"
	         aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
	         style="width: 100%;">
						    	<p class="form-control-static" id="progress_bar"></p>
							</div>
						
					    	</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary btn-ok">运行</button>
					<button type="button" class="btn btn-default btn-close" data-dismiss="modal">关闭</button>
				</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<script type="text/javascript">
function sysHelp(){
	introJs().setOption('done', 'next').start().oncomplete(function(){
	});
}
</script>

</body>