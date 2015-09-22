<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <c:forEach var="curators" items="${data }" varStatus="i">
	<div class="li_wrap">
		<input type="hidden" class="prod_id" name="prodId" value="${data[i.index].prod_id }">
        <img class="col-sm-2 image_url" src="${data[i.index].image_url }">
        <div class="col-sm-10 li_info1">
            <div class="product_name">
                <p class="maker">${data[i.index].maker}</p> 
                <p class="prod_name">${data[i.index].prod_name}</p>
            </div>
            <div class="summary">${data[i.index].summary}
            </div>
        </div>
        <div class="col-sm-10 col-sm-offset-2 li_info2">
            <div class="col-sm-3 price">${data[i.index].min_price}원</div>
            <div class="row col-sm-2 col-sm-offset-6 select_button">
             	<button type="button" class="changeBtn btn btn-lg btn-info" data-dismiss="modal" onclick="javascript:prodChange(this);">변경</button>
     			<p id="summaryInfo">${data[i.index]}</p>
            </div>
        </div>
        <hr class="li_hr">
    </div>
  </c:forEach>
 

