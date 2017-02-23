$(function(){
	$('#debugRunDialogId').on('show.bs.modal', function(e) {
		var dialog = $(this);
		var itemId = $(e.relatedTarget).data('href');
		var form = dialog.find('form');
		form.find('[name="id"]').val(itemId);
		form.find('input').removeAttr('readonly');
		form.find('[name="currentIndex"]').attr('readonly', 'readonly');
		$('#messageBody').html('');
		
		var normalTimes = form.find('[name="normalTimes"]').val();
		
		dialog.find('.btn-ok').unbind('click').click(function(){
			form.find('input').attr('readonly', 'readonly');
			dialog.find('.btn-close').unbind('click').html('关闭并查看结果').click(function(){
				$('#runInfoDialogId').modal();
			});
			
			$(this).attr('disabled', true);
			
			debugRun(form, Number(normalTimes));
		}).attr('disabled', false);
	});
});

function suiteRun(form, url, formData, maxTimes){
	$.ajax({
		type : 'POST',
		url : url,
		async : true,
		dataType : 'json',
		data : formData,
		success : function(data){
			if(data.message && data.message != ''){
				$('#messageBody').html(data.message);
				form.find('[name="currentIndex"]').attr('style', 'background-color: read;');
			}else{
				var index = form.find('[name="currentIndex"]').val();
				index = Number(index);
				if(index < Number(maxTimes)){
					form.find('[name="currentIndex"]').val(index + 1);
					
					suiteRun(form, url, formData, maxTimes);
				}
			}
		}
	});
}

function debugRun(form, normalTimes){
	var deployUrl = form.find('[name="deployUrl"]').val();
	
	$.post(deployUrl, function(){
		form.find('[name="currentIndex"]').val(1);
		
		suiteRun(form, form.attr('action'), form.serialize(), normalTimes);

		if($('#messageBody').html() == ''){
			$('#messageBody').html('成功！');
		}
	});
}