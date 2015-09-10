$('#check-password').keyup(function() {
	var input_pwd = $('#input-password').val();
	var check_pwd = $(this).val();
	
	if (input_pwd == check_pwd) {
		$('#pwd-check-output').text('비밀번호가 일치합니다.');
	} else {
		$('#pwd-check-output').text('비밀번호가 일치하지 않습니다.');
	}	
});

