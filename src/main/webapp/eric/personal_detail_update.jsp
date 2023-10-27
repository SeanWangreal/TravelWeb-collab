<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>

<%
Consumer consumer = (Consumer) request.getAttribute("consumer"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
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
<title>�ק�ӤH��T</title>
<link href="../dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
<link rel="stylesheet" href="../static/eric_css/ordinf.css">
<link rel="stylesheet" href="">

</script>
<style>
:root {
	--ifm-font-family-base: "����";
}

@font-face {
	font-family: '����';
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
					style="color: black;"></i> ��w�W��
				</a>
			</div>
			<div class="mem-data">
				<a class="left_btn" href="personal_detail.html"> <i
					class="fa-regular fa-user" style="color: black;"></i> �|�����
				</a>
			</div>
			<div class="mem-data">
				<a class="left_btn" href="ordinf.html"> <i
					class="fa-solid fa-file-invoice" style="color: black;"></i> �q���T
				</a>
			</div>
			<div class="mem-data">
				<a class="left_btn" href=""> <i class="fa-solid fa-star"
					style="color: black;"></i> �ڪ��`��
				</a>
			</div>
			<div class="mem-data">
				<a class="left_btn" href="hotel_room_review.html"> <i
					class="fa-regular fa-calendar-days" style="color: black;"></i> ��ƾ�
				</a>
			</div>
		</aside>
	</nav>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<br> <br> <br> <br>
				<!-- �|���z�n -->
				<section class="item" style="width: 800px">
					<h1 style="border: 0px solid">${consumer.cusName},�z�n!</h1>
					<br>
					<button typ="summit" class="logoutbtn" style="float: right;"
						name="action" value="Logout">�����n�X</button>

				</section>
				<br> <br> <br> <br>
				<form>
				
				<h5 style="font-weight: bolder">�|�����,${consumer.cusId}</h5>
				<span style="float: left">�b�ӤH��Ƥ���s�̷s��T�ýT�{<span>
						<div class="memBtn" id="memBtn"
							style="text-decoration: none; text-align: right; padding-right: 120px; font-size: 16px; font-family: ����">
							<p>�s��</p>
						</div>
						<div class="circle">C</div> <br>
						
						<div class="mem_detal">
								<div class="personal_item"
									style="width: 30%; font-weight: bolder; font-family: ����; border: none">
									<span>�m�W</span><br> <br> <span>�b��</span><br> <br>
									<span>�H�c </span><br> <br> <span>�q��</span><br> <br>
									<span>���}</span><br> <br> <span>�ʧO</span><br> <br>
									<span>�Ӥ�</span><br>
								</div>

								<div class="personal_item"
									style="width: 70%; border: none white; padding-left: 0px">
									<input class="mem" id="mem" value="${consumer.cusName}"
										readonly></input><br> <br> <input class="mem"
										id="mem" value="cusAccount" readonly></input><br> <br>
									<input class="mem" id="mem" value="${consumer.cusMail}"></input><br>
									<br> <input class="mem" id="mem"
										value="${consumer.cusPhone}"></input><br> <br> <input
										class="mem" id="mem" value="${consumer.cusAddress}"></input><br>
									<br> <input class="mem" id="mem"
										value="${consumer.cusSex}" ></input><br> <br> <img
										src="${pageContext.request.contextPath}/eric/PictureServlet?cus_id=${consumer.cusId}" style="height:200px>
									<input type="file" name="cusPhoto" ">
								</div>
								<button>�e�X</button>

						</div> <br> <br> <br> <br> <br> <br> <br>
													
						
						<br>
			</div>
			</form>
		</main>
	</div>


	<!-- <script src="btn4com_review.js"></script> -->
	<script src="ordinf.js"></script>



</body>

</html>