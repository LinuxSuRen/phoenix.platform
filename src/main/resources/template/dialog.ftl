<div class="modal fade" id="${dialogId}" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
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
var empty = function(){};
var callback = '${callback?default('empty()')}';
$('#${dialogId}').on('show.bs.modal', function(e) {
	if('${ajaxDel?c}' == 'true'){
		$(this).find('.btn-ok').click(function(){
			var url = $(e.relatedTarget).data('href');
			$.ajax({
				url: url,
				type: 'delete',
				success: function(){
					showSuccessAlert('删除成功');
					eval(callback);
				},
				error: function(){
					showDangerAlert('删除失败');
				}
			});
		});
	}else{
		$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
	}
});
</script>