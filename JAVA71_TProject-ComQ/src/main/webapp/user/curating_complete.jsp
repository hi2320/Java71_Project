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
 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="/js/user/complete.js"></script>
  
  <!-- Custom Fonts -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
   

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]--> 
<link rel="stylesheet" href="/css/user.css" type="text/css">
<link rel="stylesheet" href="/css/complete.css" type="text.css">
</head>

<body>
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">Start ComQ</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="page-scroll" href="#about">About</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#services">Services</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#portfolio">Curating</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#contact">Contact Us</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
<!-- 큐레이팅 완료 리스팅 -->
<div class="container">
  <h2>Collapse</h2>
  <p><strong>Note:</strong> The <strong>data-parent</strong> attribute makes sure that all collapsible elements under the specified parent will be closed when one of the collapsible item is shown.</p>
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
        <div class="panel-body">
        	<div class="li_wrap">
				<input type="hidden" class="prod_id" name="prodId" value="${data[i.index].prod_id }">
                <img class="image_url" src="${data[i.index].image_url }">
                <div class="li_info1">
                    <div class="product_name">
                        <p class="maker">${data[i.index].maker}</p> 
                        <p class="prod_name">${data[i.index].prod_name}</p>
                    </div>
                    <div class="summary">${data[i.index].summary}
                    </div>
                </div>
                    <div class="compl_price price">${data[i.index].min_price}원</div>
                    <div class="select_button">
                        <button type="button" class="compl_change btn btn-info btn-lg"
							data-toggle="modal" data-target="#myModal"
							onclick="getProdList(this);">Change</button>
						<input type="hidden" name="url"
							value="${keyword[i.index] }">
                    </div>
                
            </div>
		</div>
      </div>
    </div>
    </c:forEach>
    <button type="button" id="curatingSave">완료</button>
  </div> 
</div>
    
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
					<hr>
					<input type="hidden" name="startNum" value="11">
					<p class="prodList "></p>
				</div>
				<div class="modal-footer">
				  <button type="button" class="btn btn-default" id="moreProd">More</button>
				  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	

</body>
</html>