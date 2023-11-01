<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*"%>
<%@ page import="com.tha103.gogoyu.company.model.*"%>
<%@ page import="com.tha103.gogoyu.trip.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://kit.fontawesome.com/b4c50f14e1.js" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelMaker</title>
     <link href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/mhl_css/comp_product_review.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/mhl_css//comp_mem_l.css">
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

        body{
            background-color: #F3F3F3;
        }
     
 		
    </style>
</head>

<body>
    <script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
     <script src="${pageContext.request.contextPath}/dist/js/bootstrap.bundle.min.js"></script>
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
    </nav>
    <div class="container">
        <div class="row">
            <!--搜尋欄-->
            <div class="col-12 ">
                <!--頁籤-->
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link ${(searchRoomResult != null) ? 'active' : ''}" id="hotel-tab" data-bs-toggle="tab" data-bs-target="#hotel"
                            type="button" role="tab" aria-controls="hotel" aria-selected=${searchRoomResult != null ?"true" : "false"} ${searchRoomResult == null ? 'tabindex="-1"' : ""}>hotel</button>
                    </li>
                    <li class="nav-item " role="presentation">
                        <button class="nav-link ${(searchTripResult != null) ? 'active' : ''}" id="journey-tab" data-bs-toggle="tab" data-bs-target="#journey"
                            type="button" role="tab" aria-controls="journey" aria-selected=${searchTripResult != null ? "true" : "false"} ${searchTripResult == null ? 'tabindex="-1"' : ""}>journey</button>
                    </li>
                </ul>
                <div class="tab-content " id="myTabContent">
                    <!--hotel搜尋列-->
                    <div class="tab-pane fade ${searchRoomResult != null ? 'active show' : ''}" id="hotel" role="tabpanel" aria-labelledby="hotel-tab">
                        <nav class="navbar navbar-light bg-light">
                            <div class="container">
                                <div class="d-flex flex-wrap">
                                    <form class="d-flex" method="post" action="${pageContext.request.contextPath}/sean/SearchServlet">
                                        <!-- <input class="form-control me-2" type="text" placeholder="地點..." aria-label="Search">
                                         -->
                                        <select class="form-select me-2" name="site" aria-label="Default select example">
                                            <option value="台北市" ${searchRoomComp_address == "台北市" ? 'selected' : ""}>台北市</option>
				                            <option value="新北市" ${searchRoomComp_address == "新北市" ? 'selected' : ""}>新北市</option>
				                            <option value="桃園市" ${searchRoomComp_address == "桃園市" ? 'selected' : ""}>桃園市</option>
				                            <option value="台中市" ${searchRoomComp_address == "台中市" ? 'selected' : ""}>台中市</option>
				                            <option value="台南市" ${searchRoomComp_address == "台南市" ? 'selected' : ""}>台南市</option>
				                            <option value="高雄市" ${searchRoomComp_address == "高雄市" ? 'selected' : ""}>高雄市</option>
				                            <option value="新竹縣" ${searchRoomComp_address == "新竹縣" ? 'selected' : ""}>新竹縣</option>
				                            <option value="苗栗縣" ${searchRoomComp_address == "苗栗縣" ? 'selected' : ""}>苗栗縣</option>
				                            <option value="彰化縣" ${searchRoomComp_address == "彰化縣" ? 'selected' : ""}>彰化縣</option>
				                            <option value="南投縣" ${searchRoomComp_address == "南投縣" ? 'selected' : ""}>南投縣</option>
				                            <option value="雲林縣" ${searchRoomComp_address == "雲林縣" ? 'selected' : ""}>雲林縣</option>
				                            <option value="嘉義縣" ${searchRoomComp_address == "嘉義縣" ? 'selected' : ""}>嘉義縣</option>
				                            <option value="屏東縣" ${searchRoomComp_address == "屏東縣" ? 'selected' : ""}>屏東縣</option>
				                            <option value="宜蘭市" ${searchRoomComp_address == "宜蘭市" ? 'selected' : ""}>宜蘭市</option>
				                            <option value="花蓮市" ${searchRoomComp_address == "花蓮市" ? 'selected' : ""}>花蓮市</option>
				                            <option value="台東縣" ${searchRoomComp_address == "台東縣" ? 'selected' : ""}>台東縣</option>
				                            <option value="金門縣" ${searchRoomComp_address == "金門縣" ? 'selected' : ""}>金門縣</option>
				                            <option value="連江縣" ${searchRoomComp_address == "連江縣" ? 'selected' : ""}>連江縣</option>
				                            <option value="基隆市" ${searchRoomComp_address == "基隆市" ? 'selected' : ""}>基隆市</option>
				                            <option value="新竹市" ${searchRoomComp_address == "新竹市" ? 'selected' : ""}>新竹市</option>
				                            <option value="嘉義市" ${searchRoomComp_address == "嘉義市" ? 'selected' : ""}>嘉義市</option>
				                            <option value="澎湖縣" ${searchRoomComp_address == "澎湖縣" ? 'selected' : ""}>澎湖縣</option>
                                        </select>
                                        <input class="form-control me-2" name="checkIn" type="text" placeholder="入住日期..." aria-label="Search" onfocus="(this.type='date')"
                                        onblur="(this.type='text')" value="${searchRoomCheckIn}">
                                        <input class="form-control me-2" name="checkOut" type="text" placeholder="退房日期..." aria-label="Search" onfocus="(this.type='date')"
                                        onblur="(this.type='text')" value="${searchRoomCheckOut}">
                                        <input class="form-control me-2" value="${roomPeople}" name="number" type="text" placeholder="人數..." aria-label="Search">
                                        <button class="btn btn-outline-success" type="submit">Search</button>
                                        <input type="hidden" name="action" value="hotel">
                                    </form>
                                </div>
                            </div>
                        </nav>
                        <!--搜尋結果-->
                        <div class="d-flex flex-column mb-4">
                            <!--hotel商品-->
                             <c:forEach var="roomVO" items="${searchRoomResult.keySet()}" >
	                            <div class="container border">
	                                <div class="row">
	                                    <!--商品圖-->
	                                    <div class="col photoShell border">
	                                        <img src="MainPhotoPrintHServlet?room_id=${roomVO.roomId}" class="d-block w-100" alt="...">
	                                    </div>
	                                    <!--商品名 細況-->
	                                    <div class="col">
	                                            <h3 class="">${searchRoomResult.get(roomVO).get(1)}</h3><br>
	                                            <div>${roomVO.roomName}</div>
	                                            <div>${roomVO.roomType}人房</div><br>
	                                            <a href="${pageContext.request.contextPath}/sean/SearchServlet?action=getProductDetailRoom&room_id=${roomVO.roomId}&searchComp_address=${searchRoomComp_address}&searchCheckIn=${searchRoomCheckIn}&searchCheckOut=${searchRoomCheckOut}&number=${roomPeople}" class="btn btn-primary btn-lg" tabindex="-1" role="button" aria-disabled="true">查看房型細況</a>
	                                    </div>
	                                    <!--聯絡 評等 價格-->
	                                    <div class="col ">
	                                        <div class="d-flex flex-column">
	                                            <div class="comment_set">
	                                                <!--聯絡-->
	                                                <div class="comment_message">  
	                                                    <a href="#">
	                                                        <i class="fa-solid fa-message"></i>
	                                                    </a>
	                                                </div>
	                                                <!--評等-->
	                                                <div  class="count_star">  
	                                                    <a href="#">
	                                                        <i class="fa-solid fa-star">${searchRoomResult.get(roomVO).get(0)}</i>
	                                                    </a>
	                                                </div>
	                                                <!--價格-->
	                                            </div><br>
	                                            <span class="book_price ms-4">價格(含稅)</span>
	                                                <i class="ms-4">TWD</i>
	                                            <div class="ms-4">
	                                                <i class="howmuch">${roomVO.price.intValue()}</i>
	                                            </div>
	                                        </div>
	                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                    
                    <!-- journey搜尋列 -->
                    <div class="tab-pane fade ${searchTripResult != null ? 'active show' : ''}" id="journey" role="tabpanel" aria-labelledby="journey-tab">
                        <nav class="navbar navbar-light bg-light">
                            <div class="container">
                                <div class="d-flex flex-wrap">
                                    <form class="d-flex" method="post" action="${pageContext.request.contextPath}/sean/SearchServlet">
                                        <!-- <input class="form-control me-2" type="text" placeholder="地點..." aria-label="Search">
                                         -->
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
                                        <input class="form-control me-2" name="checkIn" type="text" placeholder="開始時間..." aria-label="Search" onfocus="(this.type='date')"
                                        onblur="(this.type='text')" value="${searchTripCheckIn}">
                                        <input class="form-control me-2" name="checkOut" type="text" placeholder="結束時間..." aria-label="Search" onfocus="(this.type='date')"
                                        onblur="(this.type='text')" value="${searchTripCheckOut}">
                                        <input class="form-control me-2" value="${tripPeople}" name="number" type="text" placeholder="人數..." aria-label="Search">
                                        <button class="btn btn-outline-success" type="submit">Search</button>
                                        <input type="hidden" name="action" value="trip">
                                    </form>
                                </div>
                            </div>
                        </nav>
                        <!--搜尋結果-->
                        <div class="d-flex flex-column mb-4">
                            <!--journey商品-->
                            <c:forEach var="tripVO" items="${searchTripResult.keySet()}" >
                            <div class="container border">
                                <div class="row">
                                    <!--商品圖-->
                                    <div class="col ">
                                        <img src="MainPhotoTripPrintServlet?tripId=${tripVO.tripId}" class="d-block w-100" alt="...">
                                    </div>
                                    <!--商品名 細況 付款-->
                                    <div class="col">
                                        <h3 class="">${tripVO.tripName}</h3><br>
                                        <div>開始時間:${tripVO.startTime}</div>
                                        <div>結束時間:${tripVO.endTime}</div>
                                        <div>人數:${tripVO.people}</div>
                                        <a href="${pageContext.request.contextPath}/sean/SearchServlet?tripId=${tripVO.tripId}&site=${tripVO.tripId}&action=getProductDetailTrip&searchCheckIn=${searchTripCheckIn}&searchCheckOut=${searchTripCheckOut}&number=${tripPeople}" class="btn btn-primary btn-lg" tabindex="-1" role="button" aria-disabled="true">查看行程細況</a>
                                    </div>
                                    <!--聯絡 評等 價格-->
                                    <div class="col ">
                                        <div class="d-flex flex-column">
                                            <div class="comment_set">
                                                <!--聯絡-->
                                                <div class="comment_message">  
                                                    <a href="#">
                                                        <i class="fa-solid fa-message"></i>
                                                    </a>
                                                </div>
                                                <!--評等-->
                                                <div  class="count_star">  
                                                    <a href="#">
                                                        <i class="fa-solid fa-star">${searchTripResult.get(tripVO)}</i>
                                                    </a>
                                                </div>
                                                <!--價格-->
                                            </div><br>
                                            <span class="book_price ms-4">價格(含稅)</span>
                                                <i class="ms-4">TWD</i>
                                            <div class="ms-4">
                                                <i class="howmuch">${tripVO.price.intValue()}</i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/static/mhl_js/btn4com_review.js"></script>
   
    <script src="${pageContext.request.contextPath}/static/mhl_js/btn4com.js"></script>
    
</body>

</html>