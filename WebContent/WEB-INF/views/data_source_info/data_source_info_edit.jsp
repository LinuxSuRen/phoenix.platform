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
<title>数据源</title>
<link href="<%=basePath %>/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/static/bootstrapValidator/css/bootstrapValidator.css" rel="stylesheet">
</head>
<body>
	<script src="<%=basePath %>/static/jquery/jquery.min.js"></script>
	<script src="<%=basePath %>/static/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath %>/static/bootstrapValidator/js/bootstrapValidator.js"></script>

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
				<a class="navbar-brand" href="edit.su?id=${dataSourceInfo.id }">数据源</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a onclick="fortest()"><span style="cursor:pointer;">保存</span></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">列表 <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<%=basePath %>/project/list.su">项目列表</a></li>
							<li class="divider"></li>
							<li><a href="<%=basePath %>/page_info/list.su?projectId=${dataSourceInfo.projectId}">页面集列表</a></li>
							<li><a href="<%=basePath %>/data_source_info/list.su?projectId=${dataSourceInfo.projectId}">数据源列表</a></li>
							<li><a href="<%=basePath %>/suite_runner_info/list.su?projectId=${dataSourceInfo.projectId}">测试套件列表</a></li>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="搜索">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="download.su?id=${dataSourceInfo.id }">下载</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	
	<ul class="nav nav-tabs" id="engine_tabs">
		<li class="active">
			<a href="#basicInfo" data-toggle="tab">基本信息</a>
		</li>
		<c:forEach items="${dataSourceInfo.dataSources.dataSource}" varStatus="i" var="item" >
		<li>
			<a href="#${item.pageClass }" data-toggle="tab" item-id="${item.pageClass }">${item.pageClass }</a>
		</li>
		</c:forEach>
	</ul>
	<div class="tab-content">
		<div class="tab-pane fade in active" id="basicInfo">
			<form class="form-horizontal" role="form" method="post">
				<div class="form-group">
					<input name="projectId" value="${dataSourceInfo.projectId }" type="hidden" />
					<input name="id" value="${dataSourceInfo.id }" type="hidden" />
					<label class="col-sm-2 control-label">数据源名称</label>
					<div class="col-sm-3">
				    	<input name="name" value="${dataSourceInfo.name }" class="form-control" type="input" required />
					</div>
					<label class="col-sm-2 control-label">包名称</label>
					<div class="col-sm-3">
				    	<input name="dataSources.pagePackage" value="${dataSourceInfo.dataSources.pagePackage }" class="form-control" type="input" required />
					</div>
				</div>
			</form>
		</div>
		
		<c:forEach items="${dataSourceInfo.dataSources.dataSource}" varStatus="i" var="dataSource" >
		<div class="tab-pane fade" id="${dataSource.pageClass }">
			<form class="form-horizontal" role="form" method="post">
			<div class="form-group">
				<label class="col-sm-1 control-label">类名</label>
				<div class="col-sm-2">
					<input name="dataSources.dataSource[${i.index }].pageClass" value="${dataSource.pageClass }"
						class="form-control" type="text" required />
				</div>
				<div class="col-sm-1">
					<a href="delDataSource.su?pageClass=${dataSource.pageClass }" class="form-control">删除</a>
				</div>
			</div>
	
			<c:forEach items="${dataSource.page[0].field}" varStatus="j" var="field" >
			<div class="panel-group" id="accordion">
			    <div class="panel panel-default">
			        <div class="panel-heading">
			            <h4 class="panel-title">
			                <a data-toggle="collapse" data-parent="#accordion" 
			                href="#collapse-${field.name }">${field.name }</a>
			            </h4>
			        </div>
			        <div id="collapse-${field.name }" class="panel-collapse collapse">
			            <div class="panel-body">
							<div class="form-group">
								<label class="col-sm-1 control-label">属性名</label>
								<div class="col-sm-2">
									<input name="dataSources.dataSource[${i.index }].page[0].field[${j.index }].name" value="${field.name }" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">数据</label>
								<div class="col-sm-2">
									<input name="dataSources.dataSource[${i.index }].page[0].field[${j.index }].data" value="${field.data }" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">类型</label>
								<div class="col-sm-2">
									<select name="dataSources.dataSource[${i.index }].page[0].field[${j.index }].type" class="form-control">
										<c:forEach items="${dataType }" var="type">
										<option value="${type.value() }" <c:if test="${field.type.value()==type.value() }">selected="true"</c:if>>${type }</option>
										</c:forEach>
									</select>
								</div>
								<label class="col-sm-1 control-label">字段</label>
								<div class="col-sm-2">
									<select name="dataSources.dataSource[${i.index }].page[0].field[${j.index }].field" class="form-control">
										<c:forEach items="${fieldType }" var="type">
										<option value="${type.value() }" <c:if test="${field.field.value()==type.value() }">selected="true"</c:if>>${type }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-1 control-label">回调接口</label>
								<div class="col-sm-2">
									<input name="dataSources.dataSource[${i.index }].page[0].field[${j.index }].callback" value="${field.callback }" class="form-control" type="text" />
								</div>
							</div>
							
							<c:forEach items="${field.param}" varStatus="x" var="param" >
							<div class="form-group">
								<label class="col-sm-1 control-label">参数名</label>
								<div class="col-sm-2">
									<input name="dataSources.dataSource[${i.index }].page[0].field[${j.index }].param[${x.index}].name" value="${param.name}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">参数值</label>
								<div class="col-sm-2">
									<input name="dataSources.dataSource[${i.index }].page[0].field[${j.index }].param[${x.index}].value" value="${param.value}" class="form-control" type="text" />
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
	
	<script type="text/javascript">
	function fortest(){
		var content = "1=1";
		$('form').each(function(){
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
			$.post('save.su', content, function(){
				location.reload();
			});
		}
	}
	
    $(document).ready(function() {
        $('form').bootstrapValidator();
    });
	</script>
</body>
</html>