$(function(){
	$('#debugRunDialogId').on('show.bs.modal', function(e) {
		var dialog = $(this);
		var itemId = $(e.relatedTarget).data('href');
		var form = dialog.find('form');
		form.find('[name="id"]').val(itemId);
		form.find('input').removeAttr('readonly');
		form.find('[name="currentIndex"]').val('0').attr('readonly', 'readonly');
		form.find('[name="successTimes"]').val('0').attr('readonly', 'readonly');
		form.find('[name="failureTimes"]').val('0').attr('readonly', 'readonly');
		$('#messageBody').html('');
		
		dialog.find('.btn-ok').unbind('click').click(function(){
			form.find('input').attr('readonly', 'readonly');
			dialog.find('.btn-close').unbind('click').html('关闭并查看结果').click(function(){
				$('#runInfoDialogId').modal();
			});
			
			$(this).attr('disabled', true);
			
			var normalTimes = form.find('[name="normalTimes"]').val();
			
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
				
				var retryTime = form.find('[name="retryTimes"]').val();
				retryTime = Number(retryTime);
				var currentfailureTimes = form.find('[name="failureTimes"]').val();
				currentfailureTimes = Number(currentfailureTimes);
				
				form.find('[name="failureTimes"]').val(currentfailureTimes + 1);
				
				if(currentfailureTimes >= retryTime) {
					clearInterval(clearId);
					return;
				}
			}else{
				var currentSuccessTimes = form.find('[name="successTimes"]').val();
				form.find('[name="successTimes"]').val(Number(currentSuccessTimes) + 1);
			}
			
			var index = form.find('[name="currentIndex"]').val();
			index = Number(index);
			
			if(index < Number(maxTimes)){
				form.find('[name="currentIndex"]').val(index + 1);
				
				suiteRun(form, url, formData, maxTimes, null);
			}else{
				clearInterval(clearId);
				
				if(data.message && data.message == ''){
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