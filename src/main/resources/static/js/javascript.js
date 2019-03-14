$(document).ready(function(){
	$("img").click(function() {
       var id = $(this).attr('id');
       console.log(id);
     $("iframe").attr("src", "showPDF/"+id);
            
    });
	
	$('#adward').click(function(){
		 $.ajax({
             url: "Handler.ashx",   //存取Json的網址             
             type: "POST",
             cache:false,
             dataType: 'json',
             data:{id:"111", user:"frank"},
             //contentType: "application/json",
             success: function (data) {

                 //方法一 (回傳多筆資料)                
                 for (var i = 0; i < data.length; i++) {
                     alert("name=" + data[i]["欄位名稱"]);    
                 }

                 //方法二 (回傳多筆資料)
                 var i = 0;                    
                 $.each(data, function () {
                     alert(data[i].欄位名稱);    
                     i++;
                 });

                 //方法三 (回傳單筆資料)
                 $.each(data, function (index, element) {
                     alert(element);                      
                 });
             },

             error: function (xhr, ajaxOptions, thrownError) {
                 alert(xhr.status);
                 alert(thrownError);
             }
         });
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


