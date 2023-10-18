<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tha103.gogoyu.hotel_info.model.*"%>

<% //��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
Hotel_info hotelInfo = (Hotel_info) request.getAttribute("Hotel_info");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_Hotel_info</title>

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
		 <h3>���u��ƭק� - update_Consumer_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Hotel_infoServlet" >
<table>
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
		<td>${hotelInfo.HotelInfoId}</td>
	</tr>
	<tr>
		<td>�Ы�id:</td>
		<td><input type="TEXT" name="hotelInfoId" value="${hotelInfo.hotelInfoId}" size="45"/></td>
	</tr>
	<tr>
		<td>�\�U:</td>
		<td><input type="TEXT" name="restaurant" value="${hotelInfo.restaurant}" size="45"/></td>
	</tr>

	<tr>
		<td>�ȩЪA��:</td>
		<td><input type="TEXT" name="roomService" value="${hotelInfo.roomService}" size="45"/></td>
	</tr>
	<tr>
		<td>24�p�ɱ����d�i:</td>
		<td><input type="TEXT" name="alldayCounter"  value="${hotelInfo.alldayCounter}" size="45"/></td>
	</tr>
	<tr>
		<td>SPA:</td>
		<td><input type="TEXT" name="spa"  value="${hotelInfo.spa}" size="45"/></td>
	</tr>
	<tr>
		<td>��������:</td>
		<td><input type="TEXT" name="gym"  value="${hotelInfo.gym}" size="45"/></td>
	</tr>
	<tr>
		<td>���:</td>
		<td><input type="TEXT" name="garden"  value="${hotelInfo.garden}" size="45"/></td>
	</tr>
	<tr>
		<td>�S�O:</td>
		<td><input type="TEXT" name="terrace"  value="${hotelInfo.terrace}" size="45"/></td>
	</tr>
	<tr>
		<td>�T�ҫȩ�:</td>
		<td><input type="TEXT" name="noSmoking"  value="${hotelInfo.noSmoking}" size="45"/></td>
	</tr>
	<tr>
		<td>�K�O�L�u����:</td>
		<td><input type="TEXT" name="freewifi"  value="${hotelInfo.freewifi}" size="45"/></td>
	</tr>
	<tr>
		<td>�x��:</td>
		<td><input type="TEXT" name="heater"  value="${hotelInfo.heater}" size="45"/></td>
	</tr>
	<tr>
		<td>���y:</td>
		<td><input type="TEXT" name="beach"  value="${hotelInfo.beach}" size="45"/></td>
	</tr>
	<tr>
		<td>�a��:</td>
		<td><input type="TEXT" name="pool"  value="${hotelInfo.pool}" size="45"/></td>
	</tr>
	<tr>
		<td>�q�ʨ��R�q��:</td>
		<td><input type="TEXT" name="chargingstation"  value="${hotelInfo.chargingstation}" size="45"/></td>
	</tr>
	<tr>
		<td>������:</td>
		<td><input type="TEXT" name="parking"  value="${hotelInfo.parking}" size="45"/></td>
	</tr>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(consumer.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="hotelInfo" value="${hotelInfo.HotelInfoId}">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=consumerVO.getHiredate()%>', // value:   new Date(), --%>
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
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

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
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


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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