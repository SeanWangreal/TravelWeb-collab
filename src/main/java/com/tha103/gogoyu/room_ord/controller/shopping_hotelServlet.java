package com.tha103.gogoyu.room_ord.controller;

import java.io.*;

import java.math.BigDecimal;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.emp.model.*;
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1* 1024 * 1024, maxRequestSize = 10* 1024 * 1024)
@WebServlet("/shopping_hotelServlet")
public class shopping_hotelServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");  //接收請求參數的編碼設定
		String Payment = req.getParameter("actionForPay"); //接收請求參數name=payment的queryString

		if ("pay".equals(Payment)) { // 來自shopping.jsp的"前往付款"請求

//			List<String> errorMsgs = new LinkedList<String>(); //將所有錯誤訊息包成一個list，前端就可以接收錯誤訊息加以渲染
//			req.setAttribute("errorMsgs", errorMsgs);

//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String str = req.getParameter("Scene");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("老鐵輸入啥ㄋ");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			Integer SceneId = null;
//			try {
//				SceneId = Integer.valueOf(str);
//			} catch (Exception e) {
//				errorMsgs.add("員工編號格式不正確");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 2.開始查詢資料 *****************************************/
//			SceneService SceneSvc = new SceneService();
//			Scene SceneVO = SceneSvc.getOneScene(SceneId);
//			if (SceneVO == null) {
//				errorMsgs.add("查無資料");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("successUpdateVO", SceneVO); // 資料庫取出的empVO物件,存入req
//			String url = "/emp/listOneEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
//		}
//
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 ****************************************/
//			Integer SceneId = Integer.valueOf(req.getParameter("sceneId"));
//
//			/*************************** 2.開始查詢資料 ****************************************/
//			SceneService SceneSvc = new SceneService();
//			Scene SceneVO = SceneSvc.getOneScene(SceneId);
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("SceneVO", SceneVO); // 資料庫取出的empVO物件,存入req
//			String url = "/emp/update_emp_input.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//			successView.forward(req, res);
//		}
//
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer sceneId = Integer.valueOf(req.getParameter("sceneId").trim());
//
//			String sceneName = req.getParameter("sceneName");
//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (sceneName == null || sceneName.trim().length() == 0) {
//				errorMsgs.add("員工姓名: 請勿空白");
//			} else if (!sceneName.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}
//
//			String openTime = req.getParameter("openTime").trim();
//			if (openTime == null || openTime.trim().length() == 0) {
//				errorMsgs.add("開放時間請勿空白");
//			}
//
//			String ticketPrice = req.getParameter("ticketPrice").trim();
//			if (ticketPrice == null || ticketPrice.trim().length() == 0) {
//				errorMsgs.add("門票價格請勿空白");
//			}
//
//			String transInfo = req.getParameter("transInfo").trim();
//			if (transInfo == null || transInfo.trim().length() == 0) {
//				errorMsgs.add("交通資訊請勿空白");
//			}
//
//			String parking = req.getParameter("parking").trim();
//			if (parking == null || parking.trim().length() == 0) {
//				errorMsgs.add("停車場請勿空白");
//			}
//
//			String address = req.getParameter("address").trim();
//			if (address == null || address.trim().length() == 0) {
//				errorMsgs.add("地址請勿空白");
//			}
//
//			BigDecimal lon = null;
//			try {
//				lon = new BigDecimal(req.getParameter("lon").trim());
//			} catch (Exception e) {
//				lon = new BigDecimal(0.0);
//				errorMsgs.add("經度異常");
//			}
//
//			BigDecimal lat = null;
//			try {
//				lat = new BigDecimal(req.getParameter("lat").trim());
//			} catch (Exception e) {
//				lat = new BigDecimal(0.0);
//				errorMsgs.add("緯度異常");
//			}
//
//			String feature = req.getParameter("feature").trim();
//			if (feature == null || feature.trim().length() == 0) {
//				errorMsgs.add("特色請勿空白");
//			}
//
//			String picture = req.getParameter("picture").trim();
//			if (picture == null || picture.trim().length() == 0) {
//				errorMsgs.add("圖片請勿空白");
//			}
//
////				
////				java.sql.Date hiredate = null;
////				try {
////hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
////				} catch (IllegalArgumentException e) {
////					hiredate=new java.sql.Date(System.currentTimeMillis());
////					errorMsgs.add("請輸入日期!");
////				}
////
////				Double sal = null;
////				try {
////sal = Double.valueOf(req.getParameter("sal").trim());
////				} catch (NumberFormatException e) {
////					sal = 0.0;
////					errorMsgs.add("薪水請填數字.");
////				}
////
////				Double comm = null;
////				try {
////comm = Double.valueOf(req.getParameter("comm").trim());
////				} catch (NumberFormatException e) {
////					comm = 0.0;
////					errorMsgs.add("獎金請填數字.");
////				}
////
////Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
//			Scene SceneVO1 = new Scene();
//			SceneVO1.setSceneId(sceneId);
//			SceneVO1.setSceneName(sceneName);
//			SceneVO1.setOpenTime(openTime);
//			SceneVO1.setTicketPrice(ticketPrice);
//			SceneVO1.setTransInfo(transInfo);
//			SceneVO1.setParking(parking);
//			SceneVO1.setAddress(address);
//			SceneVO1.setLon(lon);
//			SceneVO1.setLat(lat);
//			SceneVO1.setFeature(feature);
//			SceneVO1.setPicture(picture);
////
////				// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("SceneVO", SceneVO1); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//				return; // 程式中斷
//			}
////				
////				/***************************2.開始修改資料*****************************************/
//			SceneService SceneSvc = new SceneService();
//			Scene successUpdateVO = SceneSvc.updateScene(sceneId, sceneName, openTime, ticketPrice, transInfo, parking,
//					address, lon, lat, feature, picture);
////				
////				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("successUpdateVO", successUpdateVO); // 資料庫update成功後,正確的的empVO物件,存入req
//			String url = "/emp/listOneEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//			successView.forward(req, res);
//		}
////
//		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
////				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//
//			String sceneName = req.getParameter("sceneName");
//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (sceneName == null || sceneName.trim().length() == 0) {
//				errorMsgs.add("員工姓名: 請勿空白");
//			} else if (!sceneName.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}
////				
//			String openTime = req.getParameter("openTime").trim();
//			if (openTime == null || openTime.trim().length() == 0) {
//				errorMsgs.add("開放時間請勿空白");
//			}
//
//			String ticketPrice = req.getParameter("ticketPrice").trim();
//			if (ticketPrice == null || ticketPrice.trim().length() == 0) {
//				errorMsgs.add("門票價格請勿空白");
//			}
//
//			String transInfo = req.getParameter("transInfo").trim();
//			if (transInfo == null || transInfo.trim().length() == 0) {
//				errorMsgs.add("交通資訊請勿空白");
//			}
//
//			String parking = req.getParameter("parking").trim();
//			if (parking == null || parking.trim().length() == 0) {
//				errorMsgs.add("停車場請勿空白");
//			}
//
//			String address = req.getParameter("address").trim();
//			if (address == null || address.trim().length() == 0) {
//				errorMsgs.add("地址請勿空白");
//			}
//
//			BigDecimal lon = null;
//			try {
//				lon = new BigDecimal(req.getParameter("lon").trim());
//			} catch (Exception e) {
//				lon = new BigDecimal(0.0);
//				errorMsgs.add("經度異常");
//			}
//
//			BigDecimal lat = null;
//			try {
//				lat = new BigDecimal(req.getParameter("lat").trim());
//			} catch (Exception e) {
//				lat = new BigDecimal(0.0);
//				errorMsgs.add("緯度異常");
//			}
//
//			String feature = req.getParameter("feature").trim();
//			if (feature == null || feature.trim().length() == 0) {
//				errorMsgs.add("特色請勿空白");
//			}
//
//			String picture = req.getParameter("picture").trim();
//			if (picture == null || picture.trim().length() == 0) {
//				errorMsgs.add("圖片請勿空白");
//			}
//
////				
////Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
////
//			Scene SceneVO1 = new Scene();
//			SceneVO1.setSceneName(sceneName);
//			SceneVO1.setOpenTime(openTime);
//			SceneVO1.setTicketPrice(ticketPrice);
//			SceneVO1.setTransInfo(transInfo);
//			SceneVO1.setParking(parking);
//			SceneVO1.setAddress(address);
//			SceneVO1.setLon(lon);
//			SceneVO1.setLat(lat);
//			SceneVO1.setFeature(feature);
//			SceneVO1.setPicture(picture);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("SceneVO", SceneVO1); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//				return;
//			}
////				
////				/***************************2.開始新增資料***************************************/
//				SceneService SceneSvc = new SceneService();
//				Scene successaddVO = SceneSvc.addScene(sceneName, openTime, ticketPrice, transInfo, parking,
//						address, lon, lat, feature, picture);
////				
////				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//		}
//		
//		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			===================================移除訂單========================================
//			===================================移除訂單========================================	
//			===================================移除訂單========================================
			String Remove = req.getParameter("actionForRemove");
			if ("remove".equals(Remove)) { // 來自shopping.jsp的"移除訂單"請求

				List<String> errorMsgs = new LinkedList<String>();//將所有錯誤訊息包成一個list，前端就可以接收錯誤訊息加以渲染
				req.setAttribute("errorMsgs", errorMsgs);
//	
//				/***************************1.接收請求參數***************************************/
				Integer SceneId = Integer.valueOf(req.getParameter("sceneId"));
//				
//				/***************************2.開始刪除資料***************************************/
				SceneService SceneSvc = new SceneService();
				SceneSvc.deleteScene(SceneId);

//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
			}
			
			
//			===================================移除訂單========================================
//			===================================移除訂單========================================	
//			===================================移除訂單========================================
			
			
			
			
		}
	}
}

