<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>付款成功</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    
    .success-container {
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        text-align: center;
        padding: 20px;
    }
    
    h1 {
        color: #2ecc71;
        font-size: 36px;
    }
    
    p {
        font-size: 18px;
    }
    
    a {
        display: inline-block;
        margin-top: 20px;
        padding: 10px 20px;
        background-color: #2ecc71;
        color: #fff;
        text-decoration: none;
        border-radius: 4px;
        transition: background-color 0.3s;
    }
    
    a:hover {
        background-color: #27ae60;
    }
    </style>    
</head>
<body>
	<img src="${pageContext.request.contextPath}/chu/123.jpg">
    <div class="success-container" style="position :absolute">
        <h1>付款成功</h1>
        <p>感謝您的訂單，您的付款已成功處理。</p>
        <a href="${pageContext.request.contextPath}/chu/bookedList(hotel).jsp">返回訂單明細</a>
    </div>
</body>
</html>







   