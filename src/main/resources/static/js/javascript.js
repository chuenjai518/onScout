$(document).ready(function() {
	$("sAw").click(function() {
		var id = $(this).attr('id');
		console.log(id);
		$("iframe").attr("src", "showPDF/" + id);
	});
	$('#a1').click(function() {
		var user = $('#username').val();
		$.ajax({
			type : "POST",
			url : "http://localhost:8081/onScout/api/getSPAFinishDate/"+user, // 存取Json的網址
			cache : false,
			dataType : 'json',
			// contentType: "application/json",
			success : function(data) {
				var str = "<ul>";
				$.each(data, function(i, n) {
					console.log(n);
					// change #testSpan to the correct field
					// just copy and paste and n[{the column in ajax}]

					qID = "SPA" + n['questID'];
					console.log(qID);

					var date = n['finishDate'].split('-');
					var year = date[0];
					var month = date[1];
					var day = date[2];
					
					date = day + "/" + month +"/"+year;
					$("#" + qID).find('span').html(date);
				});
			},
		});
	});
	$('#a2').click(function() {
		var user = $('#username').val();
		$.ajax({
			type : "POST",
			url : "http://localhost:8081/onScout/api/getSSAFinishDate/"+user, // 存取Json的網址
			cache : false,
			dataType : 'json',
			// contentType: "application/json",
			success : function(data) {
				var str = "<ul>";
				$.each(data, function(i, n) {
					console.log(n);
					// change #testSpan to the correct field
					// just copy and paste and n[{the column in ajax}]

					qID = "SPA" + n['questID'];
					console.log(qID);

					var date = n['finishDate'].split('-');
					var year = date[0];
					var month = date[1];
					var day = date[2];
					
					date = day + "/" + month +"/"+year;
					$("#" + qID).find('span').html(date);
				});
			},
		});
	});
	$('#a3').click(function() {
		var user = $('#username').val();
		$.ajax({
			type : "POST",
			url : "http://localhost:8081/onScout/api/getSAAFinishDate/"+user, // 存取Json的網址
			cache : false,
			dataType : 'json',
			// contentType: "application/json",
			success : function(data) {
				var str = "<ul>";
				$.each(data, function(i, n) {
					console.log(n);
					// change #testSpan to the correct field
					// just copy and paste and n[{the column in ajax}]

					qID = "SPA" + n['questID'];
					console.log(qID);

					var date = n['finishDate'].split('-');
					var year = date[0];
					var month = date[1];
					var day = date[2];
					
					date = day + "/" + month +"/"+year;
					$("#" + qID).find('span').html(date);
				});
			},
		});
	});
	$('#a4').click(function() {
		var user = $('#username').val();
		$.ajax({
			type : "POST",
			url : "http://localhost:8081/onScout/api/getCSAFinishDate/"+user, // 存取Json的網址
			cache : false,
			dataType : 'json',
			// contentType: "application/json",
			success : function(data) {
				var str = "<ul>";
				$.each(data, function(i, n) {
					console.log(n);
					// change #testSpan to the correct field
					// just copy and paste and n[{the column in ajax}]

       var id = $(this).attr('id');
       console.log(id);
     $("iframe").attr("src", "showPDF/"+id);
    });
	$('.Award').click(function(){
		console.log($(this).attr('id'));
		var api = null;
		switch($(this).attr('id')){
		case "a1":
			api="getSPAFinishDate";
		break;
		case "a2":
			api="getSSAFinishDate";
		break;
		case "a3":
			api="getSAAFinishDate";
		break;
		case "a4":
			api="getCSAFinishDate";
		break;
		default:
			break;

		}
		url="http://localhost:8081/onScout/api/"+api+"/scout";
		 $.ajax({
			 type: "POST",
			 url: url,   // 存取Json的網址
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

            		$("#"+qID).find('span').html(n['finishDate']);
            		$("#"+qID).find('input').val(n['finishDate']);
            	 });
             },
         });
	});
	$('.dateSubmit').click(function(){
		tempId = $(this).parent().parent().parent().attr('id');
		questID = tempId.substring(3);
		username = $('#username').attr('username');
		date = $(this).parent().prev().val();
		console.log(username+","+questID+ "," + date);
		if(date != ""){
			$.ajax({
				 type: "POST",
				 url: "http://localhost:8081/onScout/scouter/editCompletedQuest",   // 存取Json的網址
				 cache:false,
				 data: {
					 "questID": questID,
					 "username": username,
					 "finishDate": date,
				 },
	            // contentType: "application/json",
	            success: function (data) {

	            },
	        });
		}else
			alert("Please input completed date ")

	});
	$("#pathfinderAward").click(function() {
		$("#pathfinderAwardInfo").show();

	});
	$("#standardAward").click(function() {
		if($("#standardAwardInfo").attr('open') == true){
			$("#standardAwardInfo").show();
		}else
			alert('Scout Not Complete the Previous Award Yet!')
	});
	$("#advancedAward").click(function() {
		if($("#advancedAwardInfo").attr('open') == true){
			$("#advancedAwardInfo").show();
		}else
			alert('Scout Not Complete the Previous Award Yet!')
	});
	$("#chiefAward").click(function() {
		if($("#chiefAwardInfo").attr('open') == true){
			$("#chiefAwardInfo").show();
		}else
			alert('Scout Not Complete the Previous Award Yet!')
	});
});
function enable() {
	if ($('input').prop('disabled') == true) {
		$('input').prop('disabled', false);
		$('#submit').prop('disabled', false);
		$('#submit2').prop('disabled', false);
	} else {
		$('input').prop('disabled', true);
		$('#submit').prop('disabled', true);
		$('#submit2').prop('disabled', true);
	}

}
