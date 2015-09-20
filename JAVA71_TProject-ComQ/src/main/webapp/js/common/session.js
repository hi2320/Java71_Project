$('document').ready(function() {
	$.ajax({
		url: "/app/user/sessionCheck",
		type: "POST",
		dataType: "JSON",
		success: function(data) {
			var user_info = JSON.parse(JSON.stringify(data));
			login_li_manage("true");
			admin_li_manage(user_info.access);
			admin_page_check(user_info.access);
		 },
		error: function() {
			login_li_manage("false");
			admin_li_manage("flase");
			mypage_check();
		 }
	});
	
	function admin_li_manage(boo) {
		if (boo == "admin") {
			$('#admin-move').css('display', 'inline');
		} else {
			$('#admin-move').css('display', 'none');
		}	
	}
	
	function login_li_manage(boo) {
		if(boo == "true") {
			$('#logout-li').css('display', 'inline');
			$('#login-li').css('display', 'none');
			$('#mypage-move').css('display', 'inline');
		} else {
			$('#login-li').css('display', 'inline');
			$('#logout-li').css('display', 'none');
			$('#mypage-move').css('display', 'none');
		}
	}
	
	function admin_page_check(access) {
		if (location.pathname == "/manager/question_manage.jsp") {
			if (access != "admin" ) {
				location.replace("/index.html");
				alert("접근 권한이 없습니다.");
			}	
		}
	}
	
	function mypage_check() {
		if (location.pathname == "/user/mypage.html") {
			alert("로그인 회원만 사용 가능 합니다.");
			location.replace("/index.html");
		}
	}
	

});