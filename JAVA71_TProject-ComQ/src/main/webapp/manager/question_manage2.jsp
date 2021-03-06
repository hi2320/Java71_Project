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
<link rel="stylesheet" href="/css/mypage.css" type="text.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

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
        <a class="navbar-brand page-scroll" href="/index.html">COMQ</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
          <li><a class="page-scroll" href="#page-top">profile</a></li>
          <li><a class="page-scroll estimates-hide" href="#Estimates">Estimates</a></li>
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

  <header>
    <div class="header-content">
      <div class="header-content-inner">
    
  <div class="row">
  
    <div  class="col-sm-3" id="div_propic">
      <img id="propic" src="/propic/default-propic.png" alt="프로필 사진" class="img-rounded">
      <div id="userMail"><p id="user_data_email"></p></div>
    </div>          
    <div class="col-sm-9" id="mypage_nav">
      <button type="button" class="btn btn-default btn-xl" id="update-start-btn">회원 정보 변경하기</button>
      <hr id="hr-line">
      
      <input type="hidden" name="startNo" value="1">
      <input type="hidden" name="endNo" value="6"> 
      <a class="page-scroll" href="#Estimates"><button type="button" class="btn btn-default btn-xl" id="esti-start-btn">큐레이팅 목록 보기</button></a>
      
    </div>
    <div class="col-sm-9" id="div-update">
    
    
     <form id="update-form" enctype="multipart/form-data" action="/app/user/updateUser" method="POST">
       
       <div class="form-group">
         <label for="update-email" id="update-email-label">이메일주소 (필수)</label>
         <p id="update-email-output" class="check-output"></p>
         <input type="email" class="form-control" id="update-email" name="email">  
       </div>
       <div class="form-group">
         <label for="input-pwd">비밀번호 (필수)</label>
         <p id="update-pwd-check-output" class="check-output"></p> 
         <input type="password" class="form-control" id="update-pwd" placeholder="비밀번호를 입력하세요." name="pwd">
         <input type="password" class="form-control" id="update-pwd-check" placeholder="비밀번호를 다시 입력하세요.">
       </div>
       <div class="form-group">
         <label for="porpic-upload">프로필 사진 (선택)</label>
         <p id="update-propic-upload-output" class="check-output"></p>
         <input type="file" id="update-propic-upload" onchange="readUpdateURL(this)" class="btn btn-default" name="propic">
       </div>
       <button type="submit" class="btn btn-default" id="update-complete">변경하기</button>
       <button type="button" class="btn btn-default" id="backMove">돌아가기</button>
       <button type="button" class="btn btn-default" id="leave-btn">회원탈퇴</button>
     </form>
         
    </div>
    
  </div> 
    
      </div> <!-- header-content-inner -->
    </div> <!-- header-content -->
  </header>

<!-- Estimate 데이터 가져오기 -->
  <section class="bg-primary" id="Estimates">
     <div class="myEstimate">
      <h2>큐레이팅 목록 확인!</h2>
     </div>
    
    <div class="row estimateList"></div>
  </section>
  
  <!-- 큐레이팅 체인지 -->
    <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h4 class="modal-title">리스트 변경 페이지</h4>
        </div>
        <div class="modal-body 123">
          <p>변경할 제품을 선택해주세요</p>
          <hr class="li_hr">
          <input type="hidden" name="startNum" value="11">
          <p class="prodList "></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" class="moreProd" onclick="getMoreProd();">More</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

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
<script src="/js/user/mypage.js"></script>
<script src="/js/user/estimate.js"></script>

<script src="/js/common/session.js"></script>
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
</body>
</html>