<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.tha103.gogoyu.trip_ord.model.*"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TravelMaker</title>
<link href="../dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../static/sean_css/comp_ord.css">
</script>
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
				<a class="profile" href="${pageContext.request.contextPath}/sean/trip_ticket_all.jsp">
					<i class="fa-solid fa-store" style="color: #000000; font-size: 30px; width: 30px; background-color: transparent;"></i>
							</a>
			</button>
			<button type="menu" class="head_btn" id="">
				<i class="fa-solid fa-user icon"
					style="color: black; font-size: 30px; background-color: transparent;"></i>
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
				<a
					href="${pageContext.request.contextPath}/sean/trip_ticket_all.jsp"
					class="left_btn" style="color: #000000"> <i
					class="fa-solid fa-ticket" style="color: #000000;"></i> 我的行程
				</a>
			</div>
			<div class="mem-data">
				<a href="${pageContext.request.contextPath}/sean/trip_com_ord.jsp" class="left_btn" style="color: #FCC416;"> <i class="fa-solid fa-file-invoice"
					style="color: black;"></i> 訂單資訊
				</a>
			</div>
			<div class="mem-data">
				<a href="${pageContext.request.contextPath}/sean/trip_ticket_review.jsp" 
				class="left_btn" style="color: #000000;"><i class="fa-regular fa-comment" style="color: #000000;"></i> 匿名評論 </a>
			</div>
		</aside>
	</nav>
	<%
	Map<Trip_ord, List<String>> tripOrdMap = (Map<Trip_ord, List<String>>) request.getAttribute("tripOrdMap");
	if (tripOrdMap == null) {
		Trip_ordServiceHibernate tripStockSvc = new Trip_ordServiceHibernate();
		String compString = (String) request.getSession().getAttribute("compId");
		if (compString == null ){
			response.sendRedirect(request.getContextPath() + "/sean/select_page.jsp");
			return;
		}
		Integer compId = Integer.parseInt((String) request.getSession().getAttribute("compId"));
		tripOrdMap = tripStockSvc.getTripOrdByCompId(compId);
	};
	request.setAttribute("tripOrdMap", tripOrdMap);
	%>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<div id="searching">
					<i class="fa-solid fa-magnifying-glass" style="color: #000000;"></i>
					<input type="search" placeholder="輸入訂單編號" id="ord-search">
					<%@ include file="page1.file" %>
				</div>
				<c:forEach var="tripOrd" items="${tripOrdMap.keySet()}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<div class="ord">
						<div class="ord-head">
							<label for="">訂單編號:</label><span>${tripOrd.tripOrdId}</span>
							<label for="">訂單狀態:</label><span>${tripOrd.ordStatus == 1?"已成立":"已取消"}</span>
						</div>
						<div class="all-info">
							<div>
								<div style="display:flex">
								<label for="" class="l_long ord-label">套票<br>
								<span class="long">${tripOrdMap.get(tripOrd).get(0)}</span>
								</label> 
								<label for=""
									class="l_long ord-label">張數<br>
								<span class="long">${tripOrd.amount}</span>
								</label> 
								<label for=""
									class="l_long ord-label">總金額<br>
								<span class="long">${tripOrd.totalPrice}</span></label> 
								
								</div>
								<label for=""
									class="l_long ord-label">顧客名稱<br>
								<span class="long">${tripOrdMap.get(tripOrd).get(1)}</span>
								</label> 
								<label for=""
									class="l_long ord-label">開始日期<br>
								<span class="long">${tripOrd.startTime}</span>
								</label> 
								<label for=""
									class="l_long ord-label">結束日期<br>
								<span class="long">${tripOrd.endTime}</span>
								</label>
							</div>
							<div class="remark-block">
								<label for="" class="remark">備註:</label>
								<p class="remark-info">${tripOrd.remark}
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
				<%@ include file="page2.file" %>
			</div>
		</main>
	</div>
	<script src="../static/sean_js/btn4com.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			
		})
	</script>
</html>