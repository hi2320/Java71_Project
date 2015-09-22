<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko" >
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Computer Curating System : myPage</title>

<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<!-- Custom Fonts -->
<link rel='stylesheet' type='text/css'
  href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'>
<link rel='stylesheet' type='text/css'
  href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'>
<link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css" type="text/css">

<!-- Plugin CSS -->
<link rel="stylesheet" href="/css/animate.min.css" type="text/css">
<link rel="stylesheet" href="/css/main.css" type="text/css">
<!-- Custom CSS -->
<link rel="stylesheet" href="/css/creative.css" type="text/css">

<link rel="stylesheet" href="/css/user.css" type="text/css">
<!-- <link rel="stylesheet" href="/css/mypage.css" type="text.css"> -->
<link rel="stylesheet" href="/css/questionpage.css" type="text/css">
<link rel="stylesheet" href="/css/arccodian.css" type="text/css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
</head>
<body id="page-top">
<div id="bg_img"></div>

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
        <a class="navbar-brand page-scroll" href="/index.html">COMQ</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
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
  
<!-- 큐레이팅 질문 페이지 시작 -->
  <header>
    <div class="header-content" style="min-height: 300px;">
      <div class="header-content-inner">
    	<h1>큐레이팅 시작하기!</h1>
    	<p>질문에 대한 답변을 해주세요. 당신이 원하는 최적의 컴퓨터를 엄선해드립니다.</p>
    	<a href="#start-curating" class="btn btn-primary btn-xl page-scroll" id="cur_move">START CURATING</a>
      </div> <!-- header-content-inner -->
    </div> <!-- header-content -->
  </header>

  <section class="bg-primary" id="start-curating">
    <div class="question_area size">
      <form name="curatingForm" method="post" id="cur-form"
        action="/app/curator/curating?curId=${list.curId }">
        <div id="accordian">
	        <ul>
	        	<c:forEach var="questions" items="${list.questionList }">
	        	<li class="questionList">
	        		<h3>${questions.qSente}||${questions.qProd }
	        			<input type="hidden" name="questionKey" value="${questions.qProd }"/>
	        		</h3>
              		
              		<ul class="checking">
              			<c:forEach var="answers" items="${questions.answerList }">
              			<li class="answerList">
              				<input class="checkTarget" type="${questions.qType eq 'check' ? 'checkbox':'radio'}" id="${answers.ansId }" name="${questions.queId }"> 
                    		<label for="${answers.ansId }">${answers.aSente}||${answers.aSpec }</label>
                  			<input type="hidden" name="" value="${answers.aSpec }"/>
              			</li>
              			</c:forEach>
              		</ul>
              		
	        	</li>
	        	</c:forEach>
	        </ul>
        </div>
      </form>
     </div>
      <div id="save_btn">
      	<a href="javascript:danawaJson()" class="btn btn-default db_send_btn">저장 </a>
      </div>
  </section>

<!-- jquery -->
<script src="/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

<!-- Plugin JavaScript -->
<script src="/js/jquery.easing.min.js"></script>
<script src="/js/jquery.fittext.js"></script>
<script src="/js/wow.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/js/creative.js"></script>
<script src="/js/user/join.js"></script>
<script src="/js/user/login.js"></script>

<script src="/js/common/session.js"></script>
<script src="/js/common/arccodian.js"></script>
<script>
/* radio button event start */
$(document).find("input:checked[type='radio']").addClass('bounce');   
$("input[type='radio']").click(function() {
    $(this).prop('checked', false);
    $(this).toggleClass('bounce');

    if( $(this).hasClass('bounce') ) {
        $(this).prop('checked', true);
        $(document).find("input:not(:checked)[type='radio']").removeClass('bounce');
        
        $(this).parent().parent().prev().css({"background-color":"forestgreen", "opacity":"0.5", "color": "white", "font-weight":"700"});
    } else {
    	$(this).parent().parent().prev().removeAttr("style");
    }
}); /* radio event end */

/* checkbox event start */
$("input[type='checkbox']").click(function() {
	console.log($(this).parent().parent().find(":checked").length);
	if($(this).parent().parent().find(":checked").length == 0) {
    	$(this).parent().parent().prev().removeAttr("style");
    } else {
        $(this).parent().parent().prev().css({"background-color":"forestgreen", "opacity":"0.5", "color": "white", "font-weight":"700"});
    }
});/* checkbox event end */

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
  
  $("#cur-form").submit();
}
</script>
</body>
</html>