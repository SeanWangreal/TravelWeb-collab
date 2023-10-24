<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tha103.gogoyu.trip_ord.model.*"%>
<%@ page import="com.tha103.gogoyu.trip.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- jsp使用  el語法註冊-->
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room_ord.model.*"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>
<%@ page import="com.tha103.gogoyu.planning.model.*"%>
<%@ page import="com.tha103.gogoyu.planning.model.*"%>
<!-- 以下三行預防快取 -->
<%
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0);

session.setAttribute("tripId", 3);
Integer tripId =(Integer)session.getAttribute("tripId");

Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
TripServiceHibernate TSH = new TripServiceHibernate();
pageContext.setAttribute("trip1", TOSH.gettripIdComment(tripId));
// String tripName = TSH.getTrip(tripId).getTripName();
pageContext.setAttribute("trip_name",TSH.getTrip(tripId).getTripName());
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
    	<h1 style="text-decoration: underline; color: blue;">${trip_name}</h1>
    	<h2>客人評語</h2>
        <c:forEach var="tripVo1" items="${trip1.keySet()}">
		<hr>
		 <div class="comment" style="display: flex; height: 45px;">
            <div style="font-size: 20px;">
                <span class="author" >會員編號: ${tripVo1.cusId}</span>
                <br>
                <div >${trip1.get(tripVo1).get(1)}</div>
                <br>    
                <img src="${pageContext.request.contextPath}/PictureServlet?cusId=${tripVo1.cusId}"  style="width: 130px; height: 140px; ">
            </div>
            <div style="height: 20%; position: relative; left:200px; display: flex;font-size: 18px;">
                <i style="position: relative; right: 100px;width: 100px;">評論時間:</i><i style="position: relative; right: 100px;width: 200px;">${tripVo1.commentsTime}</i>
                <span >${tripVo1.score}</span><span class="star" style="color: gold; position: relative;bottom: 7px;">★</span>
            </div>
           
        </div>
        <div class="comment-text" style="position:relative ; bottom:20px;left: 100px;">
            ${tripVo1.comments}
        </div>
		</c:forEach>
        <hr>

        <div class="comment-form">
            <br>
            <br>
            <h3>新增評論</h3>
            
            <form action="${pageContext.request.contextPath}/Trip_ordComment" method="post">
            
                <label>評分：</label>
                <div class="stars" data-rating="0">
                    <span class="star" data-value="1">★</span>
                    <span class="star" data-value="2">★</span>
                    <span class="star" data-value="3">★</span>
                    <span class="star" data-value="4">★</span>
                    <span class="star" data-value="5">★</span>
                </div>
                <p>你的評分是: <span id="rating">0</span> 顆星</p>
                
                <input type="hidden" id="ratingInput" name="score" value="0"> 
                
                <br>
                <label for="comment-text">評論內容 (不超過200字)：</label>
                <textarea id="comment-text" name="comment" rows="4" required style="width: 100%; max-width: 400px;"></textarea>               
                <p style="color: gray;">最大字數: <span id="charCount">200</span>/200<span></span></p>
                <button type="submit">發表評論</button>
                <input type="hidden" id="submitButtonClicked" name="submitButtonClicked" value="false">
                
            </form>
        </div>
        
  
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
            const remainingChars = 200 - charCount;
            charCountElement.textContent = remainingChars;

            if (charCount > 200) {
                commentText.value = text.substring(0, 200); // 截斷文本以限制字數
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
        
        
        
//         計算點選的星數方便傳值給後端
        const ratingInput = document.getElementById('ratingInput');
        starsContainer.addEventListener('click', (event) => {
            if (event.target.classList.contains('star')) {
                const rating = event.target.getAttribute('data-value');
                ratingElement.textContent = rating;
                ratingInput.value = rating; // 更新隱藏的輸入元素的值
                updateStars(rating);
            }
        });
//      計算點選的星數方便傳值給後端

// 確認評論發表的按鈕有按
		const submitButton = document.querySelector('button[type="submit"]');
		const submitButtonClickedInput = document.getElementById('submitButtonClicked');
		
		submitButton.addEventListener('click', () => {
		    submitButtonClickedInput.value = "true"; // 將值設置為 "true" 表示按鈕已被單擊
		});
// 確認評論發表的按鈕有按		

    </script>
</body>
</html>
