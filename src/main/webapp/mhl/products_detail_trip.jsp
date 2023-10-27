<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
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
                    <select class="form-select me-2" aria-label="Default select example">
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
                    <input class="form-control me-2" type="text" placeholder="�}�l���..." aria-label="Search" onfocus="(this.type='date')"
                    onblur="(this.type='text')">
                    <input class="form-control me-2" type="text" placeholder="�������..." aria-label="Search" onfocus="(this.type='date')"
                    onblur="(this.type='text')">
                    <input class="form-control me-2" type="text" placeholder="�H��..." aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                  </form>
                </div>
            </nav>
            <!--�ӫ~�W-->
            <h2 class="mx-auto">���h�G�Ѥ@�]</h2>
            <!--�ۿO��-->
            <div id="carouseltrip" class="carousel slide border mx-auto" data-bs-ride="carousel">
                <div class="carousel-indicators">
                  <button type="button" data-bs-target="#carouseltrip" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                  <button type="button" data-bs-target="#carouseltrip" data-bs-slide-to="1" aria-label="Slide 2"></button>
                  <button type="button" data-bs-target="#carouseltrip" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner h-100">
                  <div class="carousel-item active">
                    <img src="https://picsum.photos/2800/1600?random=1" class="d-block w-100 h-100" alt="...">
                  </div>
                  <div class="carousel-item">
                    <img src="https://picsum.photos/2800/1600?random=2" class="d-block w-100 h-100" alt="...">
                  </div>
                  <div class="carousel-item">
                    <img src="https://picsum.photos/2800/1600?random=3" class="d-block w-100 h-100" alt="...">
                  </div>
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
            <!--�a�� �Ҧb���� ���I �p�� ����-->
            <div class="container mt-2 border h-100" id="content1">
                <div class="row justify-content-center align-items-center h-100">
                    <!--�a��-->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <a href="#">
                            <i class="fa-solid fa-map-location-dot h-100" id="map"></i>
                        </a>
                    </div>
                    <!-- �Ҧb���� ���I -->
                    <div class="col-4 h-100 mt-2 mb-2">
                        <span class="badge bg-secondary">�s�_��</span>
                        <span class="badge bg-primary">�k���Y</span>
                        <span class="badge bg-primary">�P�k�c</span>
                        <span class="badge bg-primary">���s�ѵ�</span>
                        <span class="badge bg-primary">�k���Y</span>
                        <span class="badge bg-primary">�P�k�c</span>
                        <span class="badge bg-primary">���s�ѵ�</span>
                        <span class="badge bg-primary">�k���Y</span>
                        <span class="badge bg-primary">�P�k�c</span>
                        <span class="badge bg-primary">���s�ѵ�</span>
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
            <!-- ��{�ݩ�1 -->
            <div class="container border h-100" id="content1" >
                <div class="row  align-items-center h-100">
                    <!--�H�ơB�}�l�ɶ��B�����ɶ��B���B�B�w�s�B����-->
                    <div class="col-6 h-100 mt-2 mb-2 ">
                        <div>�H�ơG2</div>
                        <div>�}�l����G2023-10-10</div>
                        <div>��������G2023-10-13</div>
                        <div>���B�GNTW 2000</div>
                        <div>�w�s�G5</div>
                        <a class="" href="#">
                            <button type="button" class="btn btn-primary">
                                ����
                            </button>
                        </a>
                    </div>
                    <!--�[�J�ʪ���-->
                    <div class="col-6 h-100">
                        <form class="" method="" action="" >
                            <div>���[�J�ʪ����A���b�ɦA��ƶq</div>
                            <div>�п���ʪ����G
                            </div>
                            <select class="form-select me-2 mb-2" aria-label="Default select example" name="">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                            <input type="submit" class="btn btn-primary"  value="�[�J�ʪ���">
                        </form>
                    </div>
                </div>
            </div>
            <div class="container border h-100" id="content1" >
                <div class="row align-items-center h-100">
                    <!--���ʤ��e-->
                    <div class="col border h-100">
                        <div class="item h-100">
                            <h3 class="mt-2">���ʤ��e</h3>
                            <p class="text-wrap">���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e���ʤ��e</p>
                        </div> 
                    </div>
                </div>
            </div>
        </div>

    <script src="${pageContext.request.contextPath}/static/mhl_js/btn4com_review.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>