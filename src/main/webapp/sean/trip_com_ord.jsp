<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.tha103.gogoyu.trip_ord.model.*"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TravelMaker</title>
<link href="../dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../static/sean_css/comp_ord.css">
</script>
<style>
@media ( min-height : 500px) and (max-height: 1300px) {
	.shop {
		height: 60vh;
	}
	.info {
		height: 60vh;
	}
}

@media ( min-height : 0px) and (max-height: 500px) {
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
				<i class="fa-regular fa-message icon"
					style="color: black; font-size: 30px; background-color: transparent;"></i>
			</button>
			<button type="menu" class="head_btn" id="info">
				<i class="fa-regular fa-bell icon"
					style="color: black; font-size: 30px; width: 30px; background-color: transparent;"></i>
			</button>
			<button type="button" class="head_btn" id="">
				<a class="profile" href="${pageContext.request.contextPath}/sean/trip_ticket_all.jsp">
					<i class="fa-solid fa-store" style="color: #000000; font-size: 30px; width: 30px; background-color: transparent;"></i>
							</a>
			</button>
			<button type="menu" class="head_btn" id="">
					<a class="profile"
					href="${pageContext.request.contextPath}/ken/tripcom_mem.jsp">
					<i class="fa-solid fa-user icon"
					style="color: black; font-size: 30px; background-color: transparent;"></i>
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
				<a
					href="${pageContext.request.contextPath}/sean/trip_ticket_all.jsp"
					class="left_btn" style="color: #000000"> <i
					class="fa-solid fa-ticket" style="color: #000000;"></i> 我的行程
				</a>
			</div>
			<div class="mem-data">
				<a href="${pageContext.request.contextPath}/sean/trip_com_ord.jsp" class="left_btn" style="color: #FCC416;"> <i class="fa-solid fa-file-invoice"
					style="color: black;"></i> 訂單資訊
				</a>
			</div>
			<div class="mem-data">
				<a href="${pageContext.request.contextPath}/sean/trip_ticket_review.jsp" 
				class="left_btn" style="color: #000000;"><i class="fa-regular fa-comment" style="color: #000000;"></i> 匿名評論 </a>
			</div>
		</aside>
	</nav>
<%-- 	<% --%>
<!-- // 	Map<Trip_ord, List<String>> tripOrdMap = (Map<Trip_ord, List<String>>) request.getAttribute("tripOrdMap"); -->
<!-- // 	if (tripOrdMap == null) { -->
<!-- // 		Trip_ordServiceHibernate tripStockSvc = new Trip_ordServiceHibernate(); -->
<!-- // 		String compString = (String) request.getSession().getAttribute("compId"); -->
<!-- // 		if (compString == null ){ -->
<!-- // 			response.sendRedirect(request.getContextPath() + "/sean/select_page.jsp"); -->
<!-- // 			return; -->
<!-- // 		} -->
<!-- // 		Integer compId = Integer.parseInt((String) request.getSession().getAttribute("compId")); -->
<!-- // 		tripOrdMap = tripStockSvc.getTripOrdByCompId(compId); -->
<!-- // 	}; -->
<!-- // 	request.setAttribute("tripOrdMap", tripOrdMap); -->
<%-- 	%> --%>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<div id="searching">
					<i class="fa-solid fa-magnifying-glass" style="color: #000000;"></i>
					<input type="search" placeholder="輸入訂單編號" id="ord-search">
					<span id="warning"></span><span id=total></span>
				</div>
					<div id="allOrd">
					</div>
					<div id="page">
					</div>
			</div>
		</main>
	</div>
	<script src="../static/sean_js/btn4com.js"></script>
	<script type="text/javascript">
    $(document).ready(function() {
	var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var url = location.origin+webCtx+"/sean/TripOrdServlet"
    var totalReview = 0 ;
    var avgScore = 0 ;
    $.ajax({
        url : url,
        type : "POST", // GET | POST | PUT | DELETE | PATCH
        data : {
            "action" : "allOrd",
            "whichpage" : 1
        },
        dataType : "json", // 預期會接收到回傳資料的格式： json | xml | html
        success : function(data) { // request 成功取得回應後執行
        	var	totalOrd = 0;
        	if (data.length != 0){
        		totalOrd = data[1][3];
        	}
            var html =``;
            for (var i = 0; i < data.length;i+=2){
                console.log(data[i]);
                console.log(data[i+1]);
                var word = "";
                if (data[i].ordStatus == 1){
                    word = "已成立";
                } else{
                    word = "已取消";
                }
                html +=`<div class="ord">
                            <div class="ord-head">
                                <label for="">訂單編號:</label><span>`+data[i].tripOrdId+`</span>
                                <label for="">訂單狀態:</label><span>`+word+`</span>
                            </div>
                            <div class="all-info">
                                <div>   
                                    <div style="display:flex">
                                    <label for="" class="l_long ord-label">套票<br>
                                    <span class="long">`+data[i+1][0]+`</span>
                                    </label> 
                                    <label for=""
                                        class="l_long ord-label">張數<br>
                                    <span class="long">`+data[i].amount+`</span>
                                    </label> 
                                    <label for=""
                                        class="l_long ord-label">總金額<br>
                                    <span class="long">`+data[i].totalPrice+`</span></label> 
                                    
                                    </div>
                                    <label for=""
                                        class="l_long ord-label">顧客名稱<br>
                                    <span class="long">`+data[i+1][1]+`</span>
                                    </label> 
                                    <label for=""
                                        class="l_long ord-label">開始日期<br>
                                    <span class="long">`+data[i].startTime+`</span>
                                    </label> 
                                    <label for=""
                                        class="l_long ord-label">結束日期<br>
                                    <span class="long">`+data[i].endTime+`</span>
                                    </label>
                                </div>
                                <div class="remark-block">
                                    <label for="" class="remark">備註:</label>
                                    <p class="remark-info">`+data[i].remark+`
                                    </p>
                                </div>
                            </div>
                        </div>`
                    }
                    $("#allOrd").html(html);
                    $("#total").text("總共"+totalOrd+"筆訂單")
            var htmlA=`<span>第</span>`;
            if (totalOrd <= 5){
                htmlA  += `<button type="button" class="at" data-whichpage="1">1</button><span>頁</span>`
            } else {
            	if (totalOrd % 5 != 0){
		            for (var i =1; i <= totalOrd/5+1;i++){
		                htmlA += `<button type="button" class="at" data-whichpage=`+i+`>`+i+`</button><span>頁</span>`
		            }
	        	} else {
	        		for (var i =1; i <= totalOrd/5;i++){
		                htmlA += `<button type="button" class="at" data-whichpage=`+i+`>`+i+`</button><span>頁</span>`
		            }
	        	}
            }
            $("#page").html(htmlA);
            $(".at").eq(0).addClass("where");
            $(".at").on('click',function(){
            	$(".at").removeClass("where");
            	$(this).addClass("where");
                var whichpage = $(this).attr("data-whichpage");
                var path = window.location.pathname;
                var webCtx = path.substring(0, path.indexOf('/', 1));
                var url = location.origin+webCtx+"/sean/TripOrdServlet"
                var totalReview = 0 ;
                var avgScore = 0 ;
                $.ajax({
                    url : url,
            type : "POST", // GET | POST | PUT | DELETE | PATCH
            data : {
                "action" : "allOrd",
                "whichpage" : whichpage
            },
            dataType : "json", // 預期會接收到回傳資料的格式： json | xml | html
            success : function(data) { // request 成功取得回應後執行
            	var	totalOrd = data[1][2];
                $("#allOrd").html("");
                var html = ``;
                for (var i = 0; i < data.length;i+=2){
                    console.log(data[i]);
                    console.log(data[i+1]);
                    var word = "";
                    if (data[i].ordStatus == 1){
                        word = "已成立";
                    } else{
                        word = "已取消";
                    }
                    html +=`<div class="ord">
                                <div class="ord-head">
                                    <label for="">訂單編號:</label><span>`+data[i].tripOrdId+`</span>
                                    <label for="">訂單狀態:</label><span>`+word+`</span>
                                </div>
                                <div class="all-info">
                                    <div>   
                                        <div style="display:flex">
                                        <label for="" class="l_long ord-label">套票<br>
                                        <span class="long">`+data[i+1][0]+`</span>
                                        </label> 
                                        <label for=""
                                            class="l_long ord-label">張數<br>
                                        <span class="long">`+data[i].amount+`</span>
                                        </label> 
                                        <label for=""
                                            class="l_long ord-label">總金額<br>
                                        <span class="long">`+data[i].totalPrice+`</span></label> 
                                        
                                        </div>
                                        <label for=""
                                            class="l_long ord-label">顧客名稱<br>
                                        <span class="long">`+data[i+1][1]+`</span>
                                        </label> 
                                        <label for=""
                                            class="l_long ord-label">開始日期<br>
                                        <span class="long">`+data[i].startTime+`</span>
                                        </label> 
                                        <label for=""
                                            class="l_long ord-label">結束日期<br>
                                        <span class="long">`+data[i].endTime+`</span>
                                        </label>
                                    </div>
                                    <div class="remark-block">
                                        <label for="" class="remark">備註:</label>
                                        <p class="remark-info">`+data[i].remark+`
                                        </p>
                                    </div>
                                </div>
                            </div>`
                        }
                        $("#allOrd").html(html);
                    }
                })
            })
            }
            });
    $("#ord-search").on("keydown",function(e){
		if(e.which == 13){
			$("#warning").text("");
            var tripOrdId = $(this).val();
            if (tripOrdId !== ""){
   	            if (/[0-9*]/.test(tripOrdId)){
   	            	console.log(tripOrdId);
   	                var path = window.location.pathname;
   	                var webCtx = path.substring(0, path.indexOf('/', 1));
   	                var url = location.origin+webCtx+"/sean/TripOrdServlet"
   	            	$.ajax({
   	                 url: url,
   	                 type: "POST", 
   	                 data: {
   	                	"action":"OneTripOrd",
   	                     "tripOrdId": tripOrdId
   	                 },            
   	                 dataType: "json", 
   	                 success: function(data) { 
   	                	$("#allOrd").html("");
   	                	console.log(data);
   	                	var word = "";
   	                	if (data.length != 0){
	   	                    if (data[0].ordStatus == 1){
	   	                        word = "已成立";
	   	                    } else{
	   	                        word = "已取消";
	   	                    }
	   	                   var html =`<div class="ord">
				                           <div class="ord-head">
				                           <label for="">訂單編號:</label><span>`+data[0].tripOrdId+`</span>
				                           <label for="">訂單狀態:</label><span>`+word+`</span>
				                       </div>
				                       <div class="all-info">
				                           <div>   
				                               <div style="display:flex">
				                               <label for="" class="l_long ord-label">套票<br>
				                               <span class="long">`+data[1][0]+`</span>
				                               </label> 
				                               <label for=""
				                                   class="l_long ord-label">張數<br>
				                               <span class="long">`+data[0].amount+`</span>
				                               </label> 
				                               <label for=""
				                                   class="l_long ord-label">總金額<br>
				                               <span class="long">`+data[0].totalPrice+`</span></label> 
				                               
				                               </div>
				                               <label for=""
				                                   class="l_long ord-label">顧客名稱<br>
				                               <span class="long">`+data[1][1]+`</span>
				                               </label> 
				                               <label for=""
				                                   class="l_long ord-label">開始日期<br>
				                               <span class="long">`+data[0].startTime+`</span>
				                               </label> 
				                               <label for=""
				                                   class="l_long ord-label">結束日期<br>
				                               <span class="long">`+data[0].endTime+`</span>
				                               </label>
				                           </div>
				                           <div class="remark-block">
				                               <label for="" class="remark">備註:</label>
				                               <p class="remark-info">`+data[0].remark+`
				                               </p>
				                           </div>
				                       </div>
				                   </div>`
	   	                       $("#allOrd").html(html);
   	                		
   	                	} else {
   	                	 $("#allOrd").text("查無資料");
   	                	}
   	                 }
   	             });
   	            	
   	            } else {
   	            	$("#warning").text("請輸入正確編號格式!").css("color","red");
   	            }
            }
        }
	})
        })

    </script>
</html>