<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.gogoyu.room.model.*"%>
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
                    <button type="button" class="btn_comp_audit" data-bs-toggle="button" autocomplete="off">業者審核</button>
                </td>
                <td>
                    <button type="button" class="btn_client_msg" data-bs-toggle="button" autocomplete="off">客戶訊息</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="button" class="btn_comp_search" data-bs-toggle="button" autocomplete="off">業者查詢</button>
                </td>
                <td>
                    <button type="button" class="btn_cust_search" data-bs-toggle="button" autocomplete="off">會員查詢</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="button" class="btn_add_scene" data-bs-toggle="button" autocomplete="off">景點新增</button>
                </td>
            </tr>
        </table>
    </header>
    
    <article class="comp_audit">
        <!-- <button type="button" class="btn_empty">清空</button> -->
        <h1 class="title1">業者審核</h1>
        
        <div class="task_add_block">
            <input type="text" class="task_name" placeholder="輸入待辦事項…">
            <button type="button" class="task_add">查詢</button>
        </div>
        
        <div class="task_list_parent">
            <ul class="list">                  
                <li>
                    <a class="link_title" href="#">
                    <!-- <button type="button" class="switch_btn">
                        <span class="-plus">+</span><span class="-minus">-</span>
                    </button> -->
                    國瑜旅行社
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
                                <td>1</td>
                                <td>國瑜旅行社</td>
                                <td>國瑜競選總部</td>
                                <td>蔣國瑜</td>
                            </tr>
                        </table>
                        <button class="btn_update btn btn-outline-success btn-lg">通過</button>
                        <button class="btn_update btn btn-outline-danger btn-lg">撤銷</button>
                    </div>                    
                </li>                
                <li>
                    <a class="link_title" href="#">
                    統神大飯店
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
                                <td>2</td>
                                <td>統神大飯店</td>
                                <td>台北張家</td>
                                <td>張家兄弟</td>
                            </tr>
                        </table> 
                        <button class="btn_update btn btn-outline-success btn-lg">通過</button>
                        <button class="btn_update btn btn-outline-danger btn-lg">撤銷</button>                   
                    </div>
                </li>
            </ul>
        </div>
    </article>

    <article class="client_msg">
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

    <article class="comp_search">
        <!-- <button type="button" class="btn_empty">清空</button> -->
        <h1 class="title1">業者查詢</h1>
        
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

    <article class="cust_search">
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

    <article class="add_scene">
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
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/hollow_js/backend_js.js"></script>
</body>
</html>