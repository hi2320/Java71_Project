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
					alert('환영 합니다!');
					$('#login-Modal').modal('hide');
					location.reload();
				} else {
					alert('이메일 혹은 패스워드를 확인해주세요.');
				}
			}
		});
	});
	
	var checkUrl = ["/manager/question_manage.jsp", "/user/mypage.html"];
	$('#logout-a').on('click', function() {
		$.ajax({
			url:"/app/user/logout",
			type: "POST",
			success: function(data) {
				if(data == "true") {
					var logoutCheck = false;
					$.each(checkUrl, function(index, item) {
						if(item == location.pathname) {
							logoutCheck = true; 
						}
					});
					
					if(logoutCheck) {
						location.replace("/index.html");
						alert('로그아웃 성공!');
					} else {
						location.reload();
						alert('로그아웃 성공!');
					}
					
				} else {
					alert('로그아웃 실패!');
				}
			}
		});
	});
});