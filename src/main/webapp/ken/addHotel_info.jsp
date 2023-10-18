<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tha103.gogoyu.hotel_info.model.*"%>

<% //見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
Hotel_info hotelInfo = (Hotel_info) request.getAttribute("Hotel_info");
%>
<%-- --<%= consumer==null %>--${consumer.deptno}-- <!-- line 100 --> --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addHotel_info.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料新增 - addConsumer.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Hotel_infoServlet" >
<table>
	
	
	
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td>${hotelInfo.HotelInfoId}</td>
	</tr>
	<tr>
		<td>房型id:</td>
		<td><input type="TEXT" name="hotelInfoId" value="${hotelInfo.hotelInfoId}" size="45"/></td>
	</tr>
	<tr>
		<td>餐廳:</td>
		<td><input type="TEXT" name="restaurant" value="${hotelInfo.restaurant}" size="45"/></td>
	</tr>

	<tr>
		<td>客房服務:</td>
		<td><input type="TEXT" name="roomService" value="${hotelInfo.roomService}" size="45"/></td>
	</tr>
	<tr>
		<td>24小時接待櫃檯:</td>
		<td><input type="TEXT" name="alldayCounter"  value="${hotelInfo.alldayCounter}" size="45"/></td>
	</tr>
	<tr>
		<td>SPA:</td>
		<td><input type="TEXT" name="spa"  value="${hotelInfo.spa}" size="45"/></td>
	</tr>
	<tr>
		<td>健身中心:</td>
		<td><input type="TEXT" name="gym"  value="${hotelInfo.gym}" size="45"/></td>
	</tr>
	<tr>
		<td>花園:</td>
		<td><input type="TEXT" name="garden"  value="${hotelInfo.garden}" size="45"/></td>
	</tr>
	<tr>
		<td>露臺:</td>
		<td><input type="TEXT" name="terrace"  value="${hotelInfo.terrace}" size="45"/></td>
	</tr>
	<tr>
		<td>禁菸客房:</td>
		<td><input type="TEXT" name="noSmoking"  value="${hotelInfo.noSmoking}" size="45"/></td>
	</tr>
	<tr>
		<td>免費無線網路:</td>
		<td><input type="TEXT" name="freewifi"  value="${hotelInfo.freewifi}" size="45"/></td>
	</tr>
	<tr>
		<td>暖氣:</td>
		<td><input type="TEXT" name="heater"  value="${hotelInfo.heater}" size="45"/></td>
	</tr>
	<tr>
		<td>海灘:</td>
		<td><input type="TEXT" name="beach"  value="${hotelInfo.beach}" size="45"/></td>
	</tr>
	<tr>
		<td>泳池:</td>
		<td><input type="TEXT" name="pool"  value="${hotelInfo.pool}" size="45"/></td>
	</tr>
	<tr>
		<td>電動車充電站:</td>
		<td><input type="TEXT" name="chargingstation"  value="${hotelInfo.chargingstation}" size="45"/></td>
	</tr>
	<tr>
		<td>停車場:</td>
		<td><input type="TEXT" name="parking"  value="${hotelInfo.parking}" size="45"/></td>
	</tr>
	

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="add">
<input type="submit" value="送出新增"></FORM>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%-- <%  --%>
//   java.sql.Date hiredate = null;
//   try {
// 	    hiredate = consumer.getHiredate();
//    } catch (Exception e) {
// 	    hiredate = new java.sql.Date(System.currentTimeMillis());
//    }
<%-- %> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%-- 		   value: '<%=hiredate%>', // value:   new Date(), --%>
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>