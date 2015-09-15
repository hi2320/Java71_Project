<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
<meta charset="UTF-8">
<title>ComQ 질문 페이지11.</title>
<link rel="stylesheet" href="../../css/reset.css">

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
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

<link rel="stylesheet" href="../../css/question_page.css">
</head>

<body>
	<div class="main_wrap">
		<div class="header_wrap">
			<div id="header">
				<div class="mainlogo4">
					ComQ <span class="pagename">큐레이팅</span>
				</div>
				<div class="log-form">
					<div class="log-input">
						<p>
							<input class="form-control" type="text" placeholder=" I D ">
						</p>
						<p>
							<input class="form-control" type="text"
								placeholder=" P A S S W O R D ">
						</p>
					</div>
					<span class="glyphicon glyphicon-user user-icon-size "
						aria-hidden="true"></span>
				</div>
			</div>
		</div>
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



