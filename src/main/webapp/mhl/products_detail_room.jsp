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
            font-family: "����";
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
            <button type="menu" class="head_btn" aria-label="�W����{" id="shop">
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
                   <div style="color: black;">�~��</div>
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
        <div class="d-flex flex-column mb-4">
            <!--�j�M��-->
            <nav class="navbar navbar-light bg-light">
                <div class="container-fluid justify-content-center">
                  <form class="d-flex" method="post" action="${pageContext.request.contextPath}/sean/SearchServlet">
                    <select class="form-select me-2" name="comp_address" aria-label="Default select example">
                        <option value="�x�_��">�x�_��</option>
                        <option value="�s�_��">�s�_��</option>
                        <option value="��饫">��饫</option>
                        <option value="�x����">�x����</option>
                        <option value="�x�n��">�x�n��</option>
                        <option value="������">������</option>
                        <option value="�s�˿�">�s�˿�</option>
                        <option value="�s�_��">�s�_��</option>
                        <option value="�]�߿�">�]�߿�</option>
                        <option value="���ƿ�">���ƿ�</option>
                        <option value="�n�뿤">�n�뿤</option>
                        <option value="���L��">���L��</option>
                        <option value="�Ÿq��">�Ÿq��</option>
                        <option value="�̪F��">�̪F��</option>
                        <option value="�y����">�y����</option>
                        <option value="�Ὤ��">�Ὤ��</option>
                        <option value="�x�F��">�x�F��</option>
                        <option value="������">������</option>
                        <option value="�s����">�s����</option>
                        <option value="�򶩥�">�򶩥�</option>
                        <option value="�s�˥�">�s�˥�</option>
                        <option value="�Ÿq��">�Ÿq��</option>
                        <option value="���">���</option>
                    </select>
                    <input class="form-control me-2" name="checkIn" type="text" placeholder="�J����..." aria-label="Search" onfocus="(this.type='date')"
                    	onblur="(this.type='text')" value="${searchCheckIn}">
                    <input class="form-control me-2" name="checkOut" type="text" placeholder="�h�Ф��..." aria-label="Search" onfocus="(this.type='date')"
                    	onblur="(this.type='text')" value="${searchCheckOut}">
                    <input class="form-control me-2" value="${people}" name="number" type="text" placeholder="�H��..." aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                    <input type="hidden" name="action" value="roomSearch">
                  </form>
                </div>
            </nav>
            <!--�����W-->
            <h2 class="mx-auto"><%=company.getCompName()%></h2>
            <!--�ۿO��-->
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
            <!-- ������T -->
            <div class="container mt-2 border h-100" id="content1" >
                <div class="row align-items-center justify-content-center h-100">
                    <div class="col h-100">
                        <div class="item h-100">
                            <h3 class="mt-2">������T</h3>
                            <div class="col-4 h-100 w-100 mb-2">
                             <c:forEach var="hotelInfo" items="<%=hotelInfoList%>" >
                                <span class="badge bg-primary">${hotelInfo}</span>
                             </c:forEach>
                            </div>
                        </div> 
                    </div>
                </div>
            </div>
            <!--�a�� �Ы��]�� �p�� ����-->
            <div class="container border h-100" id="content1">
                <div class="row justify-content-center align-items-center h-100">
                    <!--�a��-->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <div class="container">
                            <div class="row">
                                <div class="col-6">
                                    <div class="">��m</div>
                                    <a href="#">
                                        <i class="fa-solid fa-map-location-dot h-100" id="map"></i>
                                    </a>
                                </div>
                                <div class="col-6">
                                    <div>�����I</div>
                                    <a href="#">
                                        <i class="fa-solid fa-map-location-dot h-100" id="map"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- �Ы��]�� -->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <c:forEach var="facility" items="<%=roomFacilities%>" >
                                <span class="badge bg-primary">${facility}</span>
                             </c:forEach>
                    </div>
                    <!-- �p�� ���� -->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <a href="#">
                            <button type="button" class="btn btn-primary mb-3">
                                <i class="fa-solid fa-message"></i>
                                �pô�~��
                            </button>
                        </a>
                        <a href="#">
                            <button type="button" class="btn btn-primary">
                                <i class="fa-solid fa-message"></i>
                                �d�ݰΦW����
                            </button>
                        </a>
                    </div>
                </div>
            </div>
            <!-- �Ы��ݩ�1 -->
            <div class="container border h-100" id="content1" >
                <div class="row  align-items-center h-100">
                    <!--�Ы��W�١B�Ы��B�}�l�ɶ��B�����ɶ��B���B�B�w�s�B����-->
                    <div class="col-4 h-100 mt-2 mb-2">
                    	<h4><%=company.getCompName()%></h4>
                        <div>�Ы��W�١G<%=room.getRoomName()%></div>
                        <div>�Ы��G<%=room.getRoomType()%>�H��</div>
                        <div>���B�GNTW <%=room.getPrice().intValue()%></div>
                        <a class="" href="#">
                            <button type="button" class="btn btn-primary">
                                ����
                            </button>
                        </a>
                    </div>
                    <!--�d�߬O�_���w�s-->
                    <div class="col-4 h-100 mt-2 mb-2">
                            <div>�w�s�d�ߡG</div>
                            <input class="form-control me-2 checkIn" type="text" placeholder="�J����..." aria-label="Search" onfocus="(this.type='date')"
                                onblur="(this.type='text')">
                            <input class="form-control me-2 checkOut" type="text" placeholder="�h�Ф��..." aria-label="Search" onfocus="(this.type='date')"
                                onblur="(this.type='text')">
                            <input class="detailPageRoomId" type="hidden" name="detailPageRoomId" value="1">
                            <div>�w�s�G</div>
                            <button type="button" class="btn btn-primary stockSearch">�d��</button>
                    </div>
                    <!--�[�J�ʪ���-->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <form class="" method="post" action="${pageContext.request.contextPath}/shopping_hotelServlet" >
                            <div>���[�J�ʪ����A���b�ɦA��ƶq</div>
                            <div>�п���ʪ����G
                            </div>
                            <select class="form-select me-2 mb-2" aria-label="Default select example" name="cart_id">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            <input class="form-control me-2" type="text" placeholder="�J����..." aria-label="Search" onfocus="(this.type='date')"
                                onblur="(this.type='text')" value="${searchCheckIn}">
                            <input class="form-control me-2" type="text" placeholder="�h�Ф��..." aria-label="Search" onfocus="(this.type='date')"
                                onblur="(this.type='text')" value="${searchCheckOut}">
                            <input type="hidden" name="roomId" value=<%=room.getRoomId()%>>
                            <input type="hidden" name="action" value="room_goShopping">
                            <button type="submit" class="btn btn-primary">
                                �[�J�ʪ���
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="container border h-100" id="content1" >
                <div class="row align-items-center h-100">
                    <!--����-->
                    <div class="col border h-100">
                        <div class="item h-100 ">
                            <h3 class="mt-2">����</h3>
                            <p class="text-wrap"><%=room.getIntro()%></p>
                        </div> 
                    </div>
                </div>
            </div>
        </div>

    <script src="${pageContext.request.contextPath}/static/mhl_js/btn4com_review.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">
    $("#stockSearch").on("click",function(e){
        var checkIn = $(".checkIn").val();
        var checkOut = $(".checkOut").val();
        var number = $(".number").val();
        var detailPageRoomId = $(".detailPageRoomId").val();
	   	            	console.log(checkIn);
                        console.log(checkOut);
                        console.log(number);
                        console.log(detailPageRoomId);
	   	                var path = window.location.pathname;
	   	                var webCtx = path.substring(0, path.indexOf('/', 1));
	   	                var url = location.origin+webCtx+"/sean/searchServlet"
	   	            	$.ajax({
	   	                 url: url,
	   	                 type: "POST", 
	   	                 data: {
	   	                	"action":"searchRoomStock",
	   	                     "checkIn": checkIn,
                             "checkOut": checkOut,
                             "detailPageRoomId": detailPageRoomId
	   	                 },            
	   	                 dataType: "json", 
	   	                 success: function(data) { 
	   	                	console.log(data);
                            }})
    </script>
</body>

</html>