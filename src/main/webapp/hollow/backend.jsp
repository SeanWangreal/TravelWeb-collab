<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.company.model.*"%>

<%
    CompanyService cmpSvc = new CompanyService();
    List<Company> list = cmpSvc.getByCheckStatus();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後台首頁</title>
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/hollow_css/backend_css.css">
</head>
<body>
    <br>
    <header>GoGoYu後台
        <table class="main_btn">
            <tr>
                <td>
                    <button type="button" class="main_btn_comp_audit" data-bs-toggle="button" autocomplete="off">業者審核</button>
                </td>
                <td>
                    <button type="button" class="main_btn_client_msg" data-bs-toggle="button" autocomplete="off">客戶訊息</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="button" class="main_btn_comp_search" data-bs-toggle="button" autocomplete="off">業者查詢</button>
                </td>
                <td>
                    <button type="button" class="main_btn_cust_search" data-bs-toggle="button" autocomplete="off">會員查詢</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="button" class="main_btn_add_scene" data-bs-toggle="button" autocomplete="off">景點新增</button>
                </td>
            </tr>
        </table>
    </header>
    
    <article class="art_comp_audit">
        <!-- <button type="button" class="btn_empty">清空</button> -->
        <h1 class="title1">業者審核</h1>
        
        <!-- 
        <div class="task_add_block">
            <input type="text" class="task_name" placeholder="輸入待辦事項…">
            <button type="button" class="task_add">查詢</button>
        </div>
        -->
        <div class="task_list_parent">
            <ul class="list">
            	<c:forEach var="cmpVO" items="${list}">
					<li>
	                    <a class="link_title" href="#">
	                    ${cmpVO.compName}
	                    </a>
	                    <div class="inner_block">
	                        <table class="table table-striped">
	                            <tr>
	                                <th>業者ID</th>
	                                <th>公司名稱</th>
	                                <th>公司地址</th>
	                                <th>負責人</th>
	                            </tr>
	                            <tr>
	                                <td>${cmpVO.compId}</td>
	                                <td>${cmpVO.compName}</td>
	                                <td>${cmpVO.compAddress}</td>
	                                <td>${cmpVO.principalName}</td>
	                            </tr>
	                        </table>
	                        <table>
	                        	<tr>
	                        		<td>
	                        			 <form action="${pageContext.request.contextPath}/CompanyServlet">
						     				<input type="hidden" name="compId"  value="${cmpVO.compId}">
						     				<input type="hidden" name="chkStatus"  value=1>
						     				<input type="hidden" name="action"	value="updChkStat">
				                        	<button type="submit" class="btn_update btn btn-outline-success btn-lg">通過</button>
				                        </form>
	                        		</td>
	                        		<td>
	                        			<form class="" action="${pageContext.request.contextPath}/CompanyServlet">
						     				<input type="hidden" name="compId"  value="${cmpVO.compId}">
						     				<input type="hidden" name="chkStatus"  value=-1>
						     				<input type="hidden" name="action"	value="updChkStat">
				                        	<button type="submit" class="btn_update btn btn-outline-danger btn-lg">撤銷</button>
				                        </form>
	                        		</td>
	                        	</tr>
	                        </table>
	                    </div>                    
	                </li>
				</c:forEach>
    </article>

    <article class="art_client_msg">
        <!-- <button type="button" class="btn_empty">清空</button> -->
        <h1 class="title1">客戶訊息</h1>
        
        <div class="task_add_block">
            <input type="text" class="task_name" placeholder="輸入待辦事項…">
            <button type="button" class="task_add">查詢</button>
        </div>
        
        <div class="task_list_parent">
            <ul class="task_list">
            </ul>
        </div>
    </article>

    <article class="art_comp_search">
        <!-- <button type="button" class="btn_empty">清空</button> -->
        <h1 class="title1">業者查詢</h1>
        
        <div class="task_add_block">
        	<form action="${pageContext.request.contextPath}/CompanyServlet">
        		<input type="hidden" name="action"	value="updChkStat">
	            <input type="text" class="task_name comp_search_value" placeholder="輸入查詢事項…">
	            <button type="button" class="task_add task_comp_search">查詢</button>
        	</form>
        </div>
        
        <div class="task_list_parent">
            <ul class="list list_company">
                <li>
                    <div class="">
                        <table class="table table-striped">
                            <tr>
                                <th>業者ID</th>
                                <td>1</td>
                            </tr>
                            <tr>
                                <th>公司名稱</th>
                                <td><input type="text" class="text" value="國瑜旅行社"></td>
                            </tr>
                            <tr>
                                <th>公司地址</th>
                                <td><input type="text" class="text" value="國瑜競選總部"></td>
                            </tr>
                            <tr>
                                <th>公司電話</th>
                                <td><input type="text" class="text" value="02-34567891"></td>
                            </tr>
                            <tr>
                                <th>負責人</th>
                                <td><input type="text" class="text" value="蔣國瑜"></td>
                            </tr>
                            <tr>
                                <th>負責人電話</th>
                                <td><input type="text" class="text" value="0912-345678"></td>
                            </tr>
                            <tr>
                                <th>帳號</th>
                                <td><input type="text" class="text" value="guoyugo"></td>
                            </tr>
                            <tr>
                                <th>信箱</th>
                                <td><input type="text" class="text" value="guoyugo@abc.com"></td>
                            </tr>
                            <tr>
                                <th>照片</th>
                                <td><input type="file" class="text" value="照片在這"></td>
                            </tr>
                        </table>
                        <div id="preview">
                            <span class="text">預覽圖</span>
                        </div><br>
                        <button class="btn_update btn btn-outline-primary btn-lg">更新</button>
                    </div>                    
                </li>                
            </ul>
        </div>
    </article>

    <article class="art_cust_search">
        <h1 class="title1">會員查詢</h1>
        
        <div class="task_add_block">
            <input type="text" class="task_name" placeholder="輸入查詢事項…">
            <button type="button" class="task_add">查詢</button>
        </div>
        
        <div class="task_list_parent">
            <ul class="list">
                <li>                    
                    <div class="">
                        <table class="table table-striped">
                            <tr>
                                <th>會員ID</th>
                                <td>2</td>
                            </tr>
                            <tr>
                                <th>姓名</th>
                                <td><input type="text" class="text" value="張家航"></td>
                            </tr>
                            <tr>
                                <th>帳號</th>
                                <td><input type="text" class="text" value="asiagodtone"></td>
                            </tr>
                            <tr>
                                <th>信箱</th>
                                <td><input type="text" class="text" value="asiagodtone@abc.com"></td>
                            </tr>
                            <tr>
                                <th>電話</th>
                                <td><input type="text" class="text" value="0912-345678"></td>
                            </tr>
                            <tr>
                                <th>住址</th>
                                <td><input type="text" class="text" value="張家公園"></td>
                            </tr>
                            <tr>
                                <th>性別</th>
                                <td>
                                    <input type="radio" name="gender" value="male" checked>男
                                    <input type="radio" name="gender" value="female">女
                                </td>
                            </tr>
                            <tr>
                                <th>照片</th>
                                <td><input type="file" value="照片在這"></td>
                            </tr>
                        </table>
                        <div id="preview">
                            <span class="text">預覽圖</span>
                        </div><br>
                        <button class="btn_update btn btn-outline-primary btn-lg">更新</button>                    
                    </div>
                </li>
            </ul>
        </div>
    </article>

    <article class="art_add_scene">
        <h1 class="title1">景點新增</h1>
        
        <!-- <div class="task_add_block">
            <input type="text" class="task_name" placeholder="輸入待辦事項…">
            <button type="button" class="task_add">查詢</button>
        </div> -->
        
        <div class="task_list_parent">
            <ul class="list">
                <li>                    
                    <div class="">
                        <table class="table table-striped">
                            <tr>
                                <th>景點名稱</th>
                                <td><input type="text" class="text" value="太平島石油井"></td>
                            </tr>
                            <tr>
                                <th>開放時間</th>
                                <td><input type="text" class="text" value="全天"></td>
                            </tr>
                            <tr>
                                <th>門票價格</th>
                                <td><input type="text" class="text" value="100,000"></td>
                            </tr>
                            <tr>
                                <th>交通資訊</th>
                                <td><input type="text" class="text" value="飛機"></td>
                            </tr>
                            <tr>
                                <th>停車場</th>
                                <td><input type="text" class="text" value="停機坪"></td>
                            </tr>
                            <tr>
                                <th>地址</th>
                                <td><input type="text" class="text" value="太平島石油公園"></td>
                            </tr>
                            <tr>
                                <th>特色</th>
                                <td><input type="text" class="text" value="可以挖石油"></td>
                            </tr>
                            <tr>
                                <th>圖片</th>
                                <td><input type="file" class="text" value="圖片放這"></td>
                            </tr>
                        </table>
                        <div id="preview">
                            <span class="text">預覽圖</span>
                        </div><br>
                        <button class="btn_update btn btn-outline-primary btn-lg">新增</button>                    
                    </div>
                </li>
            </ul>
        </div>
    </article>
    
    <script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/hollow_js/backend_js.js"></script>
    
    <script type="text/javascript">
    $("#btn_comp_search").on("click",function(e){
    	console.log("123");
        alert("業者查尋");
	})
	
	$("#btn_cust_search").on("click",function(e){
	alert("會員查尋");
	})
    </script>
</body>
</html>