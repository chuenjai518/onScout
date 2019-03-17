$(document).ready(function() {
	$("sAw").click(function() {
		var id = $(this).attr('id');
		console.log(id);
		$("iframe").attr("src", "showPDF/" + id);
	});
	$('#Award1').click(function() {
		$.ajax({
			type : "POST",
			url : "http://localhost:8081/onScout/api/getSPAFinishDate/scout", // 存取Json的網址
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
		$("#standardAwardInfo").show();
	});
	$("#advancedAward").click(function() {
		$("#advancedAwardInfo").show();
	});
	$("#chiefAward").click(function() {
		$("#chiefAwardInfo").show();
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
