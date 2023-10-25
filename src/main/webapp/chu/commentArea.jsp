<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- jsp使用  el語法註冊-->
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room_ord.model.*"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>
<%@ page import="com.tha103.gogoyu.planning.model.*"%>
<!-- 以下三行預防快取 -->
<%
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html>
<head>
    <title>住客評語</title>
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
        
        <h1 style="text-decoration: underline; color: blue;">統神大戲院</h1>
        <h2>客人評語</h2>
        <hr>
        <div class="comment" style="display: flex;">
            <div style="font-size: 20px;">
                <span class="author" >會員編號: 11</span>
                <div style="margin-top: 10px;">asiagodtone</div>
                <br>                
                <span style="margin-left: 30px;">8.7</span><span class="star" style="color: gold">★</span>
            </div>
            <div class="comment-text">
                這是一個示例評論這是一個示例評論這是一個示例評論。這是一個示例評論。這是一個示例評論。這是一個示例評論。這是一個示例評論。這是一個示例評論。這是一個示例評論。這是一個示例評論。這是一個示例評論。這是一個示例評論。這是一個示例評論。
            </div>
        </div>
        <hr>
        

        <div class="comment-form">
            <br>
            <br>
            <h3>新增評論</h3>
            <form>
                <label>評分：</label>
                <div class="stars" data-rating="0">
                    <span class="star" data-value="1">★</span>
                    <span class="star" data-value="2">★</span>
                    <span class="star" data-value="3">★</span>
                    <span class="star" data-value="4">★</span>
                    <span class="star" data-value="5">★</span>
                </div>
                <p>你的評分是: <span id="rating">0</span> 颗星</p>
                <br>
                <label for="comment-text">評論內容 (不超過100字)：</label>
                <textarea id="comment-text" name="comment-text" rows="4" required style="width: 100%; max-width: 400px;"></textarea>
                <p style="color: gray;">最大字數: <span id="charCount">100</span>/100<span></span></p>
                <button type="submit">發表評論</button>
            </form>
        </div>
        
        <!-- 離開按鈕 -->
        <button class="leave-button" id="leaveButton">離開</button>
    </div>

    <script>
        const stars = document.querySelectorAll('.star');
        const ratingElement = document.getElementById('rating');
        const starsContainer = document.querySelector('.stars');
        const commentText = document.getElementById('comment-text');
        const charCountElement = document.getElementById('charCount');

        starsContainer.addEventListener('click', (event) => {
            if (event.target.classList.contains('star')) {
                const rating = event.target.getAttribute('data-value');
                ratingElement.textContent = rating;
                updateStars(rating);
            }
        });

        commentText.addEventListener('input', () => {
            const text = commentText.value;
            const charCount = text.length;
            const remainingChars = 100 - charCount;
            charCountElement.textContent = remainingChars;

            if (charCount > 100) {
                commentText.value = text.substring(0, 100); // 截斷文本以限制字數
                charCountElement.textContent = 0;
            }
        });

        function updateStars(rating) {
            stars.forEach(star => {
                if (star.getAttribute('data-value') <= rating) {
                    star.classList.add('active');
                } else {
                    star.classList.remove('active');
                }
            });
        }
    </script>
</body>
</html>
