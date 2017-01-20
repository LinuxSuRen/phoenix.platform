<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap 101 Template</title>
<link href="<%=basePath %>/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<script src="<%=basePath %>/static/jquery/jquery.min.js"></script>
	<script src="<%=basePath %>/static/bootstrap/js/bootstrap.min.js"></script>

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
				<a class="navbar-brand" href="test.su">页面集</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a onclick="fortest()"><span style="cursor:pointer;">保存</span></a></li>
					<li><a href="addPage.su">新增页面</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li class="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="搜索">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Link</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li><a href="#">Separated link</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	
	<ul class="nav nav-tabs" id="engine_tabs">
		<li class="active">
			<a href="#engine" data-toggle="tab">Engine</a>
		</li>
		<c:forEach items="${autotest.pages.page}" varStatus="i" var="item" >
		<li>
			<a href="#${item.clazz }" data-toggle="tab" item-id="${item.clazz }">${item.clazz }</a>
		</li>
		</c:forEach>
	</ul>
	<div class="tab-content">
		<div class="tab-pane fade in active" id="engine">
			<form class="form-horizontal" role="form" method="post">
				<div class="form-group">
					<label class="col-sm-2 control-label">Browser</label>
					<div class="col-sm-3">
						<select name="engine.driver" class="form-control">
							<c:forEach items="${engineType }" var="type">
							<option value="${type.value() }" <c:if test="${autotest.engine.driver.value()==type.value() }">selected="true"</c:if>>${type }</option>
							</c:forEach>
						</select>
					</div>
					<label class="col-sm-2 control-label">Maxizie</label>
					<div class="col-sm-3">
				    	<input class="form-control" type="checkbox" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Width</label>
					<div class="col-sm-3">
				    	<input name="engine.width" value="${autotest.engine.width }" class="form-control" id="focusedInput" type="text" value="">
					</div>
					<label class="col-sm-2 control-label">Height</label>
					<div class="col-sm-3">
				    	<input name="engine.height" value="${autotest.engine.height }" class="form-control" id="focusedInput" type="text" value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">包名</label>
					<div class="col-sm-3">
				    	<input name="pages.pagePackage" value="${autotest.pages.pagePackage }" class="form-control" id="focusedInput" type="text">
					</div>
					<label class="col-sm-2 control-label">远程地址</label>
					<div class="col-sm-3">
				    	<input name="engine.remote" value="${autotest.engine.remote }" class="form-control" id="focusedInput" type="text">
					</div>
				</div>
			</form>
		</div>
			
		<c:forEach items="${autotest.pages.page}" varStatus="i" var="page" >
		<div class="tab-pane fade" id="${page.clazz }">
			<form class="form-horizontal" role="form" method="post">
			<div class="form-group">
				<label class="col-sm-1 control-label">类名</label>
				<div class="col-sm-2">
					<input name="pages.page[${i.index }].clazz" value="${page.clazz }" class="form-control" type="text" />
				</div>
				<label class="col-sm-1 control-label">URL</label>
				<div class="col-sm-6">
					<input name="pages.page[${i.index }].url" value="${page.url }" class="form-control" type="text" />
				</div>
				<div class="col-sm-1">
					<a href="delPage.su?pageName=${page.clazz }" class="form-control">删除</a>
				</div>
			</div>
	
			<c:forEach items="${page.field}" varStatus="j" var="field" >
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
									<input name="pages.page[${i.index }].field[${j.index }].name" value="${field.name }" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">类型</label>
								<div class="col-sm-2">
									<select name="pages.page[${i.index }].field[${j.index }].type" class="form-control">
										<c:forEach items="${fieldType }" var="type">
										<option value="${type.value() }" <c:if test="${field.type.value()==type.value() }">selected="true"</c:if>>${type }</option>
										</c:forEach>
									</select>
								</div>
								<label class="col-sm-1 control-label">策略</label>
								<div class="col-sm-2">
									<select name="pages.page[${i.index }].field[${j.index }].strategy" class="form-control">
										<c:forEach items="${strategyType }" var="type">
										<option value="${type.value() }" <c:if test="${field.strategy.value()==type.value() }">selected="true"</c:if>>${type }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-1 control-label">ById</label>
								<div class="col-sm-2">
									<input name="pages.page[${i.index }].field[${j.index }].byId" value="${field.byId }" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">ByName</label>
								<div class="col-sm-2">
									<input name="pages.page[${i.index }].field[${j.index }].byName" value="${field.byName}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">byLinkText</label>
								<div class="col-sm-2">
									<input name="pages.page[${i.index }].field[${j.index }].byLinkText" value="${field.byLinkText}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">byPartialLinkText</label>
								<div class="col-sm-2">
									<input name="pages.page[${i.index }].field[${j.index }].byPartialLinkText" value="${field.byPartialLinkText}" class="form-control" type="text" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-1 control-label">ByCss</label>
								<div class="col-sm-2">
									<input name="pages.page[${i.index }].field[${j.index }].byCss" value="${field.byCss}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">byTagName</label>
								<div class="col-sm-2">
									<input name="pages.page[${i.index }].field[${j.index }].byTagName" value="${field.byTagName}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">byXpath</label>
								<div class="col-sm-2">
									<input name="pages.page[${i.index }].field[${j.index }].byXpath" value="${field.byXpath}" class="form-control" type="text" />
								</div>
							</div>
							
							<c:forEach items="${field.locators.getLocator()}" varStatus="x" var="locator" >
							<div class="form-group">
								<label class="col-sm-1 control-label">定位方法</label>
								<div class="col-sm-2">
									<select name="pages.page[${i.index }].field[${j.index }].locators.locator[${x.index }].name" class="form-control">
										<c:forEach items="${locatorType }" var="type">
										<option value="${type.value() }" <c:if test="${locator.name.value()==type.value() }">selected="true"</c:if>>${type }</option>
										</c:forEach>
									</select>
								</div>
								<label class="col-sm-1 control-label">定位信息</label>
								<div class="col-sm-2">
									<input name="pages.page[${i.index }].field[${j.index }].locators.locator[${x.index }].value" value="${locator.value}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">超时</label>
								<div class="col-sm-2">
									<input name="pages.page[${i.index }].field[${j.index }].locators.locator[${x.index }].timeout" value="${locator.timeout}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">扩展</label>
								<div class="col-sm-2">
									<input name="pages.page[${i.index }].field[${j.index }].locators.locator[${x.index }].extend" value="${locator.extend}" class="form-control" type="text" />
								</div>
							</div>
							</c:forEach>
			            </div>
			        </div>
			    </div>
			    </form>
			</div>
			</c:forEach>
		</div>
		</c:forEach>
	</div>
	
	<script type="text/javascript">
	if(1==2)
	$('#engine_tabs a').click(function(){
		console.log($(this));
		$(this).tab('show');
		var id = $(this).attr('item-id');
		console.log(id);
		$('#engine_other').load('hello.su?name=' + id);
	});
	
	function fortest(){
		var content = "1=1";
		$('form').each(function(){
			var ser = $(this).serialize();
			if(ser != "")
			content += ("&" + ser);
		});
		$.post('updatePage.su', content, function(){
			location.reload();
		});
	}
	</script>
</body>
</html>