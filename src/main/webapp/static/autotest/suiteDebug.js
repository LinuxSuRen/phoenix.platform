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

function suiteRun(form, url, formData, maxTimes, clearId){
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
					
					suiteRun(form, url, formData, maxTimes, null);
				}else{
					clearInterval(clearId);
					
					if($('#messageBody').html() == ''){
						$('#messageBody').html('成功！');
					}
				}
			}
		}
	});
}

function debugRun(form, normalTimes){
	var deployUrl = form.find('[name="deployUrl"]').val();
	
	$.post(deployUrl, function(){
		setProgressInfo('', '部署成功！');
		
		form.find('[name="currentIndex"]').val(1);
		var key = form.find('[name="progress_key"]').val(new Date().getTime()).val();

		var progressUrl = form.find('[name="progressUrl"]').val() + "?key=" + key;
		var clearId = window.setInterval(_setProgressInfo(progressUrl, ''), 1500);
		
		suiteRun(form, form.attr('action'), form.serialize(), normalTimes, clearId);
	});
}

function _setProgressInfo(progressUrl, msg){
	return function(){
		setProgressInfo(progressUrl, msg)
	};
}

function setProgressInfo(progressUrl, msg){
	if(msg != ''){
		$('#progress_bar').text(msg);
	}else{
		$.ajax({
			url: progressUrl,
			dataType: 'json',
			success: function(text){
				$('#progress_bar').text(text.msg);
			}
		});
	}
}