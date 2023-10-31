<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tha103.gogoyu.consumer.model.*"%>

<%
Consumer consumer = (Consumer) request.getAttribute("consumer"); //EmpServlet.java(Concroller), 存入req的empVO物件

if ((Integer)request.getSession().getAttribute("cusId") == null){
	response.sendRedirect(request.getContextPath()+"/eric/signin.jsp");
}
Integer cusId = (Integer) request.getSession().getAttribute("cusId");
ConsumerServiceHibernate cusSvc = new ConsumerServiceHibernate();
consumer = cusSvc.getOneCus(cusId);
request.setAttribute("consumer", consumer);


%>    

<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://kit.fontawesome.com/b4c50f14e1.js" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>個人資訊</title>
    <link href="../dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="../static/eric_css/ordinf.css">

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
        img{
        width:200px;
        height:200px;
        }
        
    </style>
</head>

<body>
    <script src="../vendors/jquery/jquery-3.7.1.min.js"></script>

    <nav class="st">
        <!-- <a class="word" id="home" href="#">Home</a> -->
        <a class="logo" id="home" href="${pageContext.request.contextPath}/mhl/home.jsp">GO<i class="fa-solid fa-location-dot" style="color: #ffbf1c;"></i>GOYU</a>
         <div class="head">
            <button type="menu" class="head_btn" aria-label="規劃行程" id="shop">
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
                <a class="profile" href="${pageContext.request.contextPath}/eric/personal_detail.jsp">

<img src="${pageContext.request.contextPath}/eric/PictureServlet?cus_id=${consumer.cusId}" style="width:30px;height:30px"> 
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
        <div id="shell"></div>
        <aside class="left">
            <div class="mem-data">
                <a class="left_btn"  href="${pageContext.request.contextPath}/chu/shopping(hotel).jsp">
                    <i class="fa-solid fa-cart-shopping" style="color: black;"></i> 制定規劃
                </a>
            </div>
            <div class="mem-data">
                <a class="left_btn" href="${pageContext.request.contextPath}/eric/personal_detail.jsp">
                    <i class="fa-regular fa-user" style="color: black;"></i> 會員資料
                </a>
            </div>
            <div class="mem-data">
                <a class="left_btn" href="${pageContext.request.contextPath}/chu/bookedList(hotel).jsp">
                    <i class="fa-solid fa-file-invoice" style="color: black;"></i> 訂單資訊
                </a>
            </div>
            <div class="mem-data">
                <a class="left_btn" href="">
                    <i class="fa-solid fa-star" style="color: black;"></i> 我的蒐藏
                </a>
            </div>
            <div class="mem-data">
                <a class="left_btn" href="hotel_room_review.html">
                    <i class="fa-regular fa-calendar-days" style="color: black;"></i> 行事曆
                </a>
            </div>
        </aside>
    </nav>
    <div class="all">
        <main class="main-content">
            <div class="main-content-info">
                <br>
               
                <section class="item" style="width:800px">
                    <h1 style="border: 0px solid">${consumer.cusName},您好!</h1>
                    <br>
                    <a href="${pageContext.request.contextPath}/eric/ConsumerServlet?action=Logout" class="logoutbtn">按此登出</a>
                    
                </section>
	
                <br>
                <h5 style="font-weight:bolder">會員資料</h5>
                    <span style="float: left">在個人資料中更新最新資訊並確認</span>
                            <div class="memBtn" id="memBtn"style="text-decoration: none; text-align: right; padding-right:120px; font-size:16px; font-family: 粉圓">
                                <a href="${pageContext.request.contextPath}/eric/ConsumerServlet?action=getOne_For_Update">編輯</a>
                            </div>
                            <br>
                            <div class="mem_detal">
                                <div class="personal_item" style="width:30%; font-weight:bolder; font-family: 粉圓; border:none ">
                                    <span>姓名</span><br><br>
                                    <span>帳號</span><br><br>
                                    <span>信箱 </span><br><br>
                                    <span>電話</span><br><br>
                                    <span>住址</span><br><br>
                                    <span>性別</span><br><br>
                                    <span>照片</span><br>

                                </div>
                                <div class="personal_item" style="width:70% ; border:none white ; padding-left: 0px">
                                    <input class="mem" id="mem" value="${consumer.cusName}" readonly></input><br><br>

                                    <input class="mem" id="mem" value="${consumer.cusAccount}" readonly></input><br><br>

                                    <input class="mem" id="mem" value="${consumer.cusMail}" readonly></input><br><br>

                                    <input class="mem" id="mem" value="${consumer.cusPhone}"
                                        readonly></input><br><br>

                                    <input class="mem" id="mem"  value="${consumer.cusAddress}"
                                        readonly></input><br><br>

                                    <input class="mem" id="mem"  value="${consumer.cusSex}"
                                        readonly></input><br><br>

                                    <img src="${pageContext.request.contextPath}/eric/PictureServlet?cus_id=${consumer.cusId}"> 

                                </div>
                            </div>
        
            </div>
        </main>
    </div>


    <!-- <script src="btn4com_review.js"></script> -->
    <script src="${pageContext.request.contextPath}/static/eric_js/ordinf.js"></script>



</body>

</html>