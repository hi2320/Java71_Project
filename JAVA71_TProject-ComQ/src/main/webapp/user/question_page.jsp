<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>COMQ: Curating 시작하기</title>

<link rel="stylesheet" href="/reset.css">

<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link href="http://fonts.googleapis.com/css?family=Montserrat:700,400"
	rel="stylesheet" type="text/css">

<link rel="stylesheet" href="/css/question_page.css">
</head>

<body id="page-top">
    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed"
          data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
          <span class="sr-only">Toggle navigation</span> <span
            class="icon-bar"></span> <span class="icon-bar"></span> <span
            class="icon-bar"></span>
        </button>
        <a class="navbar-brand page-scroll" href="#page-top">COMQ</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse"
        id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
          <li><a class="page-scroll" href="#about">About</a></li>
          <li><a class="page-scroll" href="#services">Services</a></li>
          <li id="admin-move"><a href="/manager/question_manage.jsp">ADMIN</a></li>
          <li id="mypage-move"><a href="/user/mypage.html">MYPAGE</a></li>
          <li id="login-li"><a href="#" data-toggle="modal" data-target="#login-Modal">LOGIN</a></li>
          <li id="logout-li"><a href="#" id="logout-a">LOGOUT</a></li>
        </ul>  

       <!-- login-Modal -->
          <div class="modal fade" id="login-Modal" tabindex="-1" role="dialog" aria-labelledby="login-ModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal"
                    aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                  <h4 class="modal-title" id="login-ModalLabel">로그인</h4>
                </div>
                <div class="modal-body">

                  <form class="form-horizontal" id="login-form">
                    <div class="form-group">
                      <label for="user-mail" class="col-sm-2 control-label">Email</label>
                      <div class="col-sm-10">
                        <input type="email" class="form-control" id="user-email"
                          name="email" placeholder="이메일을 입력하세요.">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="user-pwd" class="col-sm-2 control-label">Password</label>
                      <div class="col-sm-10">
                        <input type="password" class="form-control" id="user-pwd"
                          name="pwd" placeholder="비밀번호 입력하세요.">
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" id="btn-login">LOGIN</button>
                        <button id="join-btn" type="button" class="btn btn-default">SIGN IN</button>

                      </div>
                    </div>
                  </form>

                </div>
              </div>
            </div>
          </div>


          <!-- join-Modal -->
          <div class="modal fade" id="join-Modal" tabindex="-1" role="dialog" aria-labelledby="join-ModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                  <h4 class="modal-title" id="join-ModalLabel">회원 가입</h4>
                </div>
                <div class="modal-body">

                  <form id="join-form" enctype="multipart/form-data"
                    action="/app/user/joinUser" method="POST">
                    <div class="form-group">
                      <label for="input-email" id="input-email-label">이메일주소 (필수)</label>
                      <p id="input-email-output" class="check-output"></p>
                      <input type="email" class="form-control" id="input-email"
                        placeholder="이메일을 입력하세요." name="email">

                    </div>
                    <div class="form-group">
                      <label for="input-pwd">비밀번호 (필수)</label>
                      <p id="pwd-check-output" class="check-output"></p> 
                      <input type="password" class="form-control" id="input-pwd" placeholder="비밀번호를 입력하세요." name="pwd">
                      <input type="password" class="form-control" id="input-pwd-check" placeholder="비밀번호를 다시 입력하세요.">
                    </div>
                    <div class="form-group">
                      <label for="porpic-upload">프로필 사진 (선택)</label>
                      <p id="propic-upload-output" class="check-output"></p>
                      <div id="propic-div">
                        <img id="propic-preview" src="/propic/default-propic.png">
                      </div>
                      <input type="file" id="propic-upload" onchange="readURL(this)" class="btn btn-default" name="propic">
                    </div>
                    <div id="btn-join">
                      <button type="submit" class="btn btn-default">회원가입</button>
                    </div>
                  </form>

                </div>
              </div>
            </div>
          </div>


        </ul>
      </div>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
  </nav>


	<div class="main_wrap">
		
		<div class="question_area size">
			<form name="curatingForm" method="post"
				action="/app/curator/curating?curId=${list.curId }">
				<div id="accordion">
					<c:forEach var="questions" items="${list.questionList }">
						<div>${questions.qSente}||${questions.qProd }
							<input type="hidden" name="questionKey"
								value="${questions.qProd }" />
						</div>
						<div>
							<c:forEach var="answers" items="${questions.answerList }">
								<div>
									<input
										type="${questions.qType eq 'check' ? 'checkbox':'radio'}"
										id="${answers.ansId }" name="${questions.queId }"> <label
										for="${answers.ansId }">${answers.aSente}||${answers.aSpec }</label>
									<input type="hidden" name="" value="${answers.aSpec }" />
								</div>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
			</form>
		</div>
	
		<div class="footer">
			<button type="button" class="btn btn-info db_send_btn">
				<a href="javascript:danawaJson()">저장</a>
			</button>
		</div>
	</div>
</body>
<script>
	$(function() {
		$("#accordion").accordion();
	});

	function danawaJson() {
		var answerSpec = $(":checked");
		for (i = 0; i < answerSpec.length; i++) {
			$(answerSpec[i]).next().next().val(
					$(answerSpec[i]).parent().parent().prev().find(
							"[name=questionKey]").val()
							+ ":" + $(answerSpec[i]).next().next().val());
			$(answerSpec[i]).next().next().attr("name", "answers");
			console.log($(answerSpec[i]).next().next().attr("name"));
		}
		$("form").submit();
	}
</script>
</html>



