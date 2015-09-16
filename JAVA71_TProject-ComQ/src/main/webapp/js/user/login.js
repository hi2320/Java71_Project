$(document).ready(function() {
	
	$('#login-a').on('click', function() {
		$('#login-Modal input').val('');
	});
	
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
					$('#login-a').css('display', 'none');
					$('#logout-a').css('display', 'inline');
					location.reload();
				} else {
					alert('이메일 혹은 패스워드를 확인해주세요.');
				}
			}
		});
	});
	
	
	$('#logout-a').on('click', function() {
		$.ajax({
			url:"/app/user/logout",
			type: "POST",
			success: function(data) {
				if(data == "true") {
					alert('로그아웃 성공!');
					$('#login-a').css('display', 'inline');
					$('#logout-a').css('display', 'none');
				} else {
					alert('로그아웃 실패!');
				}
			}
		});
	});
});