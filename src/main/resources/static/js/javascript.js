$(document).ready(function() {
	$(".sAw").click(function() {
		var id = $(this).attr('id');
		console.log(id);
		$("iframe").attr("src", "showPDF/" + id);
	});
	

	
	$("#generateForm").click(function(event){
		event.preventDefault();
		var username = $("#username").attr('value');
		$.ajax({
			 type: "POST",
			 url: "http://chuenpi.tk:8081/onScout/checkForm",   // 存取Json的網址
			 cache:false,
			 data: {
				 "username": username,
			 },
          // contentType: "application/json",
          success: function (data) {
        	  
        	  if(!data){
        		  alert("You have not complete Chief Scout's Award");
        	  }else{
        		  alert("You have completed Chief Scout's Award!");
        	  }
       	   
          },
      });
	});
	$('.Award').click(function(){
		console.log($(this).attr('id'));
		var api = null;
		var user = $('#username').attr('username');
		var id = $(this).attr('id');
		switch(id){
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
		url="http://chuenpi.tk:8081/onScout/api/"+api+"/"+user;
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
            		// change #testSpan to the correct field
            		// just copy and paste and n[{the column in ajax}]
            		var name;
            		switch(id){
            		case "a1":
            			name="SPA";
            		break;
            		case "a2":
            			name="SSA";
            		break;
            		case "a3":
            			name="SAA";
            		break;
            		case "a4":
            			name="CSA";
            		break;
            		default:
            			break;
            		}

            		qID = name + n['questID'];
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
				 url: "http://chuenpi.tk:8081/onScout/scouter/editCompletedQuest",   // 存取Json的網址
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
	$(".close").click(function() {
		$("#pathfinderAwardInfo").hide();
		$("#standardAwardInfo").hide();
		$("#advancedAwardInfo").hide();
		$("#chiefAwardInfo").hide();
	});
	$("#pathfinderAward").click(function() {
		$("#pathfinderAwardInfo").show();

	});
	$("#standardAward").click(function() {
		if($("#standardAwardInfo").attr('open') == "open"){
			$("#standardAwardInfo").show();
		}else
			alert('Scout Not Complete the Previous Award Yet!')
	});
	$("#advancedAward").click(function() {
		if($("#advancedAwardInfo").attr('open') == "open"){
			$("#advancedAwardInfo").show();
		}else
			alert('Scout Not Complete the Previous Award Yet!')
	});
	$("#chiefAward").click(function() {
		if($("#chiefAwardInfo").attr('open') == "open"){
			$("#chiefAwardInfo").show();
		}else
			alert('Scout Not Complete the Previous Award Yet!')
	});
	
	$("#changePassword").click(function(){
		var username = $("#username").attr('value');
		console.log(username);
		var pw = $("#newPassword").val();
		console.log(pw);
		$.ajax({
			 type: "POST",
			 url: "http://chuenpi.tk:8081/onScout/changePassword",   // 存取Json的網址
			 cache:false,
			 data: {
				 "password": pw,
				 "username": username,
			 },
           // contentType: "application/json",
           success: function (data) {
        	   alert("Password Changed");
           },
       });
	});
	$('.delUser').on('click',function(){
		return confirm('Confirm Delete User?');
	});
	$(".viewAward").click(function(){
		var id = $(this).attr('id');
		var name = null;
		switch(id){
		case "a1":
			name="10000";
		break;
		case "a2":
			name="20000";
		break;
		case "a3":
			name="30000";
		break;
		case "a4":
			name="40000";
		break;
		default:
			break;
		}
		$.ajax({
			 type: "POST",
			 url: "http://chuenpi.tk:8081/onScout/scouter/totalNumOfAward",   // 存取Json的網址
			 cache:false,
			 data: {
				 "questID":name
			 },
           // contentType: "application/json",
           success: function (data) {
        	   $("#content").empty();
        	   $.each(data,function(i,n){
        		   url = "window.location='http://chuenpi.tk:8081/onScout/scouter/scoutProcess/" + n['username'] +"'";
        		   var string = "<tr onclick="+url+">";
        		   string+="<td>" + n['username']+"</td>";
        		   string+="<td>" + n['lastName']+" "+n['firstName']+"</td>";
        		   string+="<td>" + n['gender']+"</td>";
        		   string+="</tr>";
        		   $("#content").append(string);
        	   })
           },
       });
	})
});

function goToProgress(username) {
	
	alert(username);
	window.location("http://chuenpi.tk:8081/onScout/scouter/scoutProcess/" + username);
}

function enable() {
	if ($('input').prop('disabled') == true) {
		$('input').prop('disabled', false);
		$('select').prop('disabled', false);
		$('#submit').prop('disabled', false);
		$('#submit2').prop('disabled', false);
	} else {
		$('input').prop('disabled', true);
		$('select').prop('disabled', true);
		$('#submit').prop('disabled', true);
		$('#submit2').prop('disabled', true);
	}

}
