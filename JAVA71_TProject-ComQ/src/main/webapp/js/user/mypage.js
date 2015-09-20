function readUpdateURL(input) {
	  $('#update-propic-upload-output').text('');
	  if(input.files && input.files[0])  {
	    if ( !(/image/i).test(input.files[0].type) ){
	    	$('#update-propic-upload-output').text('이미지 파일을 선택해 주세요!');
	    	$('#update-propic-upload-output').css('color', 'red');
	    	$('#update-propic-upload').val('');
	    	$('#propic').attr('src', '/propic/default-propic.png');
	      return false;
	    }
	    var reader = new FileReader();
	    reader.onload = function(e) {
	      $('#propic').attr('src', e.target.result);
	    }
	    reader.readAsDataURL(input.files[0]);
	  }
}

function isEmail(string) {
	var regExp = /\w+@\w+\.\w+/;	
	return regExp.test(string);
}


$(document).ready(function(){
	var user_data_propic ="";
	var user_data_email="";
	
	//유저 정보 로딩
	$.ajax({
		url: "/app/user/getUser",
		type: "POST",
		dataType: "JSON",
		success: function(res) {
			var user = JSON.parse(JSON.stringify(res));
			user_data_propic = '/propic/'+user.propic;
			user_data_email = user.email;
			$('#propic').attr('src', user_data_propic);
			$('#user_data_email').text(user_data_email);
		},
		error: function() {
			alert('유저 정보 load fail');
		},
	});
	
	var updateCheck_email = true;
	var updateCheck_pwd = true;
	
	// update-from - reset function
	function resetUpdateForm() {
		$('.check-output').text('');
		updateCheck_email = true;
		updateCheck_pwd = true;
		$('#update-form input[type=password]').val('');
		$('#update-form input[type=email]').attr('placeholder', user_data_email);
		$('#propic').attr('src', user_data_propic);
	}
	
	// 업데이트 폼 자동완성 off
	$('#update-form').attr('autocomplete', 'off');
	
	// div change
	function changeToUpdate() {
		$('#div-update').css('display', 'block');
		$('#mypage_nav').css('display', 'none');
		resetUpdateForm();
	}
	
	function changeToNav() {
		$('#div-update').css('display', 'none');
		$('#mypage_nav').css('display', 'block');
		resetUpdateForm();
	}
	
	// 유저 프로필 업데이트 시작
	$('#update-start-btn').on('click', function() {
		changeToUpdate();
	});
	
	// Mypage_nav 로 돌아가기
	$('#backMove').on('click', function() {
		changeToNav();
	});
	
	// 이메일 중복체크 및 이메일 형식
	$('#update-email').keyup( function() {
		if ( $('#update-email').val().length == 0) {
			$('#update-email-output').text('');
			updateCheck_email = true;
		} else if ( isEmail( $('#update-email').val() )) {
			if ( $('#update-email').val() == user_data_email ) {
				$('#update-email-output').text('');
				updateCheck_email = true;
			} else {
				$.ajax({
					url: "/app/user/emailDuplicationcheck",
					type: "POST",
					data: { "email" : $("#update-email").val() },
					success: function(data) {
						if(data == "false") {
						  $('#update-email-output').text("이미 존재하는  e-mail 입니다.");	
						  $('#update-email-output').css("color", "red");
						  updateCheck_email = false;
						} else { 
						  $('#update-email-output').text("사용 가능 합니다.");
						  $('#update-email-output').css("color", "green");
						  updateCheck_email = true;
						  }
						}
				});	
			}
		} else if( $('#update-email').val().length != 0 ) {
			$('#update-email-output').text("이메일 형식에 맞게 기입해주세요.");	
			$('#update-email-output').css("color", "red"); 
			updateCheck_email = false;
		}
	});

	$('#update-form input[type=password]').keyup(function() {
	  var input_pwd = $('#update-pwd').val();
	  var check_pwd = $('#update-pwd-check').val();
	  
	  if ( input_pwd.length == 0 ) {
		  $('#update-pwd-check-output').text('');
		  updateCheck_pwd = true;
		  if(check_pwd.length != 0) {
			  $('#update-pwd-check-output').text('비밀번호를 입력하세요.');
			  $('#update-pwd-check-output').css("color", "red");
			  updateCheck_pwd = false;  
		  }
	  } else {
		  if (input_pwd == check_pwd) {
		    $('#update-pwd-check-output').text('비밀번호가 일치합니다.');
		    $('#update-pwd-check-output').css("color", "green");
		    updateCheck_pwd = true;
		  } else {
		    $('#update-pwd-check-output').text('비밀번호가 일치하지 않습니다.');
		    $('#update-pwd-check-output').css("color", "red");
		    updateCheck_pwd = false;
		  }
	  }
	}); 
	
	$('#update-form').submit(function(e) {
	  e.preventDefault();
	  if(updateCheck_email == false) {
		  alert('이메일 중복을 확인해 주세요.');
	  } else if(updateCheck_pwd == false) {
		  alert('비밀번호를 확인해 주세요.');
      } else {

    	 var form = $('#update-form')[0];
    	 var formData = new FormData(form);
    	 
		 //join_request
		 $.ajax({
			 url: "/app/user/updateUser",
			 type: "POST",
			 data: formData,
			 processData: false,
             contentType: false,
		     success: function(res){
		    	 if (res == "success") {
		    		 alert('회원 정보가 성공적으로 변경 되었습니다!');
		    		 location.reload();
		    	 } else {
		    		 alert('회원 정보 변경에 실패했습니다.\n다시 시도해 주십시요.');
		    	 }
		    	 changeToNav(); 
		     },
		     error: function() {
		    	 alert('회원 정보 변경에 실패했습니다.\n다시 시도해 주십시요.');
		    	 changeToNav();
		     }
		 });
      }
	  
	});
	
	
	//회원 탈퇴
	$("#leave-btn").on('click', function() {
		var confirmPwd = prompt("본인 확인을 위해 비밀번호를 입력해주세요.");
		$.ajax({
			url: "/app/user/pwdCheck",
			data: { "pwd" : confirmPwd },
			success: function (res) {
				if ( res == "success") {
					if ( confirm("정말 탈퇴 하시겠습니까?") ) {
						$.ajax({
							url: "/app/user/deleteUser",
							type: "POST",
							success: function(res) {
								if(res == "success") {
									alert("성공적으로 탈퇴 되었습니다..");
									location.href="/";
								} else {
									alert("탈퇴에 실패 했습니다.\n다시 시도해 주십시요.");
								}
							}
						});	
					}
				} else {
					alert("패스워드가 틀렸습니다.");
				}
			}
		});
	});
	
	
});
