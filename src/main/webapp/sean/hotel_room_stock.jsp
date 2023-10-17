<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*"%>
<%@ page import="com.tha103.gogoyu.room_photo.model.*"%>
<%
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://kit.fontawesome.com/b4c50f14e1.js"
	crossorigin="anonymous"></script>
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/sean_css/comp_product.css">
<style>
.main-content-info{
	font-size: 0px;
}
.all-date {
	width:13%;
	margin-right: 1.3%;
	border: 1px solid black;
	display: inline-block;	
}
.all-date:nth-child(7n){
	margin-right: 0%;
}
.stock{
	width:50%;
	border:none;
	font-size: 16px;
}
.date{
	font-size: 16px;
}
</style>
</head>
<body>
	<script
		src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
	<nav class="st">
		<!-- <a class="word" id="home" href="#">Home</a> -->
		<div class="head">
			<button type="menu" class="head_btn" id="msg">
				<i class="fa-regular fa-message icon"
					style="color: black; font-size: 30px; background-color: transparent;"></i>
			</button>
			<button type="menu" class="head_btn" id="info">
				<i class="fa-regular fa-bell icon"
					style="color: black; font-size: 30px; width: 30px; background-color: transparent;"></i>
			</button>
			<button type="menu" class="head_btn" id="">
				<i class="fa-solid fa-store"
					style="color: #000000; font-size: 30px; width: 30px; background-color: transparent;"></i>

			</button>
			<button type="button" class="head_btn">
				<a class="profile"
					href="${pageContext.request.contextPath}/sean/select_page.jsp">
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
				<a href="${pageContext.request.contextPath}/sean/hotel_room_all.jsp"
					class="left_btn" style="color: #FCC416"> <i
					class="fa-solid fa-hotel" style="color: #000000;"></i> 我的房間
				</a>
			</div>
			<div class="mem-data">
				<a href="" class="left_btn"> <i class="fa-regular fa-comment"
					style="color: #000000;"></i> 匿名評論
				</a>
			</div>
		</aside>
	</nav>
	<div class="all">
		<main class="main-content">
			<div class="main-content-info">
				<%-- 				<% --%>
				<!-- 				LinkedHashMap<Room, Set<Room_photo>> map = (LinkedHashMap<Room, Set<Room_photo>>) request.getAttribute("map"); -->
				<!-- 				List<Room> roomList = null; -->
				<!-- 				if (map == null){ -->
				<!-- 					map = new LinkedHashMap<Room, Set<Room_photo>>(); -->
				<!-- 					RoomService roomSrc = new RoomServiceHibernate(); -->
				<!-- 					Integer compId = Integer.parseInt((String) request.getSession().getAttribute("compId")); -->
				<!-- 					roomList = roomSrc.getRoomByCompId(compId); -->
				<!-- 					for (Room li : roomList) { -->
				<!-- 						Set<Room_photo> roomPhoto = roomSrc.getAllPhoto(li.getRoomId()); -->
				<!-- 						map.put(li, roomPhoto); -->
				<!-- 					} -->
				<!-- 					request.setAttribute("map",map); -->
				<!-- 					map = (LinkedHashMap<Room, Set<Room_photo>>) request.getAttribute("map"); -->
				<!-- 				} -->
				<!-- 				// 				request.setAttribute("backHere",request.getRequestURL()); -->
				<!-- 				// 				System.out.print(request.getRequestURI()); -->
				<%-- 				%> --%>
<%-- 				<c:forEach var="room" items="${map.keySet()}"> --%>
					<%-- 					<c:if test="${room.roomStatus!=-1}"> --%>
					<div class="all-date">
						<label class="date">2023/10/17</label>
						<br>
						<input class="stock" type="text" value="5"><span>間</span>
					</div>
					<div class="all-date">
						<label class="date">2023/10/17</label>
						<br>
						<input class="stock" type="text" value="5"><span>間</span>
					</div>
					<div class="all-date">
						<label class="date">2023/10/17</label>
						<br>
						<input class="stock" type="text" value="5"><span>間</span>
					</div>
					<div class="all-date">
						<label class="date">2023/10/17</label>
						<br>
						<input class="stock" type="text" value="5"><span>間</span>
					</div>
					<div class="all-date">
						<label class="date">2023/10/17</label>
						<br>
						<input class="stock" type="text" value="5"><span>間</span>
					</div>
					<div class="all-date">
						<label class="date">2023/10/17</label>
						<br>
						<input class="stock" type="text" value="5"><span>間</span>
					</div>
					<div class="all-date">
						<label class="date">2023/10/17</label>
						<br>
						<input class="stock" type="text" value="5"><span>間</span>
					</div>
					<div class="all-date">
						<label class="date">2023/10/17</label>
						<br>
						<input class="stock" type="text" value="5"><span>間</span>
					</div>
					<div class="all-date">
						<label class="date">2023/10/17</label>
						<br>
						<input class="stock" type="text" value="5"><span>間</span>
					</div>
					<div class="all-date">
						<label class="date">2023/10/17</label>
						<br>
						<input class="stock" type="text" value="5"><span>間</span>
					</div>
					<%-- 					</c:if> --%>
<%-- 				</c:forEach> --%>



			</div>
		</main>
	</div>

	<script
		src="${pageContext.request.contextPath}/static/sean_js/btn4com.js"></script>
	<script>
		$(".detail").on("click", function () {
	        let room = $(this).closest("section.one-room");
	        $("body").css("overflow", "hidden");
	        let alert_bg = $(room).find(".alert_bg").eq(0);
	        alert_bg.addClass("on");
	        let watch = $(room).find(".watch");
	        watch.addClass("on");
	        
	        $(".no").on("click", function () {
	            $("body").css("overflow", "auto");
	            alert_bg.removeClass("on");
	            watch.removeClass("on");
	        })
	    })
		$(".renewStatus").on("click", function () {
	        let room = $(this).closest("section.one-room");
	        $("body").css("overflow", "hidden");
	        let alert_bg = $(room).find(".alert_bg").eq(1);
	        alert_bg.addClass("on");
	        let al = $(room).find(".alert").eq(0);
	       	al.addClass("on");
	        $(".yes").on("click", function () {
	            room.remove();
	            $("body").css("overflow", "auto");
	            alert_bg.removeClass("on");
	            al.removeClass("on");
	        })
	        $(".no").on("click", function () {
	            $("body").css("overflow", "auto");
	            alert_bg.removeClass("on");
	            al.removeClass("on");
	        })
	    })
        var delete_btn = document.querySelectorAll(".delete");
        $(".delete").on("click", function () {
            let room = $(this).closest("section.one-room");
            let alert_bg = $(room).find(".alert_bg").last();
            let al = $(room).find(".alert").last();
        
        	if(room.find("span").hasClass("room-status-on")){
        		alert("請先下架再刪除!!");
        	}else{
            $("body").css("overflow", "hidden");
            alert_bg.addClass("on");
           	al.addClass("on");        		
        	}
            $(".yes").on("click", function () {
                room.remove();
                $("body").css("overflow", "auto");
                alert_bg.removeClass("on");
                al.removeClass("on");
            })
            $(".no").on("click", function () {
                $("body").css("overflow", "auto");
                alert_bg.removeClass("on");
                al.removeClass("on");
            })
        })
        
        $(".change").on("click",function(){
        	let room = $(this).closest(".one-room");
        	if(room.find("span").hasClass("room-status-off")){
        		$(this).closest("form").find(".go").click();        		
        	} else{
        		alert("請先下架再修改");
        	}
        })
    </script>
	<script>
        var month_leap = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        var month_normal = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        var month_name = ["January", "Febrary", "March", "April", "May", "June", "July", "Auguest", "September", "October", "November", "December"];
        var holder = document.getElementsByClassName("days");
        var prev = document.getElementsByClassName("prev");
        var next = document.getElementsByClassName("next");
        var ctitle = document.getElementsByClassName("calendar-title");
        var cyear = document.getElementsByClassName("calendar-year");
        var my_date = new Date();
        var my_year = my_date.getFullYear();
        var my_month = my_date.getMonth();
        var my_day = my_date.getDate();
        //获取某年某月第一天是星期几
        $(function () {
            function dayStart(month, year) {
                var tmpDate = new Date(year, month, 1);
                return (tmpDate.getDay());
            }
            //计算某年是不是闰年，通过求年份除以4的余数即可
            function daysMonth(month, year) {
                if (year % 4 === 0 && year % 100 !== 0 || year % 400 === 0) {
                    return (month_leap[month]);
                } else {
                    return (month_normal[month]);
                }
            }
            function refreshDate() {
                var str = "";
                var totalDay = daysMonth(my_month, my_year); //获取该月总天数
                var firstDay = dayStart(my_month, my_year); //获取该月第一天是星期几
                var myclass;
                for (var i = 1; i < firstDay; i++) {
                    str += "<li></li>"; //为起始日之前的日期创建空白节点
                }
                for (var i = 1; i <= totalDay; i++) {
                    if ((i < my_day && my_year == my_date.getFullYear() && my_month == my_date.getMonth()) || my_year < my_date.getFullYear() || (my_year == my_date.getFullYear() && my_month < my_date.getMonth())) {
                        myclass = 'lightgrey'; //当该日期在今天之前时，以浅灰色字体显示
                    } else if (i == my_day && my_year == my_date.getFullYear() && my_month == my_date.getMonth()) {
                        myclass = 'colorbox'; //当天日期以绿色背景突出显示
                    } else {
                        myclass = 'darkgrey'; //当该日期在今天之后时，以深灰字体显示
                    }
                    str += `<li class=` + myclass + `>` +i+ `<p>30</p>
                        <input class="switch" type="text" value="213"></li>`; //创建日期节点
                }
                for (var i = 0; i < holder.length; i++) {
                    holder[i].innerHTML = str; //设置日期显示
                    ctitle[i].innerHTML = month_name[my_month]; //设置英文月份显示
                    cyear[i].innerHTML = my_year; //设置年份显示
                }
                $("p").on("click", function () {
                    $(this).toggleClass("switch");
                    $(this).closest("li").find("input").toggleClass("switch");
                })
                $("input").on("keydown", function (e) {
                    if (e.which == 13) {
                        $(this).toggleClass("switch");
                        var p = $(this).closest("li").find("p");
                        p.toggleClass("switch");
                        $(p).text($(this).val());
                    }
                })
            }
            refreshDate();
            $(prev).on('click', function (e) {
                e.preventDefault();
                my_month--;
                if (my_month < 0) {
                    my_year--;
                    my_month = 11;
                }
                refreshDate();
            })
            $(next).on('click', function (e) {
                e.preventDefault();
                my_month++;
                if (my_month > 11) {
                    my_year++;
                    my_month = 0;
                }
                refreshDate();
            })
            $(".stock").on('click', function () {
                $(this).closest(".one-room").find(".calendar").toggleClass("on");
            })
            $("h1").on("click", function () {
                my_month = my_date.getMonth();
                my_day = my_date.getDate();
                my_year = my_date.getFullYear();
                refreshDate()
            })
        })
    </script>
</body>
</html>