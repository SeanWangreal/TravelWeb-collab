<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <script src="../vendors/jquery/jquery-3.7.1.min.js" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/b4c50f14e1.js" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登入</title>
    <link href="../dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../static/eric_css/ericheader.css">
    <style>
        
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
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/eric/ConsumerServlet" >
        <div class="container-fluid" style="background-color: #d9d2c5;">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;" >
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4" >
                    <div class >
                        <div class="d-flex align-items-center justify-content-between mb-3" >
                            <h3>登入帳號</h3>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingInput" name="cusAccount" >
                            <label for="floatingInput">帳號</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="text" class="form-control" id="floatingPassword" name="cusPassword" >
                            <label for="floatingPassword">密碼</label>
                        </div>
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <a href="">Forgot Password</a>
                        </div>
                        <button type="submit" class="btn btn-primary py-3 w-100 mb-4" name="action" value="getOne_For_Login">登入</button>
                        <p class="text-center mb-0">沒有帳號嗎? <a href="">點此註冊</a></p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Sign In End -->
    </div>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
    </form>
  
  
   
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