<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link href="../../css/reset.css" type="text/css" rel="stylesheet">
<!--        <link href="css/curatingList.css" type="text/css" rel="stylesheet">-->
<!--  <link rel="stylesheet" href="../../css/question_page.css"> -->
<script src="../../js/jquery-1.11.3.js"></script>


<style>
.curating_wrap {
	position: relative;
	width: 100%;
	height: 100%;
	margin: 0 auto;
}

.product_name {
	font-weight: bold;
	margin: 5px 0px;
}

#curating_list {
	position: relative;
	width: 1024px;
	margin: 40px auto 20px;
}

#list_wrap {
	position: relative;
	width: 770px;
	float: left;
	border-radius: 5px;
	background-color: #353535;
}

.cate {
	position: relative;
	width: 95%;
	height: 55px;
	margin: 8px auto 0px;
	padding-left: 20px;
	border-radius: 10px;
	background-color: #696969;
	transition: height 0.3s;
}

.show {
	height: 240px;
}
.principal_components {
    position: relative;
    height: 50px;
    border: 5px solid;

}
    .principal_components .cate {
        
    }

.slide_cate {
	height: 50px;
	font-size: 1.2em;
    font-weight: 900;
	cursor: pointer;
	border-bottom: 1px solid black;
}
    .slide_cate .cate_icon {
        width: 40px;
        height: 40px;
        margin-top: 5px;
        background-color: yellow;
    }
    .slide_cate .cate_number {
        vertical-align: top;
        line-height: 2.5;
    }

.cate:last-Child {
	margin-bottom: 8px;
}

.curating_list {
	display: none;
	position: relative;
	width: 100%;
	height: 200px;
	border-radius: 5px;
	padding: 3px 5px;
	margin: 8px 0px 0px -17px;
	border-top: 1px solid white;
}

.li_wrap {
	position: relative;
	margin: 8px 0px 2px;
}

    .li_wrap .image_url {
        width: 130px;
        float: left;
    }

    .li_wrap .li_info1 {
        width: 76%;
        float: left;
        padding: 5px 0px 5px 10px;
    }

    .li_wrap .li_info2 {
        width: 30%;
        float: right;
        margin-right: 20px;
        font-weight: 600;
    }

    .li_wrap .price {
        text-align: left;
        font-size: 1.2em;
        color: aliceblue;
        margin-top: 6px;
        float: left;
    }

    .li_wrap .select_button {
        float: right;
        cursor: pointer;
    }
    
.li_hr {
	clear: both;
	background-color: firebrick;
	margin-right: 5px;
	height: 3px;
}

</style>
</head>
<body>
	<div class="curating_wrap">

		<div id="curating_list">
			<div id="list_wrap">
			
			<form name="" method="" action="">
			<c:forEach var="curators" items="${data }" varStatus="i">
			
				<div class="cate">
					<div class="slide_cate">
						<img class="cate_icon"></img> 
                        <span class="cate_number">${data[i.index].maker}&nbsp;${data[i.index].prod_name}</span>
						
					</div>
					<div class="curating_list" style="display: block">

						<div class="li_wrap">
							<img class="image_url" src="${data[i.index].image_url }">
							<div class="li_info1">
								<div class="product_name">
									<p class="maker">${data[i.index].maker}</p> 
                                    <p class="prod_name">${data[i.index].prod_name}</p>
								</div>
								<div class="summary">${data[i.index].summary}
                                </div>
							</div>
							<div class="li_info2">
								<div class="price">${data[i.index].min_price}원</div>
								<div class="select_button">
									<img type="button" src="../../images/select-button-mini.png">
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

		<script type="text/javascript">
		$(document).ready(function() {
			$('.curating_list').hide();
			$('.cate:first').addClass('show');
			$('.cate:first').children().next().show();
		});
			$('.slide_cate').click( function(event) {
                event.stopPropagation();
                if(!($(this).parent().parent().hasClass('principal_components'))) {
                    $('.principal_components').children().removeClass('show').children().next().slideUp('fast');
                }
                $(this).parent().siblings().removeClass('show').children().next().slideUp('fast');
                $(this).parent().toggleClass('show').children().next().slideToggle('fast');
            });
		</script>
</body>
</html>