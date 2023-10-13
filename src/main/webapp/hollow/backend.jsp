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
    <title>��x����</title>
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/hollow_css/backend_css.css">
</head>
<body>
    <br>
    <header>GoGoYu��x
        <table class="main_btn">
            <tr>
                <td>
                    <button type="button" class="btn_comp_audit" data-bs-toggle="button" autocomplete="off">�~�̼f��</button>
                </td>
                <td>
                    <button type="button" class="btn_client_msg" data-bs-toggle="button" autocomplete="off">�Ȥ�T��</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="button" class="btn_comp_search" data-bs-toggle="button" autocomplete="off">�~�̬d��</button>
                </td>
                <td>
                    <button type="button" class="btn_cust_search" data-bs-toggle="button" autocomplete="off">�|���d��</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="button" class="btn_add_scene" data-bs-toggle="button" autocomplete="off">���I�s�W</button>
                </td>
            </tr>
        </table>
    </header>
    
    <article class="comp_audit">
        <!-- <button type="button" class="btn_empty">�M��</button> -->
        <h1 class="title1">�~�̼f��</h1>
        
        <div class="task_add_block">
            <input type="text" class="task_name" placeholder="��J�ݿ�ƶ��K">
            <button type="button" class="task_add">�d��</button>
        </div>
        
        <div class="task_list_parent">
            <ul class="list">                  
                <li>
                    <a class="link_title" href="#">
                    <!-- <button type="button" class="switch_btn">
                        <span class="-plus">+</span><span class="-minus">-</span>
                    </button> -->
                    ���Ȧ��
                    </a>
                    <div class="inner_block">
                        <table class="table table-striped">
                            <tr>
                                <th>�~��ID</th>
                                <th>���q�W��</th>
                                <th>���q�a�}</th>
                                <th>�t�d�H</th>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>���Ȧ��</td>
                                <td>����v���`��</td>
                                <td>�����</td>
                            </tr>
                        </table>
                        <button class="btn_update btn btn-outline-success btn-lg">�q�L</button>
                        <button class="btn_update btn btn-outline-danger btn-lg">�M�P</button>
                    </div>                    
                </li>                
                <li>
                    <a class="link_title" href="#">
                    �ί��j����
                    </a>
                    <div class="inner_block">
                        <table class="table table-striped">
                            <tr>
                                <th>�~��ID</th>
                                <th>���q�W��</th>
                                <th>���q�a�}</th>
                                <th>�t�d�H</th>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>�ί��j����</td>
                                <td>�x�_�i�a</td>
                                <td>�i�a�S��</td>
                            </tr>
                        </table> 
                        <button class="btn_update btn btn-outline-success btn-lg">�q�L</button>
                        <button class="btn_update btn btn-outline-danger btn-lg">�M�P</button>                   
                    </div>
                </li>
            </ul>
        </div>
    </article>

    <article class="client_msg">
        <!-- <button type="button" class="btn_empty">�M��</button> -->
        <h1 class="title1">�Ȥ�T��</h1>
        
        <div class="task_add_block">
            <input type="text" class="task_name" placeholder="��J�ݿ�ƶ��K">
            <button type="button" class="task_add">�d��</button>
        </div>
        
        <div class="task_list_parent">
            <ul class="task_list">
            </ul>
        </div>
    </article>

    <article class="comp_search">
        <!-- <button type="button" class="btn_empty">�M��</button> -->
        <h1 class="title1">�~�̬d��</h1>
        
        <div class="task_add_block">
            <input type="text" class="task_name" placeholder="��J�d�ߨƶ��K">
            <button type="button" class="task_add">�d��</button>
        </div>
        
        <div class="task_list_parent">
            <ul class="list">
                <li>
                    <div class="">
                        <table class="table table-striped">
                            <tr>
                                <th>�~��ID</th>
                                <td>1</td>
                            </tr>
                            <tr>
                                <th>���q�W��</th>
                                <td><input type="text" class="text" value="���Ȧ��"></td>
                            </tr>
                            <tr>
                                <th>���q�a�}</th>
                                <td><input type="text" class="text" value="����v���`��"></td>
                            </tr>
                            <tr>
                                <th>���q�q��</th>
                                <td><input type="text" class="text" value="02-34567891"></td>
                            </tr>
                            <tr>
                                <th>�t�d�H</th>
                                <td><input type="text" class="text" value="�����"></td>
                            </tr>
                            <tr>
                                <th>�t�d�H�q��</th>
                                <td><input type="text" class="text" value="0912-345678"></td>
                            </tr>
                            <tr>
                                <th>�b��</th>
                                <td><input type="text" class="text" value="guoyugo"></td>
                            </tr>
                            <tr>
                                <th>�H�c</th>
                                <td><input type="text" class="text" value="guoyugo@abc.com"></td>
                            </tr>
                            <tr>
                                <th>�Ӥ�</th>
                                <td><input type="file" class="text" value="�Ӥ��b�o"></td>
                            </tr>
                        </table>
                        <div id="preview">
                            <span class="text">�w����</span>
                        </div><br>
                        <button class="btn_update btn btn-outline-primary btn-lg">��s</button>
                    </div>                    
                </li>                
            </ul>
        </div>
    </article>

    <article class="cust_search">
        <h1 class="title1">�|���d��</h1>
        
        <div class="task_add_block">
            <input type="text" class="task_name" placeholder="��J�d�ߨƶ��K">
            <button type="button" class="task_add">�d��</button>
        </div>
        
        <div class="task_list_parent">
            <ul class="list">
                <li>                    
                    <div class="">
                        <table class="table table-striped">
                            <tr>
                                <th>�|��ID</th>
                                <td>2</td>
                            </tr>
                            <tr>
                                <th>�m�W</th>
                                <td><input type="text" class="text" value="�i�a��"></td>
                            </tr>
                            <tr>
                                <th>�b��</th>
                                <td><input type="text" class="text" value="asiagodtone"></td>
                            </tr>
                            <tr>
                                <th>�H�c</th>
                                <td><input type="text" class="text" value="asiagodtone@abc.com"></td>
                            </tr>
                            <tr>
                                <th>�q��</th>
                                <td><input type="text" class="text" value="0912-345678"></td>
                            </tr>
                            <tr>
                                <th>��}</th>
                                <td><input type="text" class="text" value="�i�a����"></td>
                            </tr>
                            <tr>
                                <th>�ʧO</th>
                                <td>
                                    <input type="radio" name="gender" value="male" checked>�k
                                    <input type="radio" name="gender" value="female">�k
                                </td>
                            </tr>
                            <tr>
                                <th>�Ӥ�</th>
                                <td><input type="file" value="�Ӥ��b�o"></td>
                            </tr>
                        </table>
                        <div id="preview">
                            <span class="text">�w����</span>
                        </div><br>
                        <button class="btn_update btn btn-outline-primary btn-lg">��s</button>                    
                    </div>
                </li>
            </ul>
        </div>
    </article>

    <article class="add_scene">
        <h1 class="title1">���I�s�W</h1>
        
        <!-- <div class="task_add_block">
            <input type="text" class="task_name" placeholder="��J�ݿ�ƶ��K">
            <button type="button" class="task_add">�d��</button>
        </div> -->
        
        <div class="task_list_parent">
            <ul class="list">
                <li>                    
                    <div class="">
                        <table class="table table-striped">
                            <tr>
                                <th>���I�W��</th>
                                <td><input type="text" class="text" value="�ӥ��q�۪o��"></td>
                            </tr>
                            <tr>
                                <th>�}��ɶ�</th>
                                <td><input type="text" class="text" value="����"></td>
                            </tr>
                            <tr>
                                <th>��������</th>
                                <td><input type="text" class="text" value="100,000"></td>
                            </tr>
                            <tr>
                                <th>��q��T</th>
                                <td><input type="text" class="text" value="����"></td>
                            </tr>
                            <tr>
                                <th>������</th>
                                <td><input type="text" class="text" value="�����W"></td>
                            </tr>
                            <tr>
                                <th>�a�}</th>
                                <td><input type="text" class="text" value="�ӥ��q�۪o����"></td>
                            </tr>
                            <tr>
                                <th>�S��</th>
                                <td><input type="text" class="text" value="�i�H���۪o"></td>
                            </tr>
                            <tr>
                                <th>�Ϥ�</th>
                                <td><input type="file" class="text" value="�Ϥ���o"></td>
                            </tr>
                        </table>
                        <div id="preview">
                            <span class="text">�w����</span>
                        </div><br>
                        <button class="btn_update btn btn-outline-primary btn-lg">�s�W</button>                    
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