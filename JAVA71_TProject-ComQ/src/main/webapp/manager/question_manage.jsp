<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <!DOCTYPE html>
 <html>

	<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../css/reset.css">
    <title>ComQ 질문 관리 페이지 Manage.</title>
    <link rel="stylesheet"  href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet"  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <link href="http://fonts.googleapis.com/css?family=Montserrat:700,400" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="../../css/question_manage.css">
    
		<style>
			
		</style>
	</head>

	<body>
		<div class="main_wrap">
			<div class="header_wrap">
				<div id="header">
					<div class="mainlogo4">
						<a href="/index_.html">ComQ</a>
						<span class="pagename">질문 관리페이지</span>
					</div>
					<div class="log-form">
						<div class="log-input">
							<p>
								<input class="form-control" type="text" placeholder=" I D ">
							</p>
							<p>
								<input class="form-control" type="text" placeholder=" P A S S W O R D ">
							</p>
						</div>
						<span class="glyphicon glyphicon-user user-icon-size "
							aria-hidden="true"></span>
					</div>
				</div>

			</div>
		<form name="questionForm" method="post" action="/app/curator/updateQuestionPage">
	  
			<div class="aside">
				<select class="form-control curating_code" onchange="curatorPageChange(this[this.selectedIndex].value)">
					<option value="1" ${list.curId eq '1' ? 'selected':'' }>사무용PC</option>
					<option value="2" ${list.curId eq '2' ? 'selected':'' }>멀티미디어 감상용 PC</option>
					<option value="3" ${list.curId eq '3' ? 'selected':'' }>게이밍 PC</option>
					<option value="4" ${list.curId eq '4' ? 'selected':'' }>멀티미디어 편집 PC</option>
					<option value="5" ${list.curId eq '5' ? 'selected':'' }>프로그래밍 PC</option>
				</select>
				<input type="hidden" name="purpose" >
				<button type="button" class="btn btn-info addbtn" onclick="questionAdd()">+
				</button>
			</div>

			<div class="question_admin_area">
				<div class="question_answer_area">
					<div class="question">
						<div id="accordion">
							<c:if test="${empty list.questionList }">
							<div class="question_box">
								<select class="form-control product_value">
									<option>모니터</option>
									<option>마우스</option>
									<option>키보드</option>
									<option>CPU</option>
									<option>메인보드</option>
								</select>

								<select class="form-control answer_value">
									<option>checkbox</option>
									<option>radio</option>
								</select>
								<input type="text" class="form-control question_texts" placeholder="질문 문장을 입력하시오." value="">

                                <button type="button" class="btn btn-info deletebtn" onclick="questionDelete(this)">-</button>
							</div>
							
								<div class="answer_box">
									<div class="answer">
										<input type="text" class="answer_text form-control" placeholder="답변 문장을 입력하시오." value="">
	                                    <input type="text" class="answer_hidden form-control" placeholder="hidden_keyword" value="">
	                                    <button type="button" class="btn btn-info answerdeletebtn" onclick="answerDelete(this)">- </button>
	                                    <button type="button" class="btn btn-info answeraddbtn" onclick="answerAdd(this)">+ </button>
									</div>
								</div>
							</c:if>
							
							<c:forEach var="questions" items="${list.questionList }">
							<div class="question_box">
								<select class="form-control product_value">
									<option ${questions.qProd eq '모니터' ? 'selected':'' }>모니터</option>
									<option ${questions.qProd eq '마우스' ? 'selected':'' }>마우스</option>
									<option ${questions.qProd eq '키보드' ? 'selected':'' }>키보드</option>
									<option ${questions.qProd eq 'CPU' ? 'selected':'' }>CPU</option>
									<option ${questions.qProd eq '메인보드' ? 'selected':'' }>메인보드</option>
								
								</select>

								<select class="form-control answer_value">
									<option ${questions.qType eq 'check' ? 'selected':'' }>checkbox</option>
									<option ${questions.qType eq 'radio' ? 'selected':'' }>radio</option>
								</select>
								<input type="text" class="form-control question_texts" placeholder="질문 문장을 입력하시오." value="${questions.qSente }">

                                <button type="button" class="btn btn-info deletebtn" onclick="questionDelete(this)">-</button>
							</div>
							
							<div class="answer_box">
								<c:forEach var="answers" items="${questions.answerList }" varStatus="j">
								<div class="answer">
									<input type="text" class="answer_text form-control" placeholder="답변 문장을 입력하시오." value="${answers.aSente }">
                                    <input type="text" class="answer_hidden form-control" placeholder="hidden_keyword" value="${answers.aSpec }">
                                    <button type="button" class="btn btn-info answerdeletebtn" onclick="answerDelete(this)">- </button>
                                    <c:if test="${j.last}">
                                    <button type="button" class="btn btn-info answeraddbtn" onclick="answerAdd(this)">+ </button>
                                    </c:if>
								</div>
								</c:forEach>
							</div>
							</c:forEach>
						
						</div>
					</div>
				</div>

			</div>
			</form>
			<div class="footer">
				<button type="button" class="btn btn-info db_send_btn" onclick="sendQuestionManage()">저장</button>
			</div>
			<script type="text/javascript">
				var questionBox = $('.question_box').clone();
                var answerBox = $('.answer_box').clone();
                var accordion = $('#accordion');
				function questionDelete(id) {
                       event.stopPropagation();
                       var questionBoxs = $('.question_box');
					if(questionBoxs.length == 1) {
						alert('마지막 질문란입니다');
					} else {
                           $(id).parent().next().remove();
                           if($(id).parent().index() == ((questionBoxs.length - 1) * 2) || $(id).parent().hasClass('ui-accordion-header-active')) {
	                           $(id).parent().remove();
                               $('.question_box:last').trigger('click');
                           } else {
                        	   $(id).parent().remove();
                           }
					}
				}
				function questionAdd() {
                       var questionBoxClone = questionBox.clone();
                       questionBoxClone.find('input').val('');
                       accordion.append(questionBoxClone[0]);
                       var answerBoxTemp = $('<div class="answer_box"></div>');
                       var answerClone = answerBox.children().last().clone();
                       answerClone.find('input').val('');
                       answerBoxTemp.append(answerClone);
                       accordion.append(answerBoxTemp);
                       
                       accordion.accordion('refresh');
                       questionBoxClone.trigger('click');
				}
				
				function answerDelete(id) {
					var isLast = $(id).parent().next().hasClass('answer')
							|| $(id).parent().prev().hasClass('answer');
					if (isLast) {
						if ($(id).next().hasClass('answeraddbtn')) {
							$(id).parent().prev().append($(id).next());
						}
						$(id).parent().remove();
					} else {
						alert('마지막 답변란입니다');
					}
				}
				function answerAdd(id) {
					var thisForm = $(id).parent();
					var formClone = thisForm.clone();
					formClone.find('input').val('');
					formClone.appendTo(thisForm.parent());
					$(id).remove();
				}
				$(function() {
					$("#accordion").accordion();
				});
				
				function curatorPageChange(index) {
					$('form').attr('action', '/app/curator/changeQuestionPage?curId='+index);
					$('form').submit();
				}
				
				function sendQuestionManage() {
			        var questions = $('.question_box');

			        $('.curating_code').attr('name', 'curId');
			        $('[name=purpose]').val($('.curating_code option:selected').text());
			        for(var i = 0; i < questions.length; i++) {
			           var question = questions[i];
			           
			           $(question).find('[class*=question_texts]').attr('name', 'questionList['+i+'].qSente');
			           $(question).find('[class*=product_value]').attr('name', 'questionList['+i+'].qProd');
			           $(question).find('[class*=answer_value]').attr('name', 'questionList['+i+'].qType');
			            var answers = $(question).next().find('.answer');
			            for(var j = 0; j < answers.length; j++) {
			                var answer = answers[j];
			                $(answer).find('[class*=answer_text]').attr('name', 'questionList['+i+'].answerList['+j+'].aSente');
			                $(answer).find('[class*=answer_hidden]').attr('name', 'questionList['+i+'].answerList['+j+'].aSpec');
			            }
			        }
			        $('form').submit();
			    }
			</script>
	</body>
</html>