<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.tha103.gogoyu.room_ord.model.*"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TravelMaker</title>
<link href="../dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../static/sean_css/comp_product_review.css">
<style>
@media ( min-height : 500px) and (max-height: 1300px) {
	.shop {
		height: 60vh;
	}
	.info {
		height: 60vh;
	}
}

@media ( min-height : 0px) and (max-height: 500px) {
	.shop {
		height: 60vh;
	}
	.info {
		height: 60vh;
	}
}
</style>
</head>

<body>
	<script src="../vendors/jquery/jquery-3.7.1.min.js"></script>
	<nav class="st">
		<!-- <a class="word" id="home" href="#">Home</a> -->
		<div class="head">
			</button>
			<button type="menu" class="head_btn" id="msg">
				<i class="fa-regular fa-message icon"
					style="color: black; font-size: 30px; background-color: transparent;"></i>
			</button>
			<button type="menu" class="head_btn" id="info">
				<i class="fa-regular fa-bell icon"
					style="color: black; font-size: 30px; width: 30px; background-color: transparent;"></i>
			</button>
			<button type="button" class="head_btn" id="">
			<a class="profile"
					href="${pageContext.request.contextPath}/sean/hotel_room_all.jsp">
				<i class="fa-solid fa-store"
					style="color: #000000; font-size: 30px; width: 30px; background-color: transparent;"></i>
			</a>
			</button>
			<button type="button" class="head_btn">
				<a class="profile" href="#"> <i class="fa-solid fa-user icon"
					style="color: black; font-size: 30px; background-color: transparent;"></i>
				</a>
			</button>
		</div>
		<aside class="msg all_side nothing" id="msg_side">
			msg<br>msg<br>msg<br>msg<br>msg<br>msg<br>msg
			<br>msg<br>msg<br>msg<br>msg<br>msg<br>msg
		</aside>
		<aside class="info all_side nothing" id="info_side">
			info<br>info<br>info<br>info<br>info<br>info
			<br>info<br>info<br>info<br>info<br>info
		</aside>
		<aside class="shop all_side nothing" id="shop_side">
			shop<br>shop<br>shop<br>shop<br>shop<br>shop
			<br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
			<br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
			<br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
			<br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
			<br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
		</aside>
		<div id="shell"></div>
		<aside class="left">
			<div class="mem-data">
				<a href="${pageContext.request.contextPath}/sean/hotel_room_all.jsp"
					class="left_btn"> <i class="fa-solid fa-hotel"
					style="color: #000000;"></i> 我的房間
				</a>
			</div>
			<div class="mem-data">
				<a href="${pageContext.request.contextPath}/sean/hotel_com_ord.jsp" class="left_btn" style="color: #000000"> <i
					class="fa-solid fa-file-invoice" style="color: black;"></i> 訂單資訊
				</a>
			</div>
			<div class="mem-data">
				<a href="${pageContext.request.contextPath}/sean/hotel_room_review.jsp"
					class="left_btn" style="color: #FCC416;"> 
					<i class="fa-regular fa-comment" style="color: #000000;"></i> 匿名評論
				</a>
			</div>
		</aside>
	</nav>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<div id="point-place">
					<div>
						<h1></h1>
					</div>
					<div>
						<label for="">整體評分</label> <br> <span id="total"></span><span>則評價</span>
					</div>
				</div>
				<hr>
				<ul>

				</ul>
			</div>
		</main>

	</div>
	<script src="../static/sean_js/btn4com_review.js"></script>
	<script>
				$(document).ready(function() {
// 					var compId = 2;
					var totalReview = 0 ;
					var avgScore = 0 ;
					$.ajax({
						url : "/TravelWeb-collab/sean/RoomOrdServlet",
						type : "POST", // GET | POST | PUT | DELETE | PATCH
						data : {
							"action" : "allReview",
// 							"compId" : compId
						},
						dataType : "json", // 預期會接收到回傳資料的格式： json | xml | html
						success : function(data) { // request 成功取得回應後執行
// 							console.log(data);
						for (var i = 0; i < data.length;i+=2){
							console.log(data[i]);
							console.log(data[i+1]);
							var html1 = `<li class="one-review">
											<div class="title-block">
												<div>
													投稿日期:<span class="year">`+data[i].commentsTime+`</span>
												</div>
												<div class="star">
										`;
										console.log(data[i].score);
							var html2 = ``;
							
							
							for(var j = 0; j< data[i].score; j++){
							html2 += `<span class="star" data-star="1"> <i
										class="fa-solid fa-star" style="color: #e7ea43;"></i>
										</span>`;
								
							}
							for(var j = 0; j< 5-data[i].score; j++){
							html2 += `<span class="star" data-star="1">
										<i class="fa-solid fa-star" style="color: #000000;"></i>
											</span>`;
								
							}
							var html3 = `
									</div>
								</div>
								<div class="review-block">
									<p>`+data[i].comments+`</p>
									<div class="read-block">
										<button class="read">查看全部</button>
									</div>
								</div>
								<hr>
							</li>`;
							totalReview++;
							avgScore += data[i].score;
							$("ul").append(html1+html2+html3);
							
						}
					$("#total").text(totalReview);
					if (avgScore === 0){
						$("h1").text("尚無訂單");		
					} else {
						$("h1").text(Math.round(avgScore/totalReview*10)/10);						
					}
					$(".read").on('click',function(){
				        var p = $(this).closest(".review-block").find("p");
				        p.toggleClass("zoom");
				        if (p.hasClass('zoom')){
				            $(this).text("收回")
				        } else{
				            $(this).text("查看全部")
				        }
				    })
						
						}
					});
				})
	</script>
</body>

</html>