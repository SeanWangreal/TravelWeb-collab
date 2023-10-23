<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*"%>
<%
	RoomServiceHibernate roomSvc = new RoomServiceHibernate();
	List<Room> list = roomSvc.getHotRoom();
	
// 	TripServiceHibernate tripSvc = new TripServiceHibernate();
// 	List<Trip> list = roomSvc.getHotTrip();

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
        <a class="logo" id="home" href="#">GO<i class="fa-solid fa-location-dot" style="color: #ffbf1c;"></i>GOYU</a>
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
                <a class="profile" href="#">
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

    <form id="background" action="./serching.html">
        <img src="4621.png" alt="">
        <div id="search">
            <div id="btn_block">
                <button type="button" class="choose_btn -on" id="Hotel">Hotel</button>
                <input type="hidden" value="hotel" name="type" id="type">
                <button type="button" class="choose_btn" id="Trip">Trip</button>
            </div>
            <div id="outer">
                <hr id="kk">
                <div id="inside_form">
                    <div style="flex-basis: 20%;">
                        <input class="in" id="where" type="search" placeholder="Destination...">
                    </div>
                    <div style="flex-basis: 20%;">

                        <input class="in" id="date" type="text" placeholder="Check in..." onfocus="(this.type='date')"
                            onblur="(this.type='text')">
                    </div>
                    <div style="flex-basis: 20%;">

                        <input class="in" id="date" type="text" placeholder="Check out..." onfocus="(this.type='date')"
                            onblur="(this.type='text')">
                    </div>
                    <div style="flex-basis: 20%;">

                        <input class="in" id="people" type="search" placeholder="people...">
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
                    <h6 class="text-center">Hot Hotel</h6>
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
                            <div class="carousel-item active  ">
                                <img src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=<%=((Room)list.get(0)).getRoomId() %>" class="d-block w-100"   alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5 class="bg-light text-dark mb-0"><%=(list.get(0)).getRoomName() %></h5>
                                    <p class="bg-light text-dark">TWD <%=(list.get(0)).getPrice().intValue() %></p>
                                </div>
                            </div>
                            <div class="carousel-item ">
                                <img src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=<%=((Room)list.get(1)).getRoomId() %>" class="d-block w-100"  alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5 class="bg-light text-dark mb-0"><%=(list.get(1)).getRoomName() %></h5>
                                    <p class="bg-light text-dark">TWD <%=(list.get(1)).getPrice().intValue() %></p>
                                </div>
                            </div>
                            <div class="carousel-item  ">
                                <img src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=<%=((Room)list.get(2)).getRoomId() %>" class="d-block w-100"  alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5 class="bg-light text-dark mb-0"><%=(list.get(2)).getRoomName() %></h5>
                                    <p class="bg-light text-dark">TWD <%=(list.get(2)).getPrice().intValue() %></p>
                                </div>
                            </div>
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
                    <h6 class="text-center">Hot Journey</h6>
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
                            <div class="carousel-item active ">
                                <img src="https://picsum.photos/2800/1600?random=1" class="d-block w-100" alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5 class="bg-light text-dark mb-0">野柳二天一夜</h5>
                                    <p class="bg-light text-dark">每人TWD 1,000起</p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img src="https://picsum.photos/1400/800?random=2" class="d-block w-100" alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5 class="bg-light text-dark mb-0">野柳二天一夜</h5>
                                    <p class="bg-light text-dark">每人TWD 1,000起</p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img src="https://picsum.photos/1400/800?random=3" class="d-block w-100" alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5 class="bg-light text-dark mb-0">野柳二天一夜</h5>
                                    <p class="bg-light text-dark">每人TWD 1,000起</p>
                                </div>
                            </div>
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