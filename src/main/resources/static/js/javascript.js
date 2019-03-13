$(document).ready(function(){
	
});
function enable(){
	if($('input').prop('disabled') == true){
		$('input').prop('disabled',false);
		$('#submit').prop('disabled',false);
	}else{
		$('input').prop('disabled',true);
		$('#submit').prop('disabled',true);
	}
	
	
}
