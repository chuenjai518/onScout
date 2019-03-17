$(document).ready(function(){
	$("sAw").click(function() {
       var id = $(this).attr('id');
       console.log(id);
     $("iframe").attr("src", "showPDF/"+id);     
    });
	$('#Award').click(function(){
		 $.ajax({
			 type: "POST",
			 url: "http://localhost:8081/onScout/api/getSPAFinishDate/scout",   // 存取Json的網址
             cache:false,
             dataType: 'json',
             // contentType: "application/json",
             success: function (data) {
            	 var str="<ul>";    
            	 $.each(data,function(i,n){   
            		console.log(n);
            		//change #testSpan to the correct field
            		//just copy and paste and n[{the column in ajax}]
            		
            		qID = "SPA" + n['questID'];
            		console.log(qID);
            		
            		$("#"+qID).append(n['finishDate']);    
            	 });
             },
         });
	});
	$("#pathfinderAward").click(function() {
		$("#pathfinderAwardInfo").show();
	});
	$("#standardAward").click(function() {
		$("#standardAwardInfo").show();
	});
	$("#advancedAward").click(function() {
		$("#advancedAwardInfo").show();
	});
	$("#chiefAward").click(function() {
		$("#chiefAwardInfo").show();
	});
});
function enable(){
	if($('input').prop('disabled') == true){
		$('input').prop('disabled',false);
		$('#submit').prop('disabled',false);
		$('#submit2').prop('disabled',false);
	}else{
		$('input').prop('disabled',true);
		$('#submit').prop('disabled',true);
		$('#submit2').prop('disabled',true);
	}

	
}


