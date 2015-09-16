$(document).ready(function(){
	var joinCheck_email = false;
	var joinCheck_pwd = false;
	
	$('#join-btn').on('click', function() {
		$('.check-output').text('');
		joinCheck_email = false;
		joinCheck_pwd = false;
		$('#join-Modal input').val('');
		
	});
	
	$('#input-email').attr('autocomplete', 'off');
	
	

	$('#input-email').keyup( function() {
		if ( isEmail( $('#input-email').val() )) {
		 $.ajax({
			url: "/app/user/emailDuplicationcheck",
			type: "POST",
			data: { "email" : $("#input-email").val() },
			success: function(data) {
				if(data == "false") {
				  $('#input-email-output').text("이미 존재하는  e-mail 입니다.");	
				  $('#input-email-output').css("color", "red");
				  joinCheck_email = false;
				} else { 
				  $('#input-email-output').text("사용 가능 합니다.");
				  $('#input-email-output').css("color", "green");
				  joinCheck_email = true;
				  }
				}
		});
	 } else if ($('#input-email').val().length == 0) {
		 $('#input-email-output').text("이메일을 입력해주세요.");	
		 $('#input-email-output').css("color", "red");
	 } else {
		 $('#input-email-output').text("이메일 형식에 맞게 기입해주세요.");	
		 $('#input-email-output').css("color", "red"); 
	 }
		
	});
	
	 
	$('input[type=password]').keyup(function() {
	  var input_pwd = $('#input-pwd').val();
	  var check_pwd = $('#input-pwd-check').val();
	  
	  if (input_pwd.length == 0) {
		  $('#pwd-check-output').text('비밀번호를 입력하세요.');
		  $('#pwd-check-output').css("color", "red");
		  joinCheck_pwd = false;
	  } else {
		  if (input_pwd == check_pwd) {
		    $('#pwd-check-output').text('비밀번호가 일치합니다.');
		    $('#pwd-check-output').css("color", "green");
		    joinCheck_pwd = true;
		  } else {
		    $('#pwd-check-output').text('비밀번호가 일치하지 않습니다.');
		    $('#pwd-check-output').css("color", "red");
		    joinCheck_pwd = false;
		  }
	  }
	}); 
	
	$('#join-form').submit(function(e) {
	 if(joinCheck_email == false) {
		 alert('email 중복 체크를 확인해 주세요.');
		 e.preventDefault();
	 }
	 if(joinCheck_pwd == false) {
	   alert('비밀번호를 확인해 주세요.');
	   e.preventDefault();
	 }
	});
});

function readURL(input) {
  $('#propic-upload-output').text('');
  if(input.files && input.files[0])  {
    if ( !(/image/i).test(input.files[0].type) ){
    	$('#propic-upload-output').text('이미지 파일을 선택해 주세요!');
    	$('#propic-upload-output').css('color', 'red');
      return false;
    }
    var reader = new FileReader();
    reader.onload = function(e) {
      $('#propic-preview').attr('src', e.target.result);
    }
    reader.readAsDataURL(input.files[0]);
  }
}

function isEmail(string) {
	var regExp = /\w+@\w+\.\w+/;	
	return regExp.test(string);
}


