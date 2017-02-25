var msgTipId = 'msg_tip_id';

function tip(msg, callback){
	var eleStr = '<div style="position: absolute; left: 0px; top: 0px; width: 100%;" class="alert alert-success alert-dismissable hide fade" id="' + msgTipId + '">';
	eleStr += '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>';
	eleStr += msg;
	eleStr += '</div>';
	
	$('body').append(eleStr);
	var tipBody = $('#' + msgTipId);
	tipBody.removeClass('hide fade');

	window.setTimeout(function(){
		tipBody.remove();
		
		if(typeof callback == 'function'){
			callback();
		}
	}, 1000);
}