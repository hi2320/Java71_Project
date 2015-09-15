<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
              <input type="text" name="startNum" value="10">
              <input type="text" name="keyword" value="10">
<form name="" method="" action="">
              <c:forEach var="curators" items="${data }" varStatus="i">

                <div class="cate1">
                  <div class="slide2_cate">
                    <img class="cate_ic123on"></img> <span class="cate_number">${data[i.index].maker}&nbsp;${data[i.index].prod_name}</span>

                  </div>
                  <div class="curatin123g_list" style="display: block">

                    <div class="li_wr123ap">
                      <img class="imag123e_url" src="${data[i.index].image_url }">
                      <div class="li_123info1">
                        <div class="pro123duct_name">
                          <p class="ma123ker">${data[i.index].maker}</p>
                          <p class="pr123od_name">${data[i.index].prod_name}</p>
                        </div>
                        <div class="sum123mary">${data[i.index].summary}</div>
                      </div>
                      <div class="li_i123nfo2">
                        <div class="pri123ce">${data[i.index].min_price}원</div>
                        <div class="sele123ct_button">
                          <button type="button" class="changeBtn" data-dismiss="modal" onclick="javascript:prodChange(this);">바꾸기</button>
                          <p>${data[i.index]}</p>
                        </div>
                      </div>
                    </div>
                    <hr class="li_hr">
                  </div>
                </div>
              </c:forEach>
            </form>
            <script>
            var prodInfo;
            function prodChange(btn) {
                  prodInfo = $(btn).next().text();
                  var jsonObj = JSON.parse(prodInfo);
                  console.log(jsonObj.maker);
                  $(chagneBtnParent).find("[class=cate_number]").text(jsonObj.maker +" "+ jsonObj.prod_name);
                  $(chagneBtnParent).find("[class=image_url]").attr("src", jsonObj.image_url);
                  $(chagneBtnParent).find("[class=maker]").text(jsonObj.maker);
                  $(chagneBtnParent).find("[class=prod_name]").text(jsonObj.prod_name);
                  $(chagneBtnParent).find("[class=summary]").text(jsonObj.summary);
                  $(chagneBtnParent).find("[class=price]").text(jsonObj.min_price+"원");
                  $("#myModal").toggleClass("in").css("display", "none");
                  $(".modal-open").trigger("click");
              }
            </script>