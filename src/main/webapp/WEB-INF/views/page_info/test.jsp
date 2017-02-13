<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>页面集</title>
<su:link href="/static/bootstrapValidator/css/bootstrapValidator.css"></su:link>
<su:script src="/static/bootstrapValidator/js/bootstrapValidator.js"></su:script>
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
				<a class="navbar-brand" href="test.su?id=${pageInfo.id }">刷新</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a onclick="fortest()" data-step="2" data-intro="保存当前配置" data-position="right"><span style="cursor:pointer;">保存</span></a></li>
					<li><a href="addPage.su?id=${pageInfo.id}" data-step="1" data-intro="新增页面" data-position="right">新增页面</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" data-step="3" data-intro="查看列表" data-position="right">列表 <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<%=basePath %>/project/edit.su?id=${pageInfo.projectId}">当前项目</a></li>
							<li><a href="<%=basePath %>/project/list.su">项目列表</a></li>
							<li class="divider"></li>
							<li><a href="<%=basePath %>/page_info/list.su?projectId=${pageInfo.projectId}">页面集列表</a></li>
							<li><a href="<%=basePath %>/data_source_info/list.su?projectId=${pageInfo.projectId}">数据源列表</a></li>
							<li><a href="<%=basePath %>/suite_runner_info/list.su?projectId=${pageInfo.projectId}">测试套件列表</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="download.su?id=${pageInfo.id }" data-step="4" data-intro="把当前配置转为XML并提供下载" data-position="left">下载</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" data-step="5" data-intro="根据元素定位信息来生成数据源、测试套件" data-position="left">生成 <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<%=basePath %>/page_info/generateDataSource.su?id=${pageInfo.id}">数据源</a></li>
							<li><a href="<%=basePath %>/page_info/generateSuiteRunner.su?id=${pageInfo.id}">测试套件</a></li>
						</ul>
					</li>
					<li><a href="#" onclick="sysHelp();">帮助</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	
	<ul class="nav nav-tabs" id="engine_tabs">
		<li class="active">
			<a href="#engine" data-toggle="tab">基本信息</a>
		</li>
		<c:forEach items="${pageInfo.autotest.pages.page}" varStatus="i" var="item" >
		<li>
			<a href="#${item.clazz }" data-toggle="tab" item-id="${item.clazz }">${item.clazz }</a>
		</li>
		</c:forEach>
	</ul>
	<div class="tab-content">
		<div class="tab-pane fade in active" id="engine">
			<form class="form-horizontal" role="form" method="post">
				<div class="form-group">
					<input name="projectId" value="${pageInfo.projectId }" type="hidden" />
					<input name="id" value="${pageInfo.id }" type="hidden" />
					<label class="col-sm-2 control-label">页面集名称</label>
					<div class="col-sm-3" data-step="6" data-intro="给当前页面起个可爱的名字吧，无所谓中英文哦" data-position="down">
				    	<input name="name" value="${pageInfo.name }" class="form-control" type="input" required />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">浏览器</label>
					<div class="col-sm-3" data-step="7" data-intro="选择你要测试的浏览器类型，我们支持IE、谷歌、火狐等等哦" data-position="down">
						<select name="autotest.engine.driver" class="form-control">
							<c:forEach items="${engineType }" var="type">
							<option value="${type.value() }" <c:if test="${pageInfo.autotest.engine.driver.value()==type.value() }">selected="true"</c:if>>${type }</option>
							</c:forEach>
						</select>
					</div>
					<label class="col-sm-2 control-label">最大化</label>
					<div class="col-sm-3">
				    	<input class="form-control" type="checkbox" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">宽度</label>
					<div class="col-sm-3">
				    	<input name="autotest.engine.width" value="${pageInfo.autotest.engine.width }" class="form-control"
				    		type="number" min="10" max="2048"/>
					</div>
					<label class="col-sm-2 control-label">高度</label>
					<div class="col-sm-3">
				    	<input name="autotest.engine.height" value="${pageInfo.autotest.engine.height }" class="form-control"
				    		type="number" min="10" max="2048"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">包名</label>
					<div class="col-sm-3" data-step="9" data-intro="Java语言的包（package），别问我为什么" data-position="down">
				    	<input name="autotest.pages.pagePackage" value="${pageInfo.autotest.pages.pagePackage }" class="form-control"
				    		type="text" required>
					</div>
					<label class="col-sm-2 control-label">远程地址</label>
					<div class="col-sm-3">
				    	<input name="autotest.engine.remote" value="${pageInfo.autotest.engine.remote }" class="form-control"
				    		type="">
					</div>
				</div>
				
				<c:forEach items="${pageInfo.autotest.dataSources.dataSource }" var="dataSource" varStatus="dsi">
				<div class="form-group">
					<label class="col-sm-2 control-label">数据源名称</label>
					<div class="col-sm-2">
				    	<input name="autotest.dataSources.dataSource[${dsi.index }].name" value="${dataSource.name }" class="form-control"
				    		type="text">
					</div>
					<label class="col-sm-1 control-label">类型</label>
					<div class="col-sm-2">
						<select name="autotest.dataSources.dataSource[${dsi.index }].type" class="form-control">
							<c:forEach items="${dataSourceType }" var="type">
							<option value="${type.value() }" <c:if test="${dataSource.type.value()==type.value() }">selected="true"</c:if>>${type }</option>
							</c:forEach>
						</select>
					</div>
					<label class="col-sm-1 control-label">文件名</label>
					<div class="col-sm-2">
				    	<input name="autotest.dataSources.dataSource[${dsi.index }].resource" class="form-control"
				    		type="text" value="${dataSource.resource }">
					</div>
				</div>
				</c:forEach>
			</form>
		</div>
		
		<c:forEach items="${pageInfo.autotest.pages.page}" varStatus="i" var="page" >
		<div class="tab-pane fade" id="${page.clazz }">
			<form class="form-horizontal" role="form" method="post">
			<div class="form-group">
				<label class="col-sm-1 control-label">类名</label>
				<div class="col-sm-1">
					<input name="autotest.pages.page[${i.index }].clazz" value="${page.clazz }"
						class="form-control" type="text" required />
				</div>
				<label class="col-sm-1 control-label">URL</label>
				<div class="col-sm-3">
					<input name="autotest.pages.page[${i.index }].url" value="${page.url }" class="form-control" type="text" />
				</div>
				<label class="col-sm-1 control-label">数据源</label>
				<div class="col-sm-1">
					<input name="autotest.pages.page[${i.index }].dataSource" value="${page.dataSource }" class="form-control" type="text" />
				</div>
				<div class="col-sm-3">
					<a href="delPage.su?pageName=${page.clazz }" class="form-control">删除</a>
					<c:if test="${pageInfo.id!='' }">
					<a href="addField.su?id=${pageInfo.id }&pageName=${page.clazz }" class="form-control">添加属性</a>
					</c:if>
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
									<input name="autotest.pages.page[${i.index }].field[${j.index }].name" value="${field.name }" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">类型</label>
								<div class="col-sm-2">
									<select name="autotest.pages.page[${i.index }].field[${j.index }].type" class="form-control">
										<c:forEach items="${fieldType }" var="type">
										<option value="${type.value() }" <c:if test="${field.type.value()==type.value() }">selected="true"</c:if>>${type }</option>
										</c:forEach>
									</select>
								</div>
								<label class="col-sm-1 control-label">策略</label>
								<div class="col-sm-2">
									<select name="autotest.pages.page[${i.index }].field[${j.index }].strategy" class="form-control">
										<c:forEach items="${strategyType }" var="type">
										<option value="${type.value() }" <c:if test="${field.strategy.value()==type.value() }">selected="true"</c:if>>${type }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-1 control-label">ById</label>
								<div class="col-sm-2">
									<input name="autotest.pages.page[${i.index }].field[${j.index }].byId" value="${field.byId }" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">ByName</label>
								<div class="col-sm-2">
									<input name="autotest.pages.page[${i.index }].field[${j.index }].byName" value="${field.byName}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">byLinkText</label>
								<div class="col-sm-2">
									<input name="autotest.pages.page[${i.index }].field[${j.index }].byLinkText" value="${field.byLinkText}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">byPartialLinkText</label>
								<div class="col-sm-2">
									<input name="autotest.pages.page[${i.index }].field[${j.index }].byPartialLinkText" value="${field.byPartialLinkText}" class="form-control" type="text" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-1 control-label">ByCss</label>
								<div class="col-sm-2">
									<input name="autotest.pages.page[${i.index }].field[${j.index }].byCss" value="${field.byCss}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">byTagName</label>
								<div class="col-sm-2">
									<input name="autotest.pages.page[${i.index }].field[${j.index }].byTagName" value="${field.byTagName}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">byXpath</label>
								<div class="col-sm-2">
									<input name="autotest.pages.page[${i.index }].field[${j.index }].byXpath" value="${field.byXpath}" class="form-control" type="text" />
								</div>
							</div>
							
							<c:forEach items="${field.locators.getLocator()}" varStatus="x" var="locator" >
							<div class="form-group">
								<label class="col-sm-1 control-label">定位方法</label>
								<div class="col-sm-2">
									<select name="autotest.pages.page[${i.index }].field[${j.index }].locators.locator[${x.index }].name" class="form-control">
										<c:forEach items="${locatorType }" var="type">
										<option value="${type.value() }" <c:if test="${locator.name.value()==type.value() }">selected="true"</c:if>>${type }</option>
										</c:forEach>
									</select>
								</div>
								<label class="col-sm-1 control-label">定位信息</label>
								<div class="col-sm-2">
									<input name="autotest.pages.page[${i.index }].field[${j.index }].locators.locator[${x.index }].value" value="${locator.value}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">超时</label>
								<div class="col-sm-2">
									<input name="autotest.pages.page[${i.index }].field[${j.index }].locators.locator[${x.index }].timeout" value="${locator.timeout}" class="form-control" type="text" />
								</div>
								<label class="col-sm-1 control-label">扩展</label>
								<div class="col-sm-2">
									<input name="autotest.pages.page[${i.index }].field[${j.index }].locators.locator[${x.index }].extend" value="${locator.extend}" class="form-control" type="text" />
								</div>
							</div>
							</c:forEach>
			            </div><!-- panel body end -->
			        </div><!-- collapse end -->
			    </div>
			</div><!-- tab-pane end -->
			</c:forEach>
		    </form>
		</div>
		</c:forEach>
	</div>
	
	<script type="text/javascript">
	function sysHelp(){
		introJs().setOption('done', 'next').start().oncomplete(function(){
		});
	}
	
	function fortest(){
		var content = "1=1";
		$('form').each(function(){
			var bv = $(this).data('bootstrapValidator');
			bv.validate();
			if(!bv.isValid()){
				content = "";
				return false;
			}
			
			var ser = $(this).serialize();
			if(ser != ""){
				content += ("&" + ser);
			}
		});
		
		if(content != ""){
			$.post('updatePage.su', content, function(data){
				if(data.id){
					window.location = 'test.su?id=' + data.id;
				}
			});
		}
	}
	
    $(document).ready(function() {
        $('form').bootstrapValidator();
    });
	</script>
</body>
</html>