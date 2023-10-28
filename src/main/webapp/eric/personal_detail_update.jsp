<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>

<%
Consumer consumer = (Consumer) request.getAttribute("consumer"); //EmpServlet.java(Concroller), 存入req的empVO物件
if ((Integer) request.getSession().getAttribute("cusId") == null) {
	response.sendRedirect(request.getContextPath() + "/eric/signin.jsp");
}
%>
<!DOCTYPE html>
<html lang="en">

<head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改個人資訊</title>
<link href="../dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
<link rel="stylesheet" href="../static/eric_css/ordinf.css">
<link rel="stylesheet" href="">

</script>
<style>
:root {
	--ifm-font-family-base: "粉圓";
}

@font-face {
	font-family: '粉圓';
	src: url('/static/font/jf-openhuninn-2.0.ttf');
	font-display: swap;
}

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

img {
	width: 200px;
	height: 200px;
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
			<button type="menu" class="head_btn" id="">
				<i class="fa-solid fa-store"
					style="color: #000000; font-size: 30px; width: 30px; background-color: transparent;"></i>

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
				<a class="left_btn"> <i class="fa-solid fa-cart-shopping"
					style="color: black;"></i> 制定規劃
				</a>
			</div>
			<div class="mem-data">
				<a class="left_btn" href="personal_detail.html"> <i
					class="fa-regular fa-user" style="color: black;"></i> 會員資料
				</a>
			</div>
			<div class="mem-data">
				<a class="left_btn" href="ordinf.html"> <i
					class="fa-solid fa-file-invoice" style="color: black;"></i> 訂單資訊
				</a>
			</div>
			<div class="mem-data">
				<a class="left_btn" href=""> <i class="fa-solid fa-star"
					style="color: black;"></i> 我的蒐藏
				</a>
			</div>
			<div class="mem-data">
				<a class="left_btn" href="hotel_room_review.html"> <i
					class="fa-regular fa-calendar-days" style="color: black;"></i> 行事曆
				</a>
			</div>
		</aside>
	</nav>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<br> <br> <br> <br>
				<!-- 會員您好 -->
				<section class="item" style="width: 800px">
					<h1 style="border: 0px solid">${consumer.cusName},您好!</h1>
					<br>
					<button typ="summit" class="logoutbtn" style="float: right;"
						name="action" value="Logout">按此登出</button>

				</section>
				<br> <br> <br> <br>
				<form METHOD="post"
					ACTION="<%=request.getContextPath()%>/eric/ConsumerServlet"
					name="form1" enctype="multipart/form-data">

					<h5 style="font-weight: bolder">會員資料</h5>
					<span style="float: left">在個人資料中更新最新資訊並確認<span>
							<div class="memBtn" id="memBtn"
								style="text-decoration: none; text-align: right; padding-right: 120px; font-size: 16px; font-family: 粉圓">

							</div>
					

							<div class="mem_detal">
								<div class="personal_item"
									style="width: 30%; font-weight: bolder; font-family: 粉圓; border: none">
								
								
									<span>信箱 </span><br> <br> <span>電話</span><br> <br>
									<span>住址</span><br> <br>	<span>密碼</span><br>
									 <span>照片</span><br>
									
								</div>

								<div class="personal_item"
									style="width: 70%; border: none white; padding-left: 0px">

									<input class="mem" id="mem" name="cusMail" value="${consumer.cusMail}"></input><br><br>
									<input class="mem" id="mem" name="cusPhone" value="${consumer.cusPhone}"></input><br><br>
									<input class="mem" id="mem" name="cusAddress"value="${consumer.cusAddress}"></input><br> <br>
									<input class="mem" id="mem" name="cusPassword"value="${consumer.cusPassword}"></input><br> <br>
									<img src="${pageContext.request.contextPath}/eric/PictureServlet?cus_id=${consumer.cusId}">
									<input type="file" name="cusPhoto">
								</div>
								<button name="action" value="update">送出</button>

							</div> <br> <br> <br> <br> <br> <br> <br>


							<br>
				</form>
			</div>

		</main>
	</div>


	<!-- <script src="btn4com_review.js"></script> -->
	<script src="ordinf.js"></script>



</body>

</html>