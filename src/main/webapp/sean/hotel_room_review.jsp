<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://kit.fontawesome.com/b4c50f14e1.js" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelMaker</title>
    <link href="../dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../static/sean_css/comp_product_review.css">
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
    </style>
</head>

<body>
    <script src="../vendors/jquery/jquery-3.7.1.min.js"></script>
    <nav class="st">
        <!-- <a class="word" id="home" href="#">Home</a> -->
        <div class="head">
            </button>
            <button type="menu" class="head_btn" id="msg">
                <i class="fa-regular fa-message icon" style="color: black; font-size:30px; 
                            background-color:transparent;"></i>
            </button>
            <button type="menu" class="head_btn" id="info">
                <i class="fa-regular fa-bell icon" style="color: black;font-size:30px; width: 30px;
                            background-color:transparent;"></i>
            </button>
            <button type="menu" class="head_btn" id="">
                <i class="fa-solid fa-store" style="color: #000000;font-size:30px; width: 30px;
                background-color:transparent;"></i>

            </button>
            <button type="button" class="head_btn">
                <a class="profile" href="#">
                    <i class="fa-solid fa-user icon" style="color: black; font-size:30px;
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
        <div id="shell"></div>
        <aside class="left">
            <div class="mem-data">
                <a href="" class="left_btn">
                    <i class="fa-solid fa-hotel" style="color: #000000;"></i> 我的房間
                </a>
            </div>
            <div class="mem-data">
                <a href="" class="left_btn">
                    <i class="fa-regular fa-comment" style="color: #000000;"></i> 匿名評論
                </a>
            </div>
        </aside>
    </nav>
    <div class="all">
        <main class="main-content">
            <div class="main-content-info">
                <div id="point-place">
                    <div>
                        <h1>4.4</h1>
                    </div>
                    <div>
                        <label for="">整體評分</label>
                        <br>
                        <span>13</span><span>則評價</span>
                    </div>
                </div>
                <hr>
                <ul>
                    <li class="one-review">
                        <div class="title-block">
                            <img src="4621.png" alt="" style="width: 50px; height: 50px;border-radius: 25%;">
                            <div>
                                投稿日期<span class="year">2022</span><span>年</span>
                                <span class="month">8</span><span>月</span>
<!--                                 <div> -->
<!--                                     <span>1</span><span>人按讚</span> -->
<!--                                 </div> -->
                            </div>
                            <div>
                                <span class="star" data-star="1">
                                    <i class="fa-solid fa-star"></i>
                                </span>
                                <span class="star" data-star="2">
                                    <i class="fa-solid fa-star"></i>
                                </span>
                                <span class="star" data-star="3">
                                    <i class="fa-solid fa-star"></i>
                                </span>
                                <span class="star" data-star="4">
                                    <i class="fa-solid fa-star"></i>
                                </span>
                                <span class="star" data-star="5">
                                    <i class="fa-solid fa-star"></i>
                                </span>
                            </div>
                        </div>
                        <div class="review-block">
                            <p>
                                我們很高興聘請RTR為我們的家人定製5天愉快的旅行路線。Jo Wang是一位非常有責任心的旅遊顧問，她對所有的电子郵件都能及時回復。她很通融，對我們的行程提出了專業建議。保持良好的服務!👍👍👍傑弗里·彭分配司機導遊，非常關心我們。他總是把顧客的安全放在第一位，尤其是當我們帶着兩個孩子旅行的時候。他是一位經驗豐富的導遊，認真規劃我們每天的行程，確保每一個我們感興趣的地方都能得到最好的體驗。我們很高興有他作為我們這次旅行的司機和嚮導!👍👍👍
                            </p>
                            <div class="read-block">
                                <button class="read">查看全部</button>
                            </div>
                        </div>
                        <hr>
                    </li>
                </ul>
            </div>
        </main>
        
    </div>
    <script src="../static/sean_js/btn4com_review.js"></script>
    <script>
        $(document).ready(function(){
        	$.ajax({
      		  url: "/TravelWeb-collab/sean/RoomStockServlet",
      		  type: "POST",                  // GET | POST | PUT | DELETE | PATCH
      		  data: {
      			  "action" : "allReview",
      			  "compId" : compId
      		  },
      		  dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      		  success: function(data){      // request 成功取得回應後執行
      			  console.log(data);
      		  }
   		});
        })
    </script>
</body>

</html>