<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
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

        #carouselRoom {
            height: 60vh;
            width: 60%;
        }

        #map {
/*             font-size: 100px; */
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
            </nav>
            <!--飯店名-->
            <h2 class="mx-auto"><%=company.getCompName()%></h2>
            <!--幻燈片-->
            <div id="carouselRoom" class="carousel slide border mx-auto" data-bs-ride="carousel">
<!--                 <div class="carousel-indicators"> -->
<!--                   <button type="button" data-bs-target="#carouselRoom" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button> -->
<!--                   <button type="button" data-bs-target="#carouselRoom" data-bs-slide-to="1" aria-label="Slide 2"></button> -->
<!--                   <button type="button" data-bs-target="#carouselRoom" data-bs-slide-to="2" aria-label="Slide 3"></button> -->
<!--                 </div> -->
                <div class="carousel-inner h-100">
                  <div class="carousel-item active">
                    <img src="${pageContext.request.contextPath}/sean/MainPhotoPrintHServlet?room_id=<%=room.getRoomId()%>" class="d-block w-100 h-100" alt="...">
                  </div>
                  <c:forEach var="roomPhotoId" items="<%=roomPhotoIdList%>" >
	                  <div class="carousel-item">
	                    <img src="${pageContext.request.contextPath}/sean/RoomPhotoPrintHServlet?room_photo_id=${roomPhotoId}" class="d-block w-100 h-100" alt="...">
	                  </div>
                  </c:forEach>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselRoom" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselRoom" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
                </button>
            </div>
            <!-- 飯店資訊 -->
            <div class="container mt-2 border h-100" id="content1" >
                <div class="row align-items-center justify-content-center h-100">
                    <div class="col h-100">
                        <div class="item h-100">
                            <h3 class="mt-2">飯店資訊</h3>
                            <div class="col-4 h-100 w-100 mb-2">
                             <c:forEach var="hotelInfo" items="<%=hotelInfoList%>" >
                                <span class="badge bg-primary">${hotelInfo}</span>
                             </c:forEach>
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
                        <div class="container">
                            <div class="row ">
                                <div class="col-6">
                                	<div class="">飯店位置</div>
										<!-- Button trigger modal -->
									<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#siteModal">
									  <i class="fa-solid fa-map-location-dot h-100" id="map"></i>
									</button>
									<!-- Modal -->
									<div class="modal fade" id="siteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
									  <div class="modal-dialog modal-lg">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h5 class="modal-title" id="exampleModalLabel"><%=company.getCompName()%></h5>
									        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									      </div>
									      <div class="modal-body ">
									      <div class="container">
									      	<div class="row justify-content-center">
									      		<iframe
											      width="750"
											      height="350"
											      style="border:0"
											      loading="lazy"
											      allowfullscreen
											      referrerpolicy="no-referrer-when-downgrade"
											      src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAInnBRglvKKBNHyr2OIw4-4ySCv-enRUY
											        &q=<%=company.getCompAddress()%>&zoom=15">
											    </iframe>
									      	</div>
									      </div>
									      </div>
									      <div class="modal-footer">
									      </div>
									    </div>
									  </div>
									</div>
                                </div>
                                <div class="col-6">
	                                <div class="">附近景點</div>
											<!-- Button trigger modal -->
												<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#nearbyScene">
												  <i class="fa-solid fa-map-location-dot h-100" id="map"></i>
												</button>
												<!-- Modal -->
												<div class="modal fade" id="nearbyScene" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
												  <div class="modal-dialog modal-lg">
												    <div class="modal-content">
												      <div class="modal-header">
												        <h5 class="modal-title" id="exampleModalLabel"><%=company.getCompName()%> 附近景點</h5>
												        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
												      </div>
												      <div class="modal-body ">
												      <div class="container">
												      	<div class="row justify-content-center">
												      		<iframe
														      width="600"
														      height="450"
														      style="border:0"
														      loading="lazy"
														      allowfullscreen
														      referrerpolicy="no-referrer-when-downgrade"
														      src="https://www.google.com/maps/embed/v1/search?key=AIzaSyAInnBRglvKKBNHyr2OIw4-4ySCv-enRUY
														      &q=<%=company.getCompAddress()%>附近休閒文藝景點">
														    </iframe>
												      	</div>
												      </div>
												      </div>
												      <div class="modal-footer">
												      </div>
												    </div>
												  </div>
												</div>
                                
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 房型設備 -->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <c:forEach var="facility" items="<%=roomFacilities%>" >
                           <span class="badge bg-primary">${facility}</span>
                        </c:forEach>
                    </div>
                    <!-- 聯絡 評價 -->
                    <div class="col-4 h-100 mt-2 mb-2 ps-0 pe-0">
                    	<div class="container ">
                    		<div class="row align-items-center justify-content-center ">
	                    		<div class="col ps-0 pe-0  ">
				                        <a class="" href="${pageContext.request.contextPath}/chu/commentArea(hotel).jsp?roomId=<%=room.getRoomId()%>" >
				                            <button type="button" class="btn btn-primary ">
				                                <i class="fa-solid fa-message "> 聯繫業者</i>
				                            </button>
				                        </a>
		                        </div>
		                        <div class="col ps-0 pe-0 ">
			                        <a class="" href="${pageContext.request.contextPath}/chu/commentArea(hotel).jsp?roomId=<%=room.getRoomId()%>">
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
            <!-- 房型屬性1 -->
            <div class="container border h-100" id="content1" >
                <div class="row  align-items-center h-100">
                    <!--房型名稱、房型、開始時間、結束時間、金額、庫存、收藏-->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <div>房型名稱：<%=room.getRoomName()%></div>
                        <div>房型：<%=room.getRoomType()%>人房</div>
                        <div>價格：TWD <%=room.getPrice().intValue()%></div>
                        <a class="" href="">
                            <button type="button" class="btn btn-primary">
                                收藏
                            </button>
                        </a>
                    </div>
                    <!--查詢是否有庫存-->
                    <div class="col-4 h-100 mt-2 mb-2">
                            <div>庫存查詢：</div>
                            <input class="form-control me-2 stockCheckIn" type="text" placeholder="入住日期..." aria-label="Search" onfocus="(this.type='date')"
                                onblur="(this.type='text')">
                            <input class="form-control me-2 stockCheckOut" type="text" placeholder="退房日期..." aria-label="Search" onfocus="(this.type='date')"
                                onblur="(this.type='text')">
                            <input class="detailPageRoomId" type="hidden" name="detailPageRoomId" value=<%=room.getRoomId()%>>
                            <div>庫存：<span id="stock"></span></div>
                            <button type="button" class="btn btn-primary" id="stockSearch">查詢</button>
                    </div>
                    <!--加入購物車-->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <form class="" method="post" action="${pageContext.request.contextPath}/shopping_hotelServlet" >
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
                            <input class="form-control me-2" type="text" placeholder="入住日期..." aria-label="Search" onfocus="(this.type='date')"
                                onblur="(this.type='text')" value="${searchRoomCheckIn}" name="checkInTime">
                            <input class="form-control me-2" type="text" placeholder="退房日期..." aria-label="Search" onfocus="(this.type='date')"
                                onblur="(this.type='text')" value="${searchRoomCheckOut}" name="checkOutTime">
                            <input type="hidden" name="roomId" value=<%=room.getRoomId()%>>
                            <input type="hidden" name="action" value="room_goShopping">
                            <button type="submit" class="btn btn-primary">
                                加入購物車
                            </button>
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
                            <p class="text-wrap"><%=room.getIntro()%></p>
                        </div> 
                    </div>
                </div>
            </div>
        </div>

    <script src="${pageContext.request.contextPath}/static/mhl_js/btn4com_review.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
    <script type="text/javascript">
    $("#stockSearch").on("click",function(){
        var checkIn = $(".stockCheckIn").val();
        var checkOut = $(".stockCheckOut").val();
        var detailPageRoomId = $(".detailPageRoomId").val();
        console.log(checkIn);
        console.log(checkOut);
        console.log(detailPageRoomId);
        var path = window.location.pathname;
        var webCtx = path.substring(0, path.indexOf('/', 1));
        var url = location.origin+webCtx+"/sean/SearchServlet";
        $.ajax({
            url: url,
            type: "POST", 
            data: {
               "action":"searchRoomStock",
                "stockCheckIn": checkIn,
             "stockCheckOut": checkOut,
             "detailPageRoomId": detailPageRoomId
            },            
            dataType: "json", 
            success: function(data) { 
               console.log(data);
               $("#stock").text(data.minStock);
            }

        })
        
})
    </script>
</body>

</html>