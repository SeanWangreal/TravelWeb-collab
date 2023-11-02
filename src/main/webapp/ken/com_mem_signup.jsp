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
        html {
            background-color: inherit;
        }

       
        .button-container {
            display: flex;
            justify-content: center;

        }

    </style>
</head>

<body>
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
        <div class="container-fluid" style="background-color: transparent;">
    <c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
			<ul>
			    <c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
	</c:if>
        
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                    <div class>
	    				<form action="${pageContext.request.contextPath}/CompanyServlet">
	                        <div class="d-flex align-items-center justify-content-between mb-3">
	                            <h3>註冊帳號</h3>
	                        </div>
	                       
	                        <div class="form-floating mb-4">
	                            <input type="text" class="form-control" id="floatingPassword" name="compmail" value="${mail}">
	                            <label for="floatingPassword">信箱</label>
	                            <button type="submit" class="btn btn-primary py-0 w-20 mb-3" name="action" value="mail">寄驗證碼</button>
	                        </div>
	
	                        <div class="form-floating mb-4">
	                            <input type="text" class="form-control" id="floatingPassword" name="genAuthCode" value="${genAuthCode}">
	                            <label for="floatingPassword">驗證碼</label>
	                        </div>
	                        <button type="submit" class="btn btn-primary py-3 w-100 mb-3" name="action" value="genAuthCode">下一步</button>
					    </form>			
                    </div>
                </div>
            </div>
        </div>

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