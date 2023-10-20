<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Trip_photo: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM trip_photo: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM trip_photo: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllTripPhoto.jsp'>List</a> all TripPhotos.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="trip_photo.do" >  <!--  P68明確對應 web.xml -->
        <b>輸入行程照片ID:</b>
        <input type="text" name="trip_photo_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="tripPhotoSvc" scope="page" class="com.tha103.gogoyu.trip_photo.model.Trip_photoServiceJDBC" />
   
  <li>
     <FORM METHOD="post" ACTION="trip_photo.do" >
       <b>選擇行程照片ID:</b>
       <select size="1" name="trip_photo_id">
         <c:forEach var="Trip_photo" items="${tripPhotoSvc.all}" >  <%-- ${tripPhotoSvc.all} 等同於 <%= tripPhotoSvc.getAll() %> --%>
          <option value="${Trip_photo.tripPhotoId}">${Trip_photo.tripPhotoId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="trip_photo.do" >
       <b>選擇行程套票ID:</b>
       <select size="1" name="trip_photo_id">
         <c:forEach var="Trip_photo" items="${tripPhotoSvc.all}" >  <%-- ${tripPhotoSvc.all} 等同於 <%= tripPhotoSvc.getAll() %> --%>
          <option value="${Trip_photo.tripPhotoId}">${Trip_photo.tripPhotoId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="trip_photo.do" >
       <b>選擇照片:</b>
       <select size="1" name="trip_photo_id">
         <c:forEach var="Trip_photo" items="${tripPhotoSvc.all}" > 
          <option value="${Trip_photo.tripPhotoId}">${Trip_photo.tripId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="trip_photo.do" >
       <b>選擇上傳時間:</b>
       <select size="1" name="trip_photo_id">
         <c:forEach var="Trip_photo" items="${tripPhotoSvc.all}" > 
          <option value="${Trip_photo.tripPhotoId}">${Trip_photo.uploadTime}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>新增行程照片:</h3>

<ul>
  <li><a href='addTripPhoto.jsp'>Add</a> a new Trip_photo.</li>
</ul>

</body>
</html>