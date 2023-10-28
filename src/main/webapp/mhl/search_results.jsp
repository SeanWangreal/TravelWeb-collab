<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*"%>
<%@ page import="com.tha103.gogoyu.company.model.*"%>

<%
	Map<Room, String> searchRoomResult = (Map<Room, String>)request.getAttribute("searchRoomResult");
	pageContext.setAttribute("roomMap", searchRoomResult);
%>

<jsp:useBean id="CompSvc" scope="page" class="com.tha103.gogoyu.company.model.CompanyService" />

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
            font-family: "����";
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
    <div class="container">
        <div class="row">
            <!--�j�M��-->
            <div class="col-12 ">
                <!--����-->
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="hotel-tab" data-bs-toggle="tab" data-bs-target="#hotel"
                            type="button" role="tab" aria-controls="hotel" aria-selected="true">hotel</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="journey-tab" data-bs-toggle="tab" data-bs-target="#journey"
                            type="button" role="tab" aria-controls="journey" aria-selected="false">journey</button>
                    </li>
                </ul>
                <div class="tab-content " id="myTabContent">
                    <!--hotel�j�M�C-->
                    <div class="tab-pane fade show active" id="hotel" role="tabpanel" aria-labelledby="hotel-tab">
                        <nav class="navbar navbar-light bg-light">
                            <div class="container">
                                <div class="d-flex flex-wrap">
                                    <form class="d-flex" method="post" action="${pageContext.request.contextPath}/sean/SearchServlet">
                                        <!-- <input class="form-control me-2" type="text" placeholder="�a�I..." aria-label="Search">
                                         -->
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
                            </div>
                        </nav>
                        <!--�j�M���G-->
                        <div class="d-flex flex-column">
                            <!--hotel�ӫ~-->
                             <c:forEach var="roomVO" items="${roomMap.keySet()}" >
	                            <div class="container border">
	                                <div class="row">
	                                    <!--�ӫ~��-->
	                                    <div class="col photoShell border">
	                                        <img src="MainPhotoPrintHServlet?room_id=${roomVO.roomId}" class="d-block w-100" alt="...">
	                                    </div>
	                                    <!--�ӫ~�W �Ӫp-->
	                                    <div class="col">
	                                            <h3 class="">${roomMap.get(roomVO)}</h3><br>
	                                            <div>${roomVO.roomName}</div>
	                                            <div>${roomVO.roomType}�H��</div><br>
	                                            <a href="${pageContext.request.contextPath}/sean/SearchServlet?action=getProductDetailRoom&room_id=${roomVO.roomId}&searchCheckIn=${searchCheckIn}&searchCheckOut=${searchCheckOut}&number=${people}" class="btn btn-primary btn-lg" tabindex="-1" role="button" aria-disabled="true">�d�ݩЫ��Ӫp</a>
	                                    </div>
	                                    <!--�p�� ���� ����-->
	                                    <div class="col ">
	                                        <div class="d-flex flex-column">
	                                            <div class="comment_set">
	                                                <!--�p��-->
	                                                <div class="comment_message">  
	                                                    <a href="#">
	                                                        <i class="fa-solid fa-message"></i>
	                                                    </a>
	                                                </div>
	                                                <!--����-->
	                                                <div  class="count_star">  
	                                                    <a href="#">
	                                                        <i class="fa-solid fa-star">8.7</i>
	                                                    </a>
	                                                </div>
	                                                <!--����-->
	                                            </div><br>
	                                            <span class="book_price ms-4">����(���t�|)</span>
	                                                <i class="ms-4">TWD</i>
	                                            <div class="ms-4">
	                                                <i class="howmuch">${roomVO.price}</i>
	                                            </div>
	                                        </div>
	                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                    
                    <!-- journey�j�M�C -->
                    <div class="tab-pane fade" id="journey" role="tabpanel" aria-labelledby="journey-tab">
                        <nav class="navbar navbar-light bg-light">
                            <div class="container">
                                <div class="d-flex flex-wrap">
                                    <form class="d-flex">
                                        <select class="form-select me-2" aria-label="Default select example">
                                            <option selected>�a�I...</option>
                                            <option value="Taipei_City">�x�_��</option>
                                            <option value="NewTaipei_City">�s�_��</option>
                                            <option value="Taoyuan_City">��饫</option>
                                            <option value="Taichung_City">�x����</option>
                                            <option value="Tainan_City">�x�n��</option>
                                            <option value="Kaohsiung_City">������</option>
                                            <option value="Hsinchu_County">�s�˿�</option>
                                            <option value="Miaoli_County">�s�_��</option>
                                            <option value="Changhua_County">�]�߿�</option>
                                            <option value="Nantou_County">���ƿ�</option>
                                            <option value="Nantou_County">�n�뿤</option>
                                            <option value="Yunlin_County">���L��</option>
                                            <option value="Chiayi_County">�Ÿq��</option>
                                            <option value="Pingtung_County">�̪F��</option>
                                            <option value="Yilan_City">�y����</option>
                                            <option value="Hualien_City">�Ὤ��</option>
                                            <option value="Taitung_County">�x�F��</option>
                                            <option value="Kinmen_County">������</option>
                                            <option value="Lienchiang_County">�s����</option>
                                            <option value="Keelung_City">�򶩥�</option>
                                            <option value="Hsinchu_City">�s�˥�</option>
                                            <option value="Chiayi_City">�Ÿq��</option>
                                            <option value="Penghu_County">���</option>
                                        </select>
                                        <input class="form-control me-2" type="search" placeholder="�}�l���..." aria-label="Search" onfocus="(this.type='date')"
                                        onblur="(this.type='text')">
                                        <input class="form-control me-2" type="search" placeholder="�������..." aria-label="Search" onfocus="(this.type='date')"
                                        onblur="(this.type='text')">
                                        <input class="form-control me-2" type="search" placeholder="�H��..." aria-label="Search">
                                        <button class="btn btn-outline-success" type="submit">Search</button>
                                    </form>
                                </div>
                            </div>
                        </nav>
                        <!--�j�M���G-->
                        <div class="d-flex flex-column">
                            <!--journey�ӫ~-->
                            <div class="container border">
                                <div class="row">
                                    <!--�ӫ~��-->
                                    <div class="col ">
                                        <img src="https://picsum.photos/1400/800?random=3" class="d-block w-100" alt="...">
                                    </div>
                                    <!--�ӫ~�W �Ӫp �I��-->
                                    <div class="col">
                                        <h3 class="">���h�G�Ѥ@�]</h3><br>
                                        <div>�}�l�ɶ�</div>
                                        <div>�����ɶ�</div>
                                        <div>�H��</div>
                                        <a href="#" class="btn btn-primary btn-lg" tabindex="-1" role="button" aria-disabled="true">�d�ݦ�{�Ӫp</a>
                                    </div>
                                    <!--�p�� ���� ����-->
                                    <div class="col ">
                                        <div class="d-flex flex-column">
                                            <div class="comment_set">
                                                <!--�p��-->
                                                <div class="comment_message">  
                                                    <a href="#">
                                                        <i class="fa-solid fa-message"></i>
                                                    </a>
                                                </div>
                                                <!--����-->
                                                <div  class="count_star">  
                                                    <a href="#">
                                                        <i class="fa-solid fa-star">8.7</i>
                                                    </a>
                                                </div>
                                                <!--����-->
                                            </div><br>
                                            <span class="book_price ms-4">����(���t�|)</span>
                                                <i class="ms-4">TWD</i>
                                            <div class="ms-4">
                                                <i class="howmuch">2500</i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/static/mhl_js/btn4com_review.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/mhl_js/btn4com.js"></script>
    
</body>

</html>