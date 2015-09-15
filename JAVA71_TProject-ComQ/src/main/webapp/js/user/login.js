$(document).ready(function() {
	
	
	$('#login-form').submit(function(e) {
		e.preventDefault();
		$.ajax({
			url:"/app/user/login",
			type: "POST",
			data: { "email" : $("#user-email").val(), 
					"pwd" : $("#user-pwd").val() },
			success: function(data) {
				if(data == "true") {
					alert('로그인 성공!');
					$('#login-Modal').modal('hide');
					$('#login-btn').css('display', 'none');
					$('#logout-btn').css('display', 'inline');
					
				} else {
					alert('이메일 혹은 패스워드를 확인해주세요.');
				}
			}
		});
	});
	
	
	$('#logout-btn').on('click', function() {
		alert('logout 실행');
		$.ajax({
			url:"/app/user/logout",
			type: "POST",
			success: function(data) {
				alert(data);
				if(data == "true") {
					alert('로그아웃 성공!');
					$('#login-btn').css('display', 'inline');
					$('#logout-btn').css('display', 'none');
				} else {
					alert('로그아웃 실패!');
				}
			}
		});
	});
});