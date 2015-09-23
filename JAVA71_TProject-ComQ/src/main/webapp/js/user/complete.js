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
					changeBtnParent = $(btn).parent().parent().parent().parent().parent().parent();
					console.log($(changeBtnParent));
				}
			});
		}
		$("#moreProd").on("click", function() {
			$.ajax({
				url : '/app/curator/curatingMoreProd',
				data : 'keyword=' + keyword +'&startNum=' + $("[name=startNum]").val(),
				type : 'post',
				success : function(data) {
					$(".prodList").append(data);
					$("[name=startNum]").val(parseInt($("[name=startNum]").val()) + 10);
				}
			});
		});

		$("#curatingSave").on("click", function() {
			var user;
			$.ajax({
				url: "/app/user/sessionCheck",
				type:"post",
				success: function(data) {
					console.log(data);
					console.log(data.length);
					if(data.length != 0) {
						var jsondata = JSON.parse(data);
						console.log(jsondata.email);
						console.log(jsondata.access);
						
						var curId = $('[name="curId"]').val(),
						estimateName = prompt("큐레이팅 이름을 입력해주세요.");
					
					if(estimateName.trim().length == 0) {
						console.log("비어있다");
						estimateName = "큐레이팅 목록";
					}
					console.log(estimateName);
					$.ajax({
						url:"/app/curator/createEstimate",
						type:"post",
						data:{
							"curId" : curId,
							"estName" : estimateName
						},
						success: function(data) {
							console.log("create::"+data);
							var estId = parseInt(data);
							console.log("create >> " + estId)
							var targetDiv = $("[name=targetData]"),
								prod_id = $(targetDiv).find(".prod_id"),
								keyword = $(targetDiv).find("[name=url]");
							for( i = 0; i < targetDiv.length; i++) {
								var prod_kind = $(keyword[i]).val().substring(0, $(keyword[i]).val().indexOf(" "));
								var anl_ans = $(keyword[i]).val().substring($(keyword[i]).val().indexOf(" ")+1);
								console.log(estId + " : " + prod_kind + " : " + $(prod_id[i]).val() + " : "+ anl_ans);
								$.ajax({
									url:"/app/curator/curatingComplete",
									type: "post",
									data: {
										"estId": estId,
										"prolKind": prod_kind,
										"prodId": $(prod_id[i]).val(),
										"anlAns": anl_ans
									},
									success: function(data) {
										$("<form action='/user/mypage.html'></form>").submit();
									}
								});/* endCompleteEstimate */
							}
							
						},
						error: function() {
							alert("저장하기 실패했습니다.");
							return false;
						}
					});/* end createEstimate */
					} else {
						alert("회원만 이용가능합니다.");
						$("#login-li").children().trigger("click");
					}
				}
			});
			
		});
		
var prodInfo;
function prodChange(btn) {
      prodInfo = $(btn).next().text();
      var jsonObj = JSON.parse(prodInfo);
      console.log(jsonObj.maker);
      $(changeBtnParent).find("[class*=panel-title]").children().val(jsonObj.maker +" "+ jsonObj.prod_name);
      $(changeBtnParent).find("[class*=image_url]").attr("src", jsonObj.image_url);
      $(changeBtnParent).find("[class*=maker]").text(jsonObj.maker);
      $(changeBtnParent).find("[class*=prod_name]").text(jsonObj.prod_name);
      $(changeBtnParent).find("[class*=summary]").text(jsonObj.summary);
      $(changeBtnParent).find("[class*=compl_price]").text(jsonObj.min_price+"원");
      $("#myModal").toggleClass("in").css("display", "none");
      $(".modal-open").trigger("click");
  }