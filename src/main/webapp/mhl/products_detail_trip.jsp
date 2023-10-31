<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.trip.model.*" %>
<%@ page import="com.tha103.gogoyu.itinerary.model.*" %>
<%@ page import="com.tha103.gogoyu.scene.model.*" %>
<%@ page import="com.tha103.gogoyu.company.model.*" %>

<%
	List<Object> list = (List<Object>)request.getAttribute("productDetailTrip");
	Trip trip = (Trip)list.get(0);
	List<Integer> tripPhotoIdList = (List<Integer>)list.get(1);
	List<Itinerary> itineraryList = (List<Itinerary>)list.get(2);
	List<Scene> sceneList = (List<Scene>)list.get(3);
	Company company = (Company)list.get(4);
	List<String> siteList = (List<String>)list.get(5);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://kit.fontawesome.com/b4c50f14e1.js" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelMaker</title>
    <link href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/mhl_css/comp_product_review.css">
    </script>
    <style>
        @media (min-height: 500px) and (max-height: 1300px) {
            .shop {
                height: 60vh;
            }

            .info {
                height: 60vh;
            }
        }

        @media (min-height: 0px) and (max-height: 500px) {
            .shop {
                height: 60vh;
            }

            .info {
                height: 60vh;
            }
        }

        .main-content-info {
            position: relative;
            left: 360px;
            padding-bottom: 50px;
            font-family: "粉圓";
            font-size: 16px;
            background-color: white;
            width: calc(100% - 360px - 70px);
            color: black;
            min-height: calc(100vh - 125px);
            box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
            padding: 10px 40px;
        }

        #carouseltrip {
            height: 60vh;
            width: 60%;
        }

        #map {
            font-size: 100px;
        }

        #content1{
            height: 20vh;
            width: 60%;
        }

        .carousel-control-prev-icon, .carousel-control-next-icon {
            background-color: gray;
        }

    </style>
</head>

<body>
    <script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
    <nav class="st">
         <a class="logo" id="home" href="${pageContext.request.contextPath}/mhl/home.jsp">GO<i class="fa-solid fa-location-dot" style="color: #ffbf1c;"></i>GOYU</a>
        <div class="head">
            <button type="menu" class="head_btn" aria-label="規劃行程" id="shop">
            <a class="left_btn" href="${pageContext.request.contextPath}/chu/shopping(hotel).jsp">
                <i class="fa-solid fa-suitcase-rolling icon" style="color: black; font-size:30px;
                            background-color:transparent;"></i>
            </a>
            </button>
            <button type="menu" class="head_btn" id="msg">
                <i class="fa-regular fa-message icon" style="color: black; font-size:30px; 
                            background-color:transparent;"></i>
            </button>
            <button type="menu" class="head_btn" id="info">
                <i class="fa-regular fa-bell  icon" style="color: black;font-size:30px; width: 30px;
                            background-color:transparent;"></i>
            </button>
           <button type="button" class="head_btn">
                <a class="profile" href="${pageContext.request.contextPath}/ken/com_mem_signin.jsp">
                   <div style="color: black;">業者</div>
                </a>
            </button>
            <button type="button" class="head_btn">
                <a class="profile" href="${pageContext.request.contextPath}/eric/personal_detail.jsp">
                    <i class="fa-solid fa-user" style="color: black; font-size:30px;
                                background-color:transparent;"></i>
                </a>
            </button>
        </div>
       
    </nav>    
        <div class="d-flex flex-column mb-4">
            <!--搜尋欄-->
            <nav class="navbar navbar-light bg-light">
                <div class="container-fluid justify-content-center">
                    <form class="d-flex" method="post" action="${pageContext.request.contextPath}/sean/SearchServlet">
                    <select class="form-select me-2" name="site" aria-label="Default select example">
                        <option value="台北市" ${searchTripSite == "台北市" ? 'selected' : ""}>台北市</option>
                        <option value="新北市" ${searchTripSite == "新北市" ? 'selected' : ""}>新北市</option>
                        <option value="桃園市" ${searchTripSite == "桃園市" ? 'selected' : ""}>桃園市</option>
                        <option value="台中市" ${searchTripSite == "台中市" ? 'selected' : ""}>台中市</option>
                        <option value="台南市" ${searchTripSite == "台南市" ? 'selected' : ""}>台南市</option>
                        <option value="高雄市" ${searchTripSite == "高雄市" ? 'selected' : ""}>高雄市</option>
                        <option value="新竹縣" ${searchTripSite == "新竹縣" ? 'selected' : ""}>新竹縣</option>
                        <option value="苗栗縣" ${searchTripSite == "苗栗縣" ? 'selected' : ""}>苗栗縣</option>
                        <option value="彰化縣" ${searchTripSite == "彰化縣" ? 'selected' : ""}>彰化縣</option>
                        <option value="南投縣" ${searchTripSite == "南投縣" ? 'selected' : ""}>南投縣</option>
                        <option value="雲林縣" ${searchTripSite == "雲林縣" ? 'selected' : ""}>雲林縣</option>
                        <option value="嘉義縣" ${searchTripSite == "嘉義縣" ? 'selected' : ""}>嘉義縣</option>
                        <option value="屏東縣" ${searchTripSite == "屏東縣" ? 'selected' : ""}>屏東縣</option>
                        <option value="宜蘭市" ${searchTripSite == "宜蘭市" ? 'selected' : ""}>宜蘭市</option>
                        <option value="花蓮市" ${searchTripSite == "花蓮市" ? 'selected' : ""}>花蓮市</option>
                        <option value="台東縣" ${searchTripSite == "台東縣" ? 'selected' : ""}>台東縣</option>
                        <option value="金門縣" ${searchTripSite == "金門縣" ? 'selected' : ""}>金門縣</option>
                        <option value="連江縣" ${searchTripSite == "連江縣" ? 'selected' : ""}>連江縣</option>
                        <option value="基隆市" ${searchTripSite == "基隆市" ? 'selected' : ""}>基隆市</option>
                        <option value="新竹市" ${searchTripSite == "新竹市" ? 'selected' : ""}>新竹市</option>
                        <option value="嘉義市" ${searchTripSite == "嘉義市" ? 'selected' : ""}>嘉義市</option>
                        <option value="澎湖縣" ${searchTripSite == "澎湖縣" ? 'selected' : ""}>澎湖縣</option>
                    </select>
                    <input class="form-control me-2" name="checkIn" type="text" placeholder="入住日期..." aria-label="Search" onfocus="(this.type='date')"
                    	onblur="(this.type='text')" value="${searchTripCheckIn}">
                    <input class="form-control me-2" name="checkOut" type="text" placeholder="退房日期..." aria-label="Search" onfocus="(this.type='date')"
                    	onblur="(this.type='text')" value="${searchTripCheckOut}">
                    <input class="form-control me-2" value="${tripPeople}" name="number" type="text" placeholder="人數..." aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                    <input type="hidden" name="action" value="trip">
                  </form>
                </div>
            </nav>
            <!--旅行社-->
            <h2 class="mx-auto"><%=company.getCompName()%></h2>
            <!--幻燈片1 mainPhoto 行程圖片-->
            <div id="carouseltrip" class="carousel slide border mx-auto" data-bs-ride="carousel">
<!--                 <div class="carousel-indicators"> -->
<!--                   <button type="button" data-bs-target="#carouseltrip" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button> -->
<!--                   <button type="button" data-bs-target="#carouseltrip" data-bs-slide-to="1" aria-label="Slide 2"></button> -->
<!--                   <button type="button" data-bs-target="#carouseltrip" data-bs-slide-to="2" aria-label="Slide 3"></button> -->
<!--                 </div> -->
                <div class="carousel-inner h-100">
                  <div class="carousel-item active">
                    <img src="MainPhotoTripPrintServlet?tripId=<%=trip.getTripId()%>" class="d-block w-100 h-100" alt="...">
                  </div>
                  <c:forEach var="tripPhotoId" items="<%=tripPhotoIdList%>" >
	                  <div class="carousel-item">
	                    <img src="TripPhotoPrintServlet?tripPhotoId=${tripPhotoId}" class="d-block w-100 h-100" alt="...">
	                  </div>
                  </c:forEach>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouseltrip" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouseltrip" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
                </button>
            </div>
            <!--幻燈片2 景點圖片-->
<!--             <div id="carouseltripScene" class="carousel slide border mx-auto h-100" data-bs-ride="carousel"> -->
<!--                 <div class="carousel-indicators"> -->
<!--                   <button type="button" data-bs-target="#carouseltripScene" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button> -->
<!--                   <button type="button" data-bs-target="#carouseltripScene" data-bs-slide-to="1" aria-label="Slide 2"></button> -->
<!--                   <button type="button" data-bs-target="#carouseltripScene" data-bs-slide-to="2" aria-label="Slide 3"></button> -->
<!--                 </div> -->
<!--                 <div class="carousel-inner h-100"> -->
<!--                   <div class="carousel-item active"> -->
<%--                     <img src="MainPhotoTripPrintServlet?tripId=<%=trip.getTripId()%>" class="d-block w-100 h-100" alt="..."> --%>
<!--                   </div> -->
<%--                   <c:forEach var="scene" items="<%=sceneList%>" > --%>
<!-- 	                  <div class="carousel-item"> -->
<!-- 	                    <img src="" class="d-block w-100 h-100" alt="..."> -->
<!-- 	                  </div> -->
<%--                   </c:forEach> --%>
<!--                 </div> -->
<!--                 <button class="carousel-control-prev" type="button" data-bs-target="#carouseltripScene" data-bs-slide="prev"> -->
<!--                   <span class="carousel-control-prev-icon" aria-hidden="true"></span> -->
<!--                   <span class="visually-hidden">Previous</span> -->
<!--                 </button> -->
<!--                 <button class="carousel-control-next" type="button" data-bs-target="#carouseltripScene" data-bs-slide="next"> -->
<!--                   <span class="carousel-control-next-icon" aria-hidden="true"></span> -->
<!--                   <span class="visually-hidden">Next</span> -->
<!--                 </button> -->
<!--             </div> -->
            <!--地圖 所在縣市 景點 聯絡 評價-->
	            <div class="container mt-2 border h-100" id="content1">
	                <div class="row justify-content-center align-items-center h-100">
	                    <!--地圖-->
	                    <div class="col-4 h-100 mt-2 mb-2">
<%-- 	                        <a href="${pageContext.request.contextPath}/sean/SearchServlet?action=scenesMap&tripId=<%=trip.getTripId()%>"> --%>
	                        <a href="#">
	                            <i class="fa-solid fa-map-location-dot h-100" id="map"></i>
	                        </a>
                    	</div>
                    <!-- 所在縣市 景點 -->
                    <div class="col-4 h-100 mt-2 mb-2">
	                     <c:forEach var="site" items="<%=siteList%>" >
	                        <span class="badge bg-secondary">${site}</span>
                        </c:forEach>
                        <c:forEach var="scene" items="<%=sceneList%>" >
                        	<span class="badge bg-primary">${scene.sceneName} </span>
                        </c:forEach>
                    </div>
                    <!-- 聯絡 評價 -->
                   <div class="col-4 h-100 mt-2 mb-2 ps-0 pe-0">
                    	<div class="container ">
                    		<div class="row align-items-center justify-content-center ">
	                    		<div class="col ps-0 pe-0  ">
				                        <a class="" href="#" >
				                            <button type="button" class="btn btn-primary ">
				                                <i class="fa-solid fa-message "> 聯繫業者</i>
				                            </button>
				                        </a>
		                        </div>
		                        <div class="col ps-0 pe-0 ">
			                        <a class="" href="${pageContext.request.contextPath}/chu/commentArea(trip).jsp?tripId=<%=trip.getTripId()%>">
			                            <button type="button" class="btn btn-primary">
			                                <i class="fa-solid fa-message"> 查看評價</i>
			                            </button>
			                        </a>
		                        </div>
	                        </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 行程屬性1 -->
            <div class="container border h-100" id="content1" >
                <div class="row  align-items-center h-100">
                    <!--人數、開始時間、結束時間、金額、庫存、收藏-->
                    <div class="col-6 h-100 mt-2 mb-2 ">
                    	<div>行程名稱：<%=trip.getTripName()%></div>
                        <div>套票人數：<%=trip.getPeople()%></div>
                        <div>開始日期：<%=trip.getStartTime()%></div>
                        <div>結束日期：<%=trip.getEndTime()%></div>
                        <div>價格：TWD <%=trip.getPrice().intValue()%></div>
                        <div>庫存：<%=trip.getAmount()%></div>
                        <a class="" href="#">
                            <button type="button" class="btn btn-primary">
                                收藏
                            </button>
                        </a>
                    </div>
                    <!--加入購物車-->
                    <div class="col-6 h-100">
                        <form class="" method="post" action="${pageContext.request.contextPath}/shopping_tripServlet" >
                            <div>先加入購物車，結帳時再選數量</div>
                            <div>請選擇購物車：
                            </div>
                            <select class="form-select me-2 mb-2" aria-label="Default select example" name="cart_id">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            <input type="submit" class="btn btn-primary"  value="加入購物車">
                            <input type="hidden" name="tripId" value=<%=trip.getTripId()%>>
                            <input type="hidden" name="action" value="trip_goShopping">
                        </form>
                    </div>
                </div>
            </div>
            <div class="container border h-100" id="content1" >
                <div class="row align-items-center h-100">
                    <!--行程表-->
                    <div class="col border h-100">
                        <div class="item h-100">
                            <h3 class="mt-2">行程表</h3>
                            <c:forEach var="itinerary" items="<%=itineraryList%>" >
                            	<p class="text-wrap">${itinerary.sceneName}：${itinerary.beginTime}</p><br>
                            </c:forEach>
                        </div> 
                    </div>
                </div>
            </div>
            <div class="container border h-100" id="content1" >
                <div class="row align-items-center h-100">
                    <!--活動內容-->
                    <div class="col border h-100">
                        <div class="item h-100">
                            <h3 class="mt-2">活動內容</h3>
                            <p class="text-wrap"><%=trip.getContent()%></p>
                        </div> 
                    </div>
                </div>
            </div>
        </div>

    <script src="${pageContext.request.contextPath}/static/mhl_js/btn4com_review.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>