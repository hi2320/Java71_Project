$('document').ready(function() {
	$.ajax({
		url: "/app/user/sessionCheck",
		type: "POST",
		dataType: "JSON",
		success: function(data) {
		if(data != null){
			alert(data);
		} else {
			alert('로그인 되지 않았습니다.')
		}
		
	});
});