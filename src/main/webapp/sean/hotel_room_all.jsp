<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*"%>
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

.calendar-head {
	display: flex;
	/* flex-grow: 2; */
	align-items: center;
}

.calendar-word {
	text-align: center;
	width: 60%;
}

.aa {
	text-align: center;
	width: 20%;
	text-decoration: none;
}

.calendar {
	display: none;
	width: 100%;
	margin-top: 10px;
	background: #fff;
	box-shadow: 0px 1px 1px rgba(0, 0, 0, 0.1);
	padding: 10px;
}

.calendar::after {
	display: block;
	content: "";
	clear: both;
}

.body-list ul {
	width: 100%;
	font-family: arial;
	font-weight: bold;
	font-size: 14px;
	padding: 10px;
}

.body-list ul li {
	width: calc(100%/ 7);
	height: 80px;
	line-height: 36px;
	list-style-type: none;
	display: block;
	box-sizing: border-box;
	float: left;
	text-align: center;
	/* border: 1px solid black; */
}

.lightgrey {
	color: #a8a8a8;
}

.darkgrey {
	color: #565656;
}

.color {
	color: dodgerblue;
}

.colorbox {
	border: 1px solid dodgerblue;
	background-color: rgb(226, 240, 255);
}

p {
	color: black;
	font-size: 30px;
	cursor: pointer;
}

h1 {
	cursor: pointer;
}

.switch {
	display: none;
}

input {
	display: block;
	position: relative;
	left: 50%;
	transform: translateX(-50%);
	width: 50%;
	font-size: 30px;
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
				<a class="profile" href="#"> <i class="fa-solid fa-user icon"
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
				<a href="" class="left_btn"> <i class="fa-solid fa-hotel"
					style="color: #000000;"></i> 我的房間
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
				<% List<Room> roomList = (List<Room>)request.getAttribute("roomList");%>
				<c:forEach var="room" items="<%= roomList%>">
					<section class="one-room">
						<div class="title">
							<span class="room-name">${room.roomName}</span>
							<div class="do">
								<span class="room-status${room.roomStatus==1?'-on':'-off'}">
									${room.roomStatus==1?'上架中':'下架中'}</span> <a
									href="${pageContext.request.contextPath}/sean/RoomServlet?action=change&id=${room.roomId}"
									class="change">修改</a>
								<button class="delete">刪除</button>
							</div>
						</div>
						<hr>
						<div class="all-info">
							<span class="room-info" style="width: fit-content;"> <i
								class="fa-solid fa-bed"></i> <span>${room.roomType}人房</span>
							</span> <span class="d">|</span> <span class="room-info"
								style="width: 80px;"> <span>床數</span> <span>${room.beds}</span>
							</span> <span class="d">|</span> <span class="room-info"> <span>價格
									$ ${room.price.stripTrailingZeros().toPlainString()}</span>
							</span> <span class="d">|</span> <span class="room-info"
								style="width: 140px;"> <span>今日空房</span> <span>30</span>
							</span> <span class="below-btn">
								<button class="stock">查看庫存</button>
								<button type="button" class="renewStatus"
									${(room.roomStatus == 1) ? 'disabled style="filter: opacity(0.5);"' : ""}>上架</button>
								<input type="hidden" name="id" value="${roomId}">
								<button type="button" class="renewStatus"
									${(room.roomStatus == 0) ? 'disabled style="filter: opacity(0.5);"' : ""}>下架</button>
							</span>
						</div>

						<div class="calendar">
							<div class="calendar-head">
								<a href="" class="prev aa">＜</a>
								<div class="calendar-word">
									<h1 class="color calendar-title">Month</h1>
									<h2 class="color calendar-year">Year</h2>
								</div>
								<a href="" class="next aa">＞</a>
							</div>
							<div class="TIME-block">
								<div class="lightgrey body-list">
									<ul>
										<li>MON</li>
										<li>TUE</li>
										<li>WED</li>
										<li>THU</li>
										<li>FRI</li>
										<li>SAT</li>
										<li>SUN</li>
									</ul>
								</div>
								<div class="darkgrey body-list">
									<ul class="days">
									</ul>
								</div>
							</div>
						</div>
						<div class="alert_bg">
							<div class="alert">
								<div>
									確定${(room.roomStatus == 1) ?'下架':'上架'}嗎? <br>
									<form
										action="${pageContext.request.contextPath}/sean/RoomServlet?action=${(room.roomStatus == 1)?'recall':'launch'}&id=${room.roomId}"
										style="display: inline-block;" method="post">
										<%-- 									<input type="hidden" name = "id" value = "${room.roomId}"> --%>
										<button type="submit" class="other-btn">Yes</button>
									</form>
									<button type="button" class="other-btn no">No</button>
								</div>
							</div>
						</div>
						<div class="alert_bg">
							<div class="alert">
								<div>
									確定刪除嗎? <br> <a class="other-btn yes"
										href="${pageContext.request.contextPath}/sean/RoomServlet?action=delete2&id=${room.roomId}">Yes</a>
									<button type="button" class="other-btn no">No</button>
								</div>
							</div>
						</div>
					</section>
				</c:forEach>


				<a class="new"
					href="${pageContext.request.contextPath}/sean/RoomServlet?action=add">Build
					new room</a>
			</div>
		</main>
	</div>

	<script
		src="${pageContext.request.contextPath}/static/sean_js/btn4com.js"></script>
	<script>
        var delete_btn = document.querySelectorAll(".delete");
        $(".delete").on("click", function () {
            let room = $(this).closest("section.one-room");
            $("body").css("overflow", "hidden");
            let alert_bg = $(room).find(".alert_bg").last();
            console.log(alert_bg);
            alert_bg.addClass("on");
            let al = $(room).find(".alert").last();
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
        $(".renewStatus").on("click", function () {
            let room = $(this).closest("section.one-room");
            $("body").css("overflow", "hidden");
            let alert_bg = $(room).find(".alert_bg").first();
            console.log(alert_bg);
            alert_bg.addClass("on");
            let al = $(room).find(".alert").first();
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