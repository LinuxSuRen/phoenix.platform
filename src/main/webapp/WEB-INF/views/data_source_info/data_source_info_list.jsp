<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>数据源列表</title>
<su:link href="/static/bootstrap-table/bootstrap-table.css"></su:link>
<su:script src="/static/bootstrap-table/bootstrap-table.min.js"></su:script>
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
        <a class="navbar-brand" href="<%=basePath %>/data_source_info/list?projectId=${projectId }">数据源列表</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="<%=basePath%>/project/edit?id=${projectId}" data-step="2" data-intro="查看当前项目信息"
            	data-position="right">当前项目</a></li>
			<li><a href="<%=basePath%>/project/list">项目列表</a></li>
			<li><a href="#" onclick="sysHelp();">帮助</a></li>
		</ul>
    </div>
    </div>
</nav>

<div class="container">
    <h1>数据源列表</h1>
    <p class="toolbar">
        <a class="create btn btn-default" href="javascript:">新增</a>
        <a class="import btn btn-default" href="javascript:">导入</a>
        <span class="alert"></span>
    </p>
	<table id="table"
		data-toggle="table"
	    data-search="true"
	    data-query-params="queryParams"
		data-show-refresh="true"
        data-toolbar=".toolbar">
	    <thead>
	        <tr>
	            <th data-field="name">名称</th>
	            <th data-field="createTime" data-formatter="timeFormatter">创建时间</th>
	            <th data-field="id" data-formatter="operationEdit">编辑</th>
	            <th data-field="id" data-formatter="operationDel">删除</th>
	        </tr>
	    </thead>
	</table>
</div>
	
<div class="modal fade" id="dataSourceUploadDialogId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="import?projectId=${projectId }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					数据源信息导入
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

<div id="modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
            	<input type="hidden" name="projectId" value="${projectId }">
                <div class="form-group">
                    <label>数据源名称</label>
                    <input type="text" class="form-control" name="name" placeholder="Name" required>
                </div>
                <div class="form-group">
                    <label>类型</label>
                    <input type="text" class="form-control" name="type" placeholder="type">
                </div>
                <div class="form-group">
                    <label>资源</label>
                    <input type="text" class="form-control" name="resource" placeholder="resource">
                </div>
                <div class="form-group">
                    <label>备注</label>
                    <input type="text" class="form-control" name="remark" placeholder="remark">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary submit">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="delDialogId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					确认
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">确认删除？</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<a class="btn btn-danger btn-ok">确认</a>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<script type="text/javascript">
var API_URL = '<%=basePath %>/api/data_source_infos';
var $table = $('#table').bootstrapTable({url: API_URL + '/${projectId}'}),
    $modal = $('#modal').modal({show: false}),
    $alert = $('.alert').hide();
    
$(function () {
    // create event
    $('.create').click(function () {
        showModal($(this).text());
    });
    $modal.find('.submit').click(function () {
        var row = {};
        $modal.find('input[name]').each(function () {
            row[$(this).attr('name')] = $(this).val();
        });
        $.ajax({
            url: API_URL + ($modal.data('id') || ''),
            type: $modal.data('id') ? 'put' : 'post',
            contentType: 'application/json',
            data: JSON.stringify(row),
            success: function () {
                $modal.modal('hide');
                $table.bootstrapTable('refresh');
                showAlert(($modal.data('id') ? 'Update' : 'Create') + ' item successful!', 'success');
            },
            error: function () {
                $modal.modal('hide');
                showAlert(($modal.data('id') ? 'Update' : 'Create') + ' item error!', 'danger');
            }
        });
    });
});

function queryParams(params) {
    return {};
}
function actionFormatter(value) {
    return [
        '<a class="update" href="javascript:" title="Update Item"><i class="glyphicon glyphicon-edit"></i></a>',
        '<a class="remove" href="javascript:" title="Delete Item"><i class="glyphicon glyphicon-remove-circle"></i></a>',
    ].join('');
}
// update and delete events
window.actionEvents = {
    'click .update': function (e, value, row) {
        showModal($(this).attr('title'), row);
    },
    'click .remove': function (e, value, row) {
        if (confirm('Are you sure to delete this item?')) {
            $.ajax({
                url: API_URL + '/' + row.id,
                type: 'delete',
                success: function () {
                    $table.bootstrapTable('refresh');
                    showAlert('Delete item successful!', 'success');
                },
                error: function () {
                    showAlert('Delete item error!', 'danger');
                }
            })
        }
    }
};
function showModal(title, row) {
    row = row || {
        id: '',
        name: '',
        stargazers_count: 0,
        forks_count: 0,
        description: ''
    }; // default row value
    $modal.data('id', row.id);
    $modal.find('.modal-title').text(title);
    for (var name in row) {
        $modal.find('input[name="' + name + '"]').val(row[name]);
    }
    $modal.modal('show');
}
function showAlert(title, type) {
    $alert.attr('class', 'alert alert-' + type || 'success')
          .html('<i class="glyphicon glyphicon-check"></i> ' + title).show();
    setTimeout(function () {
        $alert.hide();
    }, 3000);
}

$('#delDialogId').on('show.bs.modal', function(e) {
	$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
});

function sysHelp(){
	introJs().setOption('done', 'next').start().oncomplete(function(){
	});
}
</script>

</body>