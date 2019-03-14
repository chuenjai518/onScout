$(document).ready(function(){
	$("img").click(function() {
       var id = $(this).attr('id');
       console.log(id);
     $("iframe").attr("src", "showPDF/"+id);
            
    });
	
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
