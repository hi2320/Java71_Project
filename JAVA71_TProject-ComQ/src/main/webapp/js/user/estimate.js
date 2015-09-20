$('#esti-start-btn').on('click', function() {
	getEstimateList();
});  

function getEstimateList() {
      console.log("getEstimateList function");
      $.ajax({
        url : "/app/curator/userEstimateList",
        data: {
          startNo : $('[name=startNo]').val(),
          endNo : $('[name=endNo]').val()
        },
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        timeout: 10000,
        type: "POST",
        dataType: "JSON",
        beforeSend: function() {
          $("#viewLoading").css({
          "position" : "fixed",
          "left" : 0,
          "top" : 0,
          "width" : $(window).width(),
          "height" :screen.height
        }).fadeIn(200);
          },
        success : function(data) {
          /* $('.estimateList').append(); */
          $.each(data, function(index, item) {
            var space = /\+/g;
                var newData = JSON.parse(decodeURIComponent(data[index].replace(space, ' ')));
                var divEstimate = $('<div id="pageScroll'+index+'" class="col-sm-4 estimate"></div>'),
              aTag = $('<a href="#pageScroll'+index+'" class="estimateA"></a>'),
                imgTag = $('<img src="/img/portfolio/1.jpg" alt="사무용PC">'),
                divTextPart = $('<div class="col-sm-8 col-lg-offset-2 text-center"></div>'),
                estimateName = $('<h2 class="section-heading"></h2>'),
                estiamteDate = $('<p></p>').text(newData.estDate);
              
              /* $(estimateDate).text(newData.estDate); */
              /* $(estimateName).text(newData.estName); */
              var imgsrc,
                imgalt;
              
              switch (parseInt(newData.curId)) {
                case 1: imgsrc = "/img/portfolio/1.jpg"; imgalt = "사무용PC"; break;
                case 2: imgsrc = "/img/portfolio/2.jpg"; imgalt = "게임용PC"; break;
                case 3: imgsrc = "/img/portfolio/3.jpg"; imgalt = "디자인PC"; break;
                case 4: imgsrc = "/img/portfolio/4.jpg"; imgalt = "일체형PC"; break;
                case 5: imgsrc = "/img/portfolio/5.jpg"; imgalt = "조립PC"; break;
                case 6: imgsrc = "/img/portfolio/6.jpg"; imgalt = "PC부품"; break;
              }
              aTag = $(aTag).attr('onclick', 'javascript:getThisEstimateProdList(this, '+ newData.estId +');');
              imgTag = $(imgTag).attr('src', imgsrc).attr('alt',imgalt);
              estimateName = $(estimateName).text(newData.estName);
              divTextPart = $(divTextPart).append($(aTag).clone().append(estimateName)).append(estiamteDate);
              divEstimate = $(divEstimate).append($(aTag).clone().append(imgTag)).append(divTextPart);
              $('.estimateList').append(divEstimate);
        });
        },
        complete : function() {
          /* 완료되면 로딩이미지 제거 */
          $('[name=startNo]').val(parseInt($('[name=startNo]').val())+6);
          $('[name=endNo]').val(parseInt($('[name=endNo]').val())+6);
          $('.myEstimate').slideUp("fast");
          $('#viewLoading').fadeOut(300);
           infinityScroll(); 
          
        },
        error : function() {
          
        }
      })
    }; 
    
    /* unlimited scroll */
    function infinityScroll() {
    console.log("infinityScroll function");
      $(window).on( "scroll", function() {
        if (($(window).scrollTop()) >= ($(document).height() - $(window).height())) {
          setTimeout(function() {
            console.log("infinityScroll setTimeout function");
            $.ajax({
                url : "/app/curator/userEstimateList",
                data: {
                  startNo : $('[name=startNo]').val(),
                  endNo : $('[name=endNo]').val()
                },
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                timeout: 10000,
                type: "POST",
                dataType: "JSON",
                beforeSend: function(xhr, settings) {
                  $("#viewLoading").css({
                  "position" : "fixed",
                  "left" : 0,
                  "top" : 0,
                  "width" : $(window).width(),
                  "height" :screen.height
                }).fadeIn(200);
                  },
                success : function(data) {
                    console.log("무한 성공ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
                if(data.length == 0) {
                  console.log("data 없으면 무한스크롤 종료");
                  $(window).unbind(infinityScroll());
                }
                  $.each(data, function(index, item) {
                      var countNo = parseInt($('[name=startNo]').val())+index-1;
                    var space = /\+/g;
                        var newData = JSON.parse(decodeURIComponent(data[index].replace(space, ' ')));
                      var divEstimate = $('<div id="pageScroll'+countNo+'" class="col-sm-4 estimate"></div>'),
                        aTag = $('<a href="#pageScroll'+countNo+'" class="estimateA"></a>'),
                        imgTag = $('<img src="/img/portfolio/1.jpg" alt="사무용PC">'),
                        divTextPart = $('<div class="col-sm-8 col-lg-offset-2 text-center"></div>'),
                        estimateName = $('<h2 class="section-heading"></h2>'),
                        estiamteDate = $('<p></p>').text(newData.estDate); 
                      
                      var imgsrc,
                        imgalt;
                      
                      switch (parseInt(newData.curId)) {
                        case 1: imgsrc = "/img/portfolio/1.jpg"; imgalt = "사무용PC"; break;
                        case 2: imgsrc = "/img/portfolio/2.jpg"; imgalt = "게임용PC"; break;
                        case 3: imgsrc = "/img/portfolio/3.jpg"; imgalt = "디자인PC"; break;
                        case 4: imgsrc = "/img/portfolio/4.jpg"; imgalt = "일체형PC"; break;
                        case 5: imgsrc = "/img/portfolio/5.jpg"; imgalt = "조립PC"; break;
                        case 6: imgsrc = "/img/portfolio/6.jpg"; imgalt = "PC부품"; break;
                      }
                      aTag = $(aTag).attr('onclick', 'getThisEstimateProdList(this, '+ newData.estId +');');
                      imgTag = $(imgTag).attr('src', imgsrc).attr('alt',imgalt);
                      estimateName = $(estimateName).text(newData.estName);
                      divTextPart = $(divTextPart).append($(aTag).clone().append(estimateName)).append(estiamteDate);
                      divEstimate = $(divEstimate).append($(aTag).clone().append(imgTag)).append(divTextPart);
                      $('.estimateList').append(divEstimate);
                });
                },
                complete : function() {
                  $('[name=startNo]').val(parseInt($('[name=startNo]').val())+6);
                  $('[name=endNo]').val(parseInt($('[name=endNo]').val())+6);
                  $("#viewLoading").fadeOut(200);
                },
                error : function() {
                  
                }
              })
          }, 800);
        }
      });
    };
    
    var estId;
    /* get Estimate prodlist */
    function getThisEstimateProdList(est, est_id) {     
      console.log('est_id 가져오는 중');
      estId = est_id;
      if($('#prod'+est_id+'').length == 0) {
      console.log("getThisEstimateProdList if is true");
      var estimateDiv,
        estimateDivIndex;
      var estimateDivs = $('.estimate');
      var arrowUp = $('<div class="arrow-up"></div>');
      if($(est).parent().hasClass('estimate')) {
        estimateDiv = $(est).parent();
      } else {
        estimateDiv = $(est).parent().parent();
      }
      
      estimateDivIndex = $(estimateDiv).index() + 1 - ($(estimateDiv).prevAll('.getEstimateResult').length);
      
      $('.getEstimateResult').remove();
      $('.arrow-up').remove();
      
          $.ajax({
            url : '/app/curator/getEstimate?estId='+est_id,
            type: 'get',
            dataType : 'json',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            timeout: 10000,
            beforeSend : function() {
              $("#viewLoading").css({
              "position" : "fixed",
              "left" : 0,
              "top" : 0,
              "width" : $(window).width(),
              "height" :screen.height
            }).fadeIn(500);
            },
            success : function(data) {
              $.each(data, function(index, item){
              var space = /\+/g;
              var newData = JSON.parse(decodeURIComponent(data[index].replace(space, ' ')));
              var estimate_result = $('<div id="prod'+ est_id +'" class="getEstimateResult col-sm-12" style="display:none;"></div>'),
                estimate_prod = $('<div class="estProd"></div>'),
                prod_box = $('<div class="row prod_box"></div>'),
                img_div = $('<div class="row col-sm-2 prod_img"></div>'),
                prod_info_div = $('<div class="row col-sm-9 col-sm-offset-1 prod_info"></div>');
              
              var prod_img = $('<img class="col-sm-2" src="'+ newData.imageUrl +'"alt="'+ newData.imageUrl +'">'),
                prod_name = $('<h3 class="col-sm-12 prod_name">'+ newData.maker +' '+ newData.prodName +'</h3>'),
                prod_summary = $('<p class="col-sm-12 prod_summary">'+ newData.summary +'</p>'),
                prod_price = $('<p class="col-sm-8 prod_price">'+ newData.price +'원</p>'),
                change_button = $('<a class="col-sm-2 prod_change_btn btn btn-default btn-sm" data-toggle="modal" data-target="#myModal" onclick="getProdList(this, '+newData.prolId+');">바꾸기</a>');
              var keyword = $('<input type="hidden" name="url" value="'+newData.prodKind+' '+ newData.prodKeyword +'">');


              prod_info_div = $(prod_info_div).append(prod_name).append(prod_summary).append(prod_price).append(change_button).append(keyword);
              img_div = $(img_div).append(prod_img);
              prod_box = $(prod_box).append(img_div).append(prod_info_div);
              estimate_prod = $(estimate_prod).append(prod_box);
              estimate_result = $(estimate_result).append(estimate_prod);
              $(estimateDiv).append(arrowUp);
              

              console.log("getThisEstimateProdList if is true ajax if syntax");
              console.log()
              if (estimateDivIndex % 3 == 0) { /* 3번째 */
                $(estimateDivs[estimateDivIndex-1]).after($(estimate_result));
              } else if (estimateDivIndex % 3 == 1) { /* 1번째 */
                
                if($(estimateDivs[estimateDivIndex+1]).length == 0) {
                  if($(estimateDivs[estimateDivIndex]).length == 0) {
                    $(estimateDivs[estimateDivIndex-1]).after($(estimate_result));
                  } else {
                    $(estimateDivs[estimateDivIndex]).after($(estimate_result));
                  }
                } else {
                  $(estimateDivs[estimateDivIndex+1]).after($(estimate_result));
                }
              
              } else { /* 2번째 */
                if($(estimateDivs[estimateDivIndex]) == 0) {
                  $(estimateDivs[estimateDivIndex-1]).after($(estimate_result));
                } else {
                  $(estimateDivs[estimateDivIndex]).after($(estimate_result));
                }
              }
              })
            },
            error : function() {
              
            },
            complete : function() {
              $('.getEstimateResult').slideDown("slow");
              console.log("확인바람");
              
              $("#viewLoading").fadeOut(500);
            }
          })
      } else {
      console.log("getThisEstimateProdList if is false");
        $('.getEstimateResult').slideUp("fast", function() {
        $('.getEstimateResult').remove();
        $('.arrow-up').remove();
      });
      }
    };
    
    
    /* modal get prod list */
    var changeBtnParent;
    var keyword;
    function getProdList(btn) {
      $("[name=startNum]").val(11);
      $(".prodList").children().remove();
      console.log($(".btn").index());
      keyword = $(btn).next().val();
      console.log('keyword=' + keyword);
      $.ajax({
        url : '/app/curator/curatingChangeProd',
        data : 'keyword=' + keyword,
        type : 'post',
        success : function(data) {
          $(".prodList").append(data);
          changeBtnParent = $(btn).parent().parent();
          console.log($(changeBtnParent));
        }
      });
    }
    function getMoreProd() {
      $.ajax({
        url : '/app/curator/curatingMoreProd',
        data : 'keyword=' + keyword +'&startNum=' + $("[name=startNum]").val(),
        type : 'post',
        success : function(data) {
          $(".prodList").append(data);
          $("[name=startNum]").val(parseInt($("[name=startNum]").val()) + 10);
        }
      });
    };
    function prodChange(change_btn, prolId) {
          var prodInfo = $(change_btn).next().text();
          var jsonObj = JSON.parse(prodInfo);
          console.log(prodInfo);
          console.log(prolId);
          $(changeBtnParent).find("[class*=prod_name]").text(jsonObj.maker +" "+ jsonObj.prod_name);
          $(changeBtnParent).find("img").attr("src", jsonObj.image_url);
          $(changeBtnParent).find("[class*=prod_summary]").text(jsonObj.summary);
          $(changeBtnParent).find("[class*=prod_price]").text(jsonObj.min_price+"원");
          $("#myModal").toggleClass("in").css("display", "none");
          $(".modal-open").trigger("click");
          
           $.ajax({
            url: "/app/curator/updateEstimateProd",
            type: "post",
            data: {
              "prolId": prolId,
              "estId": estId,
              "prodId": jsonObj.prod_id
            },            
          timeout: 10000,
          beforeSend : function() {
            $("#viewLoading").css({
            "position" : "fixed",
            "left" : 0,
            "top" : 0,
            "width" : $(window).width(),
            "height" :screen.height
          }).fadeIn(500);
          },
          success : function(data) {
            alert("update success");
          },
          complete : function() {
            $("#viewLoading").fadeOut(500);
          },
          error : function() {
            
          }
            
          }) 
      };

   $(document).ready(function() {
    $("#viewLoading").hide();
    /* console.log("aaaaaaaaaaaa");
      $("#viewLoading").ajaxStart(function() {
        console.log("로딩 도나?");
        $("#viewLoading").css({
          "position" : "absolute",
          "left" : 0,
          "top" : 0,
          "width" : $(screen).width(),
          "height" :$(screen).height()
        }).fadeIn(500);
      }).ajaxStop(function() {
        $(this).fadeOut(500);
      });
 */
     
     $(window).resize(function() { // 브라우저 사이즈 감지
      console.log($(document).width());
      })
  });