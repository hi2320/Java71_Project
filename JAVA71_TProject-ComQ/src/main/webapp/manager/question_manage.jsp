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
<link rel="stylesheet" href="/css/question_manage.css" type="text/css">
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
      </div>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
  </nav>
  
<!-- 큐레이팅 질문 페이지 시작 -->
  <header>
    <div class="header-content" style="min-height: 300px;">
      <div class="header-content-inner">
    	<h1>큐레이팅 관리하기!</h1>
    	<p></p>
    	<a href="#start-curating" class="btn btn-primary btn-xl page-scroll" id="cur_move">CREATE QUESTION FOR CURATING</a>
      </div> <!-- header-content-inner -->
    </div> <!-- header-content -->
  </header>

  <section class="bg-primary" id="start-curating">
  
  <!-- 사용목적에 따라 질문데이터 가져오기 -->
   <form name="curatingForm" method="post" id="cur-form"
        action="/app/curator/updateQuestionPage">
      
	<div class="nav purpose">
		<select class="curating_code" onchange="curatorPageChange(this[this.selectedIndex].value)">
			<option value="1" ${list.curId eq '1' ? 'selected':'' }>사무용PC</option>
			<option value="2" ${list.curId eq '2' ? 'selected':'' }>멀티미디어 감상용 PC</option>
			<option value="3" ${list.curId eq '3' ? 'selected':'' }>게이밍 PC</option>
			<option value="4" ${list.curId eq '4' ? 'selected':'' }>멀티미디어 편집 PC</option>
			<option value="5" ${list.curId eq '5' ? 'selected':'' }>프로그래밍 PC</option>
		</select>
		<input type="hidden" name="purpose" >
		<button type="button" class="btn btn-info question-add-btn" onclick="questionAdd()">+ 질문 추가하기</button>
	</div>
  
    <div class="question_area size">
       <div id="accordian">
       	<c:if test="${empty list.questionList }">
        <ul>
        	<li class="question_list">
        		<h3 class="question">
					<select class="product_value">
						<option>모니터</option>
						<option>마우스</option>
						<option>키보드</option>
						<option>CPU / 메인보드</option>
						<option>VGA / 파워</option>
					</select>

					<select class="answer_value">
						<option>checkbox</option>
						<option>radio</option>
					</select>
					<input type="text" class="question_texts" placeholder="질문 문장을 입력하시오." value="">
                       <button type="button" class="btn btn-info delete-btn" onclick="questionDelete(this)">-</button>
				</h3>
             		
             		<ul class="answer_list">
             		  <li class="answer">
					  <input type="text" class="answer_text" placeholder="답변 문장을 입력하시오." value="">
                         <input type="text" class="answer_hidden" placeholder="hidden_keyword" value="">
                         <button type="button" class="btn btn-info answer-delete-btn" onclick="answerDelete(this)">- </button>
                         <button type="button" class="btn btn-info answer-add-btn" onclick="answerAdd(this)">+ </button>
             		  </li>
             		</ul>
        	</li>
		</ul>	        	
       	</c:if>
       	
   	   	<c:if test="${!empty list.questionList }">
   		<c:forEach var="questions" items="${list.questionList }">
       	<ul>
			<li class="question_list">
	       		<h3 class="question">
					<select class="product_value">
						<option ${questions.qProd eq '모니터' ? 'selected':'' }>모니터</option>
						<option ${questions.qProd eq '마우스' ? 'selected':'' }>마우스</option>
						<option ${questions.qProd eq '키보드' ? 'selected':'' }>키보드</option>
						<option ${questions.qProd eq 'CPU' ? 'selected':'' }>CPU / 메인보드</option>
						<option ${questions.qProd eq 'VGA' ? 'selected':'' }>VGA / 파워</option>
					</select>
					<select class="answer_value">
						<option ${questions.qType eq 'check' ? 'selected':'' }>checkbox</option>
						<option ${questions.qType eq 'radio' ? 'selected':'' }>radio</option>
					</select>
					<input type="text" class="question_texts" placeholder="질문 문장을 입력하시오." value="${questions.qSente }">
	                      <button type="button" class="btn btn-info delete-btn" onclick="questionDelete(this)">-</button>
				</h3>
				<ul class="answer_list">
	       		  <c:forEach var="answers" items="${questions.answerList }" varStatus="j">
					<li class="answer">
					  <input type="text" class="answer_text" placeholder="답변 문장을 입력하시오." value="${answers.aSente }">
                      <input type="text" class="answer_hidden" placeholder="hidden_keyword" value="${answers.aSpec }">
                      <button type="button" class="btn btn-info answer-delete-btn" onclick="answerDelete(this)">- </button>
                      <c:if test="${j.last}">
                      	<button type="button" class="btn btn-info answer-add-btn" onclick="answerAdd(this)">+ </button>
                      </c:if>
                   </li>
				  </c:forEach>
	       		</ul>
	       	</li>
        </ul>
		</c:forEach>
		</c:if>
       </div>
     </div>
     </form>
      <div id="save_btn">
      	<a href="javascript:sendQuestionManage()" class="btn btn-default db_send_btn">저장 </a>
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
<script src="/js/question-manage.js"></script>
<!-- <script src="/js/common/arccodian.js"></script> -->
</body>
</html>