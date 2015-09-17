$(document).ready(function(){
	$.ajax({
		url: "/app/user/getUser",
		data: { "kind" : "loginUser"},
		dataType: "JSON",
		success: function(){
			
		},
		
		
	});
});
