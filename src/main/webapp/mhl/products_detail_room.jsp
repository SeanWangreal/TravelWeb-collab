<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*" %>
<%@ page import="com.tha103.gogoyu.room.model.*" %>
<%@ page import="com.tha103.gogoyu.room_photo.model.*" %>
<%@ page import="com.tha103.gogoyu.company.model.*" %>
<%@ page import="com.tha103.gogoyu.hotel_info.model.*" %>

<%
	List<Object> list = (List<Object>)request.getAttribute("productDetailRoom");
	Room room = (Room)list.get(0);
	List<Integer> roomPhotoIdList = (List<Integer>)list.get(1);
	List<String> roomFacilities = (List<String>)list.get(2);
	Company company = (Company)list.get(3);
	List<String> hotelInfoList = (List<String>)list.get(4);
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
        <!-- <a class="word" id="hotel" href="#">hotel</a> -->
        <div class="head">
            </button>
            <button type="menu" class="head_btn" id="msg">
                <i class="fa-regular fa-message icon" style="color: black; font-size:30px; 
                            background-color:transparent;"></i>
            </button>
            <button type="menu" class="head_btn" id="info">
                <i class="fa-regular fa-bell icon" style="color: black;font-size:30px; width: 30px;
                            background-color:transparent;"></i>
            </button>
            <button type="menu" class="head_btn" id="">
                <i class="fa-solid fa-store" style="color: #000000;font-size:30px; width: 30px;
                background-color:transparent;"></i>

            </button>
            <button type="button" class="head_btn">
                <a class="journey" href="#">
                    <i class="fa-solid fa-user icon" style="color: black; font-size:30px;
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
        <div class="d-flex flex-column mb-4">
            <!--搜尋欄-->
            <nav class="navbar navbar-light bg-light">
                <div class="container-fluid justify-content-center">
                  <form class="d-flex" method="post" action="${pageContext.request.contextPath}/sean/RoomServlet">
                    <select class="form-select me-2" name="comp_address" aria-label="Default select example">
                        <option value="台北市">台北市</option>
                        <option value="新北市">新北市</option>
                        <option value="桃園市">桃園市</option>
                        <option value="台中市">台中市</option>
                        <option value="台南市">台南市</option>
                        <option value="高雄市">高雄市</option>
                        <option value="新竹縣">新竹縣</option>
                        <option value="新北市">新北市</option>
                        <option value="苗栗縣">苗栗縣</option>
                        <option value="彰化縣">彰化縣</option>
                        <option value="南投縣">南投縣</option>
                        <option value="雲林縣">雲林縣</option>
                        <option value="嘉義縣">嘉義縣</option>
                        <option value="屏東縣">屏東縣</option>
                        <option value="宜蘭市">宜蘭市</option>
                        <option value="花蓮市">花蓮市</option>
                        <option value="台東縣">台東縣</option>
                        <option value="金門縣">金門縣</option>
                        <option value="連江縣">連江縣</option>
                        <option value="基隆市">基隆市</option>
                        <option value="新竹市">新竹市</option>
                        <option value="嘉義市">嘉義市</option>
                        <option value="澎湖縣">澎湖縣</option>
                    </select>
                    <input class="form-control me-2" name="checkIn" type="text" placeholder="入住日期..." aria-label="Search" onfocus="(this.type='date')"
                    	onblur="(this.type='text')">
                    <input class="form-control me-2" name="checkOut" type="text" placeholder="退房日期..." aria-label="Search" onfocus="(this.type='date')"
                    	onblur="(this.type='text')">
                    <input class="form-control me-2" name="number" type="text" placeholder="人數..." aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                    <input type="hidden" name="action" value="roomSearch">
                  </form>
                </div>
            </nav>
            <!--飯店名-->
            <h2 class="mx-auto">${company.compName}</h2>
            <!--幻燈片-->
            <div id="carouseltrip" class="carousel slide border mx-auto" data-bs-ride="carousel">
<!--                 <div class="carousel-indicators"> -->
<!--                   <button type="button" data-bs-target="#carouseltrip" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button> -->
<!--                   <button type="button" data-bs-target="#carouseltrip" data-bs-slide-to="1" aria-label="Slide 2"></button> -->
<!--                   <button type="button" data-bs-target="#carouseltrip" data-bs-slide-to="2" aria-label="Slide 3"></button> -->
<!--                 </div> -->
                <div class="carousel-inner h-100">
                  <div class="carousel-item active">
                    <img src="MainPhotoPrintHServlet?room_id=${room.roomId}" class="d-block w-100 h-100" alt="...">
                  </div>
                  <c:forEach var="roomPhotoId" items="<%=roomPhotoIdList%>" >
                  <div class="carousel-item">
                    <img src="RoomPhotoPrintHServlet?room_id=${roomPhotoId}" class="d-block w-100 h-100" alt="...">
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
            <!-- 飯店設施 -->
            <div class="container mt-2 border h-100" id="content1" >
                <div class="row align-items-center justify-content-center h-100">
                    <div class="col h-100">
                        <div class="item h-100">
                            <h3 class="mt-2">飯店設施</h3>
                            <div class="col-4 h-100 w-100 mb-2">
                                <span class="badge bg-primary">餐廳</span>
                                <span class="badge bg-primary">客房服務</span>
                                <span class="badge bg-primary">24小時接待櫃檯</span>
                                <span class="badge bg-primary">SPA</span>
                                <span class="badge bg-primary">健身中心</span>
                                <span class="badge bg-primary">花園</span>
                                <span class="badge bg-primary">露臺</span>
                                <span class="badge bg-primary">禁菸客房</span>
                                <span class="badge bg-primary">免費無線網路</span>
                                <span class="badge bg-primary">金山老街</span>
                                <span class="badge bg-primary">暖氣</span>
                                <span class="badge bg-primary">海灘</span>
                                <span class="badge bg-primary">泳池</span>
                                <span class="badge bg-primary">電動車充電站</span>
                                <span class="badge bg-primary">停車場</span>
                            </div>
                        </div> 
                    </div>
                </div>
            </div>
            <!--地圖 房型設備 聯絡 評價-->
            <div class="container border h-100" id="content1">
                <div class="row justify-content-center align-items-center h-100">
                    <!--地圖-->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <a href="#">
                            <i class="fa-solid fa-map-location-dot h-100" id="map"></i>
                        </a>
                    </div>
                    <!-- 房型設備 -->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <span class="badge bg-primary">衛生紙</span>
                        <span class="badge bg-primary">淋浴間</span>
                        <span class="badge bg-primary">廁所</span>
                        <span class="badge bg-primary">吹風機</span>
                        <span class="badge bg-primary">浴缸</span>
                        <span class="badge bg-primary">免費盥洗用品</span>
                        <span class="badge bg-primary">沖洗座</span>
                        <span class="badge bg-primary">拖鞋</span>
                        <span class="badge bg-primary">浴袍</span>
                        <span class="badge bg-primary">SPA浴缸</span>
                        <span class="badge bg-primary">電熱水壺</span>
                    </div>
                    <!-- 聯絡 評價 -->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <a href="#">
                            <button type="button" class="btn btn-primary mb-3">
                                <i class="fa-solid fa-message"></i>
                                聯繫業者
                            </button>
                        </a>
                        <a href="#">
                            <button type="button" class="btn btn-primary">
                                <i class="fa-solid fa-message"></i>
                                查看匿名評價
                            </button>
                        </a>
                    </div>
                </div>
            </div>
            <!-- 房型屬性1 -->
            <div class="container border h-100" id="content1" >
                <div class="row  align-items-center h-100">
                    <!--房型名稱、房型、開始時間、結束時間、金額、庫存、收藏-->
                    <div class="col-6 h-100 mt-2 mb-2">
                        <div>房型名稱：普通單人房</div>
                        <div>房型：單人房</div>
                        <div>金額：NTW 2000</div>
                        <a class="" href="#">
                            <button type="button" class="btn btn-primary">
                                收藏
                            </button>
                        </a>
                    </div>
                    <!--加入購物車-->
                    <div class="col-6 h-100 mt-2 mb-2">
                        <form class="" method="" action="" >
                            <div>先加入購物車，結帳時再選數量</div>
                            <div>請選擇購物車：
                            </div>
                            <select class="form-select me-2 mb-2" aria-label="Default select example" name="">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                            <input type="submit" class="btn btn-primary" value="加入購物車">
                        </form>
                    </div>
                </div>
            </div>
            <div class="container border h-100" id="content1" >
                <div class="row align-items-center h-100">
                    <!--介紹-->
                    <div class="col border h-100">
                        <div class="item h-100 ">
                            <h3 class="mt-2">介紹</h3>
                            <p class="text-wrap">活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容活動內容</p>
                        </div> 
                    </div>
                </div>
            </div>
        </div>

    <script src="${pageContext.request.contextPath}/static/mhl_js/btn4com_review.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>