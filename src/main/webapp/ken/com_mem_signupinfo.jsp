<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.tha103.gogoyu.company.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
Company company = (Company) request.getAttribute("Company");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <script src="./vendors/jquery/jquery-3.7.1.min.js" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/b4c50f14e1.js" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelMaker</title>
    <link href="${pageContext.request.contextPath}/static/ken_css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ken_css/head_and_font.css">
    <style>
       

        body {
            text-align: center;
        }

        .background {
            position: absolute;
            background-color: #d9d2c5;
        }

        .input_container {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin: 10px;
        }

        div#search {
            position: relative;
            /* padding: 20px 20px; */
            background-color: #d9d2c5;
            border-radius: 10px;
            width: 30vw;
            top: 100%;
            left: 50%;
            transform: translate(-50%, 0%);
            color: black;
            font-size: 25px;
            max-width: 1000px;
            min-width: 500px;
        }
    </style>
</head>

<body style="background-color: #d9d2c5;">
    <nav class="st">
        <a class="word" id="home" href="#">Home</a>
        <a class="word" id="hotel" href="#">HOT<i class="fa-solid fa-fire"
                style="color: #ff9500; background-color:transparent;"></i>Hotel</a>
        <a class="word" id="journel" href="#">HOT<i class="fa-solid fa-fire"
                style="color: #ff9500; background-color:transparent;"></i>Journel</a>
        <div class="head">
            <button type="menu" class="head_btn" aria-label="規劃行程" id="shop">
                <i class="fa-solid fa-suitcase-rolling" style="color: black; font-size:30px;
                            background-color:transparent;"></i>
            </button>
            <button type="menu" class="head_btn" id="msg">
                <i class="fa-regular fa-message" style="color: black; font-size:30px; 
                            background-color:transparent;"></i>
            </button>
            <button type="menu" class="head_btn" id="info">
                <i class="fa-regular fa-bell" style="color: black;font-size:30px; width: 30px;
                            background-color:transparent;"></i>
            </button>
            <button type="button" class="head_btn">
                <a class="profile" href="#">
                    <i class="fa-solid fa-user" style="color: black; font-size:30px;
                                background-color:transparent;"></i>
                </a>
            </button>
        </div>
        <aside class="msg all_side" id="msg_side">
            msg<br>msg<br>msg<br>msg<br>msg<br>msg<br>msg
            <br>msg<br>msg<br>msg<br>msg<br>msg<br>msg
        </aside>
        <aside class="info all_side" id="info_side">
            info<br>info<br>info<br>info<br>info<br>info
            <br>info<br>info<br>info<br>info<br>info
        </aside>
        <aside class="shop all_side" id="shop_side">
            shop<br>shop<br>shop<br>shop<br>shop<br>shop
            <br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
            <br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
            <br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
            <br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
            <br>shop<br>shop<br>shop<br>shop<br>shop<br>shop
        </aside>
    </nav>
    <FORM id="background"  METHOD="post" ACTION="${pageContext.request.contextPath}/CompanyServlet" enctype="multipart/form-data">
    <c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
			<ul>
			    <c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
	</c:if>

        <div class="container-fluid" style="background-color: #d9d2c5;">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                    <div class>
                        
                        <div id="search" class="mem_info">
                                <br><h3>註冊帳號</h3>
                            <div class="d-flex align-items-center justify-content-between mb-3" >
                            </div>
                            
                            <div class="form-floating mb-3">
                                <select name="compType" class="form-control" >
                                	<option value="0" style="font-size:16px; " selected>飯店業者</option>
                                	<option value="1" style="font-size:16px; ">旅行業者</option>
                                </select>
                                <label style="font-size:16px">廠商別：</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input name="compName" class="form-control" value="${company.compName}" >
                                <label style="font-size:16px">公司名稱：</label>
                            </div>
                            <div class="form-floating mb-3">
                                <select name="compType" class="form-control" >
                                	<option value="" style="font-size:16px; " selected>台北市</option>
                                	<option value="" style="font-size:16px; ">新北市</option>
                                	<option value="" style="font-size:16px; ">桃園市</option>
                                	<option value="" style="font-size:16px; ">台中市</option>
                                	<option value="" style="font-size:16px; ">台南市</option>
                                	<option value="" style="font-size:16px; ">高雄市</option>
                                	<option value="" style="font-size:16px; ">新竹縣</option>
                                	<option value="" style="font-size:16px; ">苗栗縣</option>
                                	<option value="" style="font-size:16px; ">彰化縣</option>
                                	<option value="" style="font-size:16px; ">南投縣</option>
                                	<option value="" style="font-size:16px; ">雲林縣</option>
                                	<option value="" style="font-size:16px; ">嘉義縣</option>
                                	<option value="" style="font-size:16px; ">屏東縣</option>
                                	<option value="" style="font-size:16px; ">宜蘭市</option>
                                	<option value="" style="font-size:16px; ">花蓮市</option>
                                	<option value="" style="font-size:16px; ">台東縣</option>
                                	<option value="" style="font-size:16px; ">連江縣</option>
                                	<option value="" style="font-size:16px; ">基隆市</option>
                                	<option value="" style="font-size:16px; ">新竹市</option>
                                	<option value="" style="font-size:16px; ">嘉義市</option>
                                	<option value="" style="font-size:16px; ">澎湖縣</option>
                                </select>
                                <input name="compAddress" class="form-control"  value="${company.compAddress}">
                                <label style="font-size:16px">公司地址：</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input name="compPhone" class="form-control"  value="${company.compPhone}">
                                <label style="font-size:16px">公司電話：</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input name="principalName" class="form-control"  value="${company.principalName}">
                                <label style="font-size:16px">負責人：</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input name="principalPhone" class="form-control"  value="${company.principalPhone}">
                                <label style="font-size:16px">負責電話：</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input name="compAccount" class="form-control"  value="${company.compAccount}">
                                <label style="font-size:16px">帳號：</label>
                            </div>
                            
                            <div class="form-floating mb-3">
                                <input name="compPassword" class="form-control"  value="${company.compPassword}">
                                <label style="font-size:16px">密碼：</label>
                            </div> 
                            <div class="form-floating mb-3">
                                <input name="compMail" class="form-control"  value="${company.compMail}">
                                <label style="font-size:16px">信箱：</label>
                            </div>
           					<div class="form-floating mb-3">
                                <input name="parts" type="file" class="form-control"  value="${company.compPhoto}">
                                <label style="font-size:16px;">照片： </label>
                            </div>
                            
                        </div>
                        
                    </div>
                    <button type="submit" class="btn btn-primary py-3 w-100 mb-4">註冊!</button>
                </div>
            </div>
          
        </div>

    	<input type="hidden" name="action" value="add">
<!-- 		<input type="submit" value="送出新增"> -->
	</FORM>
   


    <script>
        var msg_btn = document.getElementById("msg");
        var msg_side = document.getElementById("msg_side");
        var info_btn = document.getElementById("info");
        var info_side = document.getElementById("info_side");
        var shop_btn = document.getElementById("shop");
        var shop_side = document.getElementById("shop_side");

        function say() {
            info_side.classList.remove("on");
            shop_side.classList.remove("on");
            msg_side.classList.toggle("on");
        }

        function info() {
            msg_side.classList.remove("on");
            shop_side.classList.remove("on");
            info_side.classList.toggle("on");
        }

        function grab() {
            msg_side.classList.remove("on");
            info_side.classList.remove("on");
            shop_side.classList.toggle("on");
        }
        msg_btn.addEventListener("click", say);
        info_btn.addEventListener("click", info);
        shop_btn.addEventListener("click", grab);
    </script>
</body>

</html>