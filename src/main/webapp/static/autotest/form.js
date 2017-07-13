
$.fn.serializeObject = function(){
	var o = {};
	var a = this.serializeArray();
	$.each(a, function(){
		var name = this.name;
		
		var names = name.split('.');
		var len = names.length;
		var target = o;
		
		for(var i = 0; i < len - 1; i++){
			var item = names[i];
			var isArray = false;
			
			if(item.indexOf('[') != -1){
				item = item.substring(0, item.indexOf('['));
				isArray = true;
			}
			
			if(!target[item]){
				if(isArray){
					target = target[item] = new Array();
					target.push(target = {});
				}else{
					target = target[item] = {};
				}
			}else{
				if(isArray){
					target = target[item][0];
				}else{
					target = target[item];
				}
			}
		}
		
		target[names[len - 1]] = this.value || '';
	});
	
	return o;
}