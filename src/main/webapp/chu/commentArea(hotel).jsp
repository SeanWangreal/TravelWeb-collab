j<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tha103.gogoyu.trip_ord.model.*"%>
<%@ page import="com.tha103.gogoyu.trip.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- jsp使用  el語法註冊-->
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room_ord.model.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>
<%@ page import="com.tha103.gogoyu.company.model.*"%>
<%@ page import="com.tha103.gogoyu.planning.model.*"%>
<%@ page import="com.tha103.gogoyu.planning.model.*"%>
<!-- 以下三行預防快取 -->
<%
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0);

session.setAttribute("roomId", 1);
Integer roomId =(Integer)session.getAttribute("roomId");


Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
RoomServiceHibernate RSH = new RoomServiceHibernate();
CompanyService CS = new CompanyService();
pageContext.setAttribute("room1", ROSH.gettripIdComment(roomId)); //map物件
pageContext.setAttribute("comp_name",CS.getComp(RSH.getRoom(roomId).getCompId()).getCompName()); //房型大標題
%>

<!DOCTYPE html>
<html>
<head>
    <title>飯店評語</title>
    <style>
        /* 基本頁面樣式 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        
        /* 評論容器 */
        .comment-container {
            width: 60%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        /* 單個評論 */
        .comment {
            margin-bottom: 20px;
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 5px;
        }

        /* 作者名稱 */
        .author {
            font-weight: bold;
        }

        /* 評論內容 */
        .comment-text {
            margin-top: 0px;
            margin-left: 100px;
            width: 70%;
            height: 150px;
            max-width: 70%;
            max-height: 150px;
        }

        /* 輸入框和按鈕 */
        .comment-form {
            margin-top: 20px;
        }

        .comment-form label {
            display: block;
            margin-bottom: 5px;
        }

        .comment-form input[type="text"],
        .comment-form textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .comment-form button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 3px;
            cursor: pointer;
        }

        /* 離開按鈕樣式 */
        .leave-button {
            background-color: #ff0000;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 3px;
            cursor: pointer;
            margin-top: 20px;
            margin-left: 50%;
        }

        .stars {
            display: inline-block;
        }

        .star {
            cursor: pointer;
            font-size: 24px;
        }

        .star:hover,
        .star.active {
            color: gold;
        }
    </style>
</head>
<body>
    
    <div class="comment-container">
    	<h1 style="text-decoration: underline; color: blue;">${comp_name}</h1>
    	<h2>客人評語</h2>
        <c:forEach var="roomVo1" items="${room1.keySet()}" >
		<hr>
		 <div class="comment" style="display: flex; height: 45px;">
            <div style="font-size: 20px;">
                <span class="author" >會員編號: ${roomVo1.cusId}</span>
                <br>
                <div >${room1.get(roomVo1).get(1)}</div>
                <br>    
                <img src="${pageContext.request.contextPath}/eric/PictureServlet?cus_id=${roomVo1.cusId}"  style="width: 130px; height: 140px; ">
            </div>
            <div style="height: 20%; position: relative; left:200px; display: flex;font-size: 18px;">
                <i style="position: relative; right: 100px;width: 100px;">評論時間:</i><i style="position: relative; right: 100px;width: 200px;">${roomVo1.commentsTime}</i>
                <span >${roomVo1.score}</span><span class="star" style="color: gold; position: relative;bottom: 7px;">★</span>
            </div>
           
        </div>
        <div class="comment-text" style="position:relative ; bottom:20px;left: 100px;">
            ${roomVo1.comments}
        </div>
		</c:forEach>
        <hr>
	

</body>
</html>
