package com.tha103.gogoyu.room_ord.controller;

import java.io.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.tha103.gogoyu.room_ord.model.ShoppingCartHotel;
import com.tha103.gogoyu.trip_ord.model.Trip_ord;
import com.tha103.gogoyu.consumer.model.Consumer;
import com.tha103.gogoyu.planning.model.*;
import com.tha103.gogoyu.room_ord.model.*;
import com.tha103.gogoyu.room.model.*;
import com.tha103.gogoyu.trip.model.*;
import com.tha103.gogoyu.trip_ord.model.*;

//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1* 1024 * 1024, maxRequestSize = 10* 1024 * 1024)
@WebServlet("/shopping_hotelServlet")
public class shopping_hotelServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // 接收請求參數的編碼設定

		HttpSession session = req.getSession();
		Integer cusId = (Integer) session.getAttribute("cus_id"); // 抓會員id
		
		Integer tripId = (Integer) session.getAttribute("trip_id"); // 抓行程 id   最後getparameter(name value)
		Integer roomId = (Integer) session.getAttribute("room_id"); // 抓房間id  最後會用getparameter(name value)
		Integer cartId = Integer.valueOf(req.getParameter("cart_id")); //抓購物車號(name value)
		
		
		Integer tripAmount= 1;//Integer.valueOf(req.getParameter("amount")); //抓數量amount(name value)
		Integer roomAmount= 1;//Integer.valueOf(req.getParameter("amount")); //抓數量amount(name value)
		Timestamp checkInTime= Timestamp.valueOf("2023-10-17 12:30:45"); //抓checkingtime(name value)
		Timestamp checkOutTime= Timestamp.valueOf("2023-10-17 12:30:45"); //抓checkingtime(name value)
		
		
		
		String action = req.getParameter("action");
		
	

		if ("room_goShopping".equals(action)) { //room區塊加入購物車時
			
			if (cusId != null) {// 如果session有cus_id資料代表有人登入

				PlanningServiceHibernate PSH = new PlanningServiceHibernate();// 創造出SERVICE
				Integer plan_id = PSH.getPlanId(cusId, cartId); //得到1.他是誰   2.是哪台車 
				
					//透過取得的room_id去找到該物件的屬性
				
				//跟room拿price
				RoomServiceHibernate RSHTP = new RoomServiceHibernate(); //PRICE
				RoomServiceHibernate RSHPP = new RoomServiceHibernate(); //RSHPP
			    Room_ordServiceHibernate ROSH = new Room_ordServiceHibernate();
				BigDecimal totalPrice=RSHTP.getByPrice(roomId); 
				BigDecimal roundedtotalPriceValue = totalPrice.setScale(0, RoundingMode.HALF_UP);
				//comm = price *10%
				BigDecimal comm = new BigDecimal(0.1);
				BigDecimal commission = totalPrice.multiply(comm); //透過price算出comm
				BigDecimal roundedCommissionValue = commission.setScale(0, RoundingMode.HALF_UP);
				//profit = price - comm
				BigDecimal profit = roundedtotalPriceValue.subtract(roundedCommissionValue);
				
				//people = 數量 (明翰提供) * 幾人房(room裡面的room_type(幾人房))
				
//				RSHPP
//				SELECT ROOM_TYPE FROM ROOM WHERE ROOM_ID=x;
//				Integer people = amount * ;
				
				
				Integer result = ROSH.addFromShopping(plan_id,roomId,cusId,roomAmount,totalPrice,commission,profit, 1 , checkInTime, checkOutTime,0);
				
//				System.out.println(result == 1 ? "新增成功":"新增失敗");
				
				//10/18 要問 people 是怎樣 還有cus_id 跟 room_id 被insertable無法新增 
				

			} else { // 導回登入
				session.setAttribute("location", req.getRequestURI()); // 如果沒登入先記錄現在的位置(網址)
				res.sendRedirect(req.getContextPath() + "/chu/bookingList(trip).jsp");// 然後導回登入頁面(等到有login.jsp再改路徑)
				System.out.println(cusId);
			}

		}

		
		
		
		
		
		
		if ("trip_goShooping".equals(action)) { // trip區塊加入購物車時

			if (cusId != null) {// 如果session有cus_id資料代表有人登入
				
				PlanningServiceHibernate PSH = new PlanningServiceHibernate();// 創造出SERVICE
				Integer planId = PSH.getPlanId(cusId, cartId); //得到1.他是誰   2.是哪台車 
				
					//透過取得的trip_id去找到該物件的屬性

				TripServiceHibernate TSH = new TripServiceHibernate();
				Trip_ordServiceHibernate TOSH = new Trip_ordServiceHibernate();
				
				
//				TOSH.addTrip(tripId,planId,cusId,tripAmount,);
				
				
				

			} else { // 導回登入
				session.setAttribute("location", req.getRequestURI()); // 如果沒登入先記錄現在的位置(網址)
				res.sendRedirect(req.getContextPath() + "/chu/bookingList(trip).jsp");// 然後導回登入頁面(等到有login.jsp再改路徑)

			}

		}
	}
}
//			if(!("checkOut".equals(action))) {  //如果不是操作"前往付款"
//			
//				if("remove".equals(action)){ //如果動作是刪除
//					 
//				}
//			
//		
//			
//			
//			}else {
//				
//			}
//			
//	}

//			private ShoppingCartHotel getshoppingitem(HttpServletRequest req ,HttpSession session) {
//				
//				String compType = req.getParameter("compType");
//				String compName = req.getParameter("compName");
//				String planId = req.getParameter("planId");
//				String score= req.getParameter("score");
//				
////				有需要會員id?
//		        String cusId = String.valueOf(session.getAttribute("cus_id"));//get session是否有登入的會員id
//			
//				
//				
//				ShoppingCartHotel shoppingItem = new ShoppingCartHotel();
//				shoppingItem.setScore(score);
//				shoppingItem.setCompName(compName);
//				shoppingItem.setScore(planId);
//				shoppingItem.setCusId(cusId);
//				//要多set一個購物車號碼((要問
//				
//				return shoppingItem;
//		
//				
//			}
//			

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
//			String Remove = req.getParameter("actionForRemove");
//			if ("remove".equals(Remove)) { // 來自shopping.jsp的"移除訂單"請求
//
//				List<String> errorMsgs = new LinkedList<String>();//將所有錯誤訊息包成一個list，前端就可以接收錯誤訊息加以渲染
//				req.setAttribute("errorMsgs", errorMsgs);
////	
////				/***************************1.接收請求參數***************************************/
//				Integer SceneId = Integer.valueOf(req.getParameter("sceneId"));
////				
////				/***************************2.開始刪除資料***************************************/
//				SceneService SceneSvc = new SceneService();
//				SceneSvc.deleteScene(SceneId);
//
////				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//			}

//			===================================移除訂單========================================
//			===================================移除訂單========================================	
//			===================================移除訂單========================================

//	
//if(shoppingList == null) {
//		
//		boolean match =false ;
//		
//		shoppingList = new Vector<ShoppingCartHotel>();
//		shoppingList.add(newCart);
//			
//	}else {
//		for( int i = 0 ; i < shoppingList.size() ; i++) {
//			ShoppingCartHotel shoppping = shoppingList.get(i);
//			
//			
//			
//			
//		}

//我需要他放哪台購物車號碼   get.Parameter   or  getAttribute
//還需要會員id == cusId				要看這個session在哪set的getAttribute
//session.setAttribute("successPutShopping", successPutShopping); //成功放入購物車的提示

//ShoppingCartHotel newCart = getshoppingitem(req, session);

//	抓有沒有購物車
//	Vector<ShoppingCartHotel> shoppingList = (Vector<ShoppingCartHotel>) session.getAttribute("shoppingCart");
////	沒有就new一台
//	Vector<ShoppingCartHotel> shopping = new Vector<ShoppingCartHotel>();
//	String action = req.getParameter("action"); // 將所有name(執行按鍵)都設為action方便透過if比對目前操作何者功能
//	
//	String successPutShopping = "您已將商品放入購物車!";
//	
//	
//	
//	String cusId =String.valueOf(session.getAttribute("cus_id")); 

//
//
//
//if(shoppingList == null) { //假如購物車是空的
//	
//	String compName = req.getParameter("compName");
//	String score = req.getParameter("score");
//	String cusId = req.getParameter("cusId"); // 待討論
//	String planId = req.getParameter("planId");
//	String compType = req.getParameter("compType"); // 待討論
//	String cartNum = req.getParameter("cartNum");
//	String price = req.getParameter("price");
//	
//	ShoppingCartHotel shoppingCart = new ShoppingCartHotel();
//	shoppingCart.setCompName(compName);
//	shoppingCart.setScore(score);
//	shoppingCart.setCusId(cusId);
//	shoppingCart.setPlanId(planId);
//	shoppingCart.setCartNum(cartNum);
//	shoppingCart.setCompType(compType);
//	shoppingCart.setPrice(price);
//	
//	
//	shopping.add(shoppingCart);
//	
//	session.setAttribute("ShoppingCart", shopping);
//	
//	
//	
//	
//	String url = "chu\\shopping(hotel).jsp";
//	RequestDispatcher rd = req.getRequestDispatcher(url);
//	rd.forward(req, res);
//	
//}
//
//if ("remove".equals(action)) { 
//	String removeId = req.getParameter("planId");//有連接redis再使用
////	跑redis 執行 delete 透過 planid 規劃id
//}
//
//
//if("checkOut".equals(action)) {
//	
//}
