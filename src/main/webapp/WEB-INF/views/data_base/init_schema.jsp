<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>初始化表结构</title>
</head>
<body>

<form class="form-horizontal" role="form" method="post">
	<div class="form-group">
		<label class="col-sm-4 control-label">数据库类型</label>
		<div class="col-sm-2">
			<select name="driver" class="form-control" required>
				<option value="com.mysql.jdbc.Driver">MySQL</option>
				<option value="org.sqlite.JDBC">SQLite3</option>
			</select>
		</div>
		<label class="col-sm-1 control-label">数据库名</label>
		<div class="col-sm-2">
	    	<input name="defaultSchema" class="form-control" type="text"
	    		value="${dataBase.defaultSchema }" required="required">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">地址</label>
		<div class="col-sm-2">
	    	<input name="url" class="form-control" type="text"
	    		value="${dataBase.url }" required="required">
		</div>
		<label class="col-sm-1 control-label">端口</label>
		<div class="col-sm-2">
	    	<input name="port" class="form-control" type="number"
	    		value="${dataBase.port }" required="required" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">用户名</label>
		<div class="col-sm-2">
	    	<input name="username" class="form-control" type="text"
	    		value="${dataBase.username }" required="required" />
		</div>
		<label class="col-sm-1 control-label">密码</label>
		<div class="col-sm-2">
	    	<input name="password" class="form-control" type="password"
	    		value="${dataBase.password }" required="required">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 control-label">Phoenix平台数据库名</label>
		<div class="col-sm-2">
	    	<input name="schema" class="form-control" type="text"
	    		value="${dataBase.schema }" required="required" />
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-4 control-label"></label>
		<div class="col-sm-2">
			<button type="button" class="btn btn-primary btn-block" onclick="createSchema(this)">导入</button>
		</div>
	</div>
</form>

<div class="modal fade" id="createSchemaDialogId" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
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

<script type="text/javascript">
function createSchema(obj){
	$.ajax({
		url: '<%=basePath%>/data_base/create_schema',
		data: $('form').serialize(),
		success: function(msg){
			if(msg == ''){
				msg = '导入成功，请重启Web服务器！';
				$(obj).attr('disabled', true);
			}
			
			$('#messageBody').html(msg);
			$('#createSchemaDialogId').modal();
		}
	});
}

function sysHelp(){
	introJs().setOption('done', 'next').start().oncomplete(function(){
	});
}
</script>

</body>