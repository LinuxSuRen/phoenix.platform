<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form class="form-horizontal" role="form" action="updatePage.su" method="post">
	<input name="id" value="${page.id }" type="hidden" />
	
	<div class="form-group">
		<label class="col-sm-1 control-label">类名</label>
		<div class="col-sm-2">
			<input name="name" value="${page.clazz }" class="form-control" type="text" />
		</div>
		<label class="col-sm-1 control-label">URL</label>
		<div class="col-sm-7">
			<input value="${page.url }" class="form-control" type="text" />
		</div>
	</div>

	<c:forEach items="${page.content}" varStatus="i" var="field" >
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
							<input name="field.name" value="${field.name }" class="form-control" type="text" />
						</div>
						<label class="col-sm-1 control-label">类型</label>
						<div class="col-sm-2">
							<select class="form-control">
								<c:forEach items="${fieldType }" var="type">
								<option value="${type.value() }" <c:if test="${field.type.value()==type.value() }">selected="true"</c:if>>${type }</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-sm-1 control-label">策略</label>
						<div class="col-sm-2">
							<select class="form-control">
								<c:forEach items="${strategyType }" var="type">
								<option value="${type.value() }" <c:if test="${field.strategy.value()==type.value() }">selected="true"</c:if>>${type }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-1 control-label">ById</label>
						<div class="col-sm-2">
							<input name="byId" value="${field.byId }" class="form-control" type="text" />
						</div>
						<label class="col-sm-1 control-label">ByName</label>
						<div class="col-sm-2">
							<input name="byName" value="${field.byName}" class="form-control" type="text" />
						</div>
						<label class="col-sm-1 control-label">byLinkText</label>
						<div class="col-sm-2">
							<input name=byLinkText value="${field.byLinkText}" class="form-control" type="text" />
						</div>
						<label class="col-sm-1 control-label">byPartialLinkText</label>
						<div class="col-sm-2">
							<input name=byPartialLinkText value="${field.byPartialLinkText}" class="form-control" type="text" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-1 control-label">ByCss</label>
						<div class="col-sm-2">
							<input name="byCss" value="${field.byCss}" class="form-control" type="text" />
						</div>
						<label class="col-sm-1 control-label">byTagName</label>
						<div class="col-sm-2">
							<input name="byTagName" value="${field.byTagName}" class="form-control" type="text" />
						</div>
						<label class="col-sm-1 control-label">byXpath</label>
						<div class="col-sm-2">
							<input name="byXpath" value="${field.byXpath}" class="form-control" type="text" />
						</div>
					</div>
					
					<c:forEach items="${field.content[0].getLocator()}" varStatus="i" var="locator" >
					<div class="form-group">
						<label class="col-sm-1 control-label">定位方法</label>
						<div class="col-sm-2">
							<select class="form-control">
								<c:forEach items="${locatorType }" var="type">
								<option value="${type.value() }" <c:if test="${locator.name.value()==type.value() }">selected="true"</c:if>>${type }</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-sm-1 control-label">定位信息</label>
						<div class="col-sm-2">
							<input name="byTagName" value="${locator.value}" class="form-control" type="text" />
						</div>
						<label class="col-sm-1 control-label">超时</label>
						<div class="col-sm-2">
							<input name="byXpath" value="${locator.timeout}" class="form-control" type="text" />
						</div>
						<label class="col-sm-1 control-label">扩展</label>
						<div class="col-sm-2">
							<input name="byXpath" value="${locator.extend}" class="form-control" type="text" />
						</div>
					</div>
					</c:forEach>
	            </div>
	        </div>
	    </div>
	</div>
	</c:forEach>

	<button type="submit" class="btn btn-default">保存</button>
</form>
