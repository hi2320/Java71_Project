<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
    <html>
      <head>
         <head>
   <meta charset="UTF-8">
    <title>ComQ 질문 관리 페이지.</title>
    <link rel="stylesheet" href="../css/reset.css">
      
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <link href="http://fonts.googleapis.com/css?family=Montserrat:700,400" rel="stylesheet" type="text/css">
   
    <link rel="stylesheet" href="../css/question_page.css">
    <style>
                     
    </style>
  </head>
      </head>
      
      <body>
       <div class="main_wrap">
      <div class="header_wrap">
        <div id="header">
          <div class="mainlogo4">ComQ
            <span class="pagename">큐레이팅</span>
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
            <span class="glyphicon glyphicon-user user-icon-size " aria-hidden="true"></span>
          </div>
        </div>
      </div>
      <div class="question_area size">
        <div id="accordion" >
        <c:forEach var="questions" items="${list }">
          <div>${questions.questionCode}. ${questions.questionText}</div>
          <div >
            <c:forEach var="answers" items="${questions.answers }">
	            <div>
	              <input type="${questions.answerFormat}" value="" >
	              <label  for="">${answers.answerText}</label>
	            </div>
	        </c:forEach>     
          </div>
		</c:forEach>
        </div>
      </div>
      
    <div class="footer">
        <button type="button" class="btn btn-info db_send_btn"><a href="javascript:history.go(-1)">저장</a></button>
      </div>
    </div>
     </body>
<script>
        $(function() {
          $( "#accordion" ).accordion();
        });
      </script>
    </html>
          
          
        
          