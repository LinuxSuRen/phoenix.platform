<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>测试套件</title>
<link href="<%=basePath %>/static/bootstrapValidator/css/bootstrapValidator.css" rel="stylesheet">
<script src="<%=basePath %>/static/bootstrapValidator/js/bootstrapValidator.js"></script>
<su:script src="/static/autotest/suiteDebug.js"></su:script>
<su:script src="/static/autotest/msgTip.js"></su:script>
</head>

<body>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<c:if test="${!empty suiteRunnerInfo.id }">
			<a class="navbar-brand" href="edit.su?id=${suiteRunnerInfo.id }">测试套件</a>
			</c:if>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a onclick="fortest()"><span style="cursor:pointer;">保存</span></a></li>
				<c:if test="${!empty suiteRunnerInfo.id }">
				<li><a href="addPage.su?id=${suiteRunnerInfo.id}">新增测试</a></li>
				</c:if>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">列表 <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<%=basePath %>/project/edit.su?id=${suiteRunnerInfo.projectId}">当前项目</a></li>
						<li><a href="<%=basePath %>/project/list.su">项目列表</a></li>
						<li class="divider"></li>
						<li><a href="<%=basePath %>/page_info/list.su?projectId=${suiteRunnerInfo.projectId}">页面集列表</a></li>
						<li><a href="<%=basePath %>/data_source_info/list.su?projectId=${suiteRunnerInfo.projectId}">数据源列表</a></li>
						<li><a href="<%=basePath %>/suite_runner_info/list.su?projectId=${suiteRunnerInfo.projectId}">测试套件列表</a></li>
					</ul>
				</li>
			</ul>
			<c:if test="${!empty suiteRunnerInfo.id }">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" data-href="${suiteRunnerInfo.id }" data-toggle="modal"
					data-target="#debugRunDialogId">调试</a></li>
				<li><a href="download.su?id=${suiteRunnerInfo.id }">下载</a></li>
			</ul>
			</c:if>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>

<ul class="nav nav-tabs" id="engine_tabs">
	<li class="active">
		<a href="#basicInfo" data-toggle="tab">基本信息</a>
	</li>
	<c:forEach items="${suiteRunnerInfo.suite.page}" varStatus="i" var="item" >
	<li>
		<a href="#${item.clazz }" data-toggle="tab" item-id="${item.clazz }">${item.clazz }</a>
	</li>
	</c:forEach>
</ul>
<div class="tab-content">
	<!-- 基本信息 -->
	<div class="tab-pane fade in active" id="basicInfo">
		<form class="form-horizontal" role="form" method="post">
			<div class="form-group">
				<input name="projectId" value="${suiteRunnerInfo.projectId }" type="hidden" />
				<input name="id" value="${suiteRunnerInfo.id }" type="hidden" />
				<label class="col-sm-2 control-label">测试套件名称</label>
				<div class="col-sm-3">
			    	<input name="name" value="${suiteRunnerInfo.name }" class="form-control" type="input" required />
				</div>
				<label class="col-sm-2 control-label">包名称</label>
				<div class="col-sm-3">
			    	<input name="suite.pagePackage" value="${suiteRunnerInfo.suite.pagePackage }" class="form-control" type="input" required />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">元素定位</label>
				<div class="col-sm-3">
			    	<input name="suite.pageConfig" value="${suiteRunnerInfo.suite.pageConfig }" class="form-control"
			    		type="input" required title="英文逗号（,）分割" />
				</div>
				<label class="col-sm-2 control-label">运行结束后休眠（毫秒）</label>
				<div class="col-sm-3">
			    	<input name="suite.afterSleep" value="${suiteRunnerInfo.suite.afterSleep }" class="form-control"
			    		type="number" min="0" step="100" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">备注</label>
				<div class="col-sm-3">
			    	<input name="remark" value="${suiteRunnerInfo.remark }" class="form-control"
			    		type="input" />
				</div>
			</div>
		</form>
	</div>
	
	<c:forEach items="${suiteRunnerInfo.suite.page}" varStatus="i" var="page" >
	<div class="tab-pane fade" id="${page.clazz }">
		<form class="form-horizontal" role="form" method="post">
		<div class="form-group">
			<label class="col-sm-1 control-label">类名</label>
			<div class="col-sm-2">
				<input name="suite.page[${i.index }].clazz" value="${page.clazz }"
					class="form-control" type="text" required />
			</div>
			<div class="col-sm-1">
				<a href="del.su?clazz=${page.clazz }" class="form-control">删除</a>
			</div>
		</div>

		<c:forEach items="${page.actions.action}" varStatus="j" var="action" >
		<div class="panel-group" id="accordion">
		    <div class="panel panel-default">
		        <div class="panel-heading" <c:if test="${action.disable==true }">style="background-color: #faebcc;"</c:if>>
		            <h4 class="panel-title">
		                <a data-toggle="collapse" data-parent="#accordion" 
		                href="#collapse-${i.index }-${action.field }">${action.field }</a>
		            </h4>
		        </div>
		        <div id="collapse-${i.index }-${action.field }" class="panel-collapse collapse">
		            <div class="panel-body">
						<div class="form-group">
							<label class="col-sm-1 control-label">属性名</label>
							<div class="col-sm-2">
								<input name="suite.page[${i.index }].actions.action[${j.index }].field" value="${action.field }" class="form-control" type="text" />
							</div>
							<label class="col-sm-1 control-label">动作</label>
							<div class="col-sm-2">
								<select name="suite.page[${i.index }].actions.action[${j.index }].name" class="form-control">
									<c:forEach items="${actionType }" var="type">
									<option value="${type.value() }" <c:if test="${action.name.value()==type.value() }">selected="true"</c:if>>${type }</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-sm-1 control-label">外部接口</label>
							<div class="col-sm-2">
								<input name="suite.page[${i.index }].actions.action[${j.index }].invoker"
									value="${action.invoker }" class="form-control" type="text" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-1 control-label">动作前休眠时间（毫秒）</label>
							<div class="col-sm-2">
								<input name="suite.page[${i.index }].actions.action[${j.index }].beforeSleep"
									value="${action.beforeSleep }" class="form-control" type="number" />
							</div>
							<label class="col-sm-1 control-label">动作后休眠时间（毫秒）</label>
							<div class="col-sm-2">
								<input name="suite.page[${i.index }].actions.action[${j.index }].afterSleep"
									value="${action.afterSleep }" class="form-control" type="number" />
							</div>
							<label class="col-sm-1 control-label">禁用</label>
							<div class="col-sm-1">
								<input name="suite.page[${i.index }].actions.action[${j.index }].disable"
									value="true" class="form-control" type="radio" <c:if test="${action.disable==true }">checked="checked"</c:if>/>
							</div>
							<label class="col-sm-1 control-label">启用</label>
							<div class="col-sm-1">
								<input name="suite.page[${i.index }].actions.action[${j.index }].disable"
									value="false" class="form-control" type="radio" <c:if test="${action.disable!=true }">checked="checked"</c:if>/>
							</div>
						</div>
						
						<c:forEach items="${action.param}" varStatus="x" var="para" >
						<div class="form-group">
							<label class="col-sm-1 control-label">参数值</label>
							<div class="col-sm-2">
								<input name="suite.page[${i.index }].actions.action[${j.index }].param[${x.index}].value"
									value="${para.value}" class="form-control" type="text" />
							</div>
						</div>
						</c:forEach>
		            </div>
		        </div><!-- field end -->
		    </div>
		    </div>
		    </c:forEach><!-- field loop end -->
		    </form>
		</div><!-- tab pane end -->
	</c:forEach>
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
						<label class="col-sm-2 control-label">备注</label>
						<div class="col-sm-6">
					    	<input name="remark" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">进度信息</label>
						<div class="col-sm-6">
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
function fortest(){
	var tabIndex = $('#engine_tabs .active').index()
	var content = "tabIndex=" + tabIndex;
	$('input').each(function(){
		$(this).val($(this).val().trim());
	});
	$('form').not('[action]').each(function(){
		var ser = $(this).serialize();
		var bv = $(this).data('bootstrapValidator');
		bv.validate();
		if(!bv.isValid()){
			content = "";
			return false;
		}
		
		if(ser != ""){
			content += ("&" + ser);
		}
	});
	
	if(content != ""){
		$.post('save.su', content, function(data){
			if(data.id){
				tip('保存成功！', function(){
					window.location = 'edit.su?id=' + data.id + '&tabIndex=' + data.tabIndex;
				});
			}
		});
	}
}

$(document).ready(function() {
    $('form').bootstrapValidator();
    
	$('#engine_tabs li:eq(${suiteRunnerInfo.tabIndex}) a').tab('show');
});
</script>
</body>
</html>