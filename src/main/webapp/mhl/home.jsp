<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*"%>
<%@ page import="com.tha103.gogoyu.trip.model.*"%>
<%
	RoomServiceHibernate roomSvc = new RoomServiceHibernate();
	List<List> roomDetail = roomSvc.getHotRoomDetail();
	List<Room> roomList = roomDetail.get(0);

	for (Room room:roomList){
		System.out.println(room.getRoomId());
	}
	List<String> compNameList = roomDetail.get(1);
	request.setAttribute("roomList", roomList);
	request.setAttribute("compNameList", compNameList);

	TripServiceHibernate tripSvc = new TripServiceHibernate();
	List<Trip> tripList = tripSvc.getHotTrip();
	request.setAttribute("tripList", tripList);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://kit.fontawesome.com/b4c50f14e1.js" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelMaker</title>
    <link href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/mhl_css/home_header.css">
    </script>
    <style>
        html {
            background-color: inherit;
        }


        .main-content {
            height: 1000px;
            /* text-align: center;
            background-color: white; */
/*             border: 1px solid black; */
        }


        .on {
            display: block !important;
        }



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
        
        .carousel-control-prev-icon, .carousel-control-next-icon {
            background-color: gray;
        }
        
        .carousel-item{ 
 		  width:100%;  
 		  height: 400px !important; 
 		} 
 		.carousel-item img{ 
 		  position: absolute; 
 		  top: -9999px; 
 		  bottom: -9999px; 
 		  left: -9999px; 
 		  right: -9999px; 
 		  margin: auto; 
		  height: 100%; 
 		} 
    </style>
</head>

<body>
    <nav class="st">
        <a class="logo" id="home" href="${pageContext.request.contextPath}/mhl/home.jsp">GO<i class="fa-solid fa-location-dot" style="color: #ffbf1c;"></i>GOYU</a>
        <a class="word" id="hotel" href="#">HOT<i class="fa-solid fa-fire"
                style="color: #ff9500; background-color:transparent;"></i>Hotel</a>
        <a class="word" id="journel" href="#">HOT<i class="fa-solid fa-fire"
                style="color: #ff9500; background-color:transparent;"></i>Journel</a>
        <div class="head">
            <button type="menu" class="head_btn" aria-label="規劃行程" id="shop">
                <i class="fa-solid fa-suitcase-rolling icon" style="color: black; font-size:30px;
                            background-color:transparent;"></i>
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
            <div class="nothing">
                4則未讀訊息<br>
                <a class="btn btn-primary" href="#" role="button">查看即時訊息</a>
            </div>
        </aside>
        <aside class="info all_side nothing" id="info_side">
            3則訂單已完成<br>
            <a class="btn btn-primary" href="#" role="button">查看所有訂單</a>
        </aside>
        <aside class="shop all_side nothing" id="shop_side">
            3個商品在購物車內<br>
            <a class="btn btn-primary" href="#" role="button">查看購物車</a>
        </aside>
    </nav>

    <form id="background" method="post" action="${pageContext.request.contextPath}/sean/SearchServlet">
    	<input type="hidden" value="hotel" name="action" id="type">
        <img src="${pageContext.request.contextPath}/mhl/4621.png" alt="">
        <div id="search">
            <div id="btn_block">
                <button type="button" class="choose_btn -on" id="Hotel">Hotel</button>
<!--                 <input type="hidden" value="hotel" name="type" id="type"> -->
                <button type="button" class="choose_btn" id="Trip">Trip</button>
            </div>
            <div id="outer">
                <hr id="kk">
                <div id="inside_form">
                    <div style="flex-basis: 20%;">
                        <select class="form-select me-2 in" name="site" aria-label="Default select example">
                            <option value="台北市">台北市</option>
                            <option value="新北市">新北市</option>
                            <option value="桃園市">桃園市</option>
                            <option value="台中市">台中市</option>
                            <option value="台南市">台南市</option>
                            <option value="高雄市">高雄市</option>
                            <option value="新竹縣">新竹縣</option>
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
                    </div>
                    <div style="flex-basis: 20%;">

                        <input class="in" name="checkIn" id="date" type="text" placeholder="Check in..." onfocus="(this.type='date')"
                            onblur="(this.type='text')">
                    </div>
                    <div style="flex-basis: 20%;">

                        <input class="in" name="checkOut" id="date" type="text" placeholder="Check out..." onfocus="(this.type='date')"
                            onblur="(this.type='text')">
                    </div>
                    <div style="flex-basis: 20%;">

                        <input class="in" name="number" id="people" type="search" placeholder="people...">
                    </div>

                    <button id="s" type="submit" style="flex-basis: 100px;">
                        <i class="fa-solid fa-magnifying-glass" style="color: #000000;"></i>
                    </button>
                </div>
            </div>
        </div>
    </form>

    <div class="main-content mb-6" >
        <main class="container ">
            <div class="row justify-content-center align-items-center ">
                <div class="col-12 col-md-8 h-10">
                    <h2 class="text-center">Hot Hotel</h2>
                </div>
                <div class="col-12 col-md-8 h-40 mb-4">
                    <div id="carouselTrip" class="carousel slide ">
                        <div class="carousel-indicators">
                            <button type="button" data-bs-target="#carouselTrip" data-bs-slide-to="0"
                                class="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#carouselTrip" data-bs-slide-to="1"
                                aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#carouselTrip" data-bs-slide-to="2"
                                aria-label="Slide 3"></button>
                        </div>
                        <div class="carousel-inner ">
                        <c:forEach var="room" items="${roomList}">
                        	<c:if test="${roomList.indexOf(room) == 0}">
                            <div class="carousel-item active  ">
	                            <a href="${pageContext.request.contextPath}/sean/SearchServlet?room_id=${room.roomId}&action=getProductDetailRoom" class="d-block w-100"   alt="...">
	                                <img src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${room.roomId}" class="d-block w-100"   alt="...">
	                                <div class="carousel-caption d-none d-md-block">
	                                    <h5 class="bg-light text-dark mb-0">${compNameList.get(roomList.indexOf(room))}</h5>
	                                    <p class="bg-light text-dark mb-0">${room.roomName}</p>
	                                    <p class="bg-light text-dark">${room.roomType}人房 TWD ${room.price.intValue()}</p>
	                                </div>
	                            </a>
                            </div>
                        	</c:if>
                        	<c:if test="${roomList.indexOf(room) != 0}">
									<div class="carousel-item">
										<a href="${pageContext.request.contextPath}/sean/SearchServlet?room_id=${room.roomId}&action=getProductDetailRoom" class="d-block w-100" alt="...">
											<img src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=${room.roomId}" class="d-block w-100" alt="...">
											<div class="carousel-caption d-none d-md-block">
												<h5 class="bg-light text-dark mb-0">${compNameList.get(roomList.indexOf(room))}</h5>
												<p class="bg-light text-dark mb-0">${room.roomName}</p>
												<p class="bg-light text-dark">${room.roomType}人房TWD
													${room.price.intValue()}</p>
											</div>
										</a>
									</div>
								</c:if>
                        </c:forEach>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselTrip"
                            data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselTrip"
                            data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
                <div class="col-12 col-md-8 h-10">
                    <h2 class="text-center">Hot Journey</h2>
                </div>
                <div class="col-12 col-md-8 h-40 mb-6">
                    <div id="carouselJourney" class="carousel slide ">
                        <div class="carousel-indicators ">
                            <button type="button" data-bs-target="#carouselJourney" data-bs-slide-to="0"
                                class="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#carouselJourney" data-bs-slide-to="1"
                                aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#carouselJourney" data-bs-slide-to="2"
                                aria-label="Slide 3"></button>
                        </div>
                        <div class="carousel-inner ">
                        <c:forEach var="trip" items="${tripList}">
                        	<c:if test="${tripList.indexOf(trip) == 0}">
                        		<div class="carousel-item active ">
	                            <a href="${pageContext.request.contextPath}/sean/SearchServlet?tripId=${trip.tripId}&action=getProductDetailTrip" class="d-block w-100"   alt="...">
	                                <img src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${trip.tripId}" class="d-block w-100" alt="...">
	                                <div class="carousel-caption d-none d-md-block">
	                                    <h5 class="bg-light text-dark mb-0">${trip.tripName}</h5>
	                                    <p class="bg-light text-dark">${trip.people}人套票 TWD ${trip.price.intValue()}</p>
	                                </div>
                                </a>
                            </div>
                        	</c:if>
                        	<c:if test="${tripList.indexOf(trip) != 0}">
                        	<div class="carousel-item">
	                            <a href="${pageContext.request.contextPath}/sean/SearchServlet?tripId=${trip.tripId}&action=getProductDetailTrip" class="d-block w-100"   alt="...">
	                                <img src="${pageContext.request.contextPath}/sean/MainPhotoTripPrintServlet?tripId=${trip.tripId}" class="d-block w-100" alt="...">
	                                <div class="carousel-caption d-none d-md-block">
	                                    <h5 class="bg-light text-dark mb-0">${trip.tripName}</h5>
	                                    <p class="bg-light text-dark">${trip.people}人套票 TWD ${trip.price.intValue()}</p>
	                                </div>
                                </a>
                            </div>
                            </c:if>
                        </c:forEach>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselJourney"
                            data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselJourney"
                            data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/mhl_js/btn.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>