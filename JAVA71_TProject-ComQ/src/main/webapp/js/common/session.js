$('document').ready(function() {
	$.ajax({
		url: "/app/user/sessionCheck",
		type: "POST",
		dataType: "JSON",
		success: function(data) {
			var user_info = JSON.parse(JSON.stringify(data));
			$('#login-a').css('display', 'none');
			$('#logout-a').css('display', 'inline');
			$('#usermenu_dropdown').css('display', 'inline');
			$('#user_email').text(
					user_info.email.substring(0, user_info.email.indexOf("@")));
		 },
		error: function() {
			$('#login-a').css('display', 'inline');
			$('#usermenu_dropdown').css('display', 'none');
		 }
	});
});