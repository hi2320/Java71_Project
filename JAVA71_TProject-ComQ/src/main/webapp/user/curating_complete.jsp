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
  <title>Computer Curating CompletePage</title>

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

<link rel="stylesheet" href="/css/complete.css" type="text/css">
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


   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">제품 변경 페이지</h4>
        </div>
        <div class="modal-body">
          <p>변경할 제품을 선택해주세요</p>
          <hr>
          <input type="hidden" name="startNum" value="11">
          <p class="prodList"></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" id="moreProd">More</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>


      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>
  
<!-- 큐레이팅 완료 페이지 시작 -->
  <header>
    <div class="header-content">
      <div class="header-content-inner">
    	<h1>큐레이팅 완료!</h1>
    	<p>당신에게 최적화된 컴퓨터 입니다. 다른 부품을 원하시면 CHANGE 기능을 통해 변경해주세요.</p>
    	<a href="#cp_move" class="btn btn-primary btn-xl page-scroll">REView Curating</a>
      </div> <!-- header-content-inner -->
    </div> <!-- header-content -->
  </header>

  <section class="bg-primary" id="cp_move">
    <div class="panel-group" id="accordion">
  <input type="hidden" name="curId" value="${curId }">
   	<c:forEach var="curators" items="${data }" varStatus="i">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <button type="button" class="btn btn-lg btn-info btn-width ${i.index == 0 ? '':'collapsed' }" data-toggle="collapse" data-parent="#accordion"
           data-target="#collapse${i.index }">${data[i.index].maker} ${data[i.index].prod_name}</button>
        </h4>
      </div>
      <div id="collapse${i.index }" class="panel-collapse collapse ${i.index == 0 ? 'in':'' }" name="targetData">
        <div class="panel-body row">
        	<div class="li_wrap">
				  <input type="hidden" class="prod_id" name="prodId" value="${data[i.index].prod_id }">
                <div class="col-sm-2">
                  <img class="image_url" src="${data[i.index].image_url }">
                </div>
                <div class="li_info1 col-sm-10">
                    <div class="product_name">
                        <p class="maker">${data[i.index].maker}</p> 
                        <p class="prod_name">${data[i.index].prod_name}</p>
                    </div>
                    <div class="summary">${data[i.index].summary}
                    </div>
                </div>
                <div class="row">
	                <div class="compl_price price col-sm-2">${data[i.index].min_price}원</div>
	                <div class="select_button col-sm-6">
	                  <a class="compl_change btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" onclick="getProdList(this);">변경하기</a>
			              <input type="hidden" name="url" value="${keyword[i.index] }">
	                </div>
                </div>
            </div>
		</div>
      </div>
    </div>
    </c:forEach>
    
  </div> 
  <div id="cur_save_btn">
    <a class="btn btn-default btn-primary" id="curatingSave">저장하기</a>
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
<script src="/js/user/complete.js"></script>

</body>
</html>