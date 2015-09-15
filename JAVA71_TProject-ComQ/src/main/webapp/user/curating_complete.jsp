<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
<meta charset="UTF-8">
<title>ComQ 질문 페이지11.</title>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- Latest compiled and minified CSS -->
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->


<link href="http://fonts.googleapis.com/css?family=Montserrat:700,400"
	rel="stylesheet" type="text/css">

<link rel="stylesheet" href="../../css/question_page.css">
<link href="../../css/reset.css" type="text/css" rel="stylesheet">
<!--        <link href="css/curatingList.css" type="text/css" rel="stylesheet">-->
<!--  <link rel="stylesheet" href="../../css/question_page.css"> -->
<!-- <script src="../../js/jquery-1.11.3.js"></script> -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
</head>

<body>
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
					<h4 class="modal-title">리스트 변경 페이지</h4>
				</div>
				<div class="modal-body 123">
					<p>변경할 제품을 선택해주세요</p>
					<p class="prodList "></p>
				</div>
				<div class="modal-footer">
				  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

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
		<div class="question_complete_area">
			<div class="curating_wrap">

				<div id="curating_list">
					<div id="list_wrap">

						<form name="" method="" action="">
							<c:forEach var="curators" items="${data }" varStatus="i">

								<div class="cate">
									<div class="slide_cate">
										<img class="cate_icon"></img> <span class="cate_number">${data[i.index].maker}&nbsp;${data[i.index].prod_name}</span>

									</div>
									<div class="curating_list" style="display: block">

										<div class="li_wrap">
											<img class="image_url" src="${data[i.index].image_url }">
											<div class="li_info1">
												<div class="product_name">
													<p class="maker">${data[i.index].maker}</p>
													<p class="prod_name">${data[i.index].prod_name}</p>
												</div>
												<div class="summary">${data[i.index].summary}</div>
											</div>
											<div class="li_info2">
												<div class="price">${data[i.index].min_price}원</div>
												<div class="select_button">
													<button type="button" class="btn btn-info btn-lg"
														id="modalW" data-toggle="modal" data-target="#myModal"
														onclick="getProdList(this);">Change</button>
													<input type="hidden" name="url"
														value="${keyword[i.index] }">
												</div>
											</div>
										</div>
										<hr class="li_hr">
									</div>
								</div>
							</c:forEach>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('.curating_list').hide();
			$('.cate:first').addClass('show');
			$('.cate:first').children().next().show();
		});
		$('.slide_cate').click(
				function(event) {
					event.stopPropagation();
					if (!($(this).parent().parent()
							.hasClass('principal_components'))) {
						$('.principal_components').children().removeClass(
								'show').children().next().slideUp('fast');
					}
					$(this).parent().siblings().removeClass('show').children()
							.next().slideUp('fast');
					$(this).parent().toggleClass('show').children().next()
							.slideToggle('fast');
				});
	</script>
	<script>
	  var chagneBtnParent;
		function getProdList(btn) {
			$(".prodList").children().remove();
			console.log($(".btn").index());
			var keyword = $(btn).next().val();
			console.log(keyword);
			$.ajax({
				url : '/app/curator/curatingChangeProd',
				data : 'keyword=' + keyword,
				type : 'post',
				success : function(data) {
					$(".prodList").append(data);
					chagneBtnParent = $(btn).parent().parent().parent().parent().parent();
				}
			});
		}

		$('#modalW').on(
				'click',
				function() {
					$(window).on(
							"scroll",
							function() {
								console.log(openModal);
								console.log($(window).scrollTop());
								console.log($(window).height()
										- $(window).height() - 100);
								if ($(window).scrollTop() >= $(document)
										.height()
										- ($(window).height() - 100)) {
									console.log("bottom");
									setTimeout(function() {
										fnUnlimitedScroll();
									}, 800);
								}
							});
				});

		var fnUnlimitedScroll = function() {
			$.ajax({
				url : "/app/curator/getListAppend",
				data : "keyword=" + keyword + "&startNo="
						+ $("[name=startNum]").val(),
				dataType : 'html',
				type : 'post',
				success : function(data) {
					$(".prodList").append(data);
					$("[name=startNum]").val($("[name=startNum]").val() + 10);
					clearTimeout(fnUnlimitedScroll());
				}
			});
		};
	</script>
</body>
</html>



