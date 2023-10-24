<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.trip.model.*"%>
<%@ page import="com.tha103.gogoyu.itinerary.model.*"%>
<%@ page import="com.tha103.gogoyu.trip_photo.model.*"%>
<%
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/sean_css/comp_product.css">
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

.calendar-head {
	display: flex;
	/* flex-grow: 2; */
	align-items: center;
}

.calendar-word {
	text-align: center;
	width: 60%;
}

.between {
	margin-top: 10px;
	margin-bottom: 2px;
}

.detail {
	border: none;
	background-color: white;
}

.all-detail {
	font-size: 16px
}

.details>* {
	padding: 5px;
}

.trip {
	display: flex;
}

.product-opt {
	display: flex;
	flex-basis: 20%;
	flex-direction: column;
}

.no-css {
	position: fixed;
	top: calc(10vh - 15px);
	right: calc(10% - 15px);
	z-index: 3;
	width: 40px;
	height: 40px;
	border-radius: 50%;
	text-align: center;
	border: 2px white solid;
}

.multi-photo {
	position: relative;
	/* border: 1px dashed gray; */
	width: 100%;
	min-height: 10vw;
	max-height: fit-content;
	font-size: 0px;
}

.multi-photo>img.imgs {
	margin-right: 2.6%;
}

.multi-photo>img.imgs:nth-child(4n) {
	margin-right: 0px;
}

.stocks {
	border: none;
	background-color: white;
}

i.fa-solid.fa-square-check {
	margin-right: 5px;
}

i.fa-regular.fa-square {
	margin-right: 5px;
}

article {
	white-space: normal;
}
</style>
</head>

<body>
	<script
		src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
	<nav class="st">
		<!-- <a class="word" id="home" href="#">Home</a> -->
		<div class="head">
			<button type="button" class="head_btn" id="msg">
				<i class="fa-regular fa-message icon"
					style="color: black; font-size: 30px; background-color: transparent;"></i>
			</button>
			<button type="button" class="head_btn" id="info">
				<i class="fa-regular fa-bell icon"
					style="color: black; font-size: 30px; width: 30px; background-color: transparent;"></i>
			</button>
			<button type="button" class="head_btn" id="">
				<a class="profile"
					href="${pageContext.request.contextPath}/sean/trip_ticket_all.jsp">
				<i class="fa-solid fa-store"
					style="color: #000000; font-size: 30px; width: 30px; background-color: transparent;"></i>
			</a>

			</button>
			<button type="button" class="head_btn">
				<a class="profile"
					href="${pageContext.request.contextPath}/sean/select_page.jsp">
					<i class="fa-solid fa-user icon"
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
				<a
					href="${pageContext.request.contextPath}/sean/trip_ticket_all.jsp"
					class="left_btn" style="color: #FCC416"> <i
					class="fa-solid fa-ticket" style="color: #000000;"></i> 我的行程
				</a>
			</div>
			<div class="mem-data">
				<a href="${pageContext.request.contextPath}/sean/trip_ticket_review.jsp" 
				class="left_btn"><i class="fa-solid fa-file-invoice"
					style="color: black;"></i> 匿名評論 </a>
			</div>
		</aside>
	</nav>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<%
				LinkedHashMap<Trip, Set<Trip_photo>> map = (LinkedHashMap<Trip, Set<Trip_photo>>) request.getAttribute("map");
				LinkedHashMap<Trip, Set<Itinerary>> itineraryMap = (LinkedHashMap<Trip, Set<Itinerary>>) request.getAttribute("itineraryMap");
				List<Trip> tripList = null;
				if (map == null) {
					map = new LinkedHashMap<Trip, Set<Trip_photo>>();
					TripService tripSrc = new TripServiceHibernate();
					Integer compId = Integer.parseInt((String) request.getSession().getAttribute("compId"));
					tripList = tripSrc.getTripByCompId(compId);
					for (Trip li : tripList) {
						Set<Itinerary> itinerary= tripSrc.getItineraryByTripId(li.getTripId());
						Set<Trip_photo> tripPhoto = tripSrc.getAllPhoto(li.getTripId());
						itineraryMap.put(li,itinerary);
						map.put(li, tripPhoto);
					}
					request.setAttribute("map", map);
					request.setAttribute("itineraryMap", itineraryMap);
					map = (LinkedHashMap<Trip, Set<Trip_photo>>) request.getAttribute("map");
				}
				// 				request.setAttribute("backHere", request.getRequestURL());
				// 				System.out.print(request.getRequestURI());
				%>
				<c:forEach var="trip" items="${map.keySet()}">
					<c:if test="${trip.state!=-1}">
						<section class="one-product">
							<div class="title">
								<span class="product-status${trip.state==1?'-on':'-off'}">
									${trip.state==1?'上架中':'下架中'}</span> <span class="trip-name">${trip.tripName}</span>
								<label for=""> ${trip.people}人套票</label>
								<div class="do">
									<button class="pictures">圖庫</button>
									<button class="detail">詳細資訊</button>
									<form
										action="${pageContext.request.contextPath}/sean/TripServlet"
										method="post" style="display: inline-block">
										<input type="hidden" name="action" value="change"> <input
											type="hidden" name="tripId" value="${trip.tripId}">
										<button type="submit" class="go" style="display: none"></button>
										<button type="button" class="change">修改</button>
									</form>
									<button class="delete">刪除</button>
								</div>
							</div>
							<hr class="between">
							<div class="all-info">
								<span class="product-info" style=""> <label for="">開始日期:</label><br>
									<span>${trip.startTime}</span>
								</span> <span class="product-info" style=""> <label for="">結束日期:</label><br>
									<span>${trip.endTime}</span>
								</span> <span class="d">|</span> <span class="product-info"> <span>價格
										$</span> <span>${trip.price.stripTrailingZeros().toPlainString()}</span>
								</span> <span class="d">|</span> <span class="product-info"
									style="width: 100px;"> <span>庫存</span> <span>${trip.amount}</span>
								</span> <span class="below-btn">
									<button type="button" class="renewStatus"
										${(trip.state == 1) ? 'disabled style="filter: opacity(0.5);"' : ""}>上架</button>
									<input type="hidden" name="id" value="${tripId}">
									<button type="button" class="renewStatus"
										${(trip.state == 0) ? 'disabled style="filter: opacity(0.5);"' : ""}>下架</button>
								</span>
							</div>
							<div class="alert_bg">
								<div class="watch">
									<div class="all-detail">
										<div class="details">
											<h2>行程</h2>
											<div style="">
												<h5>行程名稱:</h5>
												<label for="name" class="detail-label">${trip.tripName}
												</label>
												<h5>套票人數:</h5>
												<label class="detail-label">${trip.people} </label>
												<h5>套票庫存:</h5>
												<label for="bed-num" class="detail-label">套票庫存:${trip.amount}
												</label>
												<h5>開始日期:</h5>
												<label class="detail-label">${trip.startTime} </label>
												<h5>結束日期:</h5>
												<label class="detail-label">${trip.endTime} </label>
											</div>
										</div>
										<hr>
										<div>
											<h2>行程地區</h2>
											<div class="trip">
												<div class="product-opt">
													<span><i
														class="${(trip.taipeiCity == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="taipeiCity">
															台北市</label> </span> <span> <i
														class="${(trip.newtaipeiCity == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="newtaipeiCity">
															新北市</label>
													</span> <span><i
														class="${(trip.taoyuanCity == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="taoyuanCity">
															桃園市</label> </span>
												</div>
												<div class="product-opt">
													<span><i
														class="${(trip.taichungCity == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="taichungCity">
															台中市</label> </span> <span> <i
														class="${(trip.tainanCity == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="tainanCity">
															台南市</label>
													</span> <span><i
														class="${(trip.kaohsiungCity == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="kaohsiungCity">
															高雄市</label> </span>

												</div>
												<div class="product-opt">
													<span> <i
														class="${(trip.hsinchuCounty == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="hsinchuCounty">
															新竹縣</label>

													</span> <span> <i
														class="${(trip.miaoliCounty == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="miaoliCounty">
															苗栗縣</label>
													</span> <span> <i
														class="${(trip.changhuaCounty == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="changhuaCounty">
															彰化縣</label>
													</span>
												</div>
												<div class="product-opt">
													<span> <i
														class="${(trip.nantouCounty == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="nantouCounty">
															南投縣</label>
													</span> <span> <i
														class="${(trip.yunlinCounty == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="yunlinCounty">
															雲林縣</label>
													</span> <span> <i
														class="${(trip.chiayiCounty == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="chiayiCounty">
															嘉義縣</label>
													</span>
												</div>
												<div class="product-opt">
													<span> <i
														class="${(trip.pingtungCounty == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="pingtungCounty">
															屏東縣</label>
													</span> <span> <i
														class="${(trip.yilanCity == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="yilanCity">
															宜蘭市</label>
													</span> <span> <i
														class="${(trip.hualienCity == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="hualienCity">
															花蓮市</label>
													</span>
												</div>
												<div class="product-opt">
													<span> <i
														class="${(trip.taitungCounty == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="taitungCounty">
															台東縣</label>
													</span> <span> <i
														class="${(trip.kinmenCounty == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="kinmenCounty">
															金門縣</label>
													</span> <span> <i
														class="${(trip.lienchiangCounty == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="lienchiangCounty">
															連江縣</label>
													</span>
												</div>
												<div class="product-opt">
													<span> <i
														class="${(trip.keelungCity == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="keelungCity">
															基隆市</label>
													</span> <span><i
														class="${(trip.hsinchuCity == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="hsinchuCity">
															新竹市</label> </span> <span><i
														class="${(trip.chiayiCity == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="chiayiCity">
															嘉義市</label> </span>
												</div>
												<div class="product-opt">
													<span> <i
														class="${(trip.penghuCounty == 1) ? 'fa-solid fa-square-check' : 'fa-regular fa-square'}"
														style="color: #81a4df;"></i><label for="penghuCounty">
															澎湖縣</label>
													</span>
												</div>
											</div>
											<hr>
											<h2>詳細行程</h2>
											<c:forEach var="it" items="${itineraryMap.get(trip)}">
												<div>
													<p>${it.beginTime}</p>
													<p>${it.sceneName}</p>
												</div>
											</c:forEach>
											<hr>
											<h2>行程介紹</h2>
											<article
												style="width: 100%; border: 1px solid black; border-radius: 5px; padding: 5px; word-break:break-all;">${trip.content}</article>
										</div>
										<br>
										<h2>行程價格</h2>
										<span>TWD
											${trip.price.stripTrailingZeros().toPlainString()}</span>
										<%-- 														<span id="percent"><%=commissionPercent%></span><span>% --%>
										<%-- 														佣金</span> <br> <span>TWD </span><span id="profit"><%=profitS%></span><span>您的收益(四捨五入之結果)</span> --%>
										<hr>
										<c:if test="${trip.mainPhoto != null}">
											<h2>行程搜尋照片</h2>
											<div class="drag">
												<img src="MainPhotoTripPrintServlet?tripId=${trip.tripId}"
													style="max-width: 100%">
											</div>
											<h2>行程詳細照片</h2>
											<div class="multi-photo">
												<c:forEach var="pics" items="${map.get(trip)}">
													<img class="imgs"
														src="TripPhotoPrintServlet?tripPhotoId=${pics.tripPhotoId}"
														style="width: 23%">
												</c:forEach>
											</div>
										</c:if>
									</div>
								</div>
								<button type="button" class="other-btn no no-css">✘</button>
							</div>
							<div class="alert_bg">
								<div class="alert">
									<div>
										確定${(trip.state == 1) ?'下架':'上架'}嗎? <br>
										<form
											action="${pageContext.request.contextPath}/sean/TripServlet"
											style="display: inline-block;" method="post">
											<input type="hidden" name="action"
												value="${(trip.state == 1)?'recall':'launch'}"> <input
												type="hidden" name="tripId" value="${trip.tripId}">
											<button type="submit" class="other-btn">Yes</button>
										</form>
										<button type="button" class="other-btn no">No</button>
									</div>
								</div>
							</div>
							<div class="alert_bg">
								<div class="alert">
									<div>
										確定刪除嗎? <br> <a class="other-btn yes"
											href="${pageContext.request.contextPath}/sean/TripServlet?action=delete&tripId=${trip.tripId}">Yes</a>
										<button type="button" class="other-btn no">No</button>
									</div>
								</div>
							</div>
						</section>
					</c:if>
				</c:forEach>


				<a class="new"
					href="${pageContext.request.contextPath}/sean/TripServlet?action=add">Build
					new trip</a>
			</div>
		</main>
	</div>

	<script
		src="${pageContext.request.contextPath}/static/sean_js/btn4com.js"></script>
	<script>
		$(".detail").on("click", function() {
			let trip = $(this).closest("section.one-product");
			$("body").css("overflow", "hidden");
			let alert_bg = $(trip).find(".alert_bg").eq(0);
			alert_bg.addClass("on");
			let watch = $(trip).find(".watch");
			watch.addClass("on");

			$(".no").on("click", function() {
				$("body").css("overflow", "auto");
				alert_bg.removeClass("on");
				watch.removeClass("on");
			})
		})
		$(".renewStatus").on("click", function() {
			let trip = $(this).closest("section.one-product");
			$("body").css("overflow", "hidden");
			let alert_bg = $(trip).find(".alert_bg").eq(1);
			alert_bg.addClass("on");
			let al = $(trip).find(".alert").eq(0);
			al.addClass("on");
			$(".yes").on("click", function() {
				trip.remove();
				$("body").css("overflow", "auto");
				alert_bg.removeClass("on");
				al.removeClass("on");
			})
			$(".no").on("click", function() {
				$("body").css("overflow", "auto");
				alert_bg.removeClass("on");
				al.removeClass("on");
			})
		})
		var delete_btn = document.querySelectorAll(".delete");
		$(".delete").on("click", function() {
			let trip = $(this).closest("section.one-product");
			let alert_bg = $(trip).find(".alert_bg").last();
			let al = $(trip).find(".alert").last();

			if (trip.find("span").hasClass("product-status-on")) {
				alert("請先下架再刪除!!");
			} else {
				$("body").css("overflow", "hidden");
				alert_bg.addClass("on");
				al.addClass("on");
			}
			$(".yes").on("click", function() {
				trip.remove();
				$("body").css("overflow", "auto");
				alert_bg.removeClass("on");
				al.removeClass("on");
			})
			$(".no").on("click", function() {
				$("body").css("overflow", "auto");
				alert_bg.removeClass("on");
				al.removeClass("on");
			})
		})

		$(".change").on("click", function() {
			let trip = $(this).closest(".one-product");
			if (trip.find("span").hasClass("product-status-off")) {
				$(this).closest("form").find(".go").click();
			} else {
				alert("請先下架再修改");
			}
		})
	</script>
</body>
</html>