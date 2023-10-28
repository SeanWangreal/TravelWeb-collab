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

        <div class="container-fluid" style="background-color: #d9d2c5;">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                    <div class>
                        
                        <div id="search" class="mem_info">
                            <div class="d-flex align-items-center justify-content-between mb-3" >
                                <h3>註冊帳號</h3>
                            </div>
                            
                            <div class="input_container">
                                <label>廠商別：</label>
                                <select name="compType" class="button_mem_info" style="font-size:20px; padding:9px ;padding-left:163px ;">
                                	<option value="0" style="font-size:20px; " selected>飯店業者</option>
                                	<option value="1" style="font-size:20px; ">旅行業者</option>
                                </select>
                            </div>
                            <div class="input_container" >
                                <label>公司名稱：</label>
                                <input name="compName" class="button_mem_info" value="${company.compName}" style="font-size:20px; padding:5px ;padding-left:51px ;">
                            </div>
                            <div class="input_container">
                                <label>公司地址：</label>
                                <input name="compAddress" class="button_mem_info"  value="${company.compAddress}" style="font-size:20px; padding:5px ;padding-left:51px ;">
                            </div>
                            <div class="input_container">
                                <label>公司電話：</label>
                                <input name="compPhone" class="button_mem_info"  value="${company.compPhone}" style="font-size:20px; padding:5px ;padding-left:51px ;">
                            </div>
                            <div class="input_container">
                                <label>負責人：</label>
                                <input name="principalName" class="button_mem_info"  value="${company.principalName}" style="font-size:20px; padding:5px ;padding-left:51px ;">
                            </div>
                            <div class="input_container">
                                <label>負責電話：</label>
                                <input name="principalPhone" class="button_mem_info"  value="${company.principalPhone}" style="font-size:20px; padding:5px ;padding-left:51px ;">
                            </div>
                            <div class="input_container">
                                <label>帳號：</label>
                                <input name="compAccount" class="button_mem_info"  value="${company.compAccount}" style="font-size:20px; padding:5px ;padding-left:51px ;">
                            </div>
                            <div class="input_container">
                                <label>密碼：</label>
                                <input name="compPassword" class="button_mem_info"  value="${company.compPassword}" style="font-size:20px; padding:5px ;padding-left:51px ;">
                            </div> 
                            <div class="input_container">
                                <label>信箱：</label>
                                <input name="compMail" class="button_mem_info"  value="${company.compMail}" style="font-size:20px; padding:5px ;padding-left:51px ;">
                            </div>
           					<div class="input_container">
                                <label>照片： </label>
                                <input name="parts" type="file" class="button_mem_info"  value="${company.compPhoto}" style="font-size:20px; padding:5px ;padding-left:101px ;">
                            </div>
                            
                        </div>
                        
                    </div>
                    <button type="submit" class="btn btn-primary py-2 w-50 mb-4">註冊!</button>
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